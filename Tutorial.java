import greenfoot.*;

public class Tutorial extends Mapa
{
    public Tutorial()
    {
        super(1000, 600, 1, new GreenfootImage("tutorial-normal-v2.png"), new GreenfootImage("tutorial-lava-v2.png"));
        preparar();
        instrucciones();
    }
    
    public void preparar() {
        tiempoInicial = System.currentTimeMillis();
        
        // Puertas
        addObject(this.crearPuerta(0,1), 250, 50);
        addObject(this.crearPuerta(0,2), 450, 250);
        addObject(this.crearPuerta(90,3), 750, 250);
        
        addObject(new Texto1("1",size[0]), 250, 50);
        addObject(new Texto1("2",size[0]), 450, 250);
        addObject(new Texto1("3",size[0]), 750, 250);
        
        // Estructura
        addObject(new Muro(), 450, 50);
        addObject(new Caja("3", 0), 650, 20);
        
        addObject(new Caja("1", 270), 20, 150);
        addObject(new Muro(), 250, 150);
        addObject(new Muro(), 450, 150);
        
        addObject(new Muro(), 50, 250);
        addObject(new Muro(), 150, 250);
        addObject(new Muro(), 250, 250);
        addObject(new Muro(), 650, 250);
        
        addObject(new Caja("2", 0), 50, 320);
        addObject(new Muro(), 450, 350);
        addObject(new Municion(), 550, 350);
        addObject(new Muro(), 650, 350);
        addObject(new Trofeo(), 750, 350);
        
        addObject(new Muro(), 50, 450);
        addObject(new Muro(), 150, 450);
        addObject(new Muro(), 250, 450);
        addObject(new Muro(), 350, 450);
        addObject(new Muro(), 450, 450);
        addObject(new Muro(), 550, 450);
        addObject(new Muro(), 650, 450);
        addObject(new Muro(), 750, 450);
        
        // Informacion
        informacion.add(new Texto1("Balas:",size[1],"3"));
        addObject(informacion.get(0), 900, 90);
        informacion.add(new Texto1("Bombas:",size[1],"1"));
        addObject(informacion.get(1), 900, 130);
        
        int anterior = 290;
        for (int i=0; i<3; i++) {
            codigos.add(new Codigo("Codigo puerta"+(i+1)+":",size[2], "??"));
            addObject(codigos.get(i), 900, anterior);
            anterior+=24;
        }
          
        // Enemigos
        int[] posicion;
        spawns.add(new Spawn(new Enemigo2(0,posicion=new int[]{50,0},posicion=new int[]{350,0},true), 50, 340));      
        spawns.add(new Spawn(new Enemigo1(1,posicion=new int[]{550,50},posicion=new int[]{550,150},posicion=new int[]{750,150},posicion=new int[]{750,50},1), 550, 50));
        
        for (Spawn spawn : spawns) addObject(spawn.getEnemigo(), spawn.getPosicionX(), spawn.getPosicionY());
           
        // Jugadores
        addObject(new Jugador1(), 50, 50);
    }
    
    public void instrucciones() {
        addObject(new Texto2("W",20), 400,550);
    }
}
