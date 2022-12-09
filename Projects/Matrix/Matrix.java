
/**
 * A class representing a matrix
 *
 * @author Shahar Schneider
 * @version 1.0
 */
public class Matrix
{
    private int[][] _values;
    private final int MIN_VALUE = 0;
    private final int MAX_VALUE = 255;
    private final int MAX_NEIGHBOURS = 8;    
    private final int DEFAULT_VALUE = -1;
    private final int UP = 0;
    private final int UP_RIGHT = 1;
    private final int RIGHT = 2;
    private final int DOWN_RIGHT = 3;
    private final int DOWN = 4;
    private final int DOWN_LEFT = 5;
    private final int LEFT = 6;
    private final int UP_LEFT = 7;

    //return number of cols
    private int numCols()
    {
        return _values[0].length;
    }

    //return number of rows
    private int numRows()
    {
        return _values.length;
    }

    //get value of a specific neighbour, by value of neighborPos
    private int getNeighbourValue(int row, int col, int neighborPos)
    {
        boolean outOfBounds = false;
        int neighbourValue = DEFAULT_VALUE;
        switch(neighborPos)
        {
            case UP:
            outOfBounds = row==0;
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row-1][col];
            break;
            case UP_RIGHT:
            outOfBounds = (row==0 || col==numCols()-1);
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row-1][col+1];
            break;
            case RIGHT:
            outOfBounds = (col==numCols()-1);
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row][col+1];
            break;
            case DOWN_RIGHT:
            outOfBounds = (row==numRows()-1 || col==numCols()-1);
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row+1][col+1];
            break;
            case DOWN:
            outOfBounds = (row==numRows()-1 );
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row+1][col];
            break;
            case DOWN_LEFT:
            outOfBounds = (row==numRows()-1 || col==0);
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row+1][col-1];
            break;
            case LEFT:
            outOfBounds = (col==0);
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row][col-1];
            break;
            case UP_LEFT:
            outOfBounds = (row==0 || col==0);
            neighbourValue = outOfBounds ? DEFAULT_VALUE : _values[row-1][col-1];
            break;
            default:
            break;
        }
        return neighbourValue;
    }

    //return array of all adjacent neighbours
    private int[] getNeighbours(int row, int col)
    {
        int[] neighbours = new int[MAX_NEIGHBOURS];
        int counter = MAX_NEIGHBOURS;

        for(int i=0; i<MAX_NEIGHBOURS; i++)
        {
            int value = getNeighbourValue(row, col, i);
            neighbours[i] = value;
            if(value==DEFAULT_VALUE)
            {
                counter--;
            }
        }
        
        int[] actualNeighbours = new int[counter+1];
        for(int i=0, j=0; i<MAX_NEIGHBOURS; i++)
        {
            if(neighbours[i]!=DEFAULT_VALUE)
            {
                actualNeighbours[j] = neighbours[i];
                j++;
            }
        }
        actualNeighbours[actualNeighbours.length-1] = _values[row][col];
        return actualNeighbours;
    }

    /**
     * Creates a Matrix object
     * @param array 2D array with rows as the first dimension and columns as the second
     */
    public Matrix(int[][] array)
    {
        _values = new int[array.length][array[0].length];
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array[i].length; j++)
            {
                _values[i][j] = array[i][j];
            }
        }
    }

    /**
     * Creates a Matrix object
     * @param size1 the number of rows
     * @param size2 the number of columns
     */
    public Matrix(int size1, int size2)
    {
        _values = new int[size1][size2];
    }

    /**
     * Translate values into readable Format
     * @return a string representation of the matrix
     */
    public String toString()
    {
        String str = Integer.toString(_values[0][0]);
        for(int i = 0; i <_values.length; i++)
        {
            for(int j = 0; j < _values[i].length; j++)
            {
                if(j==0)
                {
                    if(i==0)
                    {
                        continue;
                    }
                    str += ("\n"+Integer.toString(_values[i][j]));
                }
                else
                {
                    str += ("\t"+Integer.toString(_values[i][j]));
                }

            }
        }
        return str;
    }

    /**
     * Creates a negative image of the original
     * @return a negative (complement) matrix with each value being changed to 255-original_value
     */
    public Matrix makeNegative()
    {
        int[][] negativeValues = new int[_values.length][_values[0].length];
        for(int i = 0; i <_values.length; i++)
        {
            for(int j = 0; j < _values[i].length; j++)
            {
                negativeValues[i][j] = MAX_VALUE - _values[i][j];
            }
        }
        return new Matrix(negativeValues);
    }

    /**
     * Creates a smoothed/blurred image of the original
     * @return a matrix with each value being averaged to itself + all of its surrounding neighbours
     */
    public Matrix imageFilterAverage()
    {
        int rows = numRows();
        int cols = numCols();
        int[][] _blurredValues = new int[rows][cols];
        for(int i =0; i < rows; i++)
        {
            for(int j = 0; j <cols; j++)
            {
                int[] neighbours = getNeighbours(i, j);
                int average = 0;
                for(int k=0; k<neighbours.length; k++)
                {
                    average += neighbours[k];
                }
                average /= neighbours.length;
                _blurredValues[i][j] = average;
            }
        }
        return new Matrix(_blurredValues);
    }
    
    /**
     * rotates the image 90 degrees clockwise
     * @return a transposed matrix of the original
     */
    public Matrix rotateClockwise()
    {
        
        int[][] rotatedValues = new int[numCols()][numRows()];
        for(int i = 0; i<_values.length; i++)
        {
            for(int j=0; j<_values[i].length; j++)
            {
                rotatedValues[j][_values.length-1-i] = _values[i][j];
            }
        }
        return new Matrix(rotatedValues);
    }
    
    /**
     * rotates the image 90 degrees counter-clockwise
     * @return a reversed transposed matrix of the original
     */
    public Matrix rotateCounterClockwise()
    {
        int[][] rotatedValues = new int[numCols()][numRows()];
        for(int i = 0; i<_values.length; i++)
        {
            for(int j=0; j<_values[i].length; j++)
            {
                rotatedValues[_values[i].length-1-j][i] = _values[i][j];
            }
        }
        return new Matrix(rotatedValues);
    }
}
