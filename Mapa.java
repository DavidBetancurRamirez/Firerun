import greenfoot.*;
import java.util.ArrayList;

public class Mapa extends World
{
    protected ArrayList<Codigo> codigos = new ArrayList<Codigo>();
    protected ArrayList<Texto1> informacion = new ArrayList<Texto1>(); // [balas, bombas]
    protected boolean lava = false;
    
    public Mapa(int ancho, int alto, int escala)
    {
        super(ancho, alto, escala); 
    }
    
    public void cambiarInformacion(int opcion) {
        if (opcion == 1) {
            Texto1.color2 = new Color(135,161,171);
            Texto2.color2 = new Color(102,111,136);
            for (int i=0;i<codigos.size();i++) {
                codigos.get(i).setColor2(new Color(102,111,136));
            }
        } else if (opcion == 2) {
            Texto1.color2 = new Color(250,99,81);
            Texto2.color2 = new Color(225,68,70);
            for (int i=0;i<codigos.size();i++) {
                codigos.get(i).setColor2(new Color(225,68,70));
            }
        }
    }
    
    public void cambiarMapa() {
        if (lava) {
            setBackground(new GreenfootImage("Mapa1-normal-v7.png"));
            Muro.fuego = false;
            Puerta.fuego = false;
            cambiarInformacion(1);
        } else {
            setBackground(new GreenfootImage("Mapa1-lava-v7.png"));
            Muro.fuego = true;
            Puerta.fuego = true;
            cambiarInformacion(2);
        }
        lava=!lava;
    }
    
    public boolean isLava() {
        return this.lava;
    }
    public void setLava() {
        this.lava=!lava;
        cambiarMapa();
    }
    
    public Codigo getCodigo(int puerta) {
        return this.codigos.get(puerta-1);
    }
    public void setCodigo(String codigo, int puerta) {
        this.codigos.get(puerta-1).setCodigo(codigo, puerta);
    }
    
    public Texto1 getInformacion(int index) {
        return this.informacion.get(index);
    }
    public void setInformacion(int informacion, int index) {
        this.informacion.get(index).setExtra(String.valueOf(informacion));
    }
}
