import greenfoot.*;
import java.util.ArrayList;

public class Enemigo2 extends Enemigo
{
    private ArrayList<Integer> posiciones = new ArrayList<Integer>();
    private int velocidad=2; // Movimiento en pixeles
    private boolean este; // Direccion del movimiento, solo mueve hacia los lados
    
    private GifImage gifDerecha = new GifImage("perro-derecha-v4.gif");
    private GifImage gifIzquierda = new GifImage("perro-izquierda-v4.gif");
    
    public Enemigo2(int posicionInicial, int posicionFinal, boolean este) {
        this.posiciones.add(posicionInicial);
        this.posiciones.add(posicionFinal);
        this.este = este;
    }
    
    public void act() {
        if (!pause) {
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
        
        if(getX()==posiciones.get(0) || getX()==posiciones.get(1)) este = !este;
    }
}
