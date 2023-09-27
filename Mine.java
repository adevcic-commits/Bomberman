import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mine extends Explosive
{
    private int timer;
    
    public Mine(Player owner) {
        super(owner);
        this.timer = 5;
    }

    public void printWhoYouAre() {
        World world = this.getWorld();
        world.showText("MINE", this.getX(), this.getY());
    }

    protected boolean shouldExplode() {
        if (super.shouldExplode()) {
            return true;
        }

        if (this.timer > 0) {
            // if the timer has not expired yet
            // the mine is not active
            // so we just lower the timer
            this.timer = this.timer - 1;
            // consequently, the mine cannot explode
            return false;
        }
        else {
            // the timer has expired, the mine is active
            // it should explode if it touches a plauer
            return this.isTouching(Player.class);
        }
    }

    protected void explosion() {
        World world = this.getWorld();
        world.addObject(new Fire(5), this.getX(), this.getY());
    }
}
