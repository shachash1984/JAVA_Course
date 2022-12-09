/**
 * Purpose: A class for representing stock
 * @author: Shahar Schneider
 * @version: 14/12/2019
 */
public class Stock
{
    private FoodItem[] _stock;
    private int _noOfItems;
    private final int MAX_ITEMS = 100;

    /**
     * Creates a new Stock object
     */
    public Stock()
    {
        _stock = new FoodItem[MAX_ITEMS];
        _noOfItems = 0;
    }

    /**
     * gets the number of items
     * @return current number of items (0-100)
     */
    public int getNumOfItems()
    {
        return _noOfItems;
    }

    /**
     * Adds a foodItem to the stock
     * @param foodItem the item to add
     * @return true if item was added and false otherwise
     */
    public boolean addItem(FoodItem foodItem)
    {
        if(!canAddToStock())
        {
            //out of place in stock
            return false;
        }

        int index = getIndexInStock(foodItem);
        if(index >= 0)
        {
            //same item exists in stock
            int newQuantity = _stock[index].getQuantity()+foodItem.getQuantity();
            _stock[index].setQuantity(newQuantity);
            return true;
        }

        index = getIndexOfSimilarInStock(foodItem);
        if(index >= 0)
        {
            //similar item exists in stock
            insertAt(foodItem, index);
            return true;
        }

        //new item in stock
        _stock[_noOfItems++] = new FoodItem(foodItem);
        return true;

    }

    /**
     * Returns a String of all items which are low in stock
     * @param amount the minimum quantity for not needing to order
     * @return a String with the list of item names who have a quantity below said amount
     */
    public String order(int amount)
    {
        String orderString = "";
        for(int i = 0; i < _noOfItems; i++)
        {
            //if we already checked this item or a similar one - continue
            if(orderString.contains(_stock[i].getName()))
            {
                continue;
            }

            int highestSimilarIndex = getHighestSimilarIndex(i);
            int quantity = getAmountInRange(i, highestSimilarIndex);
            if(quantity < amount)
            {
                //handling fence post problem
                if(orderString.equals(""))
                    orderString += _stock[i].getName();
                else
                    orderString += (", " +_stock[i].getName());

            }
        }
        return orderString;
    }

    /**
     * Returns the amount of items that can be stored in a specific temperature
     * @param temp the temperature of storage
     * @return the amount of items available for storage
     */
    public int howMany(int temp)
    {
        int count = 0;
        for(int i = 0; i < _noOfItems; i++)
        {
            if(temp >= _stock[i].getMinTemperature() && temp <= _stock[i].getMaxTemperature())
            {
                count += _stock[i].getQuantity();
            }
        }
        return count;
    }

    /**
     * Removes all items with expiry dates before the passed date
     * @param d the date being passed, every item with an earlier date gets removed
     */
    public void removeAfterDate(Date d)
    {
        for(int i=0; i < _noOfItems; i++)
        {
            if(_stock[i].getExpiryDate().before(d))
            {
                removeItemAt(i);
                i--;
            }
        }
    }

    /**
     * gets the most expensive item in stock
     * @return the most expensive FoodItem
     */
    public FoodItem mostExpensive()
    {
        FoodItem maxPriceItem = null;
        int maxPrice = 0;
        for(int i = 0; i < _noOfItems; i++)
        {
            if(_stock[i].getPrice() > maxPrice)
            {
                maxPrice = _stock[i].getPrice();
                maxPriceItem = new FoodItem(_stock[i]);
            }
        }
        return maxPriceItem;
    }

    /**
     * gets the amount of items in stock
     * @return the sum total of all the items in stock
     */
    public int howManyPieces()
    {
        int count = 0;
        for(int i = 0; i < _noOfItems; i++)
        {
            count += _stock[i].getQuantity();
        }
        return count;
    }

    /**
     * Translate values into readable Format
     * @return a string containing the string representation of each item in stock
     */
    public String toString()
    {
        String text = "";
        if(_noOfItems <= 0)
            return text;
        for(int i = 0; i < _noOfItems-1; i++)
        {
            text += (_stock[i].toString() + "\n");
        }
        if(_stock != null && _noOfItems > 0)
            text += _stock[_noOfItems -1].toString();
        return text;
    }

    /**
     * Updates the amounts of items in stock after sales
     * @param itemsList the list of items to update
     */
    public void updateStock(String[] itemsList)
    {
        for(int i= 0; i< itemsList.length; i++)
        {
            for(int j = 0; j<_noOfItems; j++)
            {   
                if(itemsList[i].equals(_stock[j].getName()))
                {
                    decreaseAt(j);
                    if(_stock[j].getQuantity() == 0)
                    {
                        removeItemAt(j);
                    }
                }
            }
        }
    }

    /**
     * gets the temperature needed for storage to satisfy all item types
     * @return the minimum possible temperature for storing all the items, if item temperature ranges do not intersect, returns Integer.MAX_VALUE
     */
    public int getTempOfStock()
    {
        if(_stock == null || _stock.length==0 || _stock[0] == null)
        {
            return Integer.MAX_VALUE;
        }
        int minTemp = _stock[0].getMinTemperature();
        int maxTemp = _stock[0].getMaxTemperature();
        int temp = minTemp;
        for(int i = 1; i < _noOfItems; i++)
        {
            if(_stock[i].getMaxTemperature() < minTemp || _stock[i].getMinTemperature() > maxTemp)
            {
                return Integer.MAX_VALUE;
            }
            else if(_stock[i].getMaxTemperature() < maxTemp || _stock[i].getMinTemperature() > minTemp)
            {
                maxTemp = _stock[i].getMaxTemperature();
                minTemp = _stock[i].getMinTemperature();
                temp = minTemp;
            }
        }
        return temp;
    }

    //decrease quantity of the item at the index passed
    private void decreaseAt(int index)
    {
        if(index >= 0 && _stock != null && _stock.length > 0)
        {
            int quantity = _stock[index].getQuantity();
            _stock[index].setQuantity(quantity - 1);
        }
    }

    //remove an item at the index passed
    private void removeItemAt(int index)
    {
        int currentIndex = index;
        while(currentIndex < _noOfItems - 1)
        {
            _stock[currentIndex] = _stock[currentIndex + 1];
            currentIndex++;
        }

        _noOfItems--;
    }

    //returns a similar product if exist (similar == sam name and same cat number)
    private int getHighestSimilarIndex(int startIndex)
    {
        int index = startIndex;
        boolean similar = false;
        do
        {
            similar = index < _noOfItems-1 && _stock[index+1] != null && _stock[index].getName().equals(_stock[index+1].getName()) && _stock[index].getCatalogueNumber() == _stock[index+1].getCatalogueNumber();
            if(similar)
            {
                index++;
            }

        }while(similar);
        return index;
    }

    //returns quantity of items in a specific range
    private int getAmountInRange(int startIndex, int endIndex)
    {
        int amount = 0;
        for(int i = startIndex; i <= endIndex; i++)
        {
            amount += _stock[i].getQuantity();
        }
        return amount;
    }

    //if foodItem is in stock the method returns the index in _stock
    //else, the method returns -1
    private int getIndexInStock(FoodItem foodItem)
    {
        for(int i = 0; i < _noOfItems; i++)
        {
            if(_stock[i].equals(foodItem))
            {
                return i;
            }
        }
        return -1;
    }

    //if similar (same name and catalogue number)foodItem is in stock the method returns the index in _stock
    //else, the method returns -1
    private int getIndexOfSimilarInStock(FoodItem foodItem)
    {
        for(int i = 0; i < _noOfItems; i++)
        {
            if(areItemsOfSameType(_stock[i], foodItem))
            {
                return i;
            }
        }
        return -1;
    }

    //returns true if both items have the same name and same catalogue number
    private boolean areItemsOfSameType(FoodItem item1, FoodItem item2)
    {
        return item1.getName().equals(item2.getName()) && item1.getCatalogueNumber() == item2.getCatalogueNumber();
    }

    //insert an item at a specific index
    private boolean insertAt(FoodItem foodItem, int index)
    {
        if(canAddToStock())
        {
            int currentIndex = _noOfItems - 1;
            while(currentIndex >= index)
            {
                _stock[currentIndex + 1] = _stock[currentIndex];
                currentIndex--;
            }
            _stock[index] = foodItem;
            _noOfItems++;
            return true;
        }
        else
        {
            return false;
        }
    }

    //checks if we havent reached maximum value of stock
    private boolean canAddToStock()
    {
        return _noOfItems < MAX_ITEMS;
    }
}
