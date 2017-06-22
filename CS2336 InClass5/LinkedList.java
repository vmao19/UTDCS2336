/*
 * Victor Mao (vtm160030)
 * Seth Raley (sar160030)
 * CS 2336.003 In Class 5
 * LinkedList class extends DoubleLinkNode class extends Node class
 */

public class LinkedList extends DoubleLinkNode {
	private DoubleLinkNode head;
	private DoubleLinkNode tail;
	
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	// Add DoubleLinkNode to the end of the LinkedList
	public void addNode(DoubleLinkNode dln) {
		if (isEmpty()) {
			head = dln;
			tail = dln;
		}
		else {
			dln.setPrev(tail);
			tail.setNext(dln);
			tail = dln;
		}
	}
	
	// Remove DoubleLinkNode from the head of the LinkedList
	public void removeHead() {
		if (isEmpty())
			return;
		head = head.getNext();
		if (head != null)
			head.setPrev(null);
		else
			tail = null;
	}
	
	// Remove DoubleLinkNode from the tail of the LinkedList
	public void removeTail() {
		if (isEmpty())
			return;
		tail = tail.getPrev();
		if (tail != null)
			tail.setNext(null);
		else
			head = null;
	}
	
	// Check if the LinkedList is empty
	public boolean isEmpty() {
		return head == null && tail == null;
	}
	
	// Getters
	public DoubleLinkNode getHead() {
		return head;
	}
	public DoubleLinkNode getTail() {
		return tail;
	}
	
	// Setters
	public void setHead(DoubleLinkNode h) {
		head = h;
	}
	public void setTail(DoubleLinkNode t) {
		tail = t;
	}
	
}