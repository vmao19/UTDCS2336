/* 
 * Victor Mao (vtm160030)
 * Computer Science 2 Homework 1
 * #2.21 - pg. 74
 * Write a program that reads in an investment amount, annual interest rate,
 * and number of years, and displays the future investment value using the following formula:
 * futureInvestmentValue = investmentAmount * (1 + monthlyInterestRate) ^ (numberOfYears*12)
 */

import java.util.Scanner;

public class FutureInvestmentValue
{
	public static void main(String[] args)
	{
		// Initialize Scanner
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter investment amount: ");
		double investmentAmount = scan.nextDouble();
		
		System.out.print("Enter annual interest rate in percentage: " );
		double annualInterestRate = scan.nextDouble()/100; // Divide by 100 to change percentage into decimal
		double monthlyInterestRate = annualInterestRate/12; // Divide by 12 because 12 months in a year
		
		System.out.print("Enter number of years: ");
		int numberOfYears = scan.nextInt();
		
		// Equation: futureInvestmentValue = investmentAmount * (1 + monthlyInterestRate) ^ (numberOfYears*12)
		double futureInvestmentRate = investmentAmount * Math.pow(1+monthlyInterestRate, numberOfYears*12);
		System.out.print("Accumlated Value is $" + (int)(futureInvestmentRate*100)/100.0); // Prints only two places after the decimal point
		
		// Close Scanner
		scan.close();
	}
}
