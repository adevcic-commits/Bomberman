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
        
        // Creates rectangle of walls using method from the Arena superclass.
        this.createRectangleOfWalls(1, 1, 3, 3, 1, 1);
    }
    
    public void showDimensions() 
    {
        this.showText("Width: " + this.getWidth() + " Height: " + this.getHeight(), this.getWidth() / 2, this.getHeight() / 2);
    }
}
