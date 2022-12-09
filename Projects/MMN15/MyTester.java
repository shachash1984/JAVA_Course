
/**
 * Write a description of class MyTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyTester
{
    public static void main(String[] args)
    {
        // PolyNode p1 = new PolyNode(3, 2);
        // PolyNode p2 = new PolyNode(2, -5);
        // PolyNode p3 = new PolyNode(1, 4);
        // PolyNode p4 = new PolyNode(0, -3);

        // Polynom pol = new Polynom();
        // pol.addNode(p4);
        // pol.addNode(p2);
        // pol.addNode(p1);
        // pol.addNode(p3);

        // Polynom pol2 = new Polynom();
        // pol2.addNode(new PolyNode(4, 12));
        // pol2.addNode(new PolyNode(1, -4));
        // pol2.addNode(new PolyNode(3, 32));
        // //pol.multByScalar(2);
        // pol.addPol(pol2);

        // Polynom p = new Polynom(new PolyNode(3,8.0));
        // p.addNode(new PolyNode(2, -3.0));
        // p.addNode(new PolyNode(1, -1));
        // p.addNode(new PolyNode(0, 7.0));

        // Polynom q = new Polynom(new PolyNode(2, 1));
        // q.addNode(new PolyNode(1, 6.0));
        // q.addNode(new PolyNode(0, -15.0));
        // p.multPol(q);

        // p.differential();

        Polynom r = new Polynom(new PolyNode(10, 2.8));
        r.addNode(new PolyNode(3, -4.9));
        r.addNode(new PolyNode(2, 6.5));
        r.addNode(new PolyNode(0, -12.0));
        System.out.println(r.toString());//q.toString());
    }
}
