//Class name: Congruent
//Author: Shahar Schneider
//Date: 23/11/2019
//Purpose: A class for checking to see if 2 triangles in 2D space are congruent
import java.util.Scanner;
public class Congruent
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("This program checks to see if 2 triangles are congruent");    
        System.out.println("Please enter coordinates in 2D space for two triangles using real numbers.\nFirst x1: ");
        double x11 = scan.nextDouble();

        System.out.println("First y1: ");
        double y11 = scan.nextDouble();

        System.out.println("First x2: ");
        double x12 = scan.nextDouble();

        System.out.println("First y2: ");
        double y12 = scan.nextDouble();

        System.out.println("First x3: ");
        double x13 = scan.nextDouble();

        System.out.println("First y3: ");
        double y13 = scan.nextDouble();

        System.out.println("Second x1: ");
        double x21 = scan.nextDouble();

        System.out.println("Second y1: ");
        double y21 = scan.nextDouble();

        System.out.println("Second x2: ");
        double x22 = scan.nextDouble();

        System.out.println("Second y2: ");
        double y22 = scan.nextDouble();

        System.out.println("Second x3: ");
        double x23 = scan.nextDouble();

        System.out.println("Second y3: ");
        double y23 = scan.nextDouble();

        //calculating edge length
        double a1 = Math.sqrt(Math.pow((x11 - x12),2) + Math.pow((y11 - y12),2));
        double a2 = Math.sqrt(Math.pow((x12 - x13),2) + Math.pow((y12 - y13),2));
        double a3 = Math.sqrt(Math.pow((x13 - x11),2) + Math.pow((y13 - y11),2));

        double b1 = Math.sqrt(Math.pow((x21 - x22),2) + Math.pow((y21 - y22),2));
        double b2 = Math.sqrt(Math.pow((x22 - x23),2) + Math.pow((y22 - y23),2));   
        double b3 = Math.sqrt(Math.pow((x23 - x21),2) + Math.pow((y23 - y21),2));

        boolean congruent = false;

        //check if perimeter of the 2 triangles is identicle
        boolean equalPerimeter = (a1+a2+a3) == (b1+b2+b3);

        if(equalPerimeter)//if the perimeter is equal
        {
            // and if one pair of sides is equal
            if(a1==b1) 
            {
                if(a2==b2 || a2==b3) //and if a second pair is equal
                {
                    congruent = true;
                }
            }
            else if(a1==b2)
            {
                if(a2==b1 || a2==b3) //and if a second pair is equal
                {
                    congruent = true;
                }
            }
            else if(a1==b3)
            {
                if(a2==b1 || a2==b2) //and if a second pair is equal
                {
                    congruent = true;
                }
            }
        }
        
        System.out.println("The first triangle is ("+x11+", "+y11+") ("+x12+", "+y12+") ("+x13+", "+y13+").\nIts lengths are "+
        a1+", "+a2+", "+a3+".");
        
        System.out.println("The second triangle is ("+x21+", "+y21+") ("+x22+", "+y22+") ("+x23+", "+y23+").\nIts lengths are "+
        b1+", "+b2+", "+b3+".");
        
        if(congruent)
        {
            System.out.println("The triangles are congruent.");
        }
        else
        {
            System.out.println("The triangles are not congruent.");
        }
    }
}
