import greenfoot.*;

public class FinalJuego extends World
{
    protected Mapa mapa;
    protected boolean bandera = true;
    
    public FinalJuego(Mapa mapa)
    {
        super(1000, 600, 1);
        this.mapa = mapa;
        Greenfoot.playSound("congratulation-v1.mp3");
    }
    
    public void crearBotones(int[] posicionX, int[] posicionY) {
        addObject(new Boton(3),posicionX[0],posicionY[0]);
        addObject(new Boton(4),posicionX[1],posicionY[1]);
    }
    
    public void reintentarNivel() {
        if (Greenfoot.isKeyDown("enter") && bandera) {
            Boton boton = new Boton();
            boton.reintentar(this);
            bandera = false;
        }
    }
    
    public Mapa getMapa() {
        return this.mapa;
    }
}
