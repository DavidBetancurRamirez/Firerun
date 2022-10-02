import greenfoot.*;
import java.util.List;
import java.util.Random;

public class Caja extends Objeto
{
    protected String codigo;
    protected String puerta;
    protected boolean abierto = false;
    
    private GifImage gifAbrir = new GifImage("Caja-animacion-v1.gif");
    private GreenfootImage cajaCerrada = new GreenfootImage("Caja-cerrada-v1.png");
    private GreenfootImage ultimaImagen;
    
    public Caja(String puerta, int rotacion) {
        this.puerta = puerta;
        setRotation(rotacion);
        crearCodigo();
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
    
    public void crearCodigo() {
        Random ran = new Random();
        int numero = ran.nextInt(9999) + 1001;
        this.codigo = String.valueOf(numero);
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getPuerta() {
        return this.puerta;
    }
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }
    
    public boolean isAbierto() {
        return this.abierto;
    }
    public void setAbierto() {
        this.abierto = !abierto;
    }
}
