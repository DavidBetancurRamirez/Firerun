import greenfoot.*;

public class Trofeo extends Objeto
{
    public void act()
    {
        remover2();
        recoger();
    }
    
    public void recoger() {
        if (getIntersectingObjects(Jugador.class).size() > 0) {
            Mapa mapa = (Mapa)getWorld();            
            Jugador jugador = (Jugador)getIntersectingObjects(Jugador.class).get(0);
            Greenfoot.setWorld(new Congrats(mapa,mapa.getCronometro()));
            mapa.setMensaje("Has ganado!!");
        }
    }
}
