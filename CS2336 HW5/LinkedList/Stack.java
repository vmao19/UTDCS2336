/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Homework 5
 * Stack class extends LinkedList
 */

package LinkedList;

public class Stack extends LinkedList {
	
	private LinkedList s;
	
	public Stack() {
		s = new LinkedList();
	}
	
	// Add DoubleLinkedNode
	public void push(DoubleLinkedNode dln) {
		s.addNode(dln);
	}
	
	// Remove DoubleLinkedNode from the end
	public void pop() {
		s.removeTail();
	}
	
	// Check if stack is empty
	public boolean isEmpty() {
		return s.isEmpty();
	}
	
	// Return the next DoubleLinkedNode to be popped
	public DoubleLinkedNode peek() {
		return s.getTail();
	}
	
}