/* Victor Mao (vtm160030) + Neel Reddy (nbr150130)
 * CS 2336.003 In-Class Assignment 1
 * Invoking System.currentTimeMillis() returns the elapsed time in milliseconds since midnight of January 1, 1970.
 * Write a program that displays the date and time based on the number of milliseconds returned by the function.
 */

public class CurrentDateAndTime
{
	public static void main (String[] args)
	{
		double time = System.currentTimeMillis();
		//System.out.println("Total milliseconds: " + time);
		
		// calculate times using conversations
		double days = time/1000/60/60/24;
		double hours = (days - (long)days) * 24;
		double minutes = (hours - (long)hours) * 60;
		double seconds = (minutes - (long)minutes) * 60;
		
		//System.out.println("Days: " + days);
		//System.out.println("Hours: " + hours);
		//System.out.println("Minutes: " + minutes);
		//System.out.println("Seconds: " + seconds);
		
		// while loop to find year and days left in current year
		int year = 1970;
		long daysLeft = (long)days;
		while (daysLeft>365)
		{
			year++;
			if (year % 4 == 0) //leap year
				daysLeft-=366;
			else //not a leap year
				daysLeft-=365;
		}
		
		//System.out.println("Year: " + year);
		//System.out.println("Days Left: " + daysLeft);
		
		// check if leap year
		int leapYear = 0;
		if (year % 4 == 0)
			leapYear = 1;
		
		System.out.print("Current date and time is ");
		
		// print correct month
		if (daysLeft < 31)
			System.out.print("January");
		else if (daysLeft < 31+28+leapYear) // if year is a leap year, then leapYear=1 (add 1 day) else leapYear=0 (add 0 days)
			System.out.print("February");
		else if (daysLeft < 31+28+leapYear+31)
			System.out.print("March");
		else if (daysLeft < 31+28+leapYear+31+30)
			System.out.print("April");
		else if (daysLeft < 31+28+leapYear+31+30+31)
			System.out.print("May");
		else if (daysLeft < 31+28+leapYear+31+30+31+30)
			System.out.print("June");
		else if (daysLeft < 31+28+leapYear+31+30+31+30+31)
			System.out.print("July");
		else if (daysLeft < 31+28+leapYear+31+30+31+30+31+31)
			System.out.print("August");
		else if (daysLeft < 31+28+leapYear+31+30+31+30+31+31+30)
			System.out.print("September");
		else if (daysLeft < 31+28+leapYear+31+30+31+30+31+31+30+31)
			System.out.print("October");
		else if (daysLeft < 31+28+leapYear+31+30+31+30+31+31+30+31+30)
			System.out.print("November");
		else if (daysLeft < 31+28+leapYear+31+30+31+30+31+31+30+31+30+31)
			System.out.print("December");
		
		// print answer
		System.out.println(" " + (1+daysLeft) + ", " + year + " " + (long)hours + ":" + (long)minutes + ":" + (long)seconds + " GMT");
		
	}
}