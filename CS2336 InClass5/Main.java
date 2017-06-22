/*
 * Victor Mao (vtm160030)
 * Seth Raley (sar160030)
 * CS 2336.003 In Class 5
 * Main class
 */

import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Stack stack = new Stack();
		String output = "";
		
		Scanner scan = new Scanner(new File("infix.txt"));
		String[] expressions = scan.nextLine().split(" "); // read from file
		scan.close();
		
		// process data
		for (int i=0; i<expressions.length; i++) {
			if (expressions[i].equals("("))
				stack.push(new DoubleLinkNode('('));
			else if (expressions[i].equals(")")) {
				while (stack.peek().getLetter() != '(') {
					output += stack.peek().getLetter() + " ";
					stack.pop();
				}
				stack.pop(); // need to pop off the (
			}
			else if (expressions[i].equals("^")) {
				while (stack.peek() != null && stack.peek().getLetter() == '^') {
					output += stack.peek().getLetter() + " ";
					stack.pop();
				}
				stack.push(new DoubleLinkNode('^'));
			}
			else if (expressions[i].equals("*") || expressions[i].equals("/")) {
				while (stack.peek() != null && (stack.peek().getLetter() == '^' || stack.peek().getLetter() == '*' || stack.peek().getLetter() == '/')) {
					output += stack.peek().getLetter() + " ";
					stack.pop();
				}
				stack.push(new DoubleLinkNode(expressions[i].charAt(0)));
			}
			else if (expressions[i].equals("+") || expressions[i].equals("-")) {
				while (stack.peek() != null && stack.peek().getLetter() != '(') {
					output += stack.peek().getLetter() + " ";
					stack.pop();
				}
				stack.push(new DoubleLinkNode(expressions[i].charAt(0)));
			}
			else { // operand, so add to postfix expression
				output += expressions[i] + " ";
			}
		}
		
		// When end of input is reached, pop the stack (appending to postfix expression) until stack is empty
		while (stack.peek() != null) {
			output += stack.peek().getLetter() + " ";
			stack.pop();
		}
		
		//System.out.println(output.trim());
		
		// Write back to file
		BufferedWriter bw = new BufferedWriter(new FileWriter("postfix.txt"));
		bw.write(output.trim());
		bw.close();
		

	}

}
