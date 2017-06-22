// Victor Mao (vtm160030) and Moi Banerjee (mxb162730)
// Computer Science 2
// Jason Smith
// In Class Assignment 4

import java.util.Scanner;

public class inclassfour {
	
	public static void main(String[] args) throws NegativeNumberException
	{
		int[] a = new int[] {1234, 2345, 3456, 4567, 5678, 6789, 7890, 98765, 112233, 97531};
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int num = scan.nextInt();
		
		try
		{
			if (num < 0)
			{
				throw new NegativeNumberException(num);
			}
			else
			{
				int index = search(a, 0, num);
				if (index == -1) // not found
				{
					System.out.println(reverse("", num));
				}
				else
				{
					intToBin("", num);
				}
			}
		}
		catch (NegativeNumberException e)
		{
			System.out.println(e);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e);
		}
		
		
		scan.close();
	}
	
	// recursively search through an array
	public static int search(int[] a, int index, int num)
	{
		if (index >= a.length)
			return -1;
		else if (a[index] == num)
			return index;
		else
		{
			index++;
			return search(a, index, num);
		}
	}
	
	// recursively convert integer to binary
	public static void intToBin(String bin, int num)
	{
		if (num == 0)
		{
			System.out.println(bin);
			return;
		}
		else
		{
			bin = (num%2)+bin;
			intToBin(bin, num/2);
		}
	}
	
	// recursively reverse a string
	public static String reverse(String current, int num)
	{
		if (num == 0)
		{
			return current;
		}
		else
		{
			current = current + (num%10);
			return reverse(current, num/10);
		}
	}
	
	// NegativeNumberException class
	public static class NegativeNumberException extends Exception
	{
		private int num;
		
		public NegativeNumberException(int n)
		{
			super("Not valid: " + n + " is a negative number");
			num = n;
		}
	}
}


