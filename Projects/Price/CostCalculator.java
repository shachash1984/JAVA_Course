import java.util.Scanner;
public class CostCalculator
{
    public static void main(String[] args)
    {   
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter product price: ");
        int price = scan.nextInt();
        System.out.println("Please enter unit amount: ");
        int unitAmount = scan.nextInt();
        System.out.println("The total cost is : "+ (price*unitAmount));
        float z = 5/10;
        System.out.println("float z: " + z);
        
        int a = 5;
        int b = 2;
        int c = 4;
        
        if(a<b)
            if(b>c)
                System.out.println("max = " + b);
            else
                System.out.println("max = " + c);
        else
            if(b>c)
                System.out.println("max = " + a);
            else
                System.out.println("max = " + c);
        
    }
}