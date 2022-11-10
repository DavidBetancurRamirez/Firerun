import greenfoot.*;
import java.util.List;

public class Puerta extends Objeto
{
    private int numPuerta, rotacion;
    private boolean abierto, ingresandoCodigo;
    protected static boolean fuego = false;
    
    private GifImage gifAbrir = new GifImage("Puerta-normal-animacion-v7.2.gif");
    private GreenfootImage ultimaImagen;
    
    public Puerta(int rotacion, int numPuerta) {
        this.numPuerta = numPuerta;
        this.rotacion = rotacion;
        setRotation(rotacion);
    }
    
    public void act()
    {
        if (!abierto) remover2();
        cambiarImagen();
    }
    
    public void cambiarImagen() {
        if (getImage() == ultimaImagen) gifAbrir.pause();
        else setImage(gifAbrir.getCurrentImage());
        
        if (fuego) {
            if (abierto) setImage(new GreenfootImage("Puerta-lava-abierta-v1.png"));
            else setImage(new GreenfootImage("Puerta-lava-v3.png"));
        }
        else {
            if (!abierto) setImage(new GreenfootImage("Puerta-normal-v7.png"));
        }
    }
    
    public void abrir() {
        Greenfoot.playSound("sonido-puerta-v1.mp3");
        List<GreenfootImage> imagenes = gifAbrir.getImages();
        ultimaImagen = imagenes.get(imagenes.size()-1);
        setImage(gifAbrir.getCurrentImage());
        abierto = true;
    }
    
    public void ingresarCodigo(Jugador jugador) {
        getWorld().addObject(new IngresarCodigo(numPuerta, jugador),400,300);
    }
    
    public int getRotacion() {
        return this.rotacion;
    }
    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }
    
    public boolean isAbierto() {
        return this.abierto;
    }
    public void setAbierto() {
        this.abierto = !abierto;
    }
}
