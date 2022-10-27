import greenfoot.*;
import java.util.ArrayList;

public class Enemigo extends Actor
{
    protected int indice, segundoMuerte, primerDireccion, tiempoMuerte = 20;
    protected boolean obtenerSegundoMuerte, variasDirecciones;
    protected boolean vivo = true;
    protected boolean primerEste, este; // Direccion del movimiento, solo horizontal
    protected boolean[] direcciones = new boolean[4]; // [norte,sur,este,oeste]
    protected static boolean pause = false;
    
    protected ArrayList<int[]> posiciones = new ArrayList<int[]>();
    
    public Enemigo(int indice, boolean variasDirecciones) {
        this.indice = indice;
        this.variasDirecciones = variasDirecciones;
    }
    
    public void act()
    {
        
    }
    
     public void validarColision() {
        Actor jugador = getOneIntersectingObject(Jugador1.class);
        
        if (jugador != null && vivo) {
            getWorld().removeObject(jugador);
            getWorld().removeObject(this);
            Greenfoot.playSound("muerteJugador.mp3");
            Greenfoot.setWorld(new GameOver());
        }
    }
    
    public void mantenerMuerto() {
        if (!vivo) {
            Mapa mapa = (Mapa)getWorld();
            
            if (!obtenerSegundoMuerte) {
                segundoMuerte = mapa.getCronometro().getSegundosTotales();
                obtenerSegundoMuerte = true;
            }
            
            if (mapa.getCronometro().getSegundosTotales() == (segundoMuerte+tiempoMuerte)) {              
                Spawn spawn = mapa.getSpawn(indice);
                
                if (this.isVariasDirecciones()) {
                    mapa.addObject(new Enemigo1(this.indice,posiciones.get(0),posiciones.get(1),posiciones.get(2),posiciones.get(3),this.primerDireccion), spawn.getPosicionX(), spawn.getPosicionY());
                } else {
                    if (spawn.getEnemigo() instanceof Enemigo1) {
                        mapa.addObject(new Enemigo1(this.indice,posiciones.get(0),posiciones.get(1),this.primerEste), spawn.getPosicionX(), spawn.getPosicionY());
                    } else {
                        mapa.addObject(new Enemigo2(this.indice,posiciones.get(0),posiciones.get(1),this.primerEste), spawn.getPosicionX(), spawn.getPosicionY());
                    }
                }
                
                vivo = true;
                obtenerSegundoMuerte = false;
            }
        }
    }
    
    public boolean getVivo() {
        return this.vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public int getIndice() {
        return this.indice;
    }
    
    public boolean isVariasDirecciones() {
        return this.variasDirecciones;
    }
}
