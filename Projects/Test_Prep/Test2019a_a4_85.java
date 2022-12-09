
public class Test2019a_a4_85
{

    public static void main(String[] args)
    {
        // int[] array = {1, 3, 2, 3, 10, 10, 3, 2, 4};
        // System.out.println(""+longestPalindrome(array));
        // int[] array2 = {7, 10, 13, 16, 22, 25};
        // System.out.println(""+missingValue(array2));
        // A a2 = new B();
        // B b1 = (B)a2;
        // A a3 = new B(b1);
        
        // A a4 = new A();
        // B b2 = new B(a4);
        // System.out.println(b2);
        
        B b3 = new B();
        A a5 = b3;
        Object obj1 = a5;
        System.out.println(obj1);
    }

    public static int longestPalindrome(int[] arr)
    {
        if(arr.length==0)
            return 0;
        if(arr.length==1)
            return 1;

        return longestPalindrome(arr, 0, 0, arr.length-1);
    }

    private static int longestPalindrome(int[] arr, int longest, int first, int second)
    {
        if(first==second)
            return longest;
        else if(first > second)
            return longest == 0 ? 1 : longest;

        if(arr[first]==arr[second])
        {
            int max = Math.max(longestPalindrome(arr, longest+2, first, second-1), longestPalindrome(arr, longest+2, first+1, second));
            return Math.max(max, longestPalindrome(arr, longest+2, first+1, second-1));
        }
        else
        {
            int max = Math.max(longestPalindrome(arr, 0, first, second-1), longestPalindrome(arr, 0, first+1, second));
            return Math.max(max, longestPalindrome(arr, 0, first+1, second-1));
        }

    }

    public static int missingValue(int[] arr)
    {
        int diff = 0;
        int diff1 = arr[1]-arr[0];
        int diff2 = arr[2]-arr[1];
        int diff3 = arr[3]-arr[2];

        if(diff1==diff2)
            diff=diff1;
        else if(diff1==diff3)
            diff=diff1;
        else if(diff2==diff2)
            diff=diff2;
            
        int sum = ((arr.length+1)*((2*arr[0]) + (arr.length)*diff))/2;
        int actualSum = 0;
        for(int i=0; i< arr.length; i++)
        {
            actualSum+=arr[i];
        }
        return sum-actualSum;
    }
}
