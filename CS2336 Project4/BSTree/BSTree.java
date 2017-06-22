/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 4 - Redbox Inventory System
 * BSTree class
 */

package BSTree;

import java.io.*;

public class BSTree {
	// Private variable
	private Node root;
	
	// Constructors
	public BSTree() {
		root = null;
	}
	public BSTree(Node r) {
		root = r;
	}
	
	// Getter
	public Node getRoot() {
		return root;
	}
	
	// Setter
	public void setRoot(Node r) {
		root = r;
	}
		
	// Insert a new node into the BSTree
	public void insert(Node n) {
		if (root == null) { // tree is empty
			root = n;
		}
		else { // tree is not empty
			Node parent = insertHelper(root, n); // find appropriate parent
			if (parent.compareTo(n) > 0) // parent>n
				parent.setLeft(n);
			else // parent<=n
				parent.setRight(n);
		}
	}
	// Find the appropriate parent for Node n
	private Node insertHelper(Node parent, Node n) {
		if (parent.compareTo(n) > 0) { // parent>n
			if (parent.getLeft() == null)
				return parent;
			else
				return insertHelper(parent.getLeft(), n); // go further down to the left subtree
		}
		else { //if (parent.compareTo(n) == 0) parent<=n
			if (parent.getRight() == null)
				return parent;
			else
				return insertHelper(parent.getRight(), n); // go further down to the right subtree
		}
	}
	
	// Return the goal Node
	public Node find(Node cur, Node goal) {
		if (cur == null)
			return null;
		else if (cur.compareTo(goal) < 0)
			return find(cur.getRight(), goal);
		else if (cur.compareTo(goal) > 0)
			return find(cur.getLeft(), goal);
		else // cur.compareTo(goal) == 0
			return cur;
	}
	// Return the parent Node of Node goal
	public Node findParent(Node par, Node cur, Node goal) {
		if (cur == null)
			return null;
		else if (cur.compareTo(goal) < 0)
			return findParent(cur, cur.getRight(), goal);
		else if (cur.compareTo(goal) > 0)
			return findParent(cur, cur.getLeft(), goal);
		else // cur.compareTo(goal) == 0
			return par;
	}
	
	public void addCopies(Node n, int num) {
		Node current = find(root, n);
		if (current != null)
			current.setAvailable(current.getAvailable() + num); // increase the number available
		else // Node n does not exist in BSTree so add it
			insert(new Node(n.getTitle(), num, 0));
	}
	public void removeCopies(Node n, int num) {
		Node current = find(root, n);
		if (current != null) {
			current.setAvailable(current.getAvailable() - num); // decrease the number available
			if (current.getAvailable() == 0 && current.getCopiesRented() == 0) {
				delete(current); // if available is 0 and no copies are rented out, then delete the node
			}
		}
		else
			System.out.println("Node \"" + n.getTitle() + "\" could not be found.");
	}
	public void rentDVD(Node n) {
		Node current = find(root, n);
		if (current != null) {
			current.setAvailable(current.getAvailable() - 1); // decrement number available
			current.setCopiesRented(current.getCopiesRented() + 1); // increment number rented
		}
		else
			System.out.println("Node \"" + n.getTitle() + "\" could not be found.");
	}
	public void returnDVD(Node n) {
		Node current = find(root, n);
		if (current != null) {
			current.setAvailable(current.getAvailable() + 1); // increment number available
			current.setCopiesRented(current.getCopiesRented() - 1); // decrement number rented
		}
		else
			System.out.println("Node \"" + n.getTitle() + "\" could not be found.");
	}
	
	// Delete Node n from the BSTree
	private void delete(Node n) {
		Node current = find(root, n);
		if (current != null) {
			if (current.getLeft() == null) { // Case 1: current does not have a left child
				Node parent = findParent(null, root, current);
				if (parent != null) {
					if (parent.getLeft().compareTo(current) == 0) // current is the left child of parent
						parent.setLeft(current.getRight());
					else // current is the right child of parent
						parent.setRight(current.getRight());
				}
				else { // parent was null for some reason
					//System.out.println("Parent Node of \"" + n.getTitle() + "\" could not be found.");
					if (root.getRight() == null) { // root does not have a right child
						root = null; // tree is now empty
					}
					else { // root has a right child
						Node rightChild = root.getRight();
						root.setTitle(rightChild.getTitle());
						root.setAvailable(rightChild.getAvailable());
						root.setCopiesRented(rightChild.getCopiesRented());
						root.setLeft(rightChild.getLeft());
						root.setRight(rightChild.getRight());
					}
				}
				
			}
			else { // Case 2: current has a left child
				// find rightMost and parentOfRightMost
				Node rightMost = findRightMost(current.getLeft()); // find the rightMost Node in the left subtree
				Node parentOfRightMost = findParent(current, current.getLeft(), rightMost);
				
				// Replace the contents of current with rightMost
				current.setTitle(rightMost.getTitle());
				current.setAvailable(rightMost.getAvailable());
				current.setCopiesRented(rightMost.getCopiesRented());
				
				// Delete the rightMost Node by routing the BSTree around it
				if (parentOfRightMost.getRight()!=null && parentOfRightMost.getRight().compareTo(rightMost)==0)
					parentOfRightMost.setRight(rightMost.getLeft());
				else
					parentOfRightMost.setLeft(rightMost.getLeft());
			}
		}
		else
			System.out.println("Node \"" + n.getTitle() + "\" could not be found.");
	}
	// Return the right most node in a subtree
	private Node findRightMost(Node n) {
		if (n.getRight() == null)
			return n;
		else
			return findRightMost(n.getRight());
	}
	
	// Print the BSTree in preorder
	public void printReport() {
		System.out.printf("%-35s\t%-9s\t%-13s\n", "Title", "Available", "Copies Rented");
		preorder(root);
	}
	private void preorder(Node n) {
		if (n == null)
			return;
		preorder(n.getLeft());
		System.out.printf("%-35s\t%-9d\t%-13d\n", n.getTitle(), n.getAvailable(), n.getCopiesRented());
		//System.out.println(n.getTitle() + " " + n.getAvailable() + " " + n.getCopiesRented());
		preorder(n.getRight());
	}
	
	// Write the BStree report to redbox_kiosk.txt
	public void writeReport() throws IOException {
		PrintWriter pw = new PrintWriter(new File("redbox_kiosk5.txt"));
		if (root != null) { // only write to file if BStree has nodes
			Node leftMost = findLeftMost(root);
			writePreorder(pw, root, leftMost);
			pw.flush();
		}
		pw.close();
	}
	private void writePreorder(PrintWriter pw, Node n, Node leftMost) { // traverse in preorder
		if (n == null)
			return;
		writePreorder(pw, n.getLeft(), leftMost);
		if (n.compareTo(leftMost) == 0) // do not print newline if it is the first node
			pw.format("%-35s\t%-9d\t%-13d", n.getTitle(), n.getAvailable(), n.getCopiesRented());
		else // print newline for all other nodes
			pw.format("\n%-35s\t%-9d\t%-13d", n.getTitle(), n.getAvailable(), n.getCopiesRented());
		writePreorder(pw, n.getRight(), leftMost);
	}
	// Return the left most node in a subtree
		private Node findLeftMost(Node n) {
			if (n.getLeft() == null)
				return n;
			else
				return findLeftMost(n.getLeft());
		}
}