import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BrickWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BrickWall extends Obstacle
{
    /**
     * Act - do whatever the BrickWall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (this.isTouching(Fire.class)) {
            World world = this.getWorld();
            world.removeObject(this);
        }
    }
}
