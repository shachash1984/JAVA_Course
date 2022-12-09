
/**
 * A class of a number in a polynom
 * @author Shahar Schneider
 * @version 1.0
 */
public class PolyNode
{
    private int _power;
    private double _coefficient;
    private PolyNode _next;

    /**
     * Creates a new PolyNode object
     * @param power the value of the exponent
     * @param coefficient the value of the multiplier
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public PolyNode(int power, double coefficient)
    {
        if(power < 0)
        {
            power = 0;
            coefficient = 0;
        }
        _power = power;
        _coefficient = coefficient;
        _next = null;
    }

    /**
     * Creates a new PolyNode object
     * @param power the value of the exponent
     * @param coefficient the value of the multiplier
     * @param next the value of the next number in the Polynom
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public PolyNode(int power, double coefficient, PolyNode next)
    {
        if(power < 0)
        {
            power = 0;
            coefficient = 0;
        }
        _power = power;
        _coefficient = coefficient;
        _next = next;
    }

    /**
     * Creates a Copy of an existing Polynode
     * @param p the PolyNode to be copied
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public PolyNode(PolyNode p)
    {
        _power = p.getPower();
        _coefficient = p.getCoefficient();
        _next = p.getNext();
    }

    /**
     * Gets the value of the exponent
     * @return the value of power.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int getPower()
    {
        return _power;
    }

    /**
     * Gets the value of the multiplier
     * @return the value of coefficient  .   
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public double getCoefficient()
    {
        return _coefficient;
    }

    /**
     * Gets the reference of the next number in the Polynom
     * @return a reference of next.     
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public PolyNode getNext()
    {
        return _next;
    }

    /**
     * Sets the value of the exponent
     * @param power value to be passed.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void setPower(int power)
    {
        if(power >=0)
        {
            _power = power;
        }
    }

    /**
     * Sets the value of the multiplier
     * @param coefficient value to be passed.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void setCoefficient(double coefficient)
    {
        _coefficient = coefficient;
    }

    /**
     * Sets the reference of the next number in the Polynom
     * @param next referenced to be passed.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void setNext(PolyNode next)
    {
        _next = next;
    }

    /**
     * Creates a text represntation of the PolyNode
     * @return the PolyNode as a string
     * multiplier of '1' will not be printed
     * multiplier of '-1' will be printed only as '-'
     * power of '0' will result in a print of '1'
     * example: 3X^2
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public String toString()
    {
        String result = "";
        if(_coefficient == 0)
        {
            return result;
        }
        else if(_power == 0)
        {
            return result + _coefficient;
        }
        if(_coefficient == 1 || _coefficient == -1)
        {

            if(_coefficient == -1)
            {
                result += "-";
            }
            if(_power == 0)
            {
                result += "1";
            }
        }
        if(_power == 1)
        {
            if(_coefficient == 1 || _coefficient == -1)
            {
                result += "x";
            }
            else
            {
                result += (_coefficient+"x");
            }
        }
        else
        {
            if(_coefficient == 1 || _coefficient == -1)
            {
                result += "x";
            }
            else
            {
                result += (_coefficient+"x");
            }
            
            result += ("^"+_power);
        }
        return result;
    }
}
