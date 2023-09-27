import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class RandomArena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomArena extends Arena
{

    private Random dice;
    
    /**
     * Constructor for objects of class RandomArena.
     * 
     */
    public RandomArena() {
        super(7, 7);
        this.createRectangleOfWalls(1, 1, 3, 3, 1, 1);
        this.dice = new Random();
    }
    
    public void createRandomWall()
    {
        int randomColumn = this.dice.nextInt(this.getWidth());
        int randomRow = this.dice.nextInt(this.getHeight());   
    }
}
