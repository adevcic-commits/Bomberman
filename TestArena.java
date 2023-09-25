import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestArena here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestArena extends Arena
{

    /**
     * Constructor for objects of class TestArena.
     * 
     */
    public TestArena()
    {
        super(7, 7);
        
        // Creates three rows of walls using for loop
        
        // creates walls in the second row at positions 0-4.
	for (int i = 0; i <= 4; i++) {
		this.addObject(new Wall(), i, 1);
	}

	// creates walls in the fourth row at positions 1-5.
	for (int i = 1; i <= 5; i++) {
		this.addObject(new Wall(), i, 3);
	}

	// creates walls in the sixth row at positions 2-6.
	for (int i = 2; i <= 6; i++) {
		this.addObject(new Wall(), i, 5);
	}	
    }
    
    public void showDimensions() 
    {
        this.showText("Width: " + this.getWidth() + " Height: " + this.getHeight(), this.getWidth() / 2, this.getHeight() / 2);
    }
}
