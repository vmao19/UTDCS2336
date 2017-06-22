/* 
 * Victor Mao (vtm160030)
 * Computer Science 2 Homework 1
 * #4.23 - pg. 156
 * Write a program that reads the following information from a file and prints a payroll statement.
 * For each employee, all of his/her information will be listed on one line of the file separated by spaces.
 * Employee’s name (e.g., Smith)
 * Number of hours worked in a week (e.g., 10)
 * Hourly pay rate (e.g., 9.75)
 * Federal tax withholding rate (e.g., 20%)
 * State tax withholding rate (e.g., 9%)
 */

import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Payroll
{
	public static void main (String[] args) throws IOException
	{
		// Initialize Scanner
		Scanner scan = new Scanner(new File("employees.txt"));System.out.println();
		
		// Initialize Decimal Formatter for money decimals
		DecimalFormat df = new DecimalFormat(".00");
		
		// Read input from employees.txt
		while (scan.hasNextLine())
		{
			// Split the input at each space
			String[] info = scan.nextLine().split(" ");
			
			// Store data into variables
			String name = info[0];
			double weeklyHours = Double.parseDouble(info[1]);
			double hourlyPayRate = Double.parseDouble(info[2]);
			double federalTaxWithholdingRate = Double.parseDouble(info[3]);
			double stateTaxWithholdingRate = Double.parseDouble(info[4]);
			
			// Calculate pay and deductions
			double grossPay = weeklyHours*hourlyPayRate;
			double federalDeduction = grossPay*federalTaxWithholdingRate;
			double stateDeduction = grossPay*stateTaxWithholdingRate;
			double totalDeductions = federalDeduction+stateDeduction;
			double netPay = grossPay-totalDeductions;
			
			// Print output
			System.out.println("Employee Name: " + name);
			System.out.println("Hours Worked: " + weeklyHours);
			System.out.println("Hourly Pay Rate: $" + df.format(hourlyPayRate));
			System.out.println("Gross Pay: $" + df.format(grossPay));
			System.out.println("Deductions: ");
			System.out.println("\tFederal Withholding (" + (federalTaxWithholdingRate*100) + "%): $" + df.format(federalDeduction));
			System.out.println("\tState Withholding (" + (stateTaxWithholdingRate*100) + "%): $" + df.format(stateDeduction));
			System.out.println("\tTotal Deduction: $" + df.format(totalDeductions));
			System.out.println("Net Pay: $" + df.format(netPay));
			
			// Empty line between employees
			System.out.println();
		}
		
		// Close Scanner
		scan.close();
	}
}