import greenfoot.*;

public class Spawn extends Actor
{
    private int posicionX, posicionY;
    private Enemigo enemigo;
    
    public Spawn(Enemigo enemigo, int posicionX, int posicionY) {
        this.enemigo = enemigo;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    
    public int getPosicionX() {
        return this.posicionX;
    }
    
    public int getPosicionY() {
        return this.posicionY;
    }
    
    public Enemigo getEnemigo() {
        return this.enemigo;
    }
}
