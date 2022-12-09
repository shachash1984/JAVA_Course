import java.util.Scanner;
public class Rect
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter rectangle width");
        int width = scan.nextInt();
        System.out.println("Please enter rectangle height");
        int height = scan.nextInt();
        System.out.println("Rectangle perimeter is: " + ((width+height)*2));
        System.out.println("Rectangle area is: " + (width*height));
        
    }
}