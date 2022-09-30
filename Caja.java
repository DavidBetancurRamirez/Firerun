import greenfoot.*;
import java.util.List;

public class Caja extends Objeto
{
    protected boolean abierto = false;
    
    private GifImage gifAbrir = new GifImage("Caja-animacion-v1.gif");
    private GreenfootImage cajaCerrada = new GreenfootImage("Caja-cerrada-v1.png");
    private GreenfootImage ultimaImagen;
    
    public Caja(int rotacion) {
        setRotation(rotacion);
    }
    
    public void act()
    {
        if(abierto) {
            setImage(gifAbrir.getCurrentImage());
            if (getImage() == ultimaImagen) gifAbrir.pause();
        } else {
           setImage(cajaCerrada);
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
    public void setAbierto() {
        this.abierto = !abierto;
    }
}
