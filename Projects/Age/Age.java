import java.util.Scanner;
public class Age
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter year of birth");
        int birthYear = scan.nextInt();
        System.out.println("Please enter current year");
        int currentYear = scan.nextInt();
        
        System.out.println("You are " + (currentYear - birthYear) + " years old");;
    }
}