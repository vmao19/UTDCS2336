/* Victor Mao (vtm160030)
 * CS 2336.003 HW 2 Problem 2
 * #6.31 - pgs. 241-2
 * Verify credit card numbers using the Luhn check aka Mod 10 check.
 */

import java.util.Scanner;

public class CreditCardCheck
{
	public static void main (String[] args)
	{
		// Initialize Scanner
		Scanner scan = new Scanner(System.in);
		
		// Get User Input
		System.out.print("Enter a credit card number as a long integer: ");
		long number = scan.nextLong();
		
		// Check if credit card is valid and print answer
		if (isValid(number))
			System.out.println(number + " is valid");
		else
			System.out.println(number + " is invalid");
		
		// Close Scanner
		scan.close();
	}
	
	/** Return true if the card number is valid */
	public static boolean isValid(long number)
	{
		// Check if the credit card number is between 13 and 16 numbers long
		if (getSize(number) < 13 || getSize(number) > 16)
			return false;
		
		// Check if prefixes are valid
		long prefixOne = getPrefix(number, 1);
		long prefixTwo = getPrefix(number, 2);
		if (prefixOne == 4 || prefixOne == 5 || prefixTwo == 37 || prefixOne == 6)
		{
			// Proceed with Mod 10 check
			int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
			if (sum % 10 == 0)
				return true;
		}
		
		// If fails Mod 10 check, then return false
		return false;
	}
	
	/** Get result from step 2*/
	public static int sumOfDoubleEvenPlace(long number)
	{
		String numberString = number + "";
		int size = getSize(number);
		int sum = 0;
		
		int place = 0;
		if (size%2 == 0) // number has an even number of digits
			place = 0;
		else // number has an odd number of digits so start one digit over
			place = 1;
		
		// add up even-place digits according to step 2
		while (place < size)
		{
			sum += getDigit(Integer.parseInt(numberString.charAt(place) + "")*2);
			place += 2;
		}
		
		return sum;
	}
	
	/** Return sum of odd-place digits in number */
	public static int sumOfOddPlace(long number)
	{
		String numberString = number + "";
		int size = getSize(number);
		int sum = 0;
		
		int place = 0;
		if (size%2 == 0) // number has an even number of digits so start one digit over
			place = 1;
		else // number has an odd number of digits
			place = 0;
		
		// add up odd-place digits according to step 3
		while (place < size)
		{
			sum += Integer.parseInt(numberString.charAt(place) + "");
			place += 2;
		}
		
		return sum;
	}
	
	/** Return the number of digits in d */
	public static int getSize(long d)
	{
		String dString = d + "";
		return dString.length();
	}
	
	/** Return this number if it is a single digit, otherwise,
	 * return the sum of the two digits */
	public static int getDigit(int number)
	{
		if (getSize(number) == 1) // number is a single digit
			return number;
		else // number is two digits, return the sum of the two digits
			return (number/10)+(number%10);
	}
	
	/** Return the first k number of digits from number.
	 * If the number of digits in number is less than k, return number. */
	public static long getPrefix(long number, int k)
	{
		if (getSize(number) < k) // number of digits in number is less than k
			return number;
		// else return the first k number of digits of number
		String numberString = number + "";
		String sub = numberString.substring(0, k);
		return Long.parseLong(sub);
	}
}