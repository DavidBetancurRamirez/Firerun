import greenfoot.*;
import java.util.List;
import java.util.Random;

public class Caja extends Objeto
{
    protected String codigo, puerta;
    protected boolean abriendo, bandera = true, abierto = false;
    protected static boolean puedeAbrirUltima = false;
    
    private GifImage gifAbrir = new GifImage("Caja-animacion-v3.gif");
    private GreenfootImage cajaCerrada = new GreenfootImage("Caja-cerrada-v1.png");
    private GreenfootImage cajaProhibida = new GreenfootImage("Caja-prohibida-v1.png");
    private GreenfootImage ultimaImagen;
    
    public Caja(String puerta, int rotacion) {
        this.puerta = puerta;
        setRotation(rotacion);
        if (puerta.equals("X")) setImage(cajaProhibida);
        crearCodigo();
    }
    
    public void act()
    {        
        if(abriendo) {
            if (getImage() == ultimaImagen) {
                gifAbrir.pause();
                abriendo = false;
            }
            setImage(gifAbrir.getCurrentImage());
        }
    }
    
    public void accion(Mapa mapa) {
        if (puerta.equals("X")) {
            if (puedeAbrirUltima) {
                if (!isAbierto()) {
                    abrir();
                    String texto = "Busca la llave donde estaba\nla munición";
                    mapa.pause();
                    mapa.addObject(new Mensaje(texto),500,300);
                    mapa.setHayUltimaPista();
                    mapa.setCodigo(codigo, mapa.codigos.size());
                    mapa.setMensaje(texto);
                    mapa.setBuscandoLlave(true);
                    Caja.puedeAbrirUltima = false;
                }
            } else {
                mapa.setMensaje("Aun no puedes abrir esta caja");
            }
        } else if (!isAbierto()) {
            abrir();
            
            if (Integer.parseInt(puerta) == mapa.cantidadPuertas()){
                mapa.getCajaX().setImage(cajaCerrada);
                Caja.puedeAbrirUltima = true;
                mapa.setMensaje("Ahora puedes abrir\nla caja misteriosa");
            } else {
                mapa.setCodigo(codigo, Integer.parseInt(puerta));
            }
        }
    }
    
    public void abrir() {
        Greenfoot.playSound("Obtener codigo.mp3");
        List<GreenfootImage> imagenes = gifAbrir.getImages();
        ultimaImagen = imagenes.get(imagenes.size()-1);
        setImage(gifAbrir.getCurrentImage());
        abriendo = true;
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
