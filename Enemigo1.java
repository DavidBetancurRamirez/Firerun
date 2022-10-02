import greenfoot.*;

public class Enemigo1 extends Enemigo
{
    private GifImage gifArriba = new GifImage("monstruo1-arriba-v2.gif");
    private GifImage gifAbajo = new GifImage("monstruo1-abajo-v2.gif");
    
    public void act() {
        moverse();
    }
    
    public void moverse() {
        if (movimiento) setImage(gifAbajo.getCurrentImage());
    }
}
