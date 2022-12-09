//Class name: Triangle
//Author: Shahar Schneider
//Date: 23/11/2019
//Purpose: A class for calculating the perimeter and area of a triangle
import java.util.Scanner;
public class Triangle
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the area "
            + "and the perimeter of a given triangle. ");
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        if(a > 0 && b > 0 && c > 0) //are all sides larger than 0
        {

            if(a < (b+c) && b < (a+c) && c < (a+b)) // are all sides smaller than the sum of the other 2
            {
                //calculate triangle perimeter
                int perimeter = a+b+c;
                System.out.println("The perimeter of the triangle is: " + perimeter);
                //calculating area according to Heron's formula
                double s = perimeter/2.0;
                double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
                System.out.println("The area of the triangle is: " + area);
            }
            else
            {
                System.out.println("The input: " + a + ", " + b + ", " + c + " cannot be processed.");
                System.out.println("Each side must be smaller than the sum of the other 2");
            }
        }
        else
        {
            System.out.println("The input: " + a + ", " + b + ", " + c + " cannot be processed.");
            System.out.println("All sides of the triangle must be larger than 0");
        }

    }
}