import greenfoot.*;
import java.util.ArrayList;

public class Mapa1 extends World
{
    protected int sizeNumPuerta = 20;
    protected int sizeInfo = 25;
    protected int sizeCodigo = 16;
    
    protected long tiempoInicial, tiempoTranscurrido;
    protected int cantidadBombas = 1;
    protected boolean lava = false;
    protected boolean canFire = true;
    
    protected Texto2 mensaje = new Texto2("Crees poder llegar al trofeo...\n sin quemarte",14);
    protected Cronometro cronometro;
    protected Cronometro tiempoLava;
    
    protected ArrayList<Texto1> informacion = new ArrayList<Texto1>(); // [balas, bombas]
    protected ArrayList<Codigo> codigos = new ArrayList<Codigo>();
    
    protected Puerta puertaPruebas = new Puerta(0,1);
    
    public Mapa1()
    {
        super(1000, 600, 1);
        preparar();
    }
    
    public void act() {
        
    }
    
    public void preparar() {  
        tiempoInicial = System.currentTimeMillis();        
        tiempoTranscurrido = tiempoInicial;
        
        // Estructura
        addObject(new Muro(), 650, 50);
        
        addObject(new Caja("2", 180), 250, 179);
        addObject(new Muro(), 450, 150);
        addObject(new Caja("X", 270), 520, 150);
        addObject(puertaPruebas, 650, 150);
        addObject(new Texto1("1",sizeNumPuerta), 650, 150);
        
        addObject(new Puerta(90,3), 50, 250);
        addObject(new Texto1("3",sizeNumPuerta), 50, 250);
        addObject(new Muro(), 150, 250);
        addObject(new Muro(), 250, 250);
        addObject(new Puerta(90,4), 350, 250);
        addObject(new Texto1("4",sizeNumPuerta), 350, 250);
        addObject(new Muro(), 450, 250);        
        addObject(new Muro(), 550, 250); 
        addObject(new Muro(), 650, 250);
        
        addObject(new Municion(), 50, 350);
        addObject(new Caja("4", 0), 150, 320);
        addObject(new Muro(), 250, 350);
        addObject(new Caja("5", 90), 379, 350);
        addObject(new Muro(), 450, 350);
        addObject(new Trofeo(), 550, 350);
        addObject(new Puerta(0,7), 650, 350);
        
        addObject(new Muro(), 50, 450);
        addObject(new Puerta(90,5), 150, 450);
        addObject(new Texto1("5",sizeNumPuerta), 150, 450);
        addObject(new Muro(), 250, 450);
        addObject(new Puerta(90,6), 350, 450);
        addObject(new Texto1("6",sizeNumPuerta), 350, 450);
        addObject(new Muro(), 450, 450);        
        addObject(new Muro(), 550, 450); 
        addObject(new Muro(), 650, 450);
                
        addObject(new Caja("3", 0), 250, 520);
        addObject(new Puerta(0,2), 450, 550);
        addObject(new Texto1("2",sizeNumPuerta), 450, 550);
        addObject(new Caja("1", 0), 650, 520);
        
        // Enemigos
        int[] posicion;
        addObject(new Enemigo1(posicion=new int[]{50,50},posicion=new int[]{50,150},posicion=new int[]{350,150},posicion=new int[]{350,50},1), 50, 50);
        addObject(new Enemigo2(550,50,false), 550, 40);
        addObject(new Enemigo2(50,350,true), 50, 540);
        addObject(new Enemigo1(posicion=new int[]{750,550},posicion=new int[]{550,550},false), 750, 550);
        
        // Zona informacion
        addObject(new Texto1("Tiempo:",sizeInfo), 900, 20);
        tiempoLava = new Cronometro("Lava: 20",sizeInfo,tiempoInicial,false);
        tiempoLava.setSegundos(20);
        cronometro = new Cronometro("00:00",sizeInfo,tiempoInicial,true);
        addObject(tiempoLava, 900, 170);
        addObject(cronometro, 900, 50);
        informacion.add(new Texto1("Balas:",sizeInfo,"3"));
        addObject(informacion.get(0), 900, 90);
        informacion.add(new Texto1("Bombas:",sizeInfo,"1"));
        addObject(informacion.get(1), 900, 130);
        
        addObject(new Texto1("Codigos",sizeInfo), 900, 220);
        int anterior = 255;
        for (int i=0; i<6; i++) {
            codigos.add(new Codigo("Codigo puerta"+(i+1)+":",sizeCodigo, "??"));
            addObject(codigos.get(i), 900, anterior);
            anterior+=24;
        }
        
        addObject(new Texto1("Mensaje",sizeInfo), 900, 420);
        addObject(mensaje, 900, 465);
        
        // Jugador
        addObject(new Jugador1(), 750, 50);
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
