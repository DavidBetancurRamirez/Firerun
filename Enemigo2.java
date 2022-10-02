import greenfoot.*;

public class Enemigo2 extends Enemigo
{
    private GifImage gifDerecha = new GifImage("perro-derecha-v4.gif");
    private GifImage gifIzquierda = new GifImage("perro-izquierda-v4.gif");
    
    public void act() {
        moverse();
    }
    
    public void moverse() {
        if (movimiento) setImage(gifDerecha.getCurrentImage());
    }
}
