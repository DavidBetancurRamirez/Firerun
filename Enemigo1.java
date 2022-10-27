import greenfoot.*;
import java.util.ArrayList;

public class Enemigo1 extends Enemigo
{
    private int velocidad = 2; // Movimiento en pixeles
    
    private GifImage gifArriba = new GifImage("monstruo1-arriba-v2.gif");
    private GifImage gifAbajo = new GifImage("monstruo1-abajo-v2.gif");
    
    // Movimiento en varias direcciones
    public Enemigo1(int indice, int[] posicion1, int[] posicion2, int[] posicion3, int[] posicion4, int primerDireccion) {
        super(indice,true);
        this.posiciones.add(posicion1);
        this.posiciones.add(posicion2);
        this.posiciones.add(posicion3);
        this.posiciones.add(posicion4);
        this.primerDireccion = primerDireccion;
        this.direcciones[primerDireccion] = true;
    }
    
    // Para movimiento unicamente horizontal
    public Enemigo1(int indice, int[] posicionInicial, int[] posicionFinal, boolean este) {
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
        if (variasDirecciones) {
            if (direcciones[0]) { // norte
                setImage(gifArriba.getCurrentImage());
                setLocation(getX(),getY()-velocidad);
                if (checkPosicion()) {
                    direcciones[0] = false;
                    direcciones[3] = true;
                }
            } else if (direcciones[1]) { // sur
                setImage(gifAbajo.getCurrentImage());
                setLocation(getX(),getY()+velocidad);
                if (checkPosicion()) {
                    direcciones[1] = false;
                    direcciones[2] = true;
                }
            } else if (direcciones[2]) { // este
                setImage(gifAbajo.getCurrentImage());
                setLocation(getX()+velocidad,getY());
                if (checkPosicion()) {
                    direcciones[2] = false;
                    direcciones[0] = true;
                }
            } else if (direcciones[3]) { // oeste
                setImage(gifAbajo.getCurrentImage());
                setLocation(getX()-velocidad,getY());
                if (checkPosicion()) {
                    direcciones[3] = false;
                    direcciones[1] = true;
                }
            }
        } else {
            if (este) {
                setImage(gifAbajo.getCurrentImage());
                setLocation(getX()+velocidad,getY());
            } else {
                setImage(gifAbajo.getCurrentImage());
                setLocation(getX()-velocidad,getY());
            }
            
            if(getX()==posiciones.get(0)[0] || getX()==posiciones.get(1)[0]) este = !este;   
        }
    }
    
    public boolean checkPosicion() {
        for (int i=0;i<4;i++) {
            if (getX()==posiciones.get(i)[0] && getY()==posiciones.get(i)[1]) return true;
        }
        return false;
    }
}
