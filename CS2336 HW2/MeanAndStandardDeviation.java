/* Victor Mao (vtm160030)
 * CS 2336.003 HW 2 Problem 1
 * #5.45 - pgs. 199-200
 * Compute the mean and the standard deviation of 10 numbers.
 */

import java.util.Scanner;

public class MeanAndStandardDeviation
{
	public static void main(String[] args)
	{
		// Initialize Scanner
		Scanner scan = new Scanner(System.in);
		
		// Prompt user for input
		System.out.print("Enter ten numbers: ");
		
		// Initialize variables
		double sum = 0;
		double sumOfSquares = 0;
		
		// Read user input and calculate sum and sumOfSquares
		for (int count = 0; count < 10; count++)
		{
			double number = scan.nextDouble();
			sum += number;
			sumOfSquares += Math.pow(number, 2);
		}
		
		// Calculate mean and standard deviation
		double mean = sum/10;
		double standardDeviation = Math.pow((sumOfSquares - (Math.pow(sum, 2)/10))/9, 0.5);
		
		// Print answers
		System.out.println("The mean is " + mean);
		System.out.println("The standard deviation is " + standardDeviation);
		
		// Close Scanner
		scan.close();
	}
}