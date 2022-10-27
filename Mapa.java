import greenfoot.*;
import java.util.ArrayList;

public class Mapa extends World
{
    protected long tiempoInicial;
    protected int tiempoEntreLava = 30;
    protected int[] size = {20,25,16,14}; // [numPuerta,info,codigo,mensaje]
    protected boolean lava=true, hayUltimaPista, pause;
    protected boolean canFire = true;
    
    protected Pause ventanaPause;
    protected Tiempo cronometro, tiempoLava;
    protected Texto2 mensaje = new Texto2("Crees poder llegar al trofeo...\n sin quemarte",size[3]);
    
    protected ArrayList<GreenfootImage> mapaFondo = new ArrayList<GreenfootImage>(); // [normal, lava]
    protected ArrayList<Codigo> codigos = new ArrayList<Codigo>();
    protected ArrayList<Texto1> informacion = new ArrayList<Texto1>(); // [balas, bombas]
    protected ArrayList<Puerta> puertas = new ArrayList<Puerta>();
    protected ArrayList<Boton> botonesPause = new ArrayList<Boton>(); // [reintentar, volver]
    protected ArrayList<Spawn> spawns = new ArrayList<Spawn>();
    
    public Mapa(int ancho, int alto, int escala, GreenfootImage mapaNormal, GreenfootImage mapaLava)
    {
        super(ancho, alto, escala);
        this.mapaFondo.add(mapaNormal);
        this.mapaFondo.add(mapaLava);
        crearZonaInformacion();
        cambiarMapa();
        pause(false);
    }
    
    public void crearZonaInformacion() {
        tiempoInicial = System.currentTimeMillis();
        
        addObject(new Texto1("Tiempo:",size[1]), 900, 20);
        tiempoLava = new Tiempo("Lava: "+tiempoEntreLava,size[1],tiempoEntreLava,tiempoInicial,false);
        tiempoLava.setSegundos(tiempoEntreLava);
        addObject(tiempoLava, 900, 170);
        cronometro = new Tiempo("00:00",size[1],tiempoEntreLava,tiempoInicial,true);
        addObject(cronometro, 900, 50);
        
        addObject(new Texto1("Codigos",size[1]), 900, 220);
        
        addObject(new Texto1("Mensaje",size[1]), 900, 420);
        addObject(this.mensaje, 900, 465);
    }
    
    public void cambiarMapa() {
        if (lava) {
            Muro.fuego = false;
            Puerta.fuego = false;
            cambiarInformacion(1);
            setBackground(mapaFondo.get(0));
        } else {
            Muro.fuego = true;
            Puerta.fuego = true;
            cambiarInformacion(2);
            setBackground(mapaFondo.get(1));
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
        Arma.pause = !Arma.pause;
    }
    
    public void pause(boolean condicion) {
        pause = condicion;
        Enemigo.pause = condicion;
        Jugador.pause = condicion;
        Arma.pause = condicion;
    }
    
    public void pause(int x) { // Sacar pantalla emergente de pause
        pause();
        
        if (pause) {
            ventanaPause = new Pause();
            botonesPause.add(new Boton(2));
            botonesPause.add(new Boton(3));
            botonesPause.add(new Boton(4));
            
            addObject(ventanaPause, 500, 300);
            addObject(botonesPause.get(0), 325, 320);
            addObject(botonesPause.get(1), 675, 320);
            addObject(botonesPause.get(2), 500, 410);
        }  else {
            removeObject(ventanaPause);
            for (Boton boton : botonesPause) removeObject(boton);
            botonesPause.clear();
        }
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
    
    public Tiempo getCronometro() {
        return this.cronometro;
    }
    
    public Spawn getSpawn(int indice) {
        return this.spawns.get(indice);
    }
}
