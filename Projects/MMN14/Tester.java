
public class Tester
{
    public static void main(String[] args)
    {
        // System.out.println("first: " + Ex14.subStrC("c", 'c'));
        // System.out.println("second: " + Ex14.subStrMaxC("c", 'c', 5));
        //int[] a = {0, 1, 1 ,1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1 };
        int[] a ={0};
        Ex14.zeroDistance(a);
        for(int i= 0; i < a.length; i++)
        {
            System.out.print(""+ a[i]+ " ");
        }
        
        // String s = "abbcd";
        // System.out.println("abbcd: " + Ex14.isTrans(s, "abbcd"));
        // System.out.println("aaaabbcd: " + Ex14.isTrans(s, "aaaabbcd"));
        // System.out.println("abbcdddddd: " + Ex14.isTrans(s, "abbcdddddd"));
        // System.out.println("aabbccdd: " + Ex14.isTrans(s, "aabbccdd"));
        // System.out.println("abbbccd: " + Ex14.isTrans(s, "abbbccd"));
        
        // System.out.println("a: " + Ex14.isTrans(s, "a"));
        // System.out.println("abcd: " + Ex14.isTrans(s, "abcd"));
        // System.out.println("aaccbbdd: " + Ex14.isTrans(s, "aaccbbdd"));
        
        // int[][] mat = {{12, 22, 23, 54},{43, 35, 21, 20},{34, 21, 43, 21},{25, 30, 0, 20},{0, 22, 10, 10},{20, 13, 3, 45}};
        // System.out.println("count: " + Ex14.countPaths(mat));
    }
}
