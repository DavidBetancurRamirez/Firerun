import greenfoot.*;
import java.util.ArrayList;

public class Enemigo1 extends Enemigo
{
    private int velocidad = 1; // Movimiento en pixeles
    private int coordenadaY;
    private boolean este; // Direccion del movimiento, solo en horizontal
    private boolean variasDirrecciones;
    
    private ArrayList<int[]> posiciones = new ArrayList<int[]>();
    private boolean[] direcciones = new boolean[4]; // [norte,sur,este,oeste]
    
    private GifImage gifArriba = new GifImage("monstruo1-arriba-v2.gif");
    private GifImage gifAbajo = new GifImage("monstruo1-abajo-v2.gif");
    
    // Movimiento en varias direcciones
    public Enemigo1(int[] posicion1, int[] posicion2, int[] posicion3, int[] posicion4, int primerDireccion) {
        this.posiciones.add(posicion1);
        this.posiciones.add(posicion2);
        this.posiciones.add(posicion3);
        this.posiciones.add(posicion4);
        this.direcciones[primerDireccion] = true;
        this.variasDirrecciones = true;
    }
    
    // Para movimiento unicamente horizontal
    public Enemigo1(int[] posicionInicial, int[] posicionFinal, boolean este) {
        this.posiciones.add(posicionInicial);
        this.posiciones.add(posicionFinal);
        this.este = este;
        this.variasDirrecciones = false;
    }
    
    public void act() {
        moverse();
        validarColision();
    }
    
    public void moverse() {
        if (variasDirrecciones) {
            
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
