import greenfoot.*;

public abstract class Enemigo extends Actor
{    
    public void act()
    {
        
    }
    
    public void validarColision() {
        Actor jugador = getOneIntersectingObject(Jugador1.class);
        
        if (jugador != null) {
            System.out.println("you lose");
            Greenfoot.stop();
        }
    }
}
