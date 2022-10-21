import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverPrin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverPrin extends World
{

    /**
     * Constructor for objects of class GameOverPrin.
     * 
     */
    public GameOverPrin()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
         Greenfoot.playSound("GameOverSound.mp3");
    }
}
