/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 3
 * Node base abstract class
 */

package LinkList;

public abstract class Node {
	// Private Variables
	private int row;
	private int seat;
	
	// Constructor
	public Node(int r, int s) {
		row = r;
		seat = s;
	}
	
	// Setters
	public void setRow(int r) {
		row = r;
	}
	public void setSeat(int s) {
		seat = s;
	}
	
	// Getters
	public int getRow() {
		return row;
	}
	public int getSeat() {
		return seat;
	}
}
