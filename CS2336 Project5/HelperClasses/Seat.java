/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 5
 * Seat Class
 */

package HelperClasses;

public class Seat {
	// private variables
	private int row;
	private int col;
	private char type; // types: '.' or '#' or 'A' or 'S' or 'C'
	
	// constructor
	public Seat(int r, int c, char t) {
		row = r;
		col = c;
		type = t;
	}
	
	// getters
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public char getType() {
		return type;
	}
	
	// setters
	public void setRow(int r) {
		row = r;
	}
	public void setCol(int c) {
		col = c;
	}
	public void setType(char t) {
		type = t;
	}
	
	// compareTo method for comparing Seats
	public int compareTo(Seat s) {
		if (row < s.getRow())
			return -1;
		else if (row > s.getRow())
			return 1;
		else {
			if (col < s.getCol())
				return -1;
			else if (col > s.getCol())
				return 1;
			else
				return 0;
		}
	}
	
	// print out Seat information
	public void printSeat() {
		System.out.println("Row [" + row + "], Seat [" + col + "], Type [" + type + "]");
	}
}
