import greenfoot.*;

public class Jugador extends Actor
{
    protected Mapa mapa;
    protected int velocidad = 2;
    protected boolean[] canFire = new boolean[4]; // [e,q,p,espace]
    protected int direccionDisparo=1;
    protected boolean portaLlave = false;
    protected static boolean pause = false;
    
    public void act()
    {
        
    }
    
    public boolean isPortaLlave() {
        return this.portaLlave;
    }
    public void setPortaLlave() {
        this.portaLlave = true;
    }
}
