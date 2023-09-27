import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

/**
 * Write a description of class RandomArena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomArena extends Arena
{

    private Random dice;
    
    /**
     * Constructor for objects of class RandomArena.
     * 
     */
    public RandomArena() {
        super(7, 7);
        this.createRectangleOfWalls(1, 1, 3, 3, 1, 1);
        this.dice = new Random();

        int brickWallCount = this.getWidth() * this.getHeight() / 3;
        for (int i = 0; i < brickWallCount; i = i + 1) {
            this.createRandomWall();
        }
    }
    
    public void createRandomWall()
    {
        int randomColumn = this.dice.nextInt(this.getWidth());
        int randomRow = this.dice.nextInt(this.getHeight());   
        
        // check if the coordinates are free,
        // if not, we need to generate another ones
        while (!this.isCellFree(randomColumn, randomRow)) {
            // generate new random coordinates
            randomColumn = this.dice.nextInt(this.getWidth());
            randomRow = this.dice.nextInt(this.getHeight());
        }
        
        // coordinates are free so we can insert a brick wall
        this.addObject(new BrickWall(), randomColumn, randomRow);
    }
    
    private boolean isCellFree(int column, int row) {
        List<Actor> list = this.getObjectsAt(column, row, Actor.class);
        return list.isEmpty();
    }
}
