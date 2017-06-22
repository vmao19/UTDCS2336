/*
 * Victor Mao (vtm160030)
 * Seth Raley (sar160030)
 * CS 2336.003 In Class 5
 * Stack class extends LinkedList
 */

public class Stack extends LinkedList {
	
	private LinkedList s;
	
	public Stack() {
		s = new LinkedList();
	}
	
	// Add DoubleLinkNode
	public void push(DoubleLinkNode dln) {
		s.addNode(dln);
	}
	
	// Remove DoubleLinkNode from the end
	public void pop() {
		s.removeTail();
	}
	
	// Check if stack is empty
	public boolean isEmpty() {
		return s.isEmpty();
	}
	
	// Return the next DoubleLinkNode to be popped
	public DoubleLinkNode peek() {
		return s.getTail();
	}
	
}