import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private int stepSize;
    private int speed;
    private int counter;
     
    public Player(String upKey, String downKey, String rightKey, String leftKey)
    {
        this(upKey, downKey, rightKey, leftKey, 3);
    }

    public Player(String upKey, String downKey, String rightKey, String leftKey, int speed)
    {
        this.upKey = upKey;
        this.downKey = downKey;
        this.rightKey = rightKey;
        this.leftKey = leftKey;
        this.stepSize = stepSize;
        this.stepSize = 1;
        this.speed = speed;
        this.counter = 0;
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
            this.move(this.stepSize);
            x = x - 1;
        }
        else {
            if (Greenfoot.isKeyDown(this.rightKey)) {
                this.setRotation(0);
                this.move(this.stepSize);
                x = x + 1;
            }
            else {
                if (Greenfoot.isKeyDown(this.upKey)) {
                    this.setRotation(270);
                    this.move(this.stepSize);
                    y = y - 1;
                }
                else {
                    if (Greenfoot.isKeyDown(this.downKey)) {
                        this.setRotation(90);
                        this.move(this.stepSize);
                        y = y + 1;
                    }
                } // else “up“
            } // else “right“
        } // else “left“
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
