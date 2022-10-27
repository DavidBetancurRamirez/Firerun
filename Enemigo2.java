import greenfoot.*;
import java.util.ArrayList;

public class Enemigo2 extends Enemigo
{
    private int velocidad=4; // Movimiento en pixeles
    
    private GifImage gifDerecha = new GifImage("perro-derecha-v4.gif");
    private GifImage gifIzquierda = new GifImage("perro-izquierda-v4.gif");
    
    public Enemigo2(int indice, int[] posicionInicial, int[] posicionFinal, boolean este) {
        super(indice,false);
        this.posiciones.add(posicionInicial);
        this.posiciones.add(posicionFinal);
        this.este = este;
        this.primerEste = este;
    }
    
    public void act() {
        if (!vivo) mantenerMuerto();
        if (!pause && vivo) {
            moverse();
            validarColision();
        }
    }
    
    public void moverse() {
        if (este) {
            setImage(gifDerecha.getCurrentImage());
            setLocation(getX()+velocidad,getY());
        } else {
            setImage(gifIzquierda.getCurrentImage());
            setLocation(getX()-velocidad,getY());
        }
        
        if(getX()==posiciones.get(0)[0] || getX()==posiciones.get(1)[0]) este = !este;
    }
}
