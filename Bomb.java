import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private Player owner;
    private int power;
    private int timer;
    
    public Bomb(Player owner, int power, int timer) {
        this.owner = owner;
        this.power = power;
        this.timer = timer;
    }
    
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.timer = this.timer - 1;
        if (this.timer == 0) {
            this.owner.bombExploded(this);
            // bomb exploded, remove it from the world
            World world = this.getWorld();
            world.removeObject(this);
            // play the explosion sound
            Greenfoot.playSound("explosion.wav");         
        }
    }
}
