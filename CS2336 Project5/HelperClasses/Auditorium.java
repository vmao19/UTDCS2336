/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 5
 * Auditorium Class
 */

package HelperClasses;

import java.io.*;
import java.util.*;

public class Auditorium {
	// private variables
	private ArrayList<Seat> auditorium = new ArrayList<Seat>();
	private int numRows;
	
	// constructor - read from a file
	public Auditorium(String filename) throws FileNotFoundException {
		numRows = 0;
		Scanner scan = new Scanner(new File(filename));
		while (scan.hasNextLine()) { // for each row in the file
			String line = scan.nextLine();
			numRows++;
			
			for (int i=0; i<line.length(); i++) {
				auditorium.add(new Seat(numRows-1, i, line.charAt(i))); // add a new Seat for each '.' or '#' in the row
			}
		}
		scan.close();
	}
	
	// getters
	public ArrayList<Seat> getAuditorium() {
		return auditorium;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return auditorium.size() / numRows;
	}
	public int getOpenSeats() {
		int open = 0;
		for (int i=0; i<auditorium.size(); i++) {
			if (auditorium.get(i).getType() == '#')
				open++;
		}
		return open;
	}
	public int getTotalReservedSeats() {
		int trs = 0;
		for (int i=0; i<auditorium.size(); i++) {
			char type = auditorium.get(i).getType();
			if (type == '.' || type == 'A' || type == 'S' || type == 'C')
				trs++;
		}
		return trs;
	}
	public int getAdultSeats() {
		int adult = 0;
		for (int i=0; i<auditorium.size(); i++) {
			if (auditorium.get(i).getType() == 'A')
				adult++;
		}
		return adult;
	}
	public int getSeniorSeats() {
		int senior = 0;
		for (int i=0; i<auditorium.size(); i++) {
			if (auditorium.get(i).getType() == 'S')
				senior++;
		}
		return senior;
	}
	public int getChildSeats() {
		int child = 0;
		for (int i=0; i<auditorium.size(); i++) {
			if (auditorium.get(i).getType() == 'C')
				child++;
		}
		return child;
	}
	
	// returns true if Seat s is available aka '#'
	public boolean isAvailable(Seat s) {
		Seat seat = auditorium.get(((s.getRow()-1) * getNumCols()) + (s.getCol()-1));
		if (seat.getType() == '#')
			return true;
		else
			return false;
	}
	
	// reserve the corresponding seat with the correct type aka 'A' or 'S' or 'C'
	public void reserveSeat(Seat s) {
		int index = ((s.getRow()-1) * getNumCols()) + (s.getCol()-1);
		auditorium.get(index).setType(s.getType());
	}
	
	// unreserve the corresponding seat with '.'
	public void unreserveSeat(Seat s) {
		int index = ((s.getRow()-1) * getNumCols()) + (s.getCol()-1);
		auditorium.get(index).setType('#');
	}
	
	// return the index of the leftmost seat of the best available seats
	public int findBestAvailableIndex(int numTickets) {
		if (numTickets > getNumCols()) // more tickets than seats in one row
			return -1;
		
		int midTicket = (numTickets+1)/2; // middle of the ticket section
		if (numTickets == 1)
			midTicket = 0;
		int midRow = ((numRows+1)/2)-1; // middle row of the auditorium, rounded down
		int midSeat = ((getNumCols()+1)/2)-1; // middle col of the auditorium, rounded down
		
		double minDist = Double.MAX_VALUE;
		int minIndex = -1;
		
		for (int i=0; i<auditorium.size()-numTickets; i++) { // loop through auditorium
			boolean open = true;
			for (int j=0; j<numTickets; j++) { // check numTickets of seats to the right
				if (auditorium.get(i).getRow() != auditorium.get(i+j).getRow() || auditorium.get(i+j).getType() != '#') {
					open = false; // set as false if seat is not in the same row or seat is reserved
					break;
				}
			}
			if (open) {
				// calculate the distance from the middle of the section to the middle of the auditorium
				double distance = Math.sqrt(Math.pow(midRow - auditorium.get(i+midTicket).getRow(), 2) + Math.pow(midSeat - auditorium.get(i+midTicket).getCol(), 2));
				if (distance < minDist) {
					minDist = distance;
					minIndex = i;
				}
				else if (distance == minDist) { // if distance is the same, then check rows
					if (Math.abs(midRow-auditorium.get(i+midTicket).getRow()) < Math.abs(midRow-auditorium.get(minIndex).getRow())) {
						minIndex = i;
					}
				}
			}
		}
		return minIndex;		
	}
	
	// print the auditorium
	public void printAuditorium() {
		System.out.print("  ");
		for (int i=0; i<getNumCols(); i++) {
			System.out.print((i+1) % 10);
		}
		for (int i=0; i<getNumRows(); i++) {
			System.out.print("\n" + (i+1) % 10 + " ");
			for (int j=0; j<getNumCols(); j++) {
				Seat s = auditorium.get((i*getNumCols()) + j);
				if (s.getType() == 'A' || s.getType() == 'S' || s.getType() == 'C') // print as generic '.'
					System.out.print(".");
				else
					System.out.print(s.getType());
			}
		}
		System.out.println();
		System.out.println("(Empty seats denoted with \"#\")");
		System.out.println("(Reserved seats denoted with \".\")");
		System.out.println();
	}
	
	// save the auditorium to filename using a PrintWriter
	public void saveAuditorium(String filename) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(filename));
		for (int i=0; i<getNumRows(); i++) {
			if (i != 0)
				pw.write('\n');
			for (int j=0; j<getNumCols(); j++) {
				Seat s = auditorium.get((i*getNumCols()) + j);
				if (s.getType() == 'A' || s.getType() == 'S' || s.getType() == 'C') // store as generic '.'
					pw.write('.');
				else
					pw.write(s.getType());
			}
		}
		pw.close();
	}
}
