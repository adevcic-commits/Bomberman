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
        // check whether explosive should explode (subclass will provide an answer)
        if (this.shouldExplode()) {
            // the explosive should explode so
            // we notify the owner that one of his explosives exploded
            if (this.owner != null) {
                this.owner.explosiveExploded(this);
            }
            // we let subclasses to respond (create fires)
            this.explosion();
            // finally we remove the explosive from the world
            World world = this.getWorld();
            world.removeObject(this);
        }
    }
    
    public void removeOwner()
    {
        this.owner = null;
    }

    public void printWhoYouAre() {
        World world = this.getWorld();
        world.showText("EXPLOSIVE", this.getX(), this.getY());
    }

    protected boolean shouldExplode() {
        // this clas is stable - it never explodes
        return false;
    }

    protected void explosion() {
    }
}
