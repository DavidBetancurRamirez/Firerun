import greenfoot.*;
import java.util.ArrayList;

public class Enemigo2 extends Enemigo
{
    private GifImage gifDerecha = new GifImage("perro-derecha-v4.gif");
    private GifImage gifIzquierda = new GifImage("perro-izquierda-v4.gif");
    
    public Enemigo2(int indice, int[] posicionInicial, int[] posicionFinal, boolean este) {        
        super(indice,4,false,new GreenfootImage("perro-muerto.png"), new GifImage("revivir-perro-oeste.gif"), new GifImage("revivir-perro-este.gif"));
        this.posiciones.add(posicionInicial);
        this.posiciones.add(posicionFinal);
        this.este = este;
        this.primerEste = este;
    }
    
    public void act() {
        if (reviviendo) {
            int index = 0;
            if (this instanceof Enemigo2 && este) index = 1;
            setImage(gifRevivir.get(index).getCurrentImage());
            if (getImage() == ultimaImagen) {
                crearNuevoMonstruo();
                vivo = true;
                reviviendo = false;
            }
        }
        
        if (!vivo) mantenerMuerto();
        else if (!pause) {
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
