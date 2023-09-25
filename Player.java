import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that represents the player.
 *
 * @author Peter
 * @version 1.0
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.moveAutomatically();
        this.moveUsingArrows();
    }

    public void moveUsingArrows()
    {
        if (Greenfoot.isKeyDown("left")) {
            this.setRotation(180);
            this.move(1);
        }
        else {
            if (Greenfoot.isKeyDown("right")) {
                this.setRotation(0);
                this.move(1);
            }
            else {
                if (Greenfoot.isKeyDown("up")) {
                    this.setRotation(270);
                    this.move(1);
                }
                else {
                    if (Greenfoot.isKeyDown("down")) {
                        this.setRotation(90);
                        this.move(1);
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
