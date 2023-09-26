import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arena extends World
{
    /**
     * Constructor for objects of class Arena. 
     */
    public Arena(int width, int height)
    {    
        // Create a new world with width x height cells with a cell size of 60x60 pixels.
        super(width, height, 60);
    }

    /**
     * Creates row of walls as described by the parameters.
     * @param rowNumber specifies row in which the wall should be created. The top row is 0.
     * @param startColumnNumber specifies column, in which the wall should start. The leftmost column is 0.
     * @param cellCount specifies how many walls should be created.
     * @param spaces specifies the number of empty cells in between walls.
     */
    public void createRowOfWalls(int rowNumber, int startColumnNumber, int cellCount, int spaces)
    {
        for (int i = 1; i <= cellCount; i = i + 1) {
            //we must not forget to subtract 1 in the calculation of x coordinate
            this.addObject(new Wall(), startColumnNumber + (i - 1) *  (spaces + 1), rowNumber);
        }
    }
    
    /**
     * Creates rectangle of walls as described by the parameters.
     * @param rowNumber specifies row of the top left corner. The top row is 0.
     * @param columnNumber specifies column of the top left corner. The leftmost column is 0.
     * @param rowCount specifies the number of rows.
     * @param inRowCount specifies the number of walls in a row.
     * @param rowSpace specifies the number of empty rows in between rows occupied by walls.
     * @param columnSpace specifies the number of empty cells in between walls in a single column.
     */
    public void createRectangleOfWalls(int rowNumber, int columnNumber, int rowCount,
                                       int inRowCount, int rowSpace, int columnSpace)
    {
        for (int i = 1; i <= rowCount; i = i + 1) {
            this.createRowOfWalls(rowNumber + (i - 1) * (rowSpace + 1), columnNumber, inRowCount, columnSpace);
        }
    }
    
    public boolean isGameEnded() 
    {
        // for now, assume that the game never ends
        return false;
    }
}
