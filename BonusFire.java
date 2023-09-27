import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BonusFire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BonusFire extends Bonus
{
    /**
     * Act - do whatever the BonusFire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

    protected void applyYourself(Player player) {
        player.increaseBombPower();
    }
}
