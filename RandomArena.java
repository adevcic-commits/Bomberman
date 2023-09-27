import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomArena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomArena extends Arena
{

    /**
     * Constructor for objects of class RandomArena.
     * 
     */
    public RandomArena() {
        super(7, 7);
        this.createRectangleOfWalls(1, 1, 3, 3, 1, 1);
    }
    
    public void createRandomWall()
    {
        
    }
}
