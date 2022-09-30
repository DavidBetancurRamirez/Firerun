import greenfoot.*;
import java.util.List;

public class Jugador1 extends Jugador
{
    protected GifImage gifArriba = new GifImage("personaje hacia arriba.gif");
    protected GifImage gifDerecha = new GifImage("personaje hacia derecha.gif");
    protected GifImage gifAbajo = new GifImage("personaje hacia abajo.gif");
    protected GifImage gifIzquierda = new GifImage("personaje hacia izquierda.gif");
        
    public void act() 
    {
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX()+1,getY());
            setImage(gifDerecha.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX()-1,getY());
            setImage(gifIzquierda.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(),getY()-1);
            setImage(gifArriba.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(),getY()+1);
            setImage(gifAbajo.getCurrentImage());
        }
    }
    
    public void setLocation(int x,int y) {
        int oldX = getX();
        int oldY = getY();
        super.setLocation(x, y);
        
        if(x >= 790 || !getIntersectingObjects(Muro.class).isEmpty()) {
            super.setLocation(oldX, oldY);
        }
    }
}
