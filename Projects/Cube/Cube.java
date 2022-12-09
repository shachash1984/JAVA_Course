import java.util.Scanner;
public class Cube
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter cube length:");
        final int length = scan.nextInt();
        System.out.println("Cube volume: " + (length*length*length));
        System.out.println("Cube surface: " + (length*length*6));
    }
}