/* 
 * Victor Mao (vtm160030)
 * Computer Science 2 Homework 1
 * #3.34 - pg. 118
 * Revise Programming Exercise 3.32 to test whether a point is on a line segment.
 * Write a program that prompts the user to enter the three points for p0, p1,
 * and p2 and displays whether p2 is on the line segment from p0 to p1.
 */

import java.util.Scanner;

public class OnLineSegmentTest
{
	public static void main(String[] args)
	{
		// Initialize Scanner
		Scanner scan = new Scanner(System.in);
		
		// Read input
		System.out.print("Enter three points for p0, p1, and p2: ");
		double x0 = scan.nextDouble();
		double y0 = scan.nextDouble();
		double x1 = scan.nextDouble();
		double y1 = scan.nextDouble();
		double x2 = scan.nextDouble();
		double y2 = scan.nextDouble();
		
		// Variable onLine is true if p0 is on the line segment between p1 and p2
		boolean onLine = false;
		if (((x2 > x0) && (x2 < x1)) || ((x2 > x1) && (x2 < x0))) // x0 is between x1 and x2
		{
			if (((y2 > y0) && (y2 < y1) || (y2 > y1) && (y2 < y0))) // y0 is between y1 and y2
			{
				if ((x1 - x0)*(y2 - y0) - (x2 - x0)*(y1 - y0) == 0) // check if point is on the line segment
					onLine = true;
			}
		}
		
		// Print output 
		if (onLine) // onLine is true
			System.out.println("p2(" + x2 + ", " + y2 + ") is on the line segment from p0(" + x0 + ", " + y0 + ") to p1(" + x1 + ", " + y1 + ")");
		else // onLine is false
			System.out.println("p2(" + x2 + ", " + y2 + ") is not on the line segment from p0(" + x0 + ", " + y0 + ") to p1(" + x1 + ", " + y1 + ")");
		
		// Close Scanner
		scan.close();
		
	}
}
