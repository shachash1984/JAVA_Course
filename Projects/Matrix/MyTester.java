
public class MyTester
{
    public static void main(String[] args)
    {
        // Matrix m = new Matrix(5, 10);
        // System.out.println(m.toString());
        int[][] vals = {{19, 124, 28, 35}, {115, 22, 25, 230}, {19, 21, 22, 249}, {0, 16, 9, 232}, {62, 35, 10, 116}};
        int[][] vals1 = {{19, 124, 28, 35, 38}, {115, 22, 25, 230, 31}, {9, 21, 22, 249, 230}, {0, 6, 9, 232, 255}, {2, 5, 10, 116,129}};
        Matrix m = new Matrix(vals);
        Matrix m1 = m.rotateClockwise();
        Matrix m2 = new Matrix(vals1);
        System.out.println((m2.makeNegative()).toString());
    }

}
