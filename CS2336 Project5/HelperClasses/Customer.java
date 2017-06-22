/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 5
 * Customer Class
 */

package HelperClasses;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Customer {
	// private variables
	private String username;
	private String password;
	private ArrayList<Order> orders;
	
	// constructor
	public Customer(String u, String p) {
		username = u;
		password = p;
		orders = new ArrayList<Order>();
	}
	
	// getters
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	// add an Order to the Customer
	public void addOrder(Order o) {
		orders.add(o);
	}
	
	// print the Orders of a Customer
	public void printOrders() {
		if (orders.size() == 0)
			System.out.println("You have not made any orders.\n");
		else {
			for (int i=0; i<orders.size(); i++) {
				Order o = orders.get(i);
				System.out.println("Order #" + (i+1) + ":");
				System.out.println("Auditorium #" + o.getAuditoriumNum());
				System.out.println("Number of Adult Tickets ($10 each): " + o.getNumAdult());
				System.out.println("Number of Senior Tickets ($7.50 each): " + o.getNumSenior());
				System.out.println("Number of Child Tickets ($5.25 each): " + o.getNumChild());
				System.out.println("Seats:");
				o.printSeats();
				System.out.println();
			}
		}
	}
	
	// print a report of the Orders made by the Customer, including price
	public void printReport() {
		if (orders.size() == 0)
			System.out.println("You have not made any orders.\n");
		else {
			DecimalFormat df = new DecimalFormat("0.00");
			double totalPrice = 0;
			for (int i=0; i<orders.size(); i++) {
				Order o = orders.get(i);
				System.out.println("Order #" + (i+1) + ":");
				System.out.println("Auditorium #" + o.getAuditoriumNum());
				System.out.println("Number of Adult Tickets ($10 each): " + o.getNumAdult());
				System.out.println("Number of Senior Tickets ($7.50 each): " + o.getNumSenior());
				System.out.println("Number of Child Tickets ($5.25 each): " + o.getNumChild());
				System.out.println("Seats:");
				o.printSeats();
				System.out.println("Cost of Order #" + (i+1) + ": $" + df.format(o.getPrice()));
				totalPrice += o.getPrice();
				System.out.println();
			}
			System.out.println("Total Cost of All Orders: $" + df.format(totalPrice));
			System.out.println();
		}
	}
}
