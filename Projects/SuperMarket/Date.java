/**
 * Purpose: A class for representing and calculating a gregorian calendar date
 * @author: Shahar Schneider
 * @version: 29/11/2019
 */

public class Date
{
    private int _day;
    private int _month;
    private int _year;
    private final int JANUARY = 1;
    private final int FEBRUARY = 2;
    private final int MARCH = 3;
    private final int APRIL = 4;
    private final int MAY = 5;
    private final int JUNE = 6;
    private final int JULY = 7;
    private final int AUGUST= 8;
    private final int SEPTEMBER = 9;
    private final int OCTOBER = 10;
    private final int NOVEMBER = 11;
    private final int DECEMBER = 12;
    private final int LEAP_JUMP = 4;
    private final int CENTURY = 100;
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_DAY = 1;
    private final int MIN_DAY = 1;
    private final int MAX_DAY = 31;
    private final int SHORT_MONTH_DAYS = 30;
    private final int DAYS_IN_FEBRUARY = 28;
    private final int DAYS_IN_FEBRUARY_LEAP = 29;
    private final int MIN_YEAR = 1000;
    private final int MAX_YEAR = 9999;
    private final int MIN_MONTH = 1;
    private final int MAX_MONTH = 12;
    private final int DAYS_IN_WEEK = 7;
    private final int MIN_DOUBLE_DIGIT = 10;

    /**
     * Creates a new Date object
     * @param day the day in the month(1-31)
     * @param month the month in the year
     * @param year the year (in 4 digits)
     */
    public Date(int day, int month, int year)
    {
        if(isDateValid(day, month, year))
        {
            _day = day;
            _month = month;
            _year = year;
        }
        else
        {
            setDefault();
        }
    }

    /**
     * Creates a new Date object
     * @param other the date to copy
     */
    public Date(Date other)
    {
        if(!isDateValid(other))
        {
            setDefault();
        }
        else
        {
            this._day = other._day;
            this._month = other._month;
            this._year = other._year;
        }
    }

    /**
     * gets the day
     * @return this Date's day
     */
    public int getDay()
    {
        return this._day;
    }

    /**
     * gets the month
     * @return this Date's month
     */
    public int getMonth()
    {
        return this._month;
    }

    /**
     * gets the year
     * @return this Date's year
     */
    public int getYear()
    {
        return this._year;
    }

    /**
     * sets the day for this date
     * @param day the day to set
     */
    public void setDay(int day)
    {
        if(isDateValid(day, _month, _year))
        {
            this._day = day;
        }
    }

    /**
     * sets the month for this date
     * @param month the month to set
     */
    public void setMonth(int month)
    {
        if(isDateValid(_day, month, _year))
        {
            this._month = month;
        }
    }

    /**
     * sets the year for this date
     * @param year the year to set
     */
    public void setYear(int year)
    {
        if(isDateValid(_day, _month, year))
        {
            this._year = year;
        }
    }

    /**
     * Checks equality between dates
     * @param other the date to compare
     * @return true if day, month and year are the same
     */
    public boolean equals(Date other)
    {
        return this._day == other._day && this._month == other._month && this._year == other._year;
    }

    /**
     * Checks priority between dates
     * @param other the date to check if this date is before
     * @return true if this date is before the other
     */
    public boolean before(Date other)
    {
        boolean before = false;
        if(other._year > this._year)
            before = true;
        else if(other._year == this._year)
        {
            if(other._month > this._month)
            {
                before = true;
            }
            else if(other._month == this._month)
            {
                if(other._day > this._day)
                {
                    before = true;
                }
            }
        }
        return before;
    }

    /**
     * Checks priority between dates
     * @param other the date to check if this date is after
     * @return true if this date is after the other
     */
    public boolean after(Date other)
    {
        return other.before(this);
    }

    /**
     * Checks difference in days between dates
     * @param other the date to check the difference for
     * @return the number of days between this and the other date
     */
    public int difference(Date other)
    {
        return Math.abs(calculateDate(other) - calculateDate(this));
    }

    /**
     * Translate values into readable Format
     * @return String that represents this date in the following format: "dd/mm/yyyy" (27/07/1984)
     */
    public String toString()
    {
        String date = "";
        if(this._day < MIN_DOUBLE_DIGIT)
        {
            date += "0";
        }
        date += this._day + "/";
        if(this._month < MIN_DOUBLE_DIGIT)
        {
            date += "0";
        }
        date += this._month + "/" + this._year;
        return date;   
    }

    /**
     * Gets the Date of tomorrow for this date
     * @return new Date instance with tomrrow's date
     */
    public Date tomorrow()
    {
        int newYear = this._year;
        int newMonth = this._month;
        int newDay = this._day + 1;

        if(newDay > getLastDayOfMonth(newMonth, newYear))
        {
            newDay = MIN_DAY;
            newMonth++;
            if(newMonth > DECEMBER)
            {
                newMonth = JANUARY;
                newYear++;
            }
        }
        return new Date(newDay, newMonth, newYear);
    }

    /**
     * Gets the day of the week for this date
     * @return the number of the day in the week (0=Satursay, 1=Sunday, 2=Monday, 3=Tuesday, 4= Wednesday, 5=Thursday, 6=Friday)
     */
    public int dayInWeek()
    {
        int month = _month;
        if(month == JANUARY || month == FEBRUARY)
        {
            month += MAX_MONTH;
        }
        int modYear = _year%100;
        int century = _year/100;
        int dayInWeek = (_day + (26*(month+1))/10 + modYear + modYear/4 + century/4 -2*century)%7;
        return Math.floorMod(dayInWeek, DAYS_IN_WEEK);
    }

    private int getLastDayOfMonth(int month, int year)
    {
        int lastDayInMonth = isLongMonth(month) ? MAX_DAY : SHORT_MONTH_DAYS;
        if(month == FEBRUARY)
        {
            if(isLeapYear(year))
            {
                lastDayInMonth = DAYS_IN_FEBRUARY_LEAP;
            }
            else
            {
                lastDayInMonth = DAYS_IN_FEBRUARY;
            }
        }
        return lastDayInMonth;
    }

    private int calculateDate(Date date)
    {
        return calculateDate(date._day, date._month, date._year);
    }

    //computes the day number since the beginning of the Christian counting of years
    private int calculateDate(int day, int month, int year)
    {
        if(month < MARCH)
        {
            year--;
            month += MAX_MONTH;
        }
        return 365 * year +year/4 -year/100 + year/(400) + ((month+1) * 306)/10 + (day - 62);
    }

    private void setDefault()
    {
        this._day = DEFAULT_DAY;
        this._month = DEFAULT_MONTH;
        this._year = DEFAULT_YEAR;
    }

    private boolean isMonthValid(int month)
    {
        return month >=MIN_MONTH && month <= MAX_MONTH;
    }

    private boolean isYearValid(int year)
    {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    private boolean isLeapYear(int year)
    {
        boolean leap = false;
        leap = year % LEAP_JUMP == 0;
        leap = leap & ((year % CENTURY != 0) || (year % CENTURY == 0 && year % (LEAP_JUMP*CENTURY) == 0));
        return leap;
    }

    private boolean isLongMonth(int month)
    {
        return 
        month == JANUARY ||
        month == MARCH ||
        month == MAY ||
        month == JULY ||
        month == AUGUST ||
        month == OCTOBER ||
        month == DECEMBER; 

    }

    private boolean isDateValid(Date date)
    {
        return isDateValid(date._day, date._month, date._year);
    }

    private boolean isDateValid(int day, int month, int year)
    {
        if(isYearValid(year))
        {
            if(isMonthValid(month))
            {
                if(isLongMonth(month))
                {
                    return day >=MIN_DAY && day <= MAX_DAY;
                }
                else
                {
                    if(month == FEBRUARY)
                    {
                        if(isLeapYear(year))
                        {
                            return day >= MIN_DAY && day <= DAYS_IN_FEBRUARY_LEAP;
                        }
                        else
                        {
                            return day >= MIN_DAY && day <= DAYS_IN_FEBRUARY;
                        }
                    }
                    else
                    {
                        return day >= MIN_DAY && day <= SHORT_MONTH_DAYS;
                    }
                }
            }
        }
        return false;
    }
}
