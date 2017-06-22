/* Victor Mao (vtm160030)
 * CS 2336.003 HW 3 Problem 2
 * #10.13, pg. 403
 * MyRectangle2D Class
 */

public class MyRectangle2D
{
	// Define private variables
	private double x;
	private double y;
	private double width;
	private double height;
	
	// Default constructor
	public MyRectangle2D()
	{
		x = 0;
		y = 0;
		width = 1;
		height = 1;
	}
	
	// Constructor with parameters
	public MyRectangle2D(double newX, double newY, double newWidth, double newHeight)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
	}
	
	// Return area of rectangle
	public double getArea()
	{
		return width*height;
	}
	
	// Return perimeter of rectangle
	public double getPerimeter()
	{
		return width+width+height+height;
	}
	
	// Check if the rectangle contains point (x,y)
	public boolean contains(double x, double y)
	{
		if ((x>x-(width/2) && (x<x+(width/2))) && (y>y-(height/2) && (y<y+(height/2))))
				return true;
		return false;
	}
	
	// Check if the rectangle contains MyRectangle2D r
	public boolean contains(MyRectangle2D r)
	{
		double bottom = y-(height/2);
		double top = y+(height/2);
		double left = x-(width/2);
		double right = x+(width/2);
		
		double rBottom = r.getY()-(r.getHeight()/2);
		double rTop = r.getY()+(r.getHeight()/2);
		double rLeft = r.getX()-(r.getWidth()/2);
		double rRight = r.getX()+(r.getWidth()/2);
		
		if (rBottom>bottom && rTop<top && rLeft>left && rRight<right)
			return true;
		return false;
	}
	
	// Check if the rectangle overlaps with MyRectangle2D r
	public boolean overlaps(MyRectangle2D r)
	{
		double bottom = y-(height/2);
		double top = y+(height/2);
		double left = x-(width/2);
		double right = x+(width/2);
		
		double rBottom = r.getY()-(r.getHeight()/2);
		double rTop = r.getY()+(r.getHeight()/2);
		double rLeft = r.getX()-(r.getWidth()/2);
		double rRight = r.getX()+(r.getWidth()/2);
		
		if ((rTop > top && rBottom < top) || (rBottom < bottom && rTop > bottom) || (rTop < top && rBottom > bottom)) // top and bottom of r overlap
		{
			if ((rLeft < left && rRight > left) || (rRight > right && rLeft < right) || (rLeft > left && rRight < right)) // left and right of r overlap
				return true;
		}
		return false;
	}
	
	// Variable getters
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	// Variable setters
	public void setX(double newX)
	{
		x = newX;
	}
	
	public void setY(double newY)
	{
		y = newY;
	}
	
	public void setWidth(double newWidth)
	{
		width = newWidth;
	}
	
	public void setHeight(double newHeight)
	{
		height = newHeight;
	}
	
	// Return a string with vital information
	public String toString()
	{
		return "Rectangle is centered at (" + x + "," + y + ") with dimensions " + width + "x" + height;
	}
}