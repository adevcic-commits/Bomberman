import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arena extends World
{

    private Player player1;
    private Player player2;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Arena()
    {    
        // Create a new world with 25x15 cells with a cell size of 60x60 pixels.
        super(25, 15, 60);

        this.player1 = new Player("up", "down", "right", "left", "space");
        this.addObject(this.player1, 0, 0);
        
        this.player2 = new Player("w", "s", "d", "a", "x");
        this.addObject(this.player2, 24, 14);
    }
}
