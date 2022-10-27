import greenfoot.*;

public class FinalJuego extends World
{
    public FinalJuego(int ancho, int alto, int escala)
    {
        super(ancho, alto, escala);
    }
    
    public void crearBotones(int posicionY) {
        addObject(new Boton(3),325,posicionY);
        addObject(new Boton(4),675,posicionY);
    }
}
