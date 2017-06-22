/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Computer Science 2 Homework 4
 * TimeClock class derived from MilTime class derived from Time class
 */

package time;

public class TimeClock extends MilTime
{
	private int milHours;
	private int seconds;
	
	// Overloaded constructor
	public TimeClock (int startMH, int startS, int endMH, int endS)
	{
		super(startMH, startS);
		
		// Data validation for ending military time (0 <= endMH <= 2359)
		if (endMH > 2359)
			endMH = 2359;
		else if (endMH < 0)
			endMH = 0;
		milHours = endMH;
		
		int hours = endMH/100;
		int minutes = endMH%100;
		
		// Data validation for minutes (0 <= minutes <= 59)
		if (minutes > 60)
		{
			minutes = 59;
			milHours = (hours*100)+minutes; // adjust this.milHours
		}
		
		// Data validation for endS (0 <= endS <= 59)
		if (endS < 0)
			endS = 0;
		else if (endS > 59)
			endS = 59;
		seconds = endS;
	}
	
	// Return the hour difference between starting and ending times
	public int timeDifference()
	{
		int hr = this.milHours/100;
		return Math.abs(super.getHours() - hr);
	}
	
	// Getters
	public int getMilHour()
	{
		return milHours;
	}
	public int getEndSeconds()
	{
		return seconds;
	}
	
	// Setters
	public void setMilHour(int newMH)
	{
		milHours = newMH;
	}
	public void setEndSeconds(int newS)
	{
		seconds = newS;
	}
}