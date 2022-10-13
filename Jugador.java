import greenfoot.*;

public class Jugador extends Actor
{
    protected boolean buscandoLlave, portaLlave;
    protected static boolean pause = false;
    
    public void act()
    {
        
    }
    
    public boolean isPortaLlave() {
        return this.portaLlave;
    }
    public void setPortaLlave() {
        this.portaLlave = !portaLlave;
    }
}
