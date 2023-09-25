import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Class that represents the player.
 *
 * @author Peter
 * @version 1.0
 */
public class Player extends Actor
{
    private String upKey;
    private String downKey;
    private String rightKey;
    private String leftKey;
    private String bombKey;
    private int stepSize;
    private int speed;
    private int counter;
    private int bombPower;
     
    public Player(String upKey, String downKey, String rightKey, String leftKey, String bombKey)
    {
        this(upKey, downKey, rightKey, leftKey, bombKey, 3, 1);
    }

    public Player(String upKey, String downKey, String rightKey, String leftKey, String bombKey,
                  int speed, int bombPower)
    {
        this.upKey = upKey;
        this.downKey = downKey;
        this.rightKey = rightKey;
        this.leftKey = leftKey;
        this.stepSize = stepSize;
        this.bombKey = bombKey;
        this.stepSize = 1;
        this.speed = speed;
        this.counter = 0;
        this.bombPower = bombPower;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.counter = counter + 1;
        if (this.counter == this.speed) {
            this.moveAutomatically();
            this.moveUsingArrows();
            this.updateImage();
            this.counter = 0;
        }
    }
    
    public void updateImage()
    {
        switch (this.getRotation()) {
            case 0:
                this.setImage("right.png");
                break;
            case 90:
                this.setImage("down.png");
                break;
            case 180:
                this.setImage("left.png");
                break;
            case 270:
                this.setImage("up.png");
                break;
            default:
                // we could change the picture to a question mark
        }
    }

    public void moveUsingArrows()
    {
        int x = this.getX();
        int y = this.getY();
        
        if (Greenfoot.isKeyDown(this.leftKey)) {
            this.setRotation(180);
            x = x - 1;
        }
        else {
            if (Greenfoot.isKeyDown(this.rightKey)) {
                this.setRotation(0);
                x = x + 1;
            }
            else {
                if (Greenfoot.isKeyDown(this.upKey)) {
                    this.setRotation(270);
                    y = y - 1;
                }
                else {
                    if (Greenfoot.isKeyDown(this.downKey)) {
                        this.setRotation(90);
                        y = y + 1;
                    }
                } // else “up“
            } // else “right“
        } // else “left“
        
        // After processing the keys the variables x and y contain coordinates of the new player location.
        // Here we test if we can enter the cell.
        if (this.canEnter(x, y)) {
            // Move to the cell at coordinates x, y.
            this.setLocation(x, y);
        }
    }
    
    public boolean canEnter(int x, int y) {
        // First, we get a reference to the world and save it to a local variables
        World world = this.getWorld();
        // We ask the world to give us a list of walls and brick walls at given coordinates.
        List<Wall> walls = world.getObjectsAt(x, y, Wall.class);
        List<BrickWall> brickWalls = world.getObjectsAt(x, y, BrickWall.class);
        // Now, we can ask the lists whether they are empty.
        // If both of them are empty, we can enter the cell, the return value
        // is true if and only if both lists are empty
        return walls.isEmpty() && brickWalls.isEmpty();
    }
    
    public void moveAutomatically()
    {
        if (Greenfoot.isKeyDown("m")) {
            this.move(1);
            // if the player is at the top edge of the world
            if (this.getY() == 0) {
                // set the rotation downwards
                this.setRotation(90);
            } 
            // otherwise
            else {
                // if the player is at the right edge of the world
                if (this.getX() == 24) {
                    // set the rotation to the left
                    this.setRotation(180);
                }
                // otherwise
                else {
                    // if the player is at the bottom edge of the world
                    if (this.getY() == 14) {
                        // set the rotation upwards
                        this.setRotation(270);
                    }
                    //otherwise
                    else {
                        // if the player is at the left edge of the world
                        if (this.getX() == 0) {
                            // set the rotation to the right
                            this.setRotation(0);
                        }
                        // otherwise
                        else {
                            // if the player is touching a brick wall, he will turn 90° right
                            if (this.isTouching(BrickWall.class)) {
                                this.turn(90);
                            }
                            // otherwise
                            else {
                                // if the player is touching a wall, he will turn 90° left
                                if (this.isTouching(Wall.class)) {
                                    this.turn(-90);
                                }
                            } // brick wall touch test
                        } // left edge test
                    } // bottom edge test
                } // right edge test
            } // upper edge test
        }
    }
    
    /**
     * The player moves two cells in its current direction.
     */
    public void makeLongStep()
    {
        this.move(2);
    }
    
    /**
     * The player will walk 5x5 cell square.
     */
    public void walkSquare()
    {
        this.move(5);
        this.turn(90);
        this.move(5);
        this.turn(90);
        this.move(5);
        this.turn(90);
        this.move(5);
        this.turn(90);
    }
}
