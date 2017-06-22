/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Project 5
 * Main Class
 */

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import HelperClasses.*;

public class Main {
	// constant variables for ticket prices
	final static double ADULT_PRICE = 10;
	final static double SENIOR_PRICE = 7.5;
	final static double CHILD_PRICE = 5.25;
	
	public static void main(String[] args) throws FileNotFoundException {
		// String Menus
		String customerMainMenu = "Customer Main Menu:\n" +
						  "1. Reserve Seats\n" +
						  "2. View Orders\n" +
						  "3. Update Order\n" +
						  "4. Display Receipt\n" +
						  "5. Log Out\n" + 
						  "Enter an option: ";
		String adminMainMenu = "Admin Main Menu:\n" +
							   "1. View Auditorium\n" + 
							   "2. Print Report\n" +
							   "3. Exit\n" +
							   "Enter an option: ";
		String auditoriumMenu = "Auditorium Menu:\n" +
							    "1. Auditorium 1\n" +
							    "2. Auditorium 2\n" +
							    "3. Auditorium 3\n" +
							    "Enter an option: ";
		String updateOrderMenu = "Update Order Menu:\n" +
							     "1. Add tickets to order\n" +
							     "2. Delete tickets from order\n" +
							     "3. Cancel Order\n" +
							     "Enter an option: ";
		
		// initialize and populate hashmap of users
		HashMap<String, Customer> users = new HashMap<String, Customer>();
		users = populateHashMap();
		
		// initialize and populate the 3 auditoriums
		Auditorium a1 = new Auditorium("A1.txt");
		Auditorium a2 = new Auditorium("A2.txt");
		Auditorium a3 = new Auditorium("A3.txt");
		
		Scanner scan = new Scanner(System.in);
		
		boolean exit = false; // in order to exit the entire program
		while (true && !exit) {
			String username, password;
			int errorCounter = 0;
			
			System.out.println("Welcome to Got2Go Ticket Reservation System!");
			System.out.print("Enter your username: ");
			username = scan.nextLine();
			
			while (true) {
				try {
					boolean logout = false; // in order to log out of the current customer
					System.out.print("Enter your password: ");
					password = scan.nextLine();
					
					if (!users.get(username).getPassword().equals(password)) { // username and password do not match
						throw new InputMismatchException();
					}
					else { // username and password match
						System.out.println("Username and password match. Sucessfully logged in.\n");
						if (username.equals("admin")) { // user is admin so show admin options
							while (!exit) {
								int adminMenuOption = readInt(scan, adminMainMenu,"\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3); // read and validate user input for the admin main menu
								System.out.println();
								switch (adminMenuOption) {
									case 1: // "1. View Auditorium" was selected
										int adminAuditoriumOption = readInt(scan, auditoriumMenu,"\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3); // read and validate user input for the auditorium menu
										System.out.println();
										if (adminAuditoriumOption == 1) { // "1. Auditorium 1" was selected
											System.out.println("Auditorium 1:");
											a1.printAuditorium();
										}
										else if (adminAuditoriumOption == 2) { // "2. Auditorium 2" was selected
											System.out.println("Auditorium 2:");
											a2.printAuditorium();
										}
										else { // "3. Auditorium 3" was selected
											System.out.println("Auditorium 3:");
											a3.printAuditorium();
										}
										break;
									case 2: // "2. Print Report" was selected
										printReport(a1, a2, a3);
										break;
									case 3: // "3. Exit" was selected
										a1.saveAuditorium("A1.txt"); // write auditoriums to corresponding files
										a2.saveAuditorium("A2.txt");
										a3.saveAuditorium("A3.txt");
										exit = true;
										System.out.println("Exiting.");
										break;
								}
							}
						}
						else { // user is a customer
							while (!logout) {
								Customer user = users.get(username);
								int customerMenuOption = readInt(scan, customerMainMenu,"\nInvalid input. Enter an integer in the range of [1,5].\n", 1, 5); // read and validate user input for customer menu
								System.out.println();
								switch (customerMenuOption) {
									case 1: // "1. Reserve Seats" was selected
										int customerAuditoriumOption = readInt(scan, auditoriumMenu,"\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3); // read and validate user input for auditorium menu
										System.out.println();
										if (customerAuditoriumOption == 1) {
											System.out.println("Auditorium 1:");
											a1.printAuditorium();
											int numAdult = checkInt(scan, "Enter number of Adult tickets ($10 each): ", "\nInvalid input. Enter an integer.\n"); // read and validate user input for number of Adult tickets
											int numSenior = checkInt(scan, "Enter number of Senior tickets ($7.50 each): ", "\nInvalid input. Enter an integer.\n"); // read and validate user input for number of Senior tickets
											int numChild = checkInt(scan, "Enter number of Child tickets ($5.25 each): ", "\nInvalid input. Enter an integer.\n"); // read and validate user input for number of Child tickets
											if (numAdult == 0 && numSenior == 0 && numChild == 0) { // 0 tickets entered so do not do anything
												System.out.println("No seats reserved.\n");
											}
											else { // proceed with asking about seats for tickets
												Order newOrder = takeOrder(scan, a1, 1, numAdult, numSenior, numChild); // read and validate user input for the specific Seats
												if (newOrder.isAllAvailable()) { // reserve seats if all the seats are available
													newOrder.placeOrder(); // reserve seats
													user.addOrder(newOrder); // add the Order to the user
													System.out.println("Order sucessfully placed!\n");
													System.out.println("Updated Auditorium 1:");
													a1.printAuditorium();
												}
												else { // not all the seats are available
													System.out.println("Not all seats are available. Finding best available seats.\n");
													Order bestAvailable = findBestAvailable(a1, 1, numAdult, numSenior, numChild); // find the best available seats
													if (bestAvailable == null) // there are no best available seats
														System.out.println("There are no best available seats. Try again.\n");
													else {
														System.out.println("Best Available Seats:");
														bestAvailable.printSeats();
														String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
														if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
														{
															bestAvailable.placeOrder();
															user.addOrder(bestAvailable);
															System.out.println("Order sucessfully placed!\n");
															System.out.println("Updated Auditorium 1:");
															a1.printAuditorium();
														}
														else
															System.out.println("No seats reserved.\n"); // do not reserve best available seats
													}
												}
											}
										}
										else if (customerAuditoriumOption == 2) { // "2. Auditorium 2" was selected. Proceed with same process as auditorium 1 for reserving seats.
											System.out.println("Auditorium 2:");
											a2.printAuditorium();
											int numAdult = checkInt(scan, "Enter number of Adult tickets ($10 each): ", "\nInvalid input. Enter an integer.\n");
											int numSenior = checkInt(scan, "Enter number of Senior tickets ($7.50 each): ", "\nInvalid input. Enter an integer.\n");
											int numChild = checkInt(scan, "Enter number of Child tickets ($5.25 each): ", "\nInvalid input. Enter an integer.\n");
											if (numAdult == 0 && numSenior == 0 && numChild == 0) {
												System.out.println("No seats reserved.\n");
											}
											else {
												Order newOrder = takeOrder(scan, a2, 2, numAdult, numSenior, numChild); // read and validate user input for specific Seats
												if (newOrder.isAllAvailable()) { // reserve the seats if all are available
													newOrder.placeOrder();
													user.addOrder(newOrder);
													System.out.println("Order sucessfully placed!\n");
													System.out.println("Updated Auditorium 2:");
													a2.printAuditorium();
												}
												else { // not all the seats are available - proceed with best available
													System.out.println("Not all seats are available. Finding best available seats.\n");
													Order bestAvailable = findBestAvailable(a2, 2, numAdult, numSenior, numChild); // find the best available seats
													if (bestAvailable == null) // there are no best available seats
														System.out.println("There are no best available seats. Try again.\n");
													else {
														System.out.println("Best Available Seats:");
														bestAvailable.printSeats();
														String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
														if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
														{
															bestAvailable.placeOrder();
															user.addOrder(bestAvailable);
															System.out.println("Order sucessfully placed!\n");
															System.out.println("Updated Auditorium 2:");
															a2.printAuditorium();
														}
														else
															System.out.println("No seats reserved.\n"); // do not reserve best available seats
													}
												}
											}
										}
										else { // "3. Auditorium 3" was selected. Proceed with the same process as auditoriums 1 and 2 for reserving seats.
											System.out.println("Auditorium 3:");
											a3.printAuditorium();
											int numAdult = checkInt(scan, "Enter number of Adult tickets ($10 each): ", "\nInvalid input. Enter an integer.\n");
											int numSenior = checkInt(scan, "Enter number of Senior tickets ($7.50 each): ", "\nInvalid input. Enter an integer.\n");
											int numChild = checkInt(scan, "Enter number of Child tickets ($5.25 each): ", "\nInvalid input. Enter an integer.\n");
											if (numAdult == 0 && numSenior == 0 && numChild == 0) {
												System.out.println("No seats reserved.\n");
											}
											else {
												Order newOrder = takeOrder(scan, a3, 3, numAdult, numSenior, numChild); // read and validate user input for specific Seats
												if (newOrder.isAllAvailable()) { // reserve seats if all the seats are available
													newOrder.placeOrder();
													user.addOrder(newOrder);
													System.out.println("Order sucessfully placed!\n");
													System.out.println("Updated Auditorium 3:");
													a3.printAuditorium();
												}
												else { // not all the seats are available so find best available
													System.out.println("Not all seats are available. Finding best available seats.\n");
													Order bestAvailable = findBestAvailable(a3, 3, numAdult, numSenior, numChild); // find the best available seats
													if (bestAvailable == null) // there are no best available seats
														System.out.println("There are no best available seats. Try again.\n");
													else {
														System.out.println("Best Available Seats:");
														bestAvailable.printSeats();
														String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
														if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
														{
															bestAvailable.placeOrder();
															user.addOrder(bestAvailable);
															System.out.println("Order sucessfully placed!\n");
															System.out.println("Updated Auditorium 3:");
															a3.printAuditorium();
														}
														else
															System.out.println("No seats reserved.\n"); // do not reserve best available seats
													}
												}
											}
										}
										break;
									case 2: // "2. View Orders" was selected
										user.printOrders();
										break;
									case 3: // "3. Update Order" was selected
										user.printOrders();
										if (user.getOrders().size() != 0) {
											int orderNum = readInt(scan, "Enter the order number to update: ", "\nInvalid input. Enter an integer in the range of [1," + user.getOrders().size() + "].\n", 1, user.getOrders().size()); // read and validate user input for order menu
											System.out.println();
											int updateOrderOption = readInt(scan, updateOrderMenu, "\nInvalid input. Enter an integer in the range of [1,3].\n", 1, 3); // read and validate user input for update order menu
											System.out.println();
											Order currentOrder = user.getOrders().get(orderNum-1);
											switch (updateOrderOption) {
												case 1: // "1. Add tickets to order" was selected
													if (currentOrder.getAuditoriumNum() == 1) { // auditorium 1
														System.out.println("Auditorium 1:");
														a1.printAuditorium();
														int numAdult = checkInt(scan, "Enter number of Adult tickets ($10 each): ", "\nInvalid input. Enter an integer.\n");
														int numSenior = checkInt(scan, "Enter number of Senior tickets ($7.50 each): ", "\nInvalid input. Enter an integer.\n");
														int numChild = checkInt(scan, "Enter number of Child tickets ($5.25 each): ", "\nInvalid input. Enter an integer.\n");
														if (numAdult == 0 && numSenior == 0 && numChild == 0) {
															System.out.println("No seats reserved.\n");
														}
														else {
															Order newOrder = takeOrder(scan, a1, 1, numAdult, numSenior, numChild); // read and validate user input for specific Seats
															if (newOrder.isAllAvailable()) { // reserve seats if all are available
																newOrder.placeOrder();
																currentOrder.append(newOrder);
																System.out.println("Order sucessfully placed!\n");
																System.out.println("Updated Auditorium 1:");
																a1.printAuditorium();
															}
															else { // not all seats are available so find best available
																System.out.println("Not all seats are available. Finding best available seats.\n");
																Order bestAvailable = findBestAvailable(a1, 1, numAdult, numSenior, numChild); // find best available seats
																if (bestAvailable == null) // there are no best available seats
																	System.out.println("There are no best available seats. Try again.\n");
																else {
																	System.out.println("Best Available Seats:");
																	bestAvailable.printSeats();
																	String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
																	if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
																	{
																		bestAvailable.placeOrder();
																		currentOrder.append(bestAvailable);
																		System.out.println("Order sucessfully placed!\n");
																		System.out.println("Updated Auditorium 1:");
																		a1.printAuditorium();
																	}
																	else
																		System.out.println("Seats not reserved.\n"); // do not reserve best available seats
																}
															}
														}
													}
													else if (currentOrder.getAuditoriumNum() == 2) { // auditorium 2
														System.out.println("Auditorium 2:");
														a2.printAuditorium();
														int numAdult = checkInt(scan, "Enter number of Adult tickets ($10 each): ", "\nInvalid input. Enter an integer.\n");
														int numSenior = checkInt(scan, "Enter number of Senior tickets ($7.50 each): ", "\nInvalid input. Enter an integer.\n");
														int numChild = checkInt(scan, "Enter number of Child tickets ($5.25 each): ", "\nInvalid input. Enter an integer.\n");
														if (numAdult == 0 && numSenior == 0 && numChild == 0) {
															System.out.println("No seats reserved.\n");
														}
														else {
															Order newOrder = takeOrder(scan, a2, 2, numAdult, numSenior, numChild); // read and validate user input for specific Seats
															if (newOrder.isAllAvailable()) { // reserve seats if all seats are available
																newOrder.placeOrder();
																currentOrder.append(newOrder);
																System.out.println("Order sucessfully placed!\n");
																System.out.println("Updated Auditorium 2:");
																a2.printAuditorium();
															}
															else { // if all seats are not available then find best available
																System.out.println("Not all seats are available. Finding best available seats.\n");
																Order bestAvailable = findBestAvailable(a2, 2, numAdult, numSenior, numChild); // find best available
																if (bestAvailable == null) // there are no best available seats
																	System.out.println("There are no best available seats. Try again.\n");
																else {
																	System.out.println("Best Available Seats:");
																	bestAvailable.printSeats();
																	String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
																	if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
																	{
																		bestAvailable.placeOrder();
																		currentOrder.append(bestAvailable);
																		System.out.println("Order sucessfully placed!\n");
																		System.out.println("Updated Auditorium 2:");
																		a2.printAuditorium();
																	}
																	else
																		System.out.println("Seats not reserved.\n"); // do not reserve best available seats	
																}
															}
														}
													}
													else { // auditorium 3
														System.out.println("Auditorium 3:");
														a3.printAuditorium();
														int numAdult = checkInt(scan, "Enter number of Adult tickets ($10 each): ", "\nInvalid input. Enter an integer.\n");
														int numSenior = checkInt(scan, "Enter number of Senior tickets ($7.50 each): ", "\nInvalid input. Enter an integer.\n");
														int numChild = checkInt(scan, "Enter number of Child tickets ($5.25 each): ", "\nInvalid input. Enter an integer.\n");
														if (numAdult == 0 && numSenior == 0 && numChild == 0) {
															System.out.println("No seats reserved.\n");
														}
														else {
															Order newOrder = takeOrder(scan, a3, 3, numAdult, numSenior, numChild); // read and validate user input for specific Seats
															if (newOrder.isAllAvailable()) { // reserve seats if all the seats are available
																newOrder.placeOrder();
																currentOrder.append(newOrder);
																System.out.println("Order sucessfully placed!\n");
																System.out.println("Updated Auditorium 3:");
																a3.printAuditorium();
															}
															else { // find best available if all the seats are not available
																System.out.println("Not all seats are available. Finding best available seats.\n");
																Order bestAvailable = findBestAvailable(a3, 3, numAdult, numSenior, numChild); // find the best available seats
																if (bestAvailable == null) // there are no best available seats
																	System.out.println("There are no best available seats. Try again.\n");
																else {
																	System.out.println("Best Available Seats:");
																	bestAvailable.printSeats();
																	String answer = readString(scan, "Would you like to reserve these seats? (Y/N) ", "\nInvalid input. Enter a character. Either \'Y\' or \'N\'.\n", "Y", "N");
																	if (answer.equals("Y") || answer.equals("y")) // reserve best available seats
																	{
																		bestAvailable.placeOrder();
																		currentOrder.append(bestAvailable);
																		System.out.println("Order sucessfully placed!\n");
																		System.out.println("Updated Auditorium 3:");
																		a3.printAuditorium();
																	}
																	else
																		System.out.println("Seats not reserved.\n"); // do not reserve best available seats	
																}
															}
														}
													}
													break;
												case 2: // "2. Delete tickets from order" was selected
													while (true) {
														if (currentOrder.getSeats().size() == 0) { // there are not orders to delete tickets from
															System.out.println("There are no more seats in this order. Removing order.");
															user.getOrders().remove(orderNum-1);
															System.out.println("Order sucessfully removed.\n");
															break;
														}
														else {
															System.out.println("Seats in Order #" + orderNum + ":");
															currentOrder.printSeats();
															System.out.println((currentOrder.getSeats().size()+1) + ". Exit");
															int seatNum = readInt(scan, "Enter a seat number to remove or enter [" + (currentOrder.getSeats().size()+1) + "] to exit: ", "\nInvalid input. Enter an integer in the range of [1," + (currentOrder.getSeats().size()+1) + "].\n", 1, (currentOrder.getSeats().size()+1));
															System.out.println();
															if (seatNum == currentOrder.getSeats().size()+1) { // exit option was selected
																break;
															}
															else { // delete the specific seat entered
																Seat currentSeat = currentOrder.getSeats().get(seatNum-1);
																currentOrder.removeSeat(currentSeat);
																System.out.println("Seat sucessfully removed.\n");
															}
														}
													}
													break;
												case 3: // "3. Cancel Order" was selected
													while (currentOrder.getSeats().size() != 0)
														currentOrder.removeSeat(currentOrder.getSeats().get(0));
													user.getOrders().remove(orderNum-1);
													System.out.println("Order sucessfully removed.\n");
													break;
											}
										}
										break;
									case 4: // "4. Display Receipt" was selected
										System.out.println("Receipt: ");
										user.printReport();
										break;
									case 5: // "5. Logout" was selected
										logout = true;
										scan.nextLine();
										System.out.println("Logging out.\n");
										break;
								}
							}
						}
						
						// logout if logout or exit are true
						if (logout || exit)
							break;
					}
				}
				catch (InputMismatchException e) { // catch incorrect passwords
					errorCounter++;
					if (errorCounter == 3) { // start over if 3 incorrect passwords
						System.out.println("Attempt " + errorCounter + ": Username and password do not match. Start over.\n");
						break;
					}
					else { // else ask for another passwords
						System.out.println("Attempt " + errorCounter + ": Username and password do not match. Try again.\n");
						continue;
					}
				} // end catch
			} // end while loop for user
		} // end while loop for entire program
		
		scan.close();
		
	}
	/* END MAIN METHOD */
	
	
	/* BEGIN STATIC METHODS */
	// return a populated hashmap from information from "userdb.dat"
	public static HashMap<String, Customer> populateHashMap() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("userdb.dat"));
		HashMap<String, Customer> hm = new HashMap<String, Customer>();
		while (scan.hasNextLine()) {
			String[] info = scan.nextLine().split(" ");
			hm.put(info[0], new Customer(info[0], info[1])); // put <username, Customer>
		}
		scan.close();
		return hm;
	}
	
	// Read and validate that the input is within the range of [lowerbond, upperbound]
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
	
	// Read and validate that the input is an integer
	public static int checkInt(Scanner scan, String msg, String error) {
		int ans = -1;
		while (true) {
			System.out.print(msg);
			try {
				ans = scan.nextInt();
				break;
			}
			catch (InputMismatchException e){ // not an integer
				scan.nextLine();
				System.out.println(error);
				continue;
			}
		}
		return ans;
	}
	
	// Read and validate String inputs "Y" or "N"
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
	
	public static Order takeOrder(Scanner scan, Auditorium a, int audNum, int numAdult, int numSenior, int numChild) {
		Order newOrder = new Order(a, audNum, numAdult, numSenior, numChild);
		for (int i=0; i<numAdult; i++) {
			int row = checkInt(scan, "Enter row number for Adult Ticket #" + (i+1) + ": ", "\nInvalid input. Enter an integer.\n");
			int col = checkInt(scan, "Enter seat number for Adult Ticket #" + (i+1) + ": ", "\nInvalid input. Enter an integer.\n");
			newOrder.addSeat(new Seat(row, col, 'A'));
		}
		for (int i=0; i<numSenior; i++) {
			int row = checkInt(scan, "Enter row number for Senior Ticket #" + (i+1) + ": ", "\nInvalid input. Enter an integer.\n");
			int col = checkInt(scan, "Enter seat number for Senior Ticket #" + (i+1) + ": ", "\nInvalid input. Enter an integer.\n");
			newOrder.addSeat(new Seat(row, col, 'S'));
		}
		for (int i=0; i<numChild; i++) {
			int row = checkInt(scan, "Enter row number for Child Ticket #" + (i+1) + ": ", "\nInvalid input. Enter an integer.\n");
			int col = checkInt(scan, "Enter seat number for Child Ticket #" + (i+1) + ": ", "\nInvalid input. Enter an integer.\n");
			newOrder.addSeat(new Seat(row, col, 'C'));
		}
		return newOrder;
	}
	// print the formatted report
	public static void printReport(Auditorium a1, Auditorium a2, Auditorium a3) {
		DecimalFormat df = new DecimalFormat("0.00");
		int a1Open = a1.getOpenSeats();
		int a1Total = a1.getTotalReservedSeats();
		int a1Adult = a1.getAdultSeats();
		int a1Senior = a1.getSeniorSeats();
		int a1Child = a1.getChildSeats();
		double a1AdultPrice = a1Adult * ADULT_PRICE;
		double a1SeniorPrice = a1Senior * SENIOR_PRICE;
		double a1ChildPrice = a1Child * CHILD_PRICE;
		double a1TotalPrice = a1AdultPrice + a1SeniorPrice + a1ChildPrice;
		int a2Open = a2.getOpenSeats();
		int a2Total = a2.getTotalReservedSeats();
		int a2Adult = a2.getAdultSeats();
		int a2Senior = a2.getSeniorSeats();
		int a2Child = a2.getChildSeats();
		double a2AdultPrice = a2Adult * ADULT_PRICE;
		double a2SeniorPrice = a2Senior * SENIOR_PRICE;
		double a2ChildPrice = a2Child * CHILD_PRICE;
		double a2TotalPrice = a2AdultPrice + a2SeniorPrice + a2ChildPrice;
		int a3Open = a3.getOpenSeats();
		int a3Total = a3.getTotalReservedSeats();
		int a3Adult = a3.getAdultSeats();
		int a3Senior = a3.getSeniorSeats();
		int a3Child = a3.getChildSeats();
		double a3AdultPrice = a3Adult * ADULT_PRICE;
		double a3SeniorPrice = a3Senior * SENIOR_PRICE;
		double a3ChildPrice = a3Child * CHILD_PRICE;
		double a3TotalPrice = a3AdultPrice + a3SeniorPrice + a3ChildPrice;
		
		System.out.println("Report:");
		System.out.println("\t\tOpen Seats\tTotal Reserved Seats\tAdult Seats\tSenior Seats\tChild Seats\tTicket Sales");
		System.out.println("Auditorium 1\t" + a1Open + "\t\t" + a1Total + "\t\t\t" + a1Adult + "\t\t" + a1Senior + "\t\t" + a1Child + "\t\t$" + df.format(a1TotalPrice));
		System.out.println("Auditorium 2\t" + a2Open + "\t\t" + a2Total + "\t\t\t" + a2Adult + "\t\t" + a2Senior + "\t\t" + a2Child + "\t\t$" + df.format(a2TotalPrice));
		System.out.println("Auditorium 3\t" + a3Open + "\t\t" + a3Total + "\t\t\t" + a3Adult + "\t\t" + a3Senior + "\t\t" + a3Child + "\t\t$" + df.format(a3TotalPrice));
		System.out.print("Total\t\t" + (a1Open+a2Open+a3Open) + "\t\t" + (a1Total+a2Total+a3Total) + "\t\t\t" + (a1Adult+a2Adult+a3Adult) + "\t\t" + (a1Senior+a2Senior+a3Senior) + "\t\t" + (a1Child+a2Child+a3Child) + "\t\t$" + df.format(a1TotalPrice+a2TotalPrice+a3TotalPrice));
		System.out.println("\n");
	}
	
	// return an Order containing the best available seats
	public static Order findBestAvailable(Auditorium a, int audNum, int numAdult, int numSenior, int numChild) {
		int totalTickets = numAdult+numSenior+numChild;
		int index = a.findBestAvailableIndex(totalTickets); // index of the leftmost Seat of the best available seats
		
		if (index == -1) // there is no best available
			return null;
		else {
			Order newOrder = new Order(a, audNum, numAdult, numSenior, numChild);
			for (int i=0; i<numAdult; i++) // add Adult Seats to the Order
				newOrder.addSeat(new Seat(a.getAuditorium().get(index+i).getRow()+1, a.getAuditorium().get(index+i).getCol()+1, 'A'));
			for (int j=0; j<numSenior; j++) // add Senior Seats to the Order
				newOrder.addSeat(new Seat(a.getAuditorium().get(index+j+numAdult).getRow()+1, a.getAuditorium().get(index+j+numAdult).getCol()+1, 'S'));
			for (int k=0; k<numChild; k++) // add Child Seats to the Order
				newOrder.addSeat(new Seat(a.getAuditorium().get(index+k+numAdult+numSenior).getRow()+1, a.getAuditorium().get(index+k+numAdult+numSenior).getCol()+1, 'C'));
			
			return newOrder;
		}
		
	}
	/* END STATIC METHODS */
	
}
/* END MAIN CLASS */
