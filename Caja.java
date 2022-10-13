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
            if (getImage() == ultimaImagen) gifAbrir.pause();
            setImage(gifAbrir.getCurrentImage());
        } else {
           setImage(cajaCerrada);
        }        
    }
    
    public void accion(Mapa mapa) {
        if (this.getPuerta().equals("X")) {
            if (!mapa.getCodigo(mapa.codigos.size()-1).getCodigo().endsWith("?")) {
                this.abrir();
                mapa.setHayUltimaPista();
                mapa.setCodigo(this.getCodigo(), mapa.codigos.size());
                mapa.setMensaje("Busca la llave donde estaba\nla munición");
            } else {
                mapa.setMensaje("Aun no puedes abrir esta caja");
            }
        }
                
        if (!this.getPuerta().equals("X") && !this.isAbierto()) {
            this.abrir();
            mapa.setCodigo(this.getCodigo(), Integer.parseInt(this.getPuerta()));
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
        this.codigo = String.valueOf(ran.nextInt(9)+""+ran.nextInt(9)+""+ran.nextInt(9)+""+ran.nextInt(9));
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
