/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 4 - Redbox Inventory System
 * Main class
 */

/*
 * Adjustments:
 * 
 * 1) null pointer exception for the printwriter
 * 2) only write to file if BStree has nodes
 * 3) move parentOfRightMost before changing current
 * 4) if statements checking parentOfRightMost.getRight() != null && parentOfRightMost.getRight().compareTo(rightMost)==0
 * 5) if findParent() == null, then means current is root. Therefore need to delete accordingly.
 * 6) adjust splitting by commas under add and remove
 */

import BSTree.*;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
			BSTree inventory = readInventory();
			
			//System.out.println("Before:");
			//inventory.printReport();
			
			processTransactions(inventory);
			
			//System.out.println("\nAfter:");
			//inventory.printReport();
			
			inventory.writeReport();
	}
	
	// Read "inventory.dat" and create a BSTree based on information
	public static BSTree readInventory() throws IOException {
		Scanner scan = new Scanner(new File("inventory5.dat"));
		BSTree inventory = new BSTree();
		while (scan.hasNextLine()) {
			String[] info = scan.nextLine().split(","); // read in from inventory.dat
			Node newNode = new Node(info[0].substring(1, info[0].length()-1), Integer.parseInt(info[1]), Integer.parseInt(info[2])); // create new Node
			inventory.insert(newNode); // insert new Node into inventory
		}
		scan.close();
		return inventory;
	}
	
	// Read "transaction.log" and process the transactions accordingly
	public static void processTransactions(BSTree inventory) throws IOException {
		Scanner scan = new Scanner(new File("transaction5.log"));
		
		int errorCounter = 0;
		PrintWriter pw = null;
		
		while (scan.hasNextLine()) {
			scan.useDelimiter(" ");
			String line = ""; // String line will contain the contents of the line
			try {
				String op = scan.next(); // String op contains the operator (add/remove/rent/return)
				line += op;
				if (op.equals("add")) {
					// assuming that there are no commas in the titles
					String rest = scan.nextLine();
					line += rest;
					String[] split = rest.split(",");
					if(split.length != 2)
						throw new Exception();
					String title = split[0];
					title = title.substring(1);
					if (countQuotes(title) != 2) // Must have two quotes
						throw new Exception();
					else if (title.charAt(0) != '\"' || title.charAt(title.length()-1) != '\"') // First and last characters must be quotes
						throw new Exception();
					else {
						int quantity = Integer.parseInt(split[1]);
						inventory.addCopies(new Node (title.substring(1, title.length()-1), 0, 0), quantity);
					}
				}
				else if (op.equals("remove")) {
					// assuming that there are no commas in the titles
					String rest = scan.nextLine();
					line += rest;
					String[] split = rest.split(",");
					if(split.length != 2)
						throw new Exception();
					String title = split[0];
					title = title.substring(1);
					if (countQuotes(title) != 2) // Must have two quotes
						throw new Exception();
					else if (title.charAt(0) != '\"' || title.charAt(title.length()-1) != '\"') // First and last characters must be quotes
						throw new Exception();
					else {
						int quantity = Integer.parseInt(split[1]);
						inventory.removeCopies(new Node (title.substring(1, title.length()-1), 0, 0), quantity);
					}
				}
				else if (op.equals("rent")) {
					String title = scan.nextLine(); // String title contains the title in quotes
					line += title;
					title = title.substring(1); // Remove the space before the first quote
					if (countQuotes(title) != 2) // Must have two quotes
						throw new Exception();
					else if (title.charAt(0) != '\"' || title.charAt(title.length()-1) != '\"') // First and last characters must be quotes
						throw new Exception();
					else
						inventory.rentDVD(new Node(title.substring(1, title.length()-1), 0, 0));
				}
				else if (op.equals("return")) {
					String title = scan.nextLine(); // String title contains the title in quotes
					line += title;
					title = title.substring(1); // Remove the space before the first quote
					if (countQuotes(title) != 2) // Must have two quotes
						throw new Exception();
					else if (title.charAt(0) != '\"' || title.charAt(title.length()-1) != '\"') // First and last characters must be quotes
						throw new Exception();
					else
						inventory.returnDVD(new Node(title.substring(1, title.length()-1), 0, 0));
				}
				else {
					line += scan.nextLine(); // Scan the rest of the line
					throw new Exception();
				}
			}
			catch (Exception e) {
				errorCounter++; // increment error counter
				if (errorCounter == 1) {
					pw = new PrintWriter("error5.log"); // create "error.log" file
					pw.write(line);
				}
				else {
					pw.write("\n" + line); // only include newline in subsequent errors
				}
				//System.out.println("Line: \"" + line + "\" has an error.");
			}			
		}
		scan.close();
		if (pw != null)
			pw.close();
		
	}
	
	// return the number of quotes in the String title
	public static int countQuotes(String title) {
		int counter = 0;
		for (int i=0; i<title.length(); i++) {
			if (title.charAt(i) == '\"')
				counter++; // increment counter
		}
		return counter;
	}
}