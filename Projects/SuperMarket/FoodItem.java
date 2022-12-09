
/**
 * Class representing a food item in a supermarket
 * @author: Shahar Schneider
 * @version: 07/12/2019
 */
public class FoodItem
{
    private String _name;
    private long _catalogueNumber;
    private int _quantity;
    private Date _productionDate;
    private Date _expiryDate;
    private int _minTemperature;
    private int _maxTemperature;
    private int _price;

    private final String DEFAULT_NAME = "item";
    private final long DEFAULT_CATALOGUE_NUMBER = 9999;
    private final long MIN_CATALOGUE_NUMBER =1000;
    private final long MAX_CATALOGUE_NUMBER =9999;
    private final int MIN_QUANTITY = 0;
    private final int MIN_PRICE = 1;

    /**
     * Creates a new FoodItem object
     * @param name the name of the food item (cannot be empty)
     * @param catalogueNumber the catalogue number of the food item (must be at least 4 digits)
     * @param quantity the amount of food items of this type (must be 0 or higher)
     * @param productionDate date of production (cannot be after expiration date)
     * @param expiryDate date of expiration (cannot be before production date)
     * @param minTemperature the minimum temperature this food item can be stored in (cannot be higher than max temperature)
     * @param maxTemperature the maximum temperature this food item can be stored in (cannot be lower than min temperature)
     * @param price the price for this food item (cannot be lower than 1)
     */
    public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate, int minTemperature, int maxTemperature, int price)
    {
        if(isNameValid(name))
        {
            _name = name;
        }
        else
        {
            _name = DEFAULT_NAME;
        }
        if(isCatalogueNumberValid(catalogueNumber))
        {
            _catalogueNumber = catalogueNumber;
        }
        else
        {
            _catalogueNumber = DEFAULT_CATALOGUE_NUMBER;
        }
        if(isQuantityValid(quantity))
        {
            _quantity = quantity;
        }
        else
        {
            _quantity = MIN_QUANTITY;
        }
        _productionDate = new Date(productionDate);
        if(isExpiryDateValid(expiryDate, productionDate))
        {
            _expiryDate = new Date(expiryDate);
        }
        else
        {
            _expiryDate = productionDate.tomorrow();
        }
        if(isMinTempValid(minTemperature, maxTemperature))
        {
            _minTemperature = minTemperature;
            _maxTemperature = maxTemperature;
        }
        else
        {
            _minTemperature = maxTemperature;
            _maxTemperature = minTemperature;   
        }
        if(isPriceValid(price))
        {
            _price = price;
        }
        else
        {
            _price = MIN_PRICE;
        }
    }

    /**
     * Creates a new FoodItem object
     * @param other the FoodItem to copy
     */
    public FoodItem(FoodItem other)
    {
        if(isNameValid(other._name))
        {
            _name = other._name;
        }
        else
        {
            _name = DEFAULT_NAME;
        }
        if(isCatalogueNumberValid(other._catalogueNumber))
        {
            _catalogueNumber = other._catalogueNumber;
        }
        else
        {
            _catalogueNumber = DEFAULT_CATALOGUE_NUMBER;
        }
        if(isQuantityValid(other._quantity))
        {
            _quantity = other._quantity;
        }
        else
        {
            _quantity = MIN_QUANTITY;
        }
        _productionDate = new Date(other._productionDate);
        if(isExpiryDateValid(other._expiryDate, other._productionDate))
        {
            _expiryDate = new Date(other._expiryDate);
        }
        else
        {
            _expiryDate = other._productionDate.tomorrow();
        }
        if(isMinTempValid(other._minTemperature, other._maxTemperature))
        {
            _minTemperature = other._minTemperature;
            _maxTemperature = other._maxTemperature;
        }
        else
        {
            _minTemperature = other._maxTemperature;
            _maxTemperature = other._minTemperature;   
        }
        if(isPriceValid(other._price))
        {
            _price = other._price;
        }
        else
        {
            _price = MIN_PRICE;
        }

    }

    /**
     * gets the name
     * @return this FoodItem's name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * gets the catalogue number
     * @return this Food Item's catalogue number
     */
    public long getCatalogueNumber()
    {
        return _catalogueNumber;
    }

    /**
     * gets the minimum temperature
     * @return this Food Item's minimum temperature
     */
    public int getMinTemperature()
    {
        return _minTemperature;
    }

    /**
     * gets the maximum temperature
     * @return this Food Item's maximum temperature
     */
    public int getMaxTemperature()
    {
        return _maxTemperature;
    }

    /**
     * gets the quantity
     * @return this Food Item's quantity
     */
    public int getQuantity()
    {
        return _quantity;
    }

    /**
     * sets the quantity for this Food item
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity)
    {
        if(isQuantityValid(quantity))
        {
            _quantity = quantity;
        }
    }

    /**
     * gets the production date
     * @return this Food Item's production date
     */
    public Date getProductionDate()
    {
        return new Date(_productionDate);
    }

    /**
     * sets the production date for this Food item
     * @param productionDate the production date to set
     */
    public void setProductionDate(Date productionDate)
    {
        if(isExpiryDateValid(_expiryDate, productionDate))
        {
            _productionDate = new Date(productionDate);
        }
    }

    /**
     * gets the expiry date
     * @return this Food Item's expiry date
     */
    public Date getExpiryDate()
    {
        return new Date(_expiryDate);
    }

    /**
     * sets the expiry date for this Food item
     * @param expiryDate the expiry date to set
     */
    public void setExpiryDate(Date expiryDate)
    {
        if(isExpiryDateValid(expiryDate, _productionDate))
        {
            _expiryDate = new Date(expiryDate);
        }
    }

    /**
     * gets the price
     * @return this Food Item's price
     */
    public int getPrice()
    {
        return _price;
    }

    /**
     * sets the price for this Food item
     * @param price the price to set
     */
    public void setPrice(int price)
    {
        if(isPriceValid(price))
        {
            _price = price;
        }
    }

    /**
     * Checks equality between food items
     * @param other the FoodItem to compare
     * @return true if all attributes except quantity are equal
     */
    public boolean equals(FoodItem other)
    {
        return _name.equals(other._name) &&
        _catalogueNumber == other._catalogueNumber &&
        _productionDate.equals(other._productionDate) &&
        _expiryDate.equals(other._expiryDate) &&
        _minTemperature == other._minTemperature &&
        _maxTemperature == other._maxTemperature &&
        _price == other._price;
    }

    /**
     * Checks freshness of food item for a specific date
     * @param d the date to check
     * @return true d is after this food item's production date and before it's expiry date
     */
    public boolean isFresh(Date d)
    {
        return d.after(_productionDate) && d.before(_expiryDate);
    }

    /**
     * Translate values into readable Format
     * @return String that represents this food item in the following format: "FoodItem: Bread  Catalogue Number: 1234  Production Date: 01/01/2019 Expiry Date: 10/01/2019 Quantity: 100"
     */
    public String toString()
    {
        return "FoodItem: " + _name + "\t" +"Catalogue Number: " + _catalogueNumber + "\t" +"Production Date: " + _productionDate.toString() + "\t" + "ExpiryDate: " + _expiryDate.toString() + "\t"+ "Quantity: " + _quantity;    
    }

    /**
     * Checks priority between food items
     * @param other the FoodItem to compare
     * @return true if this food item's production date is before the other
     */
    public boolean olderFoodItem(FoodItem other)
    {
        return _productionDate.before(other._productionDate);
    }

    /**
     * Checks amount of food items can be bought with a certain amount of money
     * @param payment the amount of money
     * @return the amount of food items of this type that can be bought for the payment received (amount of items will not exceed the current food item's quantity)
     */
    public int howManyItems(int payment)
    {
        int itemAmount = payment/_price;
        return itemAmount <= _quantity ? itemAmount : _quantity;
    }

    /**
     * Compares between 2 food products based on their price
     * @param other the FoodItem to compare
     * @return true if this food item's price is lower than the other's price
     */
    public boolean isCheaper(FoodItem other)
    {
        return _price < other._price;
    }

    private boolean isNameValid(String name)
    {
        return !name.equals("");
    }

    private boolean isCatalogueNumberValid(long catNum)
    {
        return catNum >= MIN_CATALOGUE_NUMBER && catNum <= MAX_CATALOGUE_NUMBER;
    }

    private boolean isQuantityValid(int quantity)
    {
        return quantity >= MIN_QUANTITY;
    }

    private boolean isExpiryDateValid(Date expiryDate, Date productionDate)
    {
        return !expiryDate.before(productionDate);
    }

    private boolean isMinTempValid(int minTemp, int maxTemp)
    {
        return minTemp <= maxTemp;
    }

    private boolean isPriceValid(int price)
    {
        return price >= MIN_PRICE;
    }
}
