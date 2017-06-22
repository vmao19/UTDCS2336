/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Computer Science 2 Homework 4
 * Time Base class
 */

package time;

public abstract class Time
{
	private int hours;
	private int minutes;
	private int seconds;
	
	public Time (int h, int m, int s)
	{
		hours = h;
		minutes = m;
		seconds = s;
	}
	
	// Getters
	public int getHours()
	{
		return hours;
	}
	public int getMinutes()
	{
		return minutes;
	}
	public int getSeconds()
	{
		return seconds;
	}
	
	// Setters
	public void setHours(int h)
	{
		hours = h;
	}
	public void setMinutes(int m)
	{
		minutes = m;
	}
	public void setSeconds(int s)
	{
		seconds = s;
	}
}