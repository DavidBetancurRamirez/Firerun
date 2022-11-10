import greenfoot.*;

public class GameOver extends FinalJuego
{    
    public GameOver(Mapa mapa)
    {
        super(mapa);
        int[] posicion;
        crearBotones(posicion=new int[]{325,675},posicion=new int[]{520,520});
    }
    
    public void act() {
        reintentarNivel();
    }
}
