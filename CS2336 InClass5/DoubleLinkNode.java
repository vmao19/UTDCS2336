/*
 * Victor Mao (vtm160030)
 * Seth Raley (sar160030)
 * CS 2336.003 In Class 5
 * DoubleLinkNode class extends Node class
 */

public class DoubleLinkNode extends Node {
	private DoubleLinkNode next;
	private DoubleLinkNode prev;
	
	public DoubleLinkNode() {
		super(' ');
		next = null;
		prev = null;
	}
	
	public DoubleLinkNode(char l) {
		super(l);
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