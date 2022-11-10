import greenfoot.*;
import java.util.ArrayList;

public class Pause extends VentanaEmergente
{
    protected ArrayList<Boton> botones = new ArrayList<Boton>();
    protected Mapa mapa;
    private boolean primerVez=true;
    
    public void act()
    {
        if (primerVez) {
            crearBotones();
            primerVez=false;
        }
        if (Greenfoot.isKeyDown("enter")) {
            mapa = (Mapa)getWorld();
            borrarBotones();
            mapa.pause(1);
        }
    }
    
    public void crearBotones() {  
        mapa = (Mapa)getWorld();
        
        botones.add(new Boton(2));
        botones.add(new Boton(3));
        botones.add(new Boton(6));
        botones.add(new Boton(4));
        
        mapa.addObject(botones.get(0), 325, 320);
        mapa.addObject(botones.get(1), 675, 320);
        mapa.addObject(botones.get(2), 325, 410);
        mapa.addObject(botones.get(3), 675, 410);
    }
    
    public void borrarBotones() {        
        for (Boton boton : botones) mapa.removeObject(boton);
        botones.clear();
    }
}
