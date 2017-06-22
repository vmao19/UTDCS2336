/*
 * Victor Mao (vtm160030)
 * Seth Raley (sar160030)
 * CS 2336.003 In Class 5
 * Node base class
 */

public class Node {
	private char letter;
	
	public Node(char l) {
		letter = l;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public void setLetter(char l) {
		letter = l;
	}
}