import greenfoot.*;
import java.util.ArrayList;

public class Explosion extends Mina
{
    /** How many images should be used in the animation of the explostion */
    private final static int IMAGE_COUNT= 20;
    
    /** 
     * The images in the explosion. This is static so the images are not
     * recreated for every object (improves performance significantly).
     */
    private static GreenfootImage[] images;
    
    /** Current size of the explosion */
    private int size=0;
    
    /** How much do we increment the index in the explosion animation. */
    private int increment=1;    
    
    public Explosion() 
    {
        initialiseImages();
        setImage(images[0]);
        Greenfoot.playSound("explosion-v2.mp3");
    }  
    
    /**
     * EXPLODE!
     */
    public void act()
    { 
        setImage(images[size]);

        size += increment;
        if(size>=IMAGE_COUNT) {
            increment = -increment;
            size += increment;
        }
        
        explodeOthers();
        if(size <= 0) {
            getWorld().removeObject(this);
        }
    }
    
    /** 
     * Create the images for explosion.
     */
    public synchronized static void initialiseImages() 
    {
        if(images == null) {
            GreenfootImage baseImage = new GreenfootImage("explosion firerun.png");
            int maxSize = baseImage.getWidth()*7;
            int delta = maxSize / (IMAGE_COUNT+1);
            int size = 0;
            images = new GreenfootImage[IMAGE_COUNT];
            for(int i=0; i < IMAGE_COUNT; i++) {
                size = size + delta;
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * Intersecting objects.
     */
    private void explodeOthers() 
    {        
        ArrayList<Actor> actors = (ArrayList<Actor>)getIntersectingObjects(Actor.class);        
        
        for (Actor a : actors) {
            if (a instanceof Jugador) {
                Mapa mapa = (Mapa)getWorld();
                Greenfoot.setWorld(new GameOver(mapa));
            } else if (a instanceof Enemigo) {
                Enemigo enemigo = (Enemigo)a;  
                enemigo.setVivo(false);
            }
        }
    }
}