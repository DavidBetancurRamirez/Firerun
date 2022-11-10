import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Enemigo extends Actor
{
    protected int indice, segundoMuerte, velocidad, primerDireccion, tiempoMuerte = 15;
    protected boolean obtenerSegundoMuerte, variasDirecciones;
    protected boolean vivo = true, reviviendo = false;
    protected boolean primerEste, este; // Direccion del movimiento, solo horizontal
    protected boolean[] direcciones = new boolean[4]; // [norte,sur,este,oeste]
    protected static boolean pause = false;
    
    protected GreenfootImage imagenMuerte, ultimaImagen;
    protected ArrayList<GifImage> gifRevivir = new ArrayList<GifImage>();
    protected ArrayList<int[]> posiciones = new ArrayList<int[]>();
    
    public Enemigo(int indice, int velocidad, boolean variasDirecciones, GreenfootImage imagenMuerte, GifImage gifRevivir) {
        this.indice = indice;
        this.velocidad = velocidad;
        this.variasDirecciones = variasDirecciones;
        this.imagenMuerte = imagenMuerte;
        this.gifRevivir.add(gifRevivir);
    }
    
    public Enemigo(int indice, int velocidad, boolean variasDirecciones, GreenfootImage imagenMuerte, GifImage gifRevivir1, GifImage gifRevivir2) {
        this.indice = indice;
        this.velocidad = velocidad;
        this.variasDirecciones = variasDirecciones;
        this.imagenMuerte = imagenMuerte;
        this.gifRevivir.add(gifRevivir1);
        this.gifRevivir.add(gifRevivir2);
    }
    
     public void validarColision() {
        Actor jugador = getOneIntersectingObject(Jugador1.class);
        
        if (jugador != null && vivo) {
            Mapa mapa = (Mapa)getWorld();
            mapa.removeObject(jugador);
            mapa.removeObject(this);
            Greenfoot.playSound("muerteJugador.mp3");
            Greenfoot.setWorld(new GameOver(mapa));
        }
    }
    
    public void mantenerMuerto() {
        if (!vivo && !reviviendo) {
            Mapa mapa = (Mapa)getWorld();
            
            if (!obtenerSegundoMuerte) {
                setImage(imagenMuerte);
                if (this instanceof Enemigo2 && este) getImage().mirrorHorizontally();
                segundoMuerte = mapa.getCronometro().getSegundosTotales();
                obtenerSegundoMuerte = true;
            }
            
            if (mapa.getCronometro().getSegundosTotales() >= (segundoMuerte+tiempoMuerte)) {
                obtenerSegundoMuerte = false;
                obtenerUltimaImagen();
            }
        }
    }
    
    public void obtenerUltimaImagen() {
        int index = 0;
        if (this instanceof Enemigo2 && este) index = 1;
        List<GreenfootImage> imagenes = gifRevivir.get(index).getImages();
        ultimaImagen = imagenes.get(imagenes.size()-1);
        setImage(gifRevivir.get(index).getCurrentImage());
        reviviendo = true;
        Greenfoot.playSound("sonido-revivir-v1.mp3");
    }
    
    public void crearNuevoMonstruo() {
        Mapa mapa = (Mapa)getWorld();
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
