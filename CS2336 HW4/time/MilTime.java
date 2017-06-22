/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Computer Science 2 Homework 4
 * MilTime class derived from Time class
 */

package time;

public class MilTime extends Time
{
	private int milHours;
	
	// Overloaded Constructor
	public MilTime(int mh, int s)
	{
		super(mh/100, mh%100, s);
		
		// Data validation for military time (0 <= mh <= 2359)
		if (mh > 2359)
			mh = 2359;
		else if (mh < 0)
			mh = 0;
		
		milHours = mh;
		int hours = mh/100;
		int minutes = mh%100;
		
		// Adjust military time to standard time
		if (hours > 12)
		{
			hours -= 12;
			super.setHours(hours);
		}
		
		// Data validation for minutes (0 <= min <= 59)
		if (minutes > 60)
		{
			minutes = 59;
			super.setMinutes(minutes);
			milHours = (hours*100)+minutes; // adjust this.milHours
		}
		
		// Data validation for seconds (0 <= s <= 59)
		if (s < 0)
		{
			s = 0;
			super.setSeconds(s);
		}
		else if (s > 59)
		{
			s = 59;
			super.setSeconds(s);
		}
	}
	
	// Update time
	public void setTime(int mh, int s)
	{
		// Data validation for military time (0 <= mh <= 2359)
		if (mh > 2359)
			mh = 2359;
		else if (mh < 0)
			mh = 0;
		
		milHours = mh;
		int hours = mh/100;
		int minutes = mh%100;
		
		// Adjust military time to standard time
		if (hours > 12)
			hours -= 12;
		
		// Data validation for minutes (0 <= min <= 59)
		if (minutes > 60)
		{
			minutes = 59;
			milHours = (hours*100)+minutes; // adjust this.milHours
		}
		
		// Data validation for seconds (0 <= s <= 59)
		if (s < 0)
			s = 0;
		else if (s > 59)
			s = 59;
		
		// Update time
		super.setHours(hours);
		super.setMinutes(minutes);
		super.setSeconds(s);
	}
	
	// Return hours in standard format
	public int getStandHr()
	{
		return super.getHours();
	}
	
	// Getter
	@Override
	public int getHours()
	{
		return milHours/100;
	}
	
	// Setter
	@Override
	public void setHours(int milH)
	{
		milHours = milH;
		
		int hours = milH/100;

		// Adjust military time to standard time
		if (hours > 12)
			hours -= 12;
		super.setHours(hours);
	}
}