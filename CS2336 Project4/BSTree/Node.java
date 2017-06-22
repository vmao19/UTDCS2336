/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 4 - Redbox Inventory System
 * Node class implements Comparable
 */

package BSTree;

public class Node implements Comparable<Node>{
	// Private variables
	private String title;
	private int available;
	private int copiesRented;
	private Node left;
	private Node right;
	
	// Constructors
	public Node (String t, int a, int cr) {
		title = t;
		available = a;
		copiesRented = cr;
		left = null;
		right = null;
	}
	public Node (String t, int a, int cr, Node l, Node r) {
		title = t;
		available = a;
		copiesRented = cr;
		left = l;
		right = r;
	}
	
	// compareTo function for two Nodes
	@Override
	public int compareTo(Node n) {
		return title.compareTo(n.getTitle());
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	public int getAvailable() {
		return available;
	}
	public int getCopiesRented() {
		return copiesRented;
	}
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	
	// Setters
	public void setTitle(String t) {
		title = t;
	}
	public void setAvailable(int a) {
		available = a;
	}
	public void setCopiesRented(int cr) {
		copiesRented = cr;
	}
	public void setLeft(Node l) {
		left = l;
	}
	public void setRight(Node r) {
		right = r;
	}
}