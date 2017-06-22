/* Victor Mao (vtm160030)
 * CS 2336.003 Project 1
 * GotG2 Ticket Reservation System
 */

import java.io.*;
import java.util.Scanner;

public class GotG2TicketReservation
{
	public static void main(String[] args) throws IOException
	{
		// Initialize Scanner and reference array
		String[] fileNames = new String[] {"A1.txt", "A2.txt", "A3.txt"};
		Scanner scan = new Scanner(System.in);
		boolean exit = false;
		
		while(!exit) // while user decides not to quit
		{
			displayMainMenu();
			System.out.print("\nEnter an option (1-4): ");
			int option = scan.nextInt();
			
			switch (option)
			{
				case 1:
				case 2:
				case 3:
					char[][] auditorium = openAuditorium(fileNames[option-1]); // option corresponding auditorium
					System.out.println("Auditorium " + option + ": ");
					printAuditorium(auditorium);
					
					// ask user for number of tickets
					System.out.print("Enter the number of the tickets: ");
					int numberTickets = scan.nextInt();
					if (numberTickets < 1) // check for valid user input
					{
						System.out.println("You must enter at least one ticket. Please try again.\n");
						break;
					}
					
					// ask for row number
					System.out.print("Enter row number: ");
					int rowNumber = scan.nextInt();
					if (rowNumber < 1 || rowNumber > auditorium.length) // check for valid user input
					{
						System.out.println("You must enter a valid row number. Please try again.\n");
						break;
					}
					
					// ask for starting seat number
					System.out.print("Enter starting seat number: ");
					int startingSeatNumber = scan.nextInt();
					if (startingSeatNumber < 1 || startingSeatNumber > auditorium[0].length) // check for valid user input
					{
						System.out.println("You must enter a valid seat number. Please try again.\n");
						break;
					}
					
					if (checkAvailability(auditorium, rowNumber-1, startingSeatNumber-1, numberTickets)) // seats are available
					{
						reserveSeats(auditorium, rowNumber-1, startingSeatNumber-1, numberTickets);
						System.out.println("Seats sucessfully reserved!");
						System.out.println("Updated Auditorium " + option + ": ");
						printAuditorium(auditorium);
					}
					else // seats are not available
					{
						System.out.println("Seats are unavailable.\n");
						int bestAvailable = findBestAvailable(auditorium, rowNumber-1, numberTickets); // find best available seats
						if (bestAvailable < auditorium[0].length) // there exist best available seats
						{
							System.out.println("The best available seats on the same row are at " + bestAvailable);
							System.out.print("Would you like to reserve these seats? (Y/N) ");
							String garbage = scan.nextLine(); // pick up the garbage user input
							String answer = scan.nextLine();
							if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
							{
								reserveSeats(auditorium, rowNumber-1, bestAvailable-1, numberTickets);
								System.out.println("Seats sucessfully reserved!\n");
								System.out.println("Updated Auditorium " + option + ": ");
								printAuditorium(auditorium);
							}
							else
								System.out.println("Seats not reserved.\n"); // do not reserve best available seats
						}
						else // best available seats do not exist
						{
							System.out.println("There are no best available seats on the same row. Please try again.\n");
						}
					}
					
					saveAuditorium(auditorium, fileNames[option-1]); // save the auditorium to file
					
					break;
				case 4: // user selected exit
					exit = true;
					printReport();
					break;
				default: // user did not select a valid option
					System.out.println("You did not enter a valid option. Please try again.\n");
					break;
			}
					
		}
		
		// Close Scanner
		scan.close();

	}
	
	// Prints out the main menu
	public static void displayMainMenu()
	{
		System.out.println("Welcome to the Main Menu! Reserve your tickets here.");
		System.out.println("1. Auditorium 1");
		System.out.println("2. Auditorium 2");
		System.out.println("3. Auditorium 3");
		System.out.println("4. Exit");
	}
	
	// Read auditorium file and return a char[][] representing the auditorium seating
	public static char[][] openAuditorium(String inputFilePath) throws IOException
	{
		int[] sizes = getAuditoriumSize(inputFilePath);
		char[][] auditorium = new char[sizes[0]][sizes[1]];
		
		Scanner scan = new Scanner(new File(inputFilePath));
		for (int i=0; i<sizes[0]; i++)
		{
			String line = scan.nextLine();
			for (int j=0; j<line.length(); j++)
			{
				auditorium[i][j] = line.charAt(j);
			}
		}
		scan.close();
		
		return auditorium;
	}
	
	// Read auditorium file and return an int[] containing [rows, columns] of the auditorium
	public static int[] getAuditoriumSize(String inputFilePath) throws IOException
	{
		Scanner scan = new Scanner(new File(inputFilePath));
		int rows = 0;
		int columns = 0;
		
		while (scan.hasNextLine())
		{
			String line = scan.nextLine();
			columns = line.length();
			rows++;
		}
		scan.close();
		
		return new int[] {rows, columns};
		
	}
	
	// Print formatted seating chart of auditorium
	public static void printAuditorium(char[][] auditorium)
	{
		// print first row header
		System.out.print("  ");
		for (int i=0; i<auditorium[0].length; i++)
			System.out.print(" " + ((i+1)%10));
		System.out.println();
		
		// print seats in auditorium
		for (int i=0; i<auditorium.length; i++)
		{
			System.out.print(" " + (i+1));
			for (int j=0; j<auditorium[i].length; j++)
				System.out.print(" " + auditorium[i][j]);
			System.out.println();
		}
		System.out.println();
		System.out.println("(Empty seats denoted with \"#\")");
		System.out.println("(Reserved seats denoted with \".\")");
		System.out.println();
	}
	
	// Check if the selected seats are available
	public static boolean checkAvailability(char[][] auditorium, int row, int seat, int quantity)
	{
		for (int i=0; i<quantity; i++)
		{
			if (auditorium[row][seat+i] == '.')
				return false;
		}
		return true;
	}
	
	// Reserve the selected seats by changing the seats to '.'
	public static void reserveSeats(char[][] auditorium, int row, int seat, int quantity)
	{
		for (int i=0; i<quantity; i++)
			auditorium[row][seat+i] = '.';
	}
	
	// Write the char[][] auditorium seating to a file
	public static void saveAuditorium(char[][] auditorium, String outputFilePath) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));
		for (int i=0; i<auditorium.length; i++)
		{
			for (int j=0; j<auditorium[i].length; j++)
			{
				bw.write(auditorium[i][j]);
			}
			bw.write("\n");
		}
		bw.close();
	}
	
	// Print the final formatted report of the auditorium statuses and ticket sales
	public static void printReport() throws IOException
	{
		char[][] a1 = openAuditorium("A1.txt");
		char[][] a2 = openAuditorium("A2.txt");
		char[][] a3 = openAuditorium("A3.txt");
		
		int a1Reserved = getReservedSeats(a1);
		int a2Reserved = getReservedSeats(a2);
		int a3Reserved = getReservedSeats(a3);
		int totalReserved = a1Reserved+a2Reserved+a3Reserved;
		
		int a1Empty = getEmptySeats(a1);
		int a2Empty = getEmptySeats(a2);
		int a3Empty = getEmptySeats(a3);
		int totalEmpty = a1Empty+a2Empty+a3Empty;
		
		System.out.println("Ticket Sales Report: ");
		System.out.println("\t\t\t\tAuditorium 1\tAuditorium 2\tAuditorium 3\tTotal");
		System.out.println("Number of Reserved Seats:\t" + a1Reserved + "\t\t" + a2Reserved + "\t\t" + a3Reserved + "\t\t" + totalReserved);
		System.out.println("Number of Empty Seats:\t\t" + a1Empty + "\t\t" + a2Empty + "\t\t" + a3Empty + "\t\t" + totalEmpty);
		System.out.println("Total of Ticket Sales:\t\t$" + (a1Reserved*7) + "\t\t$" + (a2Reserved*7) + "\t\t$" + (a3Reserved*7) + "\t\t$" + ((totalReserved*7)));
		
	}
	
	// Return the total number of reserved seats in the auditorium
	public static int getReservedSeats(char[][] auditorium)
	{
		int counter = 0;
		for (int i=0; i<auditorium.length; i++)
		{
			for (int j=0; j<auditorium[i].length; j++)
			{
				if (auditorium[i][j] == '.') // seat is reserved
					counter++;
			}
		}
		return counter;
	}
	
	// Return the total number of empty seats in the auditorium
	public static int getEmptySeats(char[][] auditorium)
	{
		int counter = 0;
		for (int i=0; i<auditorium.length; i++)
		{
			for (int j=0; j<auditorium[i].length; j++)
			{
				if (auditorium[i][j] == '#') // seat is empty
					counter++;
			}
		}
		return counter;
	}
	
	// Obtain the best available seats in the given row
	public static int findBestAvailable(char[][] auditorium, int row, int quantity)
	{
		int middle = auditorium[0].length / 2;
		int leftSide = auditorium[0].length + 1; // Initialize leftSide to larger than the auditorium
		int rightSide = auditorium[0].length + 1; // Initialize rightSide to larger than the auditorium
		
		// Check for available seats from middle to the left
		for (int i=middle; i>=quantity-1; i--)
		{
			if (auditorium[row][i] == '#')
			{
				boolean available = true;
				for (int j=0; j<quantity; j++)
				{
					if (auditorium[row][i-j] == '.')
					{
						available = false;
						break;
					}
				}
				if (available)
				{
					leftSide = i;
					break;
				}					
			}
		}
		
		// Check for available seats from middle to the right
		for (int i=middle; i<auditorium[0].length-quantity+1; i++)
		{
			if (auditorium[row][i] == '#')
			{
				boolean available = true;
				for (int j=0; j<quantity; j++)
				{
					if (auditorium[row][i+j] == '.')
					{
						available = false;
						break;
					}
				}
				if (available)
				{
					rightSide = i;
					break;
				}					
			}
		}
		
		if (leftSide == auditorium[0].length + 1 && rightSide == auditorium[0].length + 1) // no available seats
			return leftSide;
		else if (leftSide == auditorium[0].length + 1) // left side has no available seats
			return rightSide+1;
		else if (rightSide == auditorium[0].length + 1) // right side has no available seats
			return leftSide-quantity+2;
		else // return the side closest to middle
		{
			int closest = Math.min(middle-(leftSide+1), (rightSide+1)-middle);
			if (closest == middle-(leftSide+1))
				return leftSide-quantity+2;
			else
				return rightSide+1;
		}
		
	}
}
