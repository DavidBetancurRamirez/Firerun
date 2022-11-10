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
            
            String texto = "Parece que el ultimo codigo\nno esta ordenado.\n¿Cual orden será?";
            mapa.pause();
            mapa.addObject(new Mensaje(texto),500,300);
            jugador.setPortaLlave();
            mapa.setMensaje(texto);
            mapa.removeObject(this);
        }
    }
}
