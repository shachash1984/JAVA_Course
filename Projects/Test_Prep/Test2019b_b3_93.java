
public class Test2019b_b3_93
{
    public static void main(String[] args)
    {
        // int[] a = {5,4,2,1,3};
        // int num = 0;
        // System.out.println("0: "+ isSum(a, num));
        // num = 8;
        // System.out.println("8: "+ isSum(a, num));
        // num = 9;
        // System.out.println("9: "+ isSum(a, num));
        // num = 2;
        // System.out.println("2: "+ isSum(a, num));
        // num = 11;
        // System.out.println("11: "+ isSum(a, num));
        // num = 17;
        // System.out.println("17: "+ isSum(a, num));
        int[] road1 = {5,4,5,8,12,9,9,3};
        int[] road2 = {7,3,3,12,10,2,10,7};
        int res = shortestRoad(road1, road2);
        System.out.println(""+res);
    }

    public static boolean isSum(int[] a, int num)
    {
        if(num==0)
            return true;

        return isSum(a, num, 0, num);

    }

    private static boolean isSum(int[] a, int num, int index, int original)
    {
        if(index==a.length || num < 0)
            return false;

        if(a[index] == num)
        {
            return true;
        }

        return isSum(a, num, index+1, original)&&(!threeFollowers(a, index+1,original)) || isSum(a, num-a[index], index+1, original)&&(!threeFollowers(a, index+1,original)); 
    }

    private static boolean threeFollowers(int[] a, int index, int num)
    {
        if(index < 2)
            return false;
        return (a[index-2]+a[index-1]+a[index])==num;
    }

    public static int shortestRoad(int[] road1, int[] road2)
    {
        int sum1=0, sum2=0, minRd=Integer.MAX_VALUE;
        for(int i = 0; i <road1.length; i++)
        {
            sum1 += road1[i];
            sum2 += road2[i];
        }
        minRd = sum1 < sum2 ? sum1 : sum2;
        int tempSum1= sum1;
        int tempSum2=sum2;

        for(int i = road1.length-1; i>=0; i--)
        {
            tempSum1 -= road1[i];
            tempSum1 += road2[i];
            tempSum2 -= road2[i];
            tempSum2 += road1[i];
            int minTemp = tempSum1 < tempSum2 ? tempSum1 : tempSum2;
            minRd = minRd < minTemp ? minRd: minTemp;
        }
        return minRd;
    }
}
