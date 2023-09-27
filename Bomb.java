import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Explosive
{
    private int power;
    private int timer;
    
    public Bomb(Player owner, int power, int timer) {
        super(owner);
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
        if (this.timer == 0 || this.isTouching(Fire.class)) {
            if (super.owner != null) {
                super.owner.bombExploded(this);
            }

            // bomb exploded, remove it from the world
            World world = this.getWorld();

            // create fire in the location of the bomb before we remove it from the world
            world.addObject(new Fire(5), this.getX(), this.getY());
            this.spreadFire(+1, 0); // spread the fire to the right
            this.spreadFire(-1, 0); // spread the fire to the left
            this.spreadFire(0, -1); // spread the fire upwards
            this.spreadFire(0, +1); // spread the fire downwards

            world.removeObject(this);
            // play the explosion sound
            Greenfoot.playSound("explosion.wav");         
        }
    }
    
    private void spreadFire(int deltaX, int deltaY) {
        // initialize fire counter
        int i = 1;
        // calculate the coordinates at which the fire will be placed
        int coordColumn = this.getX() + deltaX;
        int coordRow = this.getY() + deltaY;
        
        World world = this.getWorld();
        while (i <= this.power && this.canCellExplode(coordColumn, coordRow)) {
            world.addObject(new Fire(5), coordColumn, coordRow);
            
            // test whether the explosion can continue
            if (this.canExplosionContinue(coordColumn, coordRow)) {
                // update the counter
                i = i + 1;
                // update the coordinates
                coordColumn = coordColumn + deltaX;
                coordRow = coordRow + deltaY;
            }
            else {
                // if the explosion cannot continue we increase the value of the variable i
                // this will make the condition of the while loop evaluate to false
                i = this.power + 1;
            }
        }
    }

    private boolean canCellExplode(int x, int y) {
        World world = this.getWorld();
        if (x >= 0 && x < world.getWidth() &&
            y >= 0 && y < world.getHeight()) {
            List<Wall> walls = world.getObjectsAt(x, y, Wall.class);
            return walls.isEmpty();
        }
        else {
            // coordinates do not belong to the world
            return false;
        }
    }

    private boolean canExplosionContinue(int x, int y) {
        World world = this.getWorld();
        if (x >= 0 && x < world.getWidth() &&
            y >= 0 && y < world.getHeight()) {
            List<BrickWall> brickWalls = world.getObjectsAt(x, y, BrickWall.class);
            return brickWalls.isEmpty();
        }
        else {
            // coordinates do not belong to the world
            return false;
        }
    }
}
