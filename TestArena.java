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
        
        // Creates three rows of walls using method from the Arena superclass.
 
        this.createRowOfWalls(1, 0, 5);
	this.createRowOfWalls(3, 1, 5);
	this.createRowOfWalls(5, 2, 5);
    }
    
    public void showDimensions() 
    {
        this.showText("Width: " + this.getWidth() + " Height: " + this.getHeight(), this.getWidth() / 2, this.getHeight() / 2);
    }
}
