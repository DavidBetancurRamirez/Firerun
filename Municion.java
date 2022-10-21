import greenfoot.*;

public class Municion extends Objeto
{
    private boolean usado;
    
    public void act()
    {
        remover2();
    }
    
    public void obtenerMunicion(Mapa mapa) {
        if (!usado) {
            mapa.setInformacion(3,0);
            mapa.setInformacion(1,1);
            getWorld().removeObject(this);
            usado = true;
        }
    }
}
