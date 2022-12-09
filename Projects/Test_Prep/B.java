

public class B extends A
{
    // instance variables - replace the example below with your own
    private String str2;

    /**
     * Constructor for objects of class B
     */
    public B()
    {
        str2 = "Good Job!";
        System.out.println("in B's"+"constructor\n"+str2);
    }

    
    public B(A a)
    {
        if(a instanceof B)
        {
            str2 = ((B)a).str2;
            System.out.println(str2);
            a.f();
        }
        else
            str2 = "Done";
    }
    
    public String toString()
    {
        return str2;
    }
}
