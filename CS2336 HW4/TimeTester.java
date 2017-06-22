/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Computer Science 2 Homework 4
 * TimeTester main class
 */

// Import time package
import time.*;

public class TimeTester
{
	public static void main(String[] args)
	{
		// Test MilTime class
		MilTime mt = new MilTime(200, 3);
		System.out.println("Original MilTime: " + mt.getHours() + " hrs, " + mt.getMinutes() + " mins, " + mt.getSeconds() + " secs");
		
		System.out.println();
		
		mt.setTime(1300, 45);
		System.out.println("Adjusted MilTime: " + mt.getHours() + " hrs, " + mt.getMinutes() + " mins, " + mt.getSeconds() + " secs");
		System.out.println("Adjusted MilTime in standard format: " + mt.getStandHr() + " hrs, " + mt.getMinutes() + " mins, " + mt.getSeconds() + " secs");
		
		mt.setHours(1400);
		System.out.println("Adjusted MilTime: " + mt.getHours() + " hrs, " + mt.getMinutes() + " mins, " + mt.getSeconds() + " secs");
		System.out.println("Adjusted MilTime in standard format: " + mt.getStandHr() + " hrs, " + mt.getMinutes() + " mins, " + mt.getSeconds() + " secs");
		
		System.out.println();
		
		// Test TimeClock class
		TimeClock tc = new TimeClock(123, 4, 1234, 5);
		System.out.println("Original Starting time: " + tc.getHours() + " hrs, " + tc.getMinutes() + " mins, " + tc.getSeconds() + " secs");
		System.out.println("Original Ending time: " + (tc.getMilHour()/100) + " hrs, " + (tc.getMilHour()%100) + " mins, " + tc.getEndSeconds() + " secs");
		System.out.println("Original Time difference: " + tc.timeDifference());
		
		System.out.println();
		
		tc.setMilHour(2345);
		tc.setEndSeconds(6);
		System.out.println("Adjusted Starting time: " + tc.getHours() + " hrs, " + tc.getMinutes() + " mins, " + tc.getSeconds() + " secs");
		System.out.println("Adjusted Ending time: " + (tc.getMilHour()/100) + " hrs, " + (tc.getMilHour()%100) + " mins, " + tc.getEndSeconds() + " secs");
		System.out.println("Adjusted Time difference: " + tc.timeDifference());
		
	}
}