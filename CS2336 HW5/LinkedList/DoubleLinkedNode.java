/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Homework 5
 * DoubleLinkedNode class extends Node class
 */

package LinkedList;

public class DoubleLinkedNode extends Node {
	private DoubleLinkedNode next;
	private DoubleLinkedNode prev;
	
	public DoubleLinkedNode() {
		super(' ');
		next = null;
		prev = null;
	}
	
	public DoubleLinkedNode(char l) {
		super(l);
		next = null;
		prev = null;
	}
	
	// Setters
	public void setNext(DoubleLinkedNode n) {
		next = n;
	}
	public void setPrev(DoubleLinkedNode p) {
		prev = p;
	}
	
	// Getters
	public DoubleLinkedNode getNext() {
		return next;
	}
	public DoubleLinkedNode getPrev() {
		return prev;
	}
}