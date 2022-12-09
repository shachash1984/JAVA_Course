
public class Ex14
{
    /** 
     * Checks how many times a string contains a substring(not continuous) starting, ending and containing exactly one of the char passed
     * @param s the string to check
     * @param c the char of the substring
     * @return the amount of substrings
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int subStrC(String s, char c)
    {
        int charCount = 0;
        final int PADDING = 2; //one char in the beginning and the end
        for(int i = 0; i < s.length(); i++)
        {
            //count the amount of chars equal to c
            if(s.charAt(i) == c)
            {
                charCount++;
            }
        }
        //only if the char count is larger than the Padding then we can deduct the Padding from the count
        if(charCount>PADDING)
        {
            charCount-=PADDING;
        }
        //edge case: there are no valid substrings that meet the criteria
        else if(charCount<3)
        {
            charCount=0;
        }
        return charCount;
    }

    /**
     * Checks how many times a string contains a substring(not continuous) starting, ending and containing up to "k" amount of the char passed
     * @param s the string to check
     * @param c the char of the substring
     * @param k the maximum amount of contained chars equal to c
     * @return the amount of substrings
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int subStrMaxC(String s, char c, int k)
    {
        int charCount = 0;   
        final int MIN_PADDING = 1; //one char at the beginning and one at the end for the min amount of k's which is 0
        for(int i = 0; i < s.length(); i++)
        {
            //count the amount of chars equal to c
            if(s.charAt(i) == c)
            {
                charCount++;
            }
        }
        //if there aren't at least 2 chars, we dont meet the criteria
        if(charCount<2)
            return 0;
        //calculating according to Arithmetic progression formula (sidra heshbonit)
        int a1 = charCount-(k+MIN_PADDING);
        int an = charCount-MIN_PADDING;
        int n = an-a1+1;
        int result = (n*(2*a1+(n-1)))/2;

        return result;
    }

    /**
     * Rewrites an array of 0's and 1's to show the distance of each '1' from it's closest '0' neighbour
     * @param a the original array of zeros and ones
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static void zeroDistance(int[] a)
    {
        int lastZeroIndex =-1;
        for(int i=0; i< a.length; i++)
        {
            if(a[i]==0)
            {
                if(lastZeroIndex==-1)
                {
                    lastZeroIndex=i;
                    continue;
                }

                int currentZeroIndex = i;
                int j = (lastZeroIndex+(currentZeroIndex - lastZeroIndex)/2) + 1;
                while (j<currentZeroIndex)
                {
                    a[j]=Math.abs(j-currentZeroIndex);
                    j++;
                }
                lastZeroIndex=currentZeroIndex;
            }
            else
            {
                a[i] = Math.abs(i-lastZeroIndex);
            }
        }
    }

    /**
     * checks if a string is a transformed version of another string
     * @param s the original string
     * @param t the transformed string
     * @return true if t is a transformed version of s
     */
    public static boolean isTrans(String s, String t)
    {
        //if t is shorter than s, we fail to meet to criteria
        if(t.length()<s.length())
        {
            return false;
        }
        //if t's first char is not the same as s's first char, we fail to meet to criteria
        else if(t.charAt(0) != s.charAt(0))
        {
            return false;
        }
        //if t's last char is not the same as s's last char, we fail to meet to criteria
        else if(t.charAt(t.length()-1) != s.charAt(s.length()-1))
        {
            return false;
        }
        //if we haven't failed we pass the substrings starting from index 1
        return isTrans(s, t, 1, 1);
    }

    //helper method to compare between s and t (t may contain duplicates)
    private static boolean isTrans(String s, String t, int sCount, int tCount)
    {
        //safety check
        if(sCount < 1)
            return false;
        //if we are at the final char in both strings, check equality and return result (end of recursion)
        if(sCount == s.length()-1 && tCount == t.length()-1)
        {
            return t.charAt(tCount)==s.charAt(sCount);
        }
        //check if the chars are equal at the current count OR if t's char at the current Count is equal to s's char at the current Count -1
        boolean trans = t.charAt(tCount)==s.charAt(sCount) || t.charAt(tCount)==s.charAt(sCount-1);
        //return the result of last line AND 2 recursive calls: one where s's index stays the same and one where s's index is incremented (t is incremented in both calls)
        return trans && (isTrans(s, t, sCount, tCount + 1) || isTrans(s, t, sCount+1, tCount + 1));      
    }

    /**
     * Returns the amount of paths from the first cell to the last according to the values in the cell
     * @param mat the matrix of values
     * @return the amount of valid paths
     */
    public static int countPaths(int[][] mat)
    {
        return countPaths(mat, 0, 0);
    }

    //helper method to find all the paths
    private static int countPaths(int[][] mat, int x, int y)
    {
        //helper variables for the amount of rows and columns
        int numRows = mat.length;
        int numCols = mat[mat.length-1].length;

        //if we are at the last array's last index -- end recursion and return 1
        if(x==numRows-1 && y==numCols-1)
        {
            return 1;
        }
        //if we are out of bounds return 0
        else if(x >numRows-1 || y>numCols-1)
        {
            return 0;
        }
        if(mat[x][y]==0)
        {
            return 0;
        }
        //get the tens and ones
        int tens = mat[x][y]/10;
        int ones = mat[x][y]%10;

        //call the recursion twice: one where x gets the tens and y gets the ones and vice versa
        return countPaths(mat, x+tens, y+ones) + countPaths(mat, x+ones, y+tens);
    }
}

