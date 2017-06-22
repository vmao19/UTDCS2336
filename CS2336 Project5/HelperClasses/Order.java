/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 5
 * Order Class
 */

package HelperClasses;

import java.util.ArrayList;

public class Order {
	// private variables
	private ArrayList<Seat> seats;
	private Auditorium auditorium;
	private int auditoriumNum; // stores the auditorium number
	private int numAdult;
	private int numSenior;
	private int numChild;
	
	// constructor
	public Order(Auditorium aud, int audNum, int a, int s, int c) {
		seats = new ArrayList<Seat>();
		auditoriumNum = audNum;
		auditorium = aud;
		numAdult = a;
		numSenior = s;
		numChild = c;
	}
	
	// getters
	public int getAuditoriumNum() {
		return auditoriumNum;
	}
	public int getNumAdult() {
		return numAdult;
	}
	public int getNumSenior() {
		return numSenior;
	}
	public int getNumChild() {
		return numChild;
	}
	public double getPrice() {
		double price = 0;
		for (int i=0; i<seats.size(); i++) {
			char type = seats.get(i).getType();
			if (type == 'A')
				price += 10;
			else if (type == 'S')
				price += 7.5;
			else
				price += 5.25;
		}
		return price;
	}
	public ArrayList<Seat> getSeats() {
		return seats;
	}
	
	// add a Seat to the Order
	public void addSeat(Seat s) {
		seats.add(s);
	}
	
	// remove a Seat from the Order
	public void removeSeat(Seat s) {
		auditorium.unreserveSeat(s); // unreserve Seat first aka set to '#'
		seats.remove(s); // then remove the Seat from the Order
	}
	
	// returns true if Order contains Seat s
	public boolean contains(Seat s) {
		for (int i=0; i<seats.size(); i++) {
			if (seats.get(i).compareTo(s) == 0)
				return true;
		}
		return false;
	}
	
	// returns true if all the Seats in Order are available
	public boolean isAllAvailable() {
		for (int i=0; i<seats.size(); i++) {
			if (!auditorium.isAvailable(seats.get(i))) // return false if Seat is not available
				return false;
		}
		return true;
	}
	
	// place the Order
	public void placeOrder() {
		for (int i=0; i<seats.size(); i++) {
			auditorium.reserveSeat(seats.get(i)); // reserve each Seat in Order
		}
	}
	
	// print out the Seats in the Order in an orderly manner
	public void printSeats() {
		for (int i=0; i<seats.size(); i++) {
			System.out.print("Seat #" + (i+1) + ": ");
			seats.get(i).printSeat();
		}
	}
	
	// append the contents of Order o to this Order
	public void append(Order o) {
			seats.addAll(o.getSeats());
			numAdult += o.getNumAdult(); // increment the number of Adult tickets
			numSenior += o.getNumSenior(); // increment the number of Senior tickets
			numChild += o.getNumChild(); // increment the number of Child tickets
	}
	
}
