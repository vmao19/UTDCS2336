/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Homework 5
 * Queue class extends LinkedList
 */

package LinkedList;

public class Queue extends LinkedList {
	private LinkedList q;
	
	public Queue() {
		q = new LinkedList();
	}
	
	// Add DoubleLinkedNode
	public void enqueue(DoubleLinkedNode dln) {
		q.addNode(dln);
	}
	
	// Remove DoubleLinkedNode from the beginning of the LinkedList
	public void dequeue() {
		q.removeHead();
	}
	
	// Return the DoubleLinkedNode that will be dequeued next
	public DoubleLinkedNode peek() {
		return q.getHead(); 
	}
	
	// Check if the queue is empty
	public boolean isEmpty() {
		return q.isEmpty();
	}
}