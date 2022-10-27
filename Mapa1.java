import greenfoot.*;
import java.util.ArrayList;

public class Mapa1 extends Mapa
{    
    public Mapa1()
    {
        super(1000, 600, 1, new GreenfootImage("Mapa1-normal-v7.png"), new GreenfootImage("Mapa1-lava-v7.png"));
        preparar();
    }
    
    public void preparar() {        
        // Puertas
        addObject(this.crearPuerta(0,1), 650, 150);
        addObject(this.crearPuerta(0,2), 450, 550);   
        addObject(this.crearPuerta(90,3), 50, 250);     
        addObject(this.crearPuerta(90,4), 350, 250);
        addObject(this.crearPuerta(90,5), 150, 450);
        addObject(this.crearPuerta(90,6), 350, 450);
        addObject(this.crearPuerta(0,7), 650, 350);
        
        addObject(new Texto1("1",size[0]), 650, 150);
        addObject(new Texto1("2",size[0]), 450, 550);
        addObject(new Texto1("3",size[0]), 50, 250);
        addObject(new Texto1("4",size[0]), 350, 250);
        addObject(new Texto1("5",size[0]), 150, 450);
        addObject(new Texto1("6",size[0]), 350, 450);
        
        // Estructura
        addObject(new Muro(), 650, 50);
        
        addObject(new Caja("2", 180), 250, 179);
        addObject(new Muro(), 450, 150);
        addObject(new Caja("X", 270), 520, 150);
        
        addObject(new Muro(), 150, 250);
        addObject(new Muro(), 250, 250);
        addObject(new Muro(), 450, 250);        
        addObject(new Muro(), 550, 250); 
        addObject(new Muro(), 650, 250);
        
        addObject(new Municion(), 50, 350);
        addObject(new Caja("4", 0), 150, 320);
        addObject(new Muro(), 250, 350);
        addObject(new Caja("5", 90), 379, 350);
        addObject(new Muro(), 450, 350);
        addObject(new Trofeo(), 550, 350);
        
        addObject(new Muro(), 50, 450);
        addObject(new Muro(), 250, 450);
        addObject(new Muro(), 450, 450);        
        addObject(new Muro(), 550, 450); 
        addObject(new Muro(), 650, 450);
                
        addObject(new Caja("3", 0), 250, 520);
        addObject(new Caja("1", 0), 650, 520);
                
        // Informacion
        informacion.add(new Texto1("Balas:",size[1],"3"));
        addObject(informacion.get(0), 900, 90);
        informacion.add(new Texto1("Bombas:",size[1],"1"));
        addObject(informacion.get(1), 900, 130);
        
        int anterior = 255;
        for (int i=0; i<6; i++) {
            codigos.add(new Codigo("Codigo puerta"+(i+1)+":",size[2], "??"));
            addObject(codigos.get(i), 900, anterior);
            anterior+=24;
        }
        
        // Enemigos
        int[] posicion;
        spawns.add(new Spawn(new Enemigo1(0,posicion=new int[]{50,50},posicion=new int[]{50,150},posicion=new int[]{350,150},posicion=new int[]{350,50},1), 50, 50));
        spawns.add(new Spawn(new Enemigo2(1,posicion=new int[]{550,0},posicion=new int[]{50,0},false), 550, 40));
        spawns.add(new Spawn(new Enemigo2(2,posicion=new int[]{50,0},posicion=new int[]{350,0},true), 50, 540));
        spawns.add(new Spawn(new Enemigo1(3,posicion=new int[]{550,550},posicion=new int[]{750,550},true), 550, 550));
        
        for (Spawn spawn : spawns) addObject(spawn.getEnemigo(), spawn.getPosicionX(), spawn.getPosicionY());
                
        // Jugadores
        addObject(new Jugador1(), 750, 50);
    }
}

