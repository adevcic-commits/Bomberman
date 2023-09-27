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
            // create an auxiliary variable for the bonus
            Bonus bonus = null;
            // generate number from 0 to 99
            int number = Greenfoot.getRandomNumber(100);
            if (number < 10) {
                // create fire bonus with the probability of 10%
                bonus = new BonusFire();
            }
            else if (number < 20) {
                // create bomb bonus with the probability of 10%
                bonus = new BonusBomb();
            }

            // if neither of the above conditions is satisfied,
            // then we have not generated anything with probability 80%
            // the bonus variable still has the value null
            // if we generated som bonus,
            // we insert it into the world in place of the wall
            World world = this.getWorld();
            if (bonus != null) {
                world.addObject(bonus, this.getX(), this.getY());
            }
            // finally, remove the wall from the world
            world.removeObject(this);
        }
    }
}
