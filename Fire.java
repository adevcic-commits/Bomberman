import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fire extends Actor
{
    private int timer;
    
    public Fire(int timer) {
        this.timer = timer;
    }
    
    /**
     * Act - do whatever the Fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.timer = this.timer - 1;
        if (this.timer == 0) {
            World world = this.getWorld();
            world.removeObject(this);
        }
    }
}
