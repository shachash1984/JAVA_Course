

public class Test2018a
{
    public static void main(String[] args)
    {
        System.out.println(""+howManySorted(2,3));
    }
    
    public static int howManySorted(int n, int max)
    {
        return howManySorted(n, max, n-1, max);
    }
    private static int howManySorted(int n, int max, int val)
    {
        if(val < 1)
        {
            return 0;
        }
        int count = howManySorted(n, max, n-1, val);
        return count + howManySorted(n, max, val-1);
    }
    
    private static int howManySorted(int n, int max, int index, int val)
    {
        if(val < 1)
        {
            return 0;
        }
        if(index ==0)
        {
            return 1;
        }
        return howManySorted(n, max, index -1, val)+howManySorted(n, max, index -1, val-1);
    }
}
