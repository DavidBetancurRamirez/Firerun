import greenfoot.*;

public class Pause extends VentanaEmergente
{    
    public void act()
    {
        if (Greenfoot.isKeyDown("escape")) {
            Mapa mapa = (Mapa)getWorld();
            mapa.pause(1);
        }
    }
}
