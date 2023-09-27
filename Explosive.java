import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosive extends Actor
{
    protected Player owner;
    
    public Explosive(Player owner) {
        this.owner = owner;
    }
    
    /**
     * Act - do whatever the Explosive wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void removeOwner()
    {
        this.owner = null;
    }
}
