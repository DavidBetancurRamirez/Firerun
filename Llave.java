import greenfoot.*;

public class Llave extends Objeto
{
    public Llave() {
        getImage().scale(30,30);
    }
    
    public void act()
    {
        remover2();
        recoger();
    }
    
    public void recoger() {
        if (getIntersectingObjects(Jugador.class).size() > 0) {
            Mapa mapa = (Mapa)getWorld();            
            Jugador jugador = (Jugador)getIntersectingObjects(Jugador.class).get(0);
            
            jugador.setPortaLlave();
            mapa.setMensaje("El ultimo codigo es el de\nla puerta 6, pero...\n¿en cual orden?");
            mapa.removeObject(this);
        }
    }
}
