/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 3
 * DoubleLinkNode derived class
 */

package LinkList;

public class DoubleLinkNode extends Node {
	// Private Variables
	private DoubleLinkNode next;
	private DoubleLinkNode prev;
	
	// Constructor
	public DoubleLinkNode(int r, int s) {
		super(r, s);
		next = null;
		prev = null;
	}
	
	// Setters
	public void setNext(DoubleLinkNode n) {
		next = n;
	}
	public void setPrev(DoubleLinkNode p) {
		prev = p;
	}
	
	// Getters
	public DoubleLinkNode getNext() {
		return next;
	}
	public DoubleLinkNode getPrev() {
		return prev;
	}
}
