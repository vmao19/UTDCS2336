/* Victor Mao (vtm160030)
 * CS 2336.003 HW 3 Problem 2
 * #10.13, pg. 403
 * Tester class for MyRectangle2D
 */

public class RectangleTest
{
	public static void main(String[] args)
	{
		// Initialize new MyRectangle2D
		MyRectangle2D rect = new MyRectangle2D(2, 2, 5.5, 4.9);
		
		// Print test information about rect
		System.out.println(rect.toString());
		System.out.println("Area of rectangle is: " + rect.getArea());
		System.out.println("Perimeter of rectangle is: " + rect.getPerimeter());
		System.out.println("Rectangle contains point (3,3)? " + rect.contains(3,3));
		System.out.println("Rectangle contains rectangle centered at (4,5) with dimensions 10.5x3.2? " + rect.contains(new MyRectangle2D(4, 5, 10.5, 3.2)));
		System.out.println("Rectangle overlaps rectangle centered at (3,5) with dimensions 2.3x5.4? " + rect.overlaps(new MyRectangle2D(3, 5, 2.3, 5.4)));
	}
}