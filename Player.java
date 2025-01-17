import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.LinkedList;

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
    private String mineKey;
    private int stepSize;
    private int speed;
    private int counter;
    private int bombPower;
    private int bombCount;
    private int mineCount;
    private LinkedList<Explosive> listOfActiveExplosives;
     
    public Player(String upKey, String downKey, String rightKey, String leftKey, String bombKey, String mineKey)
    {
        this(upKey, downKey, rightKey, leftKey, bombKey, mineKey, 3, 1, 2, 1);
    }

    public Player(String upKey, String downKey, String rightKey, String leftKey, String bombKey, String mineKey,
                  int speed, int bombPower, int bombCount, int mineCount)
    {
        this.upKey = upKey;
        this.downKey = downKey;
        this.rightKey = rightKey;
        this.leftKey = leftKey;
        this.stepSize = stepSize;
        this.bombKey = bombKey;
        this.mineKey = mineKey;
        this.stepSize = 1;
        this.speed = speed;
        this.counter = 0;
        this.bombPower = bombPower;
        this.bombCount = bombCount;
        this.mineCount = mineCount;
        this.listOfActiveExplosives = new LinkedList<Explosive>();
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // if the player is touching a fire, the he is hit and wont respond to any controls
        if (this.isTouching(Fire.class)) {
            this.hit();
        }
        else {
            this.counter = counter + 1;
            if (this.counter == this.speed) {
                this.moveAutomatically();
                this.moveUsingArrows();
                this.updateImage();
                this.counter = 0;
            }

            if (this.canPlantBomb()) {
                Bomb bomb = new Bomb(this, this.bombPower, 90); // create an instance of the Bomb class
                World world = this.getWorld(); // get a reference to the world
                world.addObject(bomb, this.getX(), this.getY()); // insert the bomb into the world
                this.bombCount = this.bombCount - 1; // lower the bomb count
                this.listOfActiveExplosives.add(bomb); // register the bomb
            }

            if (this.canPlantMine()) {
                Mine mine = new Mine(this); // create an instance of the Mine class
                World world = this.getWorld(); // get a reference to the world
                world.addObject(mine, this.getX(), this.getY()); // insert the mine into the world
                this.mineCount = this.mineCount - 1; // lower the mine count
                this.listOfActiveExplosives.add(mine); // register the mine
            }
        }
    }

    public void increaseBombCount() {
        this.bombCount = this.bombCount + 1;
    }
    
    public void increaseBombPower() {
        this.bombPower = this.bombPower + 1;
    }

    public void hit() {
        Arena arena = (Arena)this.getWorld();
        arena.unregisterAndRemovePlayer(this);
        for (Explosive explosive : this.listOfActiveExplosives) {
            explosive.removeOwner();
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
        // First, we get a reference to the world and save it to a local variable.
        World world = this.getWorld();
        // We ask the world to give us a list of obstacles at given coordinates.
        List<Obstacle> obstacles = world.getObjectsAt(x, y, Obstacle.class);
        // Now, we can ask the list whether it is empty.
        // If it is empty, we can enter the cell, the return value
        // is the same as the value of obstacles.isEmpty().
        return obstacles.isEmpty();
    }
    
    public boolean canPlantBomb()
    {
        if (!Greenfoot.isKeyDown(this.bombKey) || this.bombCount == 0) {
            // if the key is not pressed or the player has no bombs the method ends right here
            return false;
        }

        // if the key is pressed, the cell must be empty
        int x = this.getX();
        int y = this.getY();
        World world = this.getWorld();
        List<Bomb> bombs = world.getObjectsAt(x, y, Bomb.class);
        return bombs.isEmpty();
    }
    
    public boolean canPlantMine() 
    {
        if (!Greenfoot.isKeyDown(this.mineKey) || this.mineCount == 0) {
            // if the key si not pressed or the player has no mines, the method ends
            return false;
        }
        
        // if the key is pressed the cell muset be empty
        int x = this.getX();
        int y = this.getY();
        World world = this.getWorld(); 
        List<Mine> mines = world.getObjectsAt(x, y, Mine.class);
        return mines.isEmpty();
    }
    
    public void explosiveExploded(Explosive explosive) {
        if (explosive instanceof Bomb) {
            this.bombCount = this.bombCount + 1;
        }
        else if (explosive instanceof Mine) {
            this.mineCount = this.mineCount + 1;
        }
        this.listOfActiveExplosives.remove(explosive);
    }

    public void showDimensions()
    {
        // get a reference to the world
        World world = this.getWorld();
        // check whether the player is inside test arena
        if (world instanceof TestArena) {
            // safe typecast
            TestArena arena = (TestArena)world;
            // call the right method
            arena.showDimensions();
        }
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
