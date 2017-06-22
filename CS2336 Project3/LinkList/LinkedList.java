/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 3
 * LinkList class
 */

package LinkList;

public class LinkedList {
	// Private Variables
	private DoubleLinkNode head;
	private DoubleLinkNode tail;
	
	// Constructor
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	// Setters
	public void setHead(DoubleLinkNode h) {
		head = h;
	}
	public void setTail(DoubleLinkNode t) {
		tail = t;
	}
	
	// Getters
	public DoubleLinkNode getHead() {
		return head;
	}
	public DoubleLinkNode getTail() {
		return tail;
	}
	
	// Check if the LinkList is empty
	public boolean isEmpty() {
		return head == null && tail == null;
	}
	
	// Add node to the end of the list
	public void addNodeAtEnd(DoubleLinkNode dln) {
		if (isEmpty()) { // list is empty
			head = dln;
			tail = dln;
		}
		else { // list has one or more node
			tail.setNext(dln);
			dln.setPrev(tail);
			tail = dln;
		}
	}
	
	// Add node to the list in order
	public void addNode(DoubleLinkNode dln) {
		if (contains(dln)) { // list already contains dln
			return;
		}
		else if (isEmpty()) { // list is empty
			head = dln;
			tail = dln;
		}
		// dln goes at the head of the list
		else if (dln.getRow() < head.getRow() || (dln.getRow() == head.getRow() && dln.getSeat() < head.getSeat())) {
			dln.setNext(head);
			head.setPrev(dln);
			head = dln;
		}
		// dln goes at the tail of the list
		else if (dln.getRow() > tail.getRow() || (dln.getRow() == tail.getRow() && dln.getSeat() > tail.getSeat())) {
			tail.setNext(dln);
			dln.setPrev(tail);
			tail = dln;
		}
		// dln goes in the middle of the list
		else {
			DoubleLinkNode pointer = head;
			while (pointer.getNext() != null && ((dln.getRow() > pointer.getRow()) || (dln.getRow() == pointer.getRow() && dln.getSeat() > pointer.getSeat()))) {
				pointer = pointer.getNext();
			}
			pointer = pointer.getPrev();
			dln.setNext(pointer.getNext());
			pointer.getNext().setPrev(dln);
			pointer.setNext(dln);
			dln.setPrev(pointer);
		}
	}
	
	// remove node from LinkList
	public void removeNode(DoubleLinkNode dln) {
		if (isEmpty() || !contains(dln)) // if LinkList is empty
			return;
		else if (head.getRow() == dln.getRow() && head.getSeat() == dln.getSeat()) // remove head node
			removeHead();
		else if (tail.getRow() == dln.getRow() && head.getSeat() == dln.getSeat()) // remove tail node
			removeTail();
		else { // node is in the middle so need to search for it
			DoubleLinkNode pointer = head;
			while ((pointer.getRow() != dln.getRow()) ||  (pointer.getRow() == dln.getRow() && pointer.getSeat() != dln.getSeat()))
				pointer = pointer.getNext();
			pointer.getPrev().setNext(pointer.getNext());
			if (pointer.getNext() != null)
				pointer.getNext().setPrev(pointer.getPrev());
		}
	}
	
	// Remove DoubleLinkedNode from the head of the LinkList
	public void removeHead() {
		if (isEmpty())
			return;
		head = head.getNext();
		if (head != null)
			head.setPrev(null);
		else
			tail = null;
	}
	
	// Remove DoubleLinkedNode from the tail of the LinkList
	public void removeTail() {
		if (isEmpty())
			return;
		tail = tail.getPrev();
		if (tail != null)
			tail.setNext(null);
		else
			head = null;
	}
	
	// Return the length of the LinkList
	public int getLength() {
		if (isEmpty())
			return 0;
		else {
			int counter = 0;
			DoubleLinkNode pointer = head;
			while(pointer != null) {
				counter++;
				pointer = pointer.getNext();
			}
			return counter;
		}
	}
	
	// Check if the LinkList contains the DoubleLinkNode dln
	public boolean contains(DoubleLinkNode dln) {
		DoubleLinkNode pointer = head;
		while (pointer != null) {
			if (pointer.getRow() == dln.getRow() && pointer.getSeat() == dln.getSeat())
				return true;
			pointer = pointer.getNext();
		}
		return false;
	}
}
