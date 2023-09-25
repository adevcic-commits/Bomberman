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
     */
    public void createRowOfWalls(int rowNumber, int startColumnNumber, int cellCount)
    {
        for (int i = 1; i <= cellCount; i = i + 1) {
            //we must not forget to subtract 1 in the calculation of x coordinate
            this.addObject(new Wall(), startColumnNumber + (i - 1), rowNumber);
        }
    }
}
