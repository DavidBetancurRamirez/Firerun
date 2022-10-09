import greenfoot.*;

public class Municion extends Objeto
{
    private boolean usado;
    
    public void act()
    {
        
    }
    
    public void obtenerMunicion(Mapa1 mapa1) {
        if (!usado) {
            mapa1.setInformacion(3,0);
            mapa1.setInformacion(1,1);
            usado = true;
        }
    }
}
