import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bonus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bonus extends Actor
{
    /**
     * Act - do whatever the Bonus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        Player player = (Player)this.getOneIntersectingObject(Player.class);
        if (player != null) {
            this.applyYourself(player);
            World world = this.getWorld();
            world.removeObject(this);
        }
    }
    
    protected void applyYourself(Player hrac) {
    }
}