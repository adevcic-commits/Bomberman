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
    }
    
    public void showDimensions() 
    {
        this.showText("Width: " + this.getWidth() + " Height: " + this.getHeight(), this.getWidth() / 2, this.getHeight() / 2);
    }
}
