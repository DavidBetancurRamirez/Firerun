import greenfoot.*;
import java.util.ArrayList;

public class Mapa extends World
{
    protected long tiempoInicial, tiempoTranscurrido;
    protected int tiempoEntreLava = 30;
    protected int sizeNumPuerta = 20;
    protected int sizeInfo = 25;
    protected int sizeCodigo = 16;
    private int sizeMensaje = 14;
    protected int cantidadBombas = 1;
    protected boolean lava, hayUltimaPista, pause;
    protected boolean canFire = true;
    
    protected Pause ventanaPause;
    protected Tiempo cronometro, tiempoLava;
    protected Texto2 mensaje = new Texto2("Crees poder llegar al trofeo...\n sin quemarte",sizeMensaje);
    protected ArrayList<Codigo> codigos = new ArrayList<Codigo>();
    protected ArrayList<Texto1> informacion = new ArrayList<Texto1>(); // [balas, bombas]
    protected ArrayList<Puerta> puertas = new ArrayList<Puerta>();
    
    public Mapa(int ancho, int alto, int escala)
    {
        super(ancho, alto, escala); 
    }
    
    public void cambiarMapa() {
        if (lava) {
            Muro.fuego = false;
            Puerta.fuego = false;
            cambiarInformacion(1);
            setBackground(new GreenfootImage("Mapa1-normal-v7.png"));
        } else {
            Muro.fuego = true;
            Puerta.fuego = true;
            cambiarInformacion(2);
            setBackground(new GreenfootImage("Mapa1-lava-v7.png"));
        }
        lava=!lava;
    }
    
    public void cambiarInformacion(int opcion) {
        // Opcion 1: Cambiar colores de mapa-normal
        // Opcion 2: Cambiar colores de mapa-lava
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
    
    public Puerta crearPuerta(int rotacion, int numPuerta) {
        Puerta puerta = new Puerta(rotacion,numPuerta);
        puertas.add(puerta);
        return puerta;
    }
    
    public void pause() {
        pause = !pause;
        Enemigo.pause = !Enemigo.pause;
        Jugador.pause = !Jugador.pause;
        Armas.pause = !Armas.pause;
    }
    
    public void pause(int x) {
        pause();
        if (pause) {
            ventanaPause = new Pause();
            addObject(ventanaPause, 500, 300);
        }  else removeObject(ventanaPause);
    }
    
    public boolean isLava() {
        return this.lava;
    }
    public void setLava() {
        this.lava=!lava;
        cambiarMapa();
    }
    
    public boolean isPause() {
        return this.pause;
    }
    public void setPause() {
        this.pause = !pause;
    }
    
    public boolean isHayUltimaPista() {
        return this.hayUltimaPista;
    }
    public void setHayUltimaPista() {
        this.hayUltimaPista = !hayUltimaPista;
    }
    
    public Texto2 getMensaje() {
        return this.mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje.setTexto(mensaje);
    }
    
    public Puerta getPuerta(int puerta) {
        return this.puertas.get(puerta-1);
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
