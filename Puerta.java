import greenfoot.*;
import java.util.List;

public class Puerta extends Objeto
{
    protected int numPuerta;
    protected boolean abierto = false;
    protected static boolean fuego = false;
    
    private GifImage gifAbrir = new GifImage("Puerta-normal-animacion-v7.2.gif");
    private GreenfootImage ultimaImagen;
    
    public Puerta(int rotacion, int numPuerta) {
        this.numPuerta = numPuerta;
        setRotation(rotacion);
    }
    
    public void act()
    {
        cambiarImagen();
    }
    
    public void cambiarImagen() {
        if (getImage() == ultimaImagen) {
            gifAbrir.pause();
        } else setImage(gifAbrir.getCurrentImage());
        
        if (fuego) {
            if (abierto) setImage(new GreenfootImage("Puerta-lava-abierta-v1.png"));
            else setImage(new GreenfootImage("Puerta-lava-v3.png"));
        }
        else {
            if (!abierto) setImage(new GreenfootImage("Puerta-normal-v7.png"));
        }
    }
    
    public void abrir() {
        List<GreenfootImage> imagenes = gifAbrir.getImages();
        ultimaImagen = imagenes.get(imagenes.size()-1);
        setImage(gifAbrir.getCurrentImage());
        abierto = true;
    }
    
    public boolean isAbierto() {
        return this.abierto;
    }
    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }
}
