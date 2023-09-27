import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mine extends Actor
{
    private Player owner;
    private int timer;
    
    public Mine(Player owner) {
        this.owner = owner;
        this.timer = 5;
    }
    
    /**
     * Act - do whatever the Mine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        boolean shouldExplode = false;
        // the mine explodes when it touches fire
        if (this.isTouching(Fire.class)) {
            shouldExplode = true;
        }
        // the mine does not touch fire
        else {
            if (this.timer > 0) {
                // if the timer has not expired yet
                // the mine is not active
                // so we just lower the timer
                this.timer = this.timer - 1;
            }
            else {
                // the timer has expired, the mine is active
                // now we check whether some player stands on the mine
                if (this.isTouching(Player.class)) {
                    shouldExplode = true;
                }
            }
        }

        if (shouldExplode) {
            // we let the player know that the mine exploded
            if (this.owner != null) {
                this.owner.mineExploded(this);
            }
            // create a fire in place of the mine
            // before we remove it from the world
            // (so that its coordinates are still available)
            World world = this.getWorld();
            world.addObject(new Fire(5), this.getX(), this.getY());
            // remove the mine from the world
            world.removeObject(this);
        }
    }
    
    public void removeOwner()
    {
        this.owner = null;
    }
}
