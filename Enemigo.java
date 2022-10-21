import greenfoot.*;

public abstract class Enemigo extends Actor
{    
    protected static boolean pause = false;
    
    public void act()
    {
        
    }
    
     public void validarColision() {
        Actor jugador = getOneIntersectingObject(Jugador1.class);
        
        if (jugador != null) {
        
        getWorld().removeObject(jugador);
        getWorld().removeObject(this);
        Greenfoot.playSound("muerteJugador.mp3");
        //Greenfoot.stop();
        Greenfoot.setWorld(new GameOverPrin());
        }
    }
}
