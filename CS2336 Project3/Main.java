/*
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 3
 * Main class
 * 
 * Recursive function to write the data back to the file:
 * INITIAL RECURSIVE CALL @ LINE 330
 * RECURSIVE FUNCTION DEFINITION @ LINE 336
 */

import LinkList.*;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) throws IOException {
		String menu1 = "Welcome to the Main Menu! Choose an option.\n" +
						"1. Reserve Seats\n" +
						"2. View Auditorium\n" +
						"3. Exit\n";
		String menu2 = "Choose an auditorium.\n" +
						"1. Auditorium 1\n" +
						"2. Auditorium 2\n" +
						"3. Auditorium 3\n";
		
		// Read in auditoriums
		LinkedList[] a1 = readAuditorium("A1.txt"); // LinkList[] = [reserved, unreserved]
		LinkedList[] a2 = readAuditorium("A2.txt");
		LinkedList[] a3 = readAuditorium("A3.txt");
		
		// Store sizes of auditoriums
		int[] a1size = getSize("A1.txt");
		int[] a2size = getSize("A2.txt");
		int[] a3size = getSize("A3.txt");
		
		// Intialize Scanner
		Scanner scan = new Scanner(System.in);
		
		boolean quit = false;
		while (!quit) {
			int option = readInt(scan, menu1,"\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3); // read and validate user input
			switch (option) {
				case 1: // 1. Reserve Seats
					int option1 = readInt(scan, menu2, "\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3);
						switch (option1) {
							case 1: // 1. Auditorium 1
								System.out.println("Auditorium 1: ");
								printAuditorium(a1[0], a1size[0], a1size[1]);
								
								// read and validate user input
								int rowNum = readInt(scan, "Enter Row Number: ", "\nInvalid input. Enter an integer in the range of [1," + a1size[0] + "].\n", 1, a1size[0]);
								int seatNum = readInt(scan, "Enter Seat Number: ", "\nInvalid input. Enter an integer in the range of [1," + a1size[1] + "].\n", 1, a1size[1]);
								int numTix = readInt(scan, "Enter Number of Tickets: ", "\nInvalid input. Enter an integer in the range of [1," + a1size[1] + "].\n", 1, a1size[1]);
								
								if (checkAvailability(a1[1], rowNum-1, seatNum-1, numTix)) // seats are available
								{
									reserveSeats(a1, rowNum-1, seatNum-1, numTix);
									System.out.println("Seats sucessfully reserved!");
									System.out.println("Updated Auditorium " + option1 + ": ");
									printAuditorium(a1[0], a1size[0], a1size[1]);
								}
								
								else // seats are not available
								{
									System.out.println("Seats are unavailable.\n");
									DoubleLinkNode bestAvailable = findBestAvailable(a1[1], a1size[0], a1size[1], numTix); // find best available seats
									if (bestAvailable.getRow() == -1 || bestAvailable.getSeat() == -1) { // best available seats do not exist
										System.out.println("There are no best available seats in the auditorium. Please try again.\n");
									}
									else { // there exist best available seats
										System.out.println("The best available seats on row " + (bestAvailable.getRow()+1) + " at seat " + (bestAvailable.getSeat()+1) + ".");
										String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
										if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
										{
											reserveSeats(a1, bestAvailable.getRow(), bestAvailable.getSeat(), numTix);
											System.out.println("Seats sucessfully reserved!\n");
											System.out.println("Updated Auditorium " + option1 + ": ");
											printAuditorium(a1[0], a1size[0], a1size[1]);
										}
										else
											System.out.println("Seats not reserved.\n"); // do not reserve best available seats
									}
								}
								
								break;
							case 2:  // 2. Auditorium 2
								System.out.println("Auditorium 2: ");
								printAuditorium(a2[0], a2size[0], a2size[1]);
								
								// read and validate user input
								rowNum = readInt(scan, "Enter Row Number: ", "\nInvalid input. Enter an integer in the range of [1," + a2size[0] + "].\n", 1, a2size[0]);
								seatNum = readInt(scan, "Enter Seat Number: ", "\nInvalid input. Enter an integer in the range of [1," + a2size[1] + "].\n", 1, a2size[1]);
								numTix = readInt(scan, "Enter Number of Tickets: ", "\nInvalid input. Enter an integer in the range of [1," + a2size[1] + "].\n", 1, a2size[1]);
								
								if (checkAvailability(a2[1], rowNum-1, seatNum-1, numTix)) // seats are available
								{
									reserveSeats(a2, rowNum-1, seatNum-1, numTix);
									System.out.println("Seats sucessfully reserved!");
									System.out.println("Updated Auditorium " + option1 + ": ");
									printAuditorium(a2[0], a2size[0], a2size[1]);
								}
								
								else // seats are not available
								{
									System.out.println("Seats are unavailable.\n");
									DoubleLinkNode bestAvailable = findBestAvailable(a2[1], a2size[0], a2size[1], numTix); // find best available seats
									if (bestAvailable.getRow() == -1 || bestAvailable.getSeat() == -1) { // best available seats do not exist
										System.out.println("There are no best available seats on the same row. Please try again.\n");
									}
									else { // there exist best available seats
										System.out.println("The best available seats on row " + (bestAvailable.getRow()+1) + " at seat " + (bestAvailable.getSeat()+1) + ".");
										String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
										if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
										{
											reserveSeats(a2, bestAvailable.getRow(), bestAvailable.getSeat(), numTix);
											System.out.println("Seats sucessfully reserved!\n");
											System.out.println("Updated Auditorium " + option1 + ": ");
											printAuditorium(a2[0], a2size[0], a2size[1]);
										}
										else
											System.out.println("Seats not reserved.\n"); // do not reserve best available seats
									}
								}
								break;
							case 3:  // 3. Auditorium 3
								System.out.println("Auditorium 3: ");
								printAuditorium(a3[0], a3size[0], a3size[1]);
								
								// read and validate user input
								rowNum = readInt(scan, "Enter Row Number: ", "\nInvalid input. Enter an integer in the range of [1," + a3size[0] + "].\n", 1, a3size[0]);
								seatNum = readInt(scan, "Enter Seat Number: ", "\nInvalid input. Enter an integer in the range of [1," + a3size[1] + "].\n", 1, a3size[1]);
								numTix = readInt(scan, "Enter Number of Tickets: ", "\nInvalid input. Enter an integer in the range of [1," + a3size[1] + "].\n", 1, a3size[1]);
								
								if (checkAvailability(a3[1], rowNum-1, seatNum-1, numTix)) // seats are available
								{
									reserveSeats(a3, rowNum-1, seatNum-1, numTix);
									System.out.println("Seats sucessfully reserved!");
									System.out.println("Updated Auditorium " + option1 + ": ");
									printAuditorium(a3[0], a3size[0], a3size[1]);
								}
								
								else // seats are not available
								{
									System.out.println("Seats are unavailable.\n");
									DoubleLinkNode bestAvailable = findBestAvailable(a3[1], a3size[0], a3size[1], numTix); // find best available seats
									if (bestAvailable.getRow() == -1 || bestAvailable.getSeat() == -1) { // best available seats do not exist
										System.out.println("There are no best available seats on the same row. Please try again.\n");
									}
									else { // there exist best available seats
										System.out.println("The best available seats on row " + (bestAvailable.getRow()+1) + " at seat " + (bestAvailable.getSeat()+1) + ".");
										String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
										if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
										{
											reserveSeats(a3, bestAvailable.getRow(), bestAvailable.getSeat(), numTix);
											System.out.println("Seats sucessfully reserved!\n");
											System.out.println("Updated Auditorium " + option1 + ": ");
											printAuditorium(a3[0], a3size[0], a3size[1]);
										}
										else
											System.out.println("Seats not reserved.\n"); // do not reserve best available seats
									}
								}
								break;
						}
						break;
				case 2: // 2. View Auditorium
					int option2 = readInt(scan, menu2, "\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3);
						switch (option2) {
							case 1:  // 1. Auditorium 1
								System.out.println("Auditorium 1: ");
								printAuditorium(a1[0], a1size[0], a1size[1]);
								break;
							case 2:  // 2. Auditorium 2
								System.out.println("Auditorium 2: ");
								printAuditorium(a2[0], a2size[0], a2size[1]);
								break;
							case 3:  // 3. Auditorium 3
								System.out.println("Auditorium 3: ");
								printAuditorium(a3[0], a3size[0], a3size[1]);
								break;
						}
						break;
				case 3: // 3. Exit
					quit = true;
					// Save all the auditoriums back to the files
					saveAuditorium(a1, a1size[0], a1size[1], "A1.txt");
					saveAuditorium(a2, a2size[0], a2size[1], "A2.txt");
					saveAuditorium(a3, a3size[0], a3size[1], "A3.txt");
					printReport(a1, a2, a3); // print ticket sales report
					break;
			}
		}
		
		scan.close();
	}
	
	// Read and validate integer input
	public static int readInt(Scanner scan, String msg, String error, int lowerbound, int upperbound) {
		int ans = -1;
		while (true) {
			System.out.print(msg);
			try {
				ans = scan.nextInt();
				if (ans < lowerbound || ans > upperbound) // out of bounds
					throw new InputMismatchException();
				break;
			}
			catch (InputMismatchException e){
				scan.nextLine();
				System.out.println(error);
				continue;
			}
		}
		return ans;
	}
	
	// Read and validate String input
	public static String readString(Scanner scan, String msg, String error, String yes, String no) {
		String ans = "";
		while (true) {
			System.out.print(msg);
			try {
				ans = scan.next();
				ans = ans.toUpperCase();
				if (!ans.equals("Y") && !ans.equals("N"))
					throw new InputMismatchException(); // incorrect input
				break;
			}
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println(error);
				continue;
			}
		}
		return ans;
	}
	
	// Read in auditorium from file and return LinkList[] where [reserved, unreserved]
	public static LinkedList[] readAuditorium(String path) throws IOException {
		Scanner scan = new Scanner(new File(path));
		LinkedList[] auditoriums = new LinkedList[2];
		auditoriums[0] = new LinkedList(); // reserved seats
		auditoriums[1] = new LinkedList(); // unreserved seats
		int rowCounter = 0;
		while (scan.hasNextLine()) {
			String row = scan.nextLine();
			for (int s=0; s<row.length(); s++) {
				if (row.charAt(s) == '.') // '.' means reserved
					auditoriums[0].addNodeAtEnd(new DoubleLinkNode(rowCounter, s));
				else // '#' means unreserved
					auditoriums[1].addNodeAtEnd(new DoubleLinkNode(rowCounter, s));
			}
			rowCounter++;
		}
		scan.close();
		return auditoriums;
	}
	
	// Return an int[] where [rows, seats] of the auditorium
	public static int[] getSize(String path) throws IOException {
		Scanner scan = new Scanner(new File(path));
		int rows = 0, cols = 0;
		while (scan.hasNextLine()) {
			rows++;
			String row = scan.nextLine();
			cols = row.length();
		}
		scan.close();
		return new int[] {rows, cols};
	}
	
	// Print Auditorium
	public static void printAuditorium(LinkedList audi, int rows, int seats) {
		DoubleLinkNode pointer = audi.getHead();
		System.out.print("   ");
		for (int i=1; i<=seats; i++)
			System.out.print(i%10);
		System.out.println();
		for (int i=0; i<rows; i++) {
			System.out.print(((i+1)%10)+"  ");
			for (int j=0; j<seats; j++) {
				if(pointer.getRow()==i && pointer.getSeat()==j) {
					System.out.print(".");
					if (pointer.getNext() != null)
						pointer = pointer.getNext();
				}
				else
					System.out.print("#");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("(Empty seats denoted with \"#\")");
		System.out.println("(Reserved seats denoted with \".\")");
		System.out.println();
	}
	
	// Print ticket sales report
	public static void printReport(LinkedList[] a1, LinkedList[] a2, LinkedList[] a3) {
		System.out.println("Ticket Sales Report: ");
		System.out.println("\t\t# Reserved\t# Unreserved\tTicket Sales");
		System.out.println("Auditorium 1\t" + a1[0].getLength() + "\t\t" + a1[1].getLength() + "\t\t$" + (a1[0].getLength()*7));
		System.out.println("Auditorium 2\t" + a2[0].getLength() + "\t\t" + a2[1].getLength() + "\t\t$" + (a2[0].getLength()*7));
		System.out.println("Auditorium 3\t" + a3[0].getLength() + "\t\t" + a3[1].getLength() + "\t\t$" + (a3[0].getLength()*7));
		System.out.println("Total\t\t" + (a1[0].getLength()+a2[0].getLength()+a3[0].getLength()) + "\t\t" + (a1[1].getLength()+a2[1].getLength()+a3[1].getLength()) + "\t\t$" + ((a1[0].getLength()+a2[0].getLength()+a3[0].getLength())*7));
	}
	
	// Check if the selected seats are available
	public static boolean checkAvailability(LinkedList auditorium, int row, int seat, int quantity) {
		for (int i=0; i<quantity; i++) {
			if (!auditorium.contains(new DoubleLinkNode(row, seat+i)))
				return false;
		}
		return true;
	}
	
	// Reserve the selected seats by changing the seats to '.'
	public static void reserveSeats(LinkedList[] auditoriums, int row, int seat, int quantity) {
		for (int i=0; i<quantity; i++) {
			auditoriums[0].addNode(new DoubleLinkNode(row, seat+i));
			auditoriums[1].removeNode(new DoubleLinkNode(row, seat+i));
		}
	}
	
	// helper function for recursive write to file
	public static void saveAuditorium(LinkedList[] auditorium, int rows, int seats, String outputFilePath) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
		bw.write(writeToFile(auditorium, rows-1, seats-1, 0, 0, ""));
		bw.close();
		
	}
	
	// recursive function to write to file
	public static String writeToFile(LinkedList[] auditorium, int rows, int seats, int rowCounter, int seatCounter, String ans) {
		if (auditorium[0].contains(new DoubleLinkNode(rowCounter, seatCounter))) {
			ans += "."; // seat is reserved
		}
		else
			ans += "#"; // seat is not reserved
		seatCounter++;
		if(seatCounter > seats) {
			if (rowCounter != rows) // write a new line
				ans += "\n";
			rowCounter++;
			seatCounter = 0;
		}
		if (rowCounter > rows) // base case
			return ans;
		return writeToFile(auditorium, rows, seats, rowCounter, seatCounter, ans);
		
	}
	
	/* UNUSED - because not recursion, used loops
	// Write the char[][] auditorium seating to a file
	public static void saveAuditorium(LinkList auditorium, int rows, int seats, String outputFilePath) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
		for (int i=0; i<rows; i++) {
			for (int j=0; j<seats; j++) {
				if(auditorium.getHead().getRow()==i && auditorium.getHead().getSeat()==j) {
					bw.write(".");
					if (auditorium.getHead().getNext() != null)
						auditorium.setHead(auditorium.getHead().getNext());
				}
				else
					bw.write("#");
			}
			if (i != rows-1)
				bw.write("\n");
		}
		bw.close();
	}
	*/
	
	// Return a DoubleLinkNode containing the left-most seat marking the best available seats
	public static DoubleLinkNode findBestAvailable(LinkedList auditorium, int rows, int seats, int quantity) {
		int midRow = ((rows+1)/2)-1;
		int midSeat = ((seats+1)/2)-1;
		
		double minDist = Double.MAX_VALUE;
		int minRow = -1;
		int minSeat = -1;
		
		DoubleLinkNode front = auditorium.getHead();
		while(front != null) {
			DoubleLinkNode pointer = front;
			boolean open = true;
			for (int i=0; i<quantity; i++) { // checks if adjacent seats are available
				if (pointer == null) {
					open = false;
					break;
				}
				if (front.getRow() != pointer.getRow() || front.getSeat() + i != pointer.getSeat()) {
					open = false;
					break;
				}
				pointer = pointer.getNext();
			}
			if (open) {
				// calculate the distance to the middle of the auditorium
				double distance = Math.sqrt(Math.pow(midRow - front.getRow(), 2) + Math.pow(midSeat - front.getSeat(), 2));
				if (distance < minDist) {
					minDist = distance;
					minRow = front.getRow();
					minSeat = front.getSeat();
				}
				else if (distance == minDist) {
					if (Math.abs(midRow-front.getRow()) < Math.abs(midRow-minRow)) {
						minRow = front.getRow();
						minSeat = front.getSeat();
					}
				}
			}
			front = front.getNext();
		}
		return new DoubleLinkNode(minRow, minSeat);
	}
	
}
