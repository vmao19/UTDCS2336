/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Homework 5
 * LinkedList class extends DoubleLinkedNode class extends Node class
 */

package LinkedList;

public class LinkedList extends DoubleLinkedNode {
	private DoubleLinkedNode head;
	private DoubleLinkedNode tail;
	
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	// Add DoubleLinkedNode to the end of the LinkedList
	public void addNode(DoubleLinkedNode dln) {
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
	
	// Remove DoubleLinkedNode from the head of the LinkedList
	public void removeHead() {
		if (isEmpty())
			return;
		head = head.getNext();
		if (head != null)
			head.setPrev(null);
		else
			tail = null;
	}
	
	// Remove DoubleLinkedNode from the tail of the LinkedList
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
	public DoubleLinkedNode getHead() {
		return head;
	}
	public DoubleLinkedNode getTail() {
		return tail;
	}
	
	// Setters
	public void setHead(DoubleLinkedNode h) {
		head = h;
	}
	public void setTail(DoubleLinkedNode t) {
		tail = t;
	}
	
}