import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestArena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestArena extends Arena
{

    /**
     * Constructor for objects of class TestArena.
     * 
     */
    public TestArena()
    {
        super(7, 7);
        
        // Create a line of walls not using a loop
        /*
        this.addObject(new Wall(), 1, 3);
        this.addObject(new Wall(), 2, 3);
        this.addObject(new Wall(), 3, 3);
        this.addObject(new Wall(), 4, 3);
        this.addObject(new Wall(), 5, 3);
        */

        // Create a line of walls not using for loop
        
        for (int i = 1; i <= 5; i = i + 1) {
            this.addObject(new Wall(), i, 3);
        }
        
    }
    
    public void showDimensions() 
    {
        this.showText("Width: " + this.getWidth() + " Height: " + this.getHeight(), this.getWidth() / 2, this.getHeight() / 2);
    }
}
