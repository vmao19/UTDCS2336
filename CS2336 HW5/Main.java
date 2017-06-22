/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Homework 5
 * Main class
 */

import java.io.*;
import java.util.Scanner;
import LinkedList.*;

public class Main {

	public static void main(String[] args) throws IOException {
		stackReverse("stack_input.txt", "stack_output.txt");
		queueUppercase("queue_input.txt", "queue_output.txt");
		
		boolean identical = queueCompare("stack_input.txt", "queue_input.txt");
		if (identical)
			System.out.println("The two files are identical.");
		else
			System.out.println("The two files are not identical.");
	}
	
	// Read input as a stack, reverse the characters, send the answer to output
	static void stackReverse(String input, String output) throws FileNotFoundException {
		// Read input as a stack
		Scanner scan = new Scanner(new File(input));
		Stack stack = new Stack();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			for (int i=0; i<line.length(); i++) {
				stack.push(new DoubleLinkedNode(line.charAt(i)));
			}
		}
		scan.close();
		
		// Send the answer to output
		PrintWriter pw = new PrintWriter(output);
		while(!stack.isEmpty()) {
			pw.append(stack.peek().getLetter());
			stack.pop();
		}
		pw.flush();
		pw.close();
	}
	
	// Read input as a queue, make characters uppercase, send the answer to output
	static void queueUppercase(String input, String output) throws FileNotFoundException {
		// Read input as a queue
		Scanner scan = new Scanner(new File(input));
		Queue queue = new Queue();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			for (int i=0; i<line.length(); i++) {
				queue.enqueue(new DoubleLinkedNode(line.charAt(i)));
			}
		}
		scan.close();
		
		// Send the answer to output
		PrintWriter pw = new PrintWriter(output);
		while(!queue.isEmpty()) {
			String temp = queue.peek().getLetter() + "";
			pw.append(temp.toUpperCase()); // make characters uppercase
			queue.dequeue();
		}
		pw.flush();
		pw.close();
	}
	
	// Read in two inputs as queues, check if two files are identical, return appropriate boolean
	static boolean queueCompare(String input1, String input2) throws FileNotFoundException {
		// Read input1 as a queue
		Scanner scan1 = new Scanner(new File(input1));
		Queue q1 = new Queue();
		while (scan1.hasNextLine()) {
			String line = scan1.nextLine();
			for (int i=0; i<line.length(); i++) {
				q1.enqueue(new DoubleLinkedNode(line.charAt(i)));
			}
		}
		scan1.close();
		// Read input2 as a queue
		Scanner scan2 = new Scanner(new File(input2));
		Queue q2 = new Queue();
		while (scan2.hasNextLine()) {
			String line = scan2.nextLine();
			for (int i=0; i<line.length(); i++) {
				q2.enqueue(new DoubleLinkedNode(line.charAt(i)));
			}
		}
		scan2.close();
		
		// Check if two files are identical and return appropriate boolean
		while(!q1.isEmpty() && !q2.isEmpty()) {
			if (q1.peek().getLetter() != q2.peek().getLetter())
				return false;
			else {
				q1.dequeue();
				q2.dequeue();
			}
		}
		return true;
		
	}
	
}