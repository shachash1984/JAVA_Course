/**
 * A class of a polynom
 * @author Shahar Schneider
 * @version 1.0
 */
public class Polynom
{
    private PolyNode _head;

    /**
     * Creates a new Polynom object
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public Polynom()
    {
        _head = null;
    }

    /**
     * Creates a new Polynom object
     * @param p the PolyNode representing the first number
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public Polynom(PolyNode p)
    {
        _head = p;
    }

    /**
     * Gets the first number of the Polynom
     * @return this Polynom's first monom
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public PolyNode getHead()
    {
        return _head;
    }

    /**
     * Sets the first number of the Polynom
     * @param p the PolyNode being passed to be the first number
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void setHead(PolyNode p)
    {
        _head = p;
    }
    
    //Helper method for adding nodes with the same power together
    //Time Complexity: O(1)
    //Space Complexity: O(1)
    private PolyNode addNodes(PolyNode originalNode, PolyNode newNode)
    {

        if(originalNode.getPower() != newNode.getPower())
        {
            return null;
        }
        originalNode.setCoefficient(originalNode.getCoefficient()+newNode.getCoefficient());
        return originalNode;
    }

    //Helper method for multiplying nodes together
    //Time Complexity: O(1)
    //Space Complexity: O(1)
    private PolyNode multNodes(PolyNode originalNode, PolyNode newNode)
    {
        PolyNode tempNode = new PolyNode(originalNode);
        tempNode.setCoefficient(originalNode.getCoefficient()*newNode.getCoefficient());
        tempNode.setPower(originalNode.getPower()+newNode.getPower());
        return tempNode;
    }

    /**
     * Adds a monom to the Polynom
     * if a monom with the same power already exists, it will be joined together with monom passed
     * @param p the PolyNode being passed
     * @return this Polynom after adding a PolyNode
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public Polynom addNode(PolyNode p)
    {
        //if Polynom is empty return a new Polynom with p as its head
        if(_head==null)
        {
            setHead(p);
            return this;      
        }
        else if(p.getPower() > _head.getPower())
        {
            p.setNext(_head);
            setHead(p);
            return this;
        }
        else if(p.getPower() == _head.getPower())
        {
            _head = addNodes(_head, p);
            return this;
        }

        PolyNode prev = _head;
        PolyNode temp = _head.getNext();
        while(temp !=null)
        {
            if(p.getPower() > temp.getPower())
            {
                prev.setNext(p);
                p.setNext(temp);
                return this;
            }
            else if(p.getPower() == temp.getPower())
            {
                temp = addNodes(temp, p);
                return this;
            }
            prev = temp;
            temp = temp.getNext();
        }
        prev.setNext(p);
        p.setNext(null);
        return this;
    }

    /**
     * Multiplies the polynom by a scalar
     * @param num the scalar to multiply
     * @return this Polynom after multiplication with num
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public Polynom multByScalar(int num)
    {
        PolyNode current = getHead();
        if(current==null)
        {
            return null;
        }

        while(current != null)
        {
            current.setCoefficient(current.getCoefficient()*num);
            current = current.getNext();
        }
        return this;
    }

    /**
     * Adds two polynoms together
     * @param other the polynom to add
     * @return this Polynom after addition with other
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public Polynom addPol(Polynom other)
    {
        if(other==null)
        {
            return null;
        }
        PolyNode temp = other.getHead();
        while(temp != null)
        {
            PolyNode otherNext = temp.getNext();
            this.addNode(temp);
            temp = otherNext;
        }
        return this;
    }

    /**
     * Multiplies two polynoms together
     * @param other the polynom to multiply
     * @return this Polynom after multiplication with other
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     */
    public Polynom multPol(Polynom other)
    {
        if(other==null)
        {
            return null;
        }
        PolyNode thisCurrentNode = _head;
        Polynom newPol = new Polynom();
        while(thisCurrentNode != null)
        {
            PolyNode otherCurrentNode = other._head;      
            while(otherCurrentNode != null)
            {
                PolyNode tempThisCurrentNode = new PolyNode(thisCurrentNode);
                tempThisCurrentNode.setNext(null); 
                newPol.addNode(multNodes(tempThisCurrentNode, otherCurrentNode));
                otherCurrentNode = otherCurrentNode.getNext();
            }

            thisCurrentNode = thisCurrentNode.getNext();
        }
        setHead(newPol._head);
        return this;
    }

    /**
     * Gets the differential of the Polynom
     * @return the same Polynom affter differential
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public Polynom differential()
    {
        PolyNode temp = _head;
        while(temp != null)
        {
            temp.setCoefficient(temp.getPower()*temp.getCoefficient());
            if(temp.getPower()==0)
            {
                temp.setCoefficient(0);
                temp = temp.getNext();
                continue;
            }
            temp.setPower(temp.getPower()-1);
            temp = temp.getNext();
        }
        return this;
    }

    /**
     * Creates a text represntation of the Polynom
     * @return the Polynom as a string
     * multiplier of '1' will not be printed
     * multiplier of '-1' will be printed only as '-'
     * power of '0' will result in a print of '1'
     * a member with a coefficient of '0' will not be printed
     * example: 3X^2
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String toString()
    {
        String str = "";
        PolyNode temp = _head;
        while(temp != null)
        {
            if(temp.getCoefficient()==0)
            {
                temp = temp.getNext();
                continue;
            }
            if(temp != _head)
            {
                if(temp.getCoefficient() > 0)
                {
                    str += "+";
                }
            }
            str += temp.toString();
            temp = temp.getNext();
        }
        return str;
    }
}
