/* 
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 2
 * Disneyland Dining Rewards
 * Main class
 */

import Customer.*;
import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// Populate Customer[] with Customer information
		Customer[] customers = readCustomerFile();
		PreferredCustomer[] pCustomers = null;
		boolean exists = preferredCustomerExists();
		
		// Populate PreferredCustomer[] if it exists
		if (exists)
		{
			pCustomers = readPreferredCustomerFile();
			exists = true;
		}
		
		// Create Scanner for "orders.dat"
		Scanner scan = new Scanner(new File("orders.dat"));
		
		while (scan.hasNextLine())
		{	
			// Read orders
			int id = scan.nextInt();
			double radius = scan.nextDouble();
			double height = scan.nextDouble();
			double oz = scan.nextDouble();
			double ozPrice = scan.nextDouble();
			double sqInPrice = scan.nextDouble();
			int quantity = scan.nextInt();
			
			// Find index of specific Customer id in PreferredCustomer[]
			int index = -1;
			if (exists)
			{
				for (int i=0; i<pCustomers.length; i++)
				{
					if (id == pCustomers[i].getId())
					{
						index = i;
						break;
					}
				}
			}
			
			if (index != -1) // order is for a preferred customer
			{
				int discount = pCustomers[index].getDiscountPercentage();
				double price = getUnitPrice(radius, height, oz, ozPrice, sqInPrice, discount) * quantity;
				pCustomers[index].setAmountSpent(pCustomers[index].getAmountSpent() + price); // update amount spent
				
				// check if PreferredCustomer is eligible for a better discount
				if (pCustomers[index].getAmountSpent() > 350)
					pCustomers[index].setDiscountPercentage(10);
				else if (pCustomers[index].getAmountSpent() > 200)
					pCustomers[index].setDiscountPercentage(7);
			}
			else // order is not for a preferred customer (aka a regular customer)
			{
				// check if order is for an existing customer
				for (int i=0; i<customers.length; i++)
				{
					if (id == customers[i].getId())
					{
						index = i;
						break;
					}
				}
				
				if (index != -1) // order is for a customer
				{
					double price = getUnitPrice(radius, height, oz, ozPrice, sqInPrice) * quantity;
					customers[index].setAmountSpent(customers[index].getAmountSpent() + price); // update amount spent
					
					// check if Customer can be upgraded to PreferredCustomer
					if (customers[index].getAmountSpent() > 350)
					{
						if (!exists) // no other PreferredCustomers
						{
							pCustomers = newPreferredCustomer(customers[index], 10);
							exists = true;
						}
						else // PreferredCustomer[] exists
							pCustomers = addPreferredCustomer(pCustomers, customers[index], 10);
						customers = removeCustomer(customers, index);
					}
					else if (customers[index].getAmountSpent() > 200)
					{
						if (!exists) // no other PreferredCustomers
						{
							pCustomers = newPreferredCustomer(customers[index], 7);
							exists = true;
						}
						else // PreferredCustomer[] exists
							pCustomers = addPreferredCustomer(pCustomers, customers[index], 7);
						customers = removeCustomer(customers, index);
					}
					else if (customers[index].getAmountSpent() > 150)
					{
						if (!exists) // no other PreferredCustomers
						{
							pCustomers = newPreferredCustomer(customers[index], 5);
							exists = true;
						}
						else // PreferredCUstomers[] exists
							pCustomers = addPreferredCustomer(pCustomers, customers[index], 5);
						customers = removeCustomer(customers, index);
					}
				}
				else // order is not a customer
				{
					System.out.println("Specified customer does not exist.");
				}	
			}
		}
		
		// write back to files
		writeCustomerFile(customers);
		if (exists)
			writePreferredCustomerFile(pCustomers);
		
		// Close Scanner for "orders.dat"
		scan.close();
	}
	
	// Returns a Customer[] array containing Customer objects read from "customer.dat"
	public static Customer[] readCustomerFile() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("customer.dat"));
		Customer[] customers = new Customer[getNumberOfCustomers()]; // create Customer[] array
		int counter = 0;
		while (scan.hasNextLine())
		{
			int id = scan.nextInt();
			String firstName = scan.next();
			String lastName = scan.next();
			double amountSpent = scan.nextDouble();
			Customer c = new Customer(id, firstName, lastName, amountSpent); // create Customer object
			customers[counter] = c;
			counter++;
		}
		scan.close();
		return customers;
	}
	
	// Returns the number of Customers in "customer.dat"
	private static int getNumberOfCustomers() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("customer.dat"));
		int counter = 0;
		while (scan.hasNextLine())
		{
			scan.nextLine();
			counter++;
		}
		scan.close();
		return counter;
	}
	
	// Returns true if "preferred.dat" exists, false if it doesn't
	public static boolean preferredCustomerExists() throws FileNotFoundException
	{
		File f = new File("preferred.dat");
		return f.exists();
	}
	
	// Returns a PreferredCustomer[] array containing PreferredCustomer objects read from "preferred.dat"
	public static PreferredCustomer[] readPreferredCustomerFile() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("preferred.dat"));
		PreferredCustomer[] pCustomers = new PreferredCustomer[getNumberOfPreferredCustomers()]; // create PreferredCustomer[] array
		int counter = 0;
		while (scan.hasNextLine())
		{
			int id = scan.nextInt();
			String firstName = scan.next();
			String lastName = scan.next();
			double amountSpent = scan.nextDouble();
			String discountPercentage = scan.next();
			int discount = Integer.parseInt(discountPercentage.substring(0, discountPercentage.length()-1)); // parse for discount percentage
			PreferredCustomer pc = new PreferredCustomer(id, firstName, lastName, amountSpent, discount); // create PreferredCustomer
			pCustomers[counter] = pc;
			counter++;
		}
		scan.close();
		return pCustomers;
	}
	
	// Returns the number of PreferredCustomers in "preferred.dat"
	private static int getNumberOfPreferredCustomers() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("preferred.dat"));
		int counter = 0;
		while (scan.hasNextLine())
		{
			scan.nextLine();
			counter++;
		}
		scan.close();
		return counter;
	}
	
	// Return the total price of one drink based on given parameters
	public static double getUnitPrice(double r, double h, double oz, double ozPrice, double squreInchPrice)
	{
		return getLiquidPrice(oz, ozPrice) + getPersonalizationPrice(r, h, squreInchPrice);
	}
	
	// Return the total price of one drink based on given parameters with a discount
	public static double getUnitPrice(double r, double h, double oz, double ozPrice, double squreInchPrice, int discount)
	{
		double d = 1.0 - (discount/100.0);
		return d * (getLiquidPrice(oz, ozPrice) + getPersonalizationPrice(r, h, squreInchPrice));
	}
	
	// Return the price of drink liquid
	public static double getLiquidPrice(double oz, double ozPrice)
	{
		return (oz * ozPrice);
	}
	
	// Return the price of the personalization
	public static double getPersonalizationPrice(double r, double h, double squareInchPrice)
	{
		double surfaceArea = (2 * Math.PI * r * h) + (2 * Math.PI * r * r);
		return (surfaceArea * squareInchPrice);
	}
	
	// Add the upgraded Customer to a new PreferredCustomer[] array and return the new array
	public static PreferredCustomer[] newPreferredCustomer(Customer c, int discount)
	{
		PreferredCustomer[] newPC = new PreferredCustomer[1];
		newPC[0] = new PreferredCustomer(c.getId(), c.getFirstName(), c.getLastName(), c.getAmountSpent(), discount);
		return newPC;
	}
	
	// Add the upgraded Customer to an existing PreferredCusomter[] array and return the new array
	public static PreferredCustomer[] addPreferredCustomer(PreferredCustomer[] pc, Customer c, int discount)
	{
		PreferredCustomer[] newPCArray = new PreferredCustomer[pc.length+1];
		for (int i=0; i<pc.length; i++)
			newPCArray[i] = pc[i];
		PreferredCustomer newPC = new PreferredCustomer(c.getId(), c.getFirstName(), c.getLastName(), c.getAmountSpent(), discount);
		newPCArray[newPCArray.length-1] = newPC; // add new upgraded PreferredCustomer to the end of the new array
		return newPCArray;
	}
	
	// Remove the Customer at given index from Customer[] array and return the new array
	public static Customer[] removeCustomer(Customer[] ca, int index)
	{
		Customer[] newCA = new Customer[ca.length-1];
		for (int i=0; i<index; i++) // all the Customers from indices 0 to index
			newCA[i] = ca[i];
		for (int j=index+1; j<ca.length; j++) // all the Customers from indices index to the end
			newCA[j-1] = ca[j];
		return newCA;
	}
	
	// Write Customer[] array to "customer.dat"
	public static void writeCustomerFile(Customer[] c) throws IOException
	{
		DecimalFormat df = new DecimalFormat("0.00"); // correct format for money
		BufferedWriter bw = new BufferedWriter(new FileWriter("customer.dat"));
		for (int i=0; i<c.length; i++)
		{
			Customer customer = c[i];
			if (i != 0)
				bw.write("\n");
			bw.write(customer.getId() + " " + customer.getFirstName() + " " + customer.getLastName() + " " + df.format(customer.getAmountSpent()));
		}
		bw.close();
	}
	
	// Write PreferredCustomer[] array to "preferred.dat"
	public static void writePreferredCustomerFile(PreferredCustomer[] pc) throws IOException
	{
		DecimalFormat df = new DecimalFormat("0.00"); // correct format for money
		BufferedWriter bw = new BufferedWriter(new FileWriter("preferred.dat"));
		for (int i=0; i<pc.length; i++)
		{
			PreferredCustomer pCustomer = pc[i];
			if (i != 0)
				bw.write("\n");
			bw.write(pCustomer.getId() + " " + pCustomer.getFirstName() + " " + pCustomer.getLastName() + " " + df.format(pCustomer.getAmountSpent()) + " " + pCustomer.getDiscountPercentage() + "%");
		}
		bw.close();
	}	
}