import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
            if (this.owner != null) {
                this.owner.bombExploded(this);
            }

            // get a list of players that are in the range of the explosion
            List<Player> hitPlayers = this.getObjectsInRange(this.power, Player.class);

            // get a reference to the arena since it can unregister the player
            Arena arena = (Arena)this.getWorld();

            // we need to iterate over all players efficiently
            for (Player player : hitPlayers) {
                player.hit(); // player was hit by the bomb
            }

            // bomb exploded, remove it from the world
            World world = this.getWorld();

            // create fire in the location of the bomb before we remove it from the world
            world.addObject(new Fire(5), this.getX(), this.getY());
            // spread fire to the right
            int i = 1;
            while (i <= this.power) {
                world.addObject(new Fire(5), this.getX() + i, this.getY());
                i = i + 1;
            }   
            // spread fire to the left
            i = 1;
            while (i <= this.power) {
                world.addObject(new Fire(5), this.getX() - i, this.getY());
                i = i + 1;
            }
            // spread fire upwards
            i = 1;
            while (i <= this.power) {
                world.addObject(new Fire(5), this.getX(), this.getY() - i);
                i = i + 1;
            }
            // spread fire downwards
            i = 1;
            while (i <= this.power) {
                world.addObject(new Fire(5), this.getX(), this.getY() + i);
                i = i + 1;
            }

            world.removeObject(this);
            // play the explosion sound
            Greenfoot.playSound("explosion.wav");         
        }
    }

    public void removeOwner()
    {
        this.owner = null;
    }
    
    private void spreadFire(int deltaX, int deltaY) {
        // initialize fire counter
        int i = 1;
        // calculate the coordinates at which the fire will be placed
        int coordColumn = this.getX() + deltaX;
        int coordRow = this.getY() + deltaY;
        
        World world = this.getWorld();
        while (i <= this.power) {
            world.addObject(new Fire(5), coordColumn, coordRow);
            // update the counter
            i = i + 1;
            // update the coordinates
            coordColumn = coordColumn + deltaX;
            coordRow = coordRow + deltaY;
        }
    }
}
