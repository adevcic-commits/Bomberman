import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.util.Random;
import java.util.List;

/**
 * Write a description of class Arena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arena extends World
{
    private LinkedList<Player> listOfPlayers;
    private Random dice;

    /**
     * Constructor for objects of class Arena. 
     */
    public Arena(int width, int height)
    {    
        // Create a new world with width x height cells with a cell size of 60x60 pixels.
        super(width, height, 60);

        this.listOfPlayers = new LinkedList<Player>();
        this.dice = new Random();
    }

    public void act()
    {
        if (this.isGameEnded()) {
            Greenfoot.stop();
        }
    }
    
    public void registerPlayer(Player player) 
    {
        // we should not register already registered player
        if (!this.listOfPlayers.contains(player)) {
            this.listOfPlayers.add(player);
        }
    }
    
    public void unregisterAndRemovePlayer(Player player)
    {
        this.listOfPlayers.remove(player);
        this.removeObject(player);
    }

    /**
     * Creates row of walls as described by the parameters.
     * @param rowNumber specifies row in which the wall should be created. The top row is 0.
     * @param startColumnNumber specifies column, in which the wall should start. The leftmost column is 0.
     * @param cellCount specifies how many walls should be created.
     * @param spaces specifies the number of empty cells in between walls.
     */
    public void createRowOfWalls(int rowNumber, int startColumnNumber, int cellCount, int spaces)
    {
        for (int i = 1; i <= cellCount; i = i + 1) {
            //we must not forget to subtract 1 in the calculation of x coordinate
            this.addObject(new Wall(), startColumnNumber + (i - 1) *  (spaces + 1), rowNumber);
        }
    }
    
    /**
     * Creates rectangle of walls as described by the parameters.
     * @param rowNumber specifies row of the top left corner. The top row is 0.
     * @param columnNumber specifies column of the top left corner. The leftmost column is 0.
     * @param rowCount specifies the number of rows.
     * @param inRowCount specifies the number of walls in a row.
     * @param rowSpace specifies the number of empty rows in between rows occupied by walls.
     * @param columnSpace specifies the number of empty cells in between walls in a single column.
     */
    public void createRectangleOfWalls(int rowNumber, int columnNumber, int rowCount,
                                       int inRowCount, int rowSpace, int columnSpace)
    {
        for (int i = 1; i <= rowCount; i = i + 1) {
            this.createRowOfWalls(rowNumber + (i - 1) * (rowSpace + 1), columnNumber, inRowCount, columnSpace);
        }
    }
    
    public boolean isGameEnded() 
    {
        // game ends when there is at most one player left
        return this.listOfPlayers.size() <= 1;
    }
    
    public void insertActorRandomly(Actor actor)
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
        
        // coordinates are free so we can insert the actor
        this.addObject(actor, randomColumn, randomRow);
    }
    
    private boolean isCellFree(int column, int row) {
        List<Actor> list = this.getObjectsAt(column, row, Actor.class);
        return list.isEmpty();
    }
}
