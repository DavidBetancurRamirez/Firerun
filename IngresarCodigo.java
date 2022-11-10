import greenfoot.*;
import java.util.ArrayList;

public class IngresarCodigo extends VentanaEmergente
{
    private Mapa mapa;
    private String codigoIngresado = "";
    private int puerta;
    private boolean canFireB = true;    
    private boolean canFireE = true;
    private boolean[] canFire = new boolean[10];
    
    private Jugador jugador;
    private ArrayList<Texto> codigo = new ArrayList<Texto>();
    
    public IngresarCodigo(int puerta, Jugador jugador) {
        for(int i = 0; i < 10; i++) canFire[i] = true;
        this.puerta = puerta;
        this.jugador = jugador;
    }
    
    public void act()
    {
        mapa = (Mapa)getWorld();
        if (!mapa.isPause()) mapa.pause();
        ingresarDigito();
        borrar();
        if (Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("enter")) salir();
    }
    
    public void ingresarDigito() {        
        for (int i = 0; i < 10; i++) {
            if (Greenfoot.isKeyDown(String.valueOf(i)) && canFire[i]) {
                if (codigoIngresado.length() < 4) {
                    codigoIngresado = codigoIngresado + i;                    
                    codigo.add(new Texto(String.valueOf(i),200,Color.BLACK));
                    getWorld().addObject(codigo.get(codigoIngresado.length()-1),100+(codigoIngresado.length()*120),300);
                }
                canFire[i] = false;
            } else if (!Greenfoot.isKeyDown(String.valueOf(i))) canFire[i] = true;
        }
        
        if (codigoIngresado.length() >= 4) {
            Mapa mapa = (Mapa)getWorld();
            String codigoPuerta;
            
            if (puerta==mapa.cantidadPuertas()) {
                codigoPuerta = mapa.getCodigo(mapa.cantidadPuertas()).getExtra();
                StringBuilder codigoPuertaInvertido = new StringBuilder(codigoPuerta);
                codigoPuerta = codigoPuertaInvertido.reverse().toString();
            } else {
                codigoPuerta = mapa.getCodigo(puerta).getExtra();
            }
            
            if (puerta==mapa.cantidadPuertas() && !jugador.isPortaLlave()) mapa.setMensaje("Debes portar la llave\npara poder ingresar");
            
            else if (codigoIngresado.equals(codigoPuerta)) mapa.getPuerta(puerta).abrir();
                
            else mapa.setMensaje("El codigo es incorrecto");
            
            salir();
        }
    }
    
    public void borrar() {
        if (Greenfoot.isKeyDown("backspace") && canFireB && codigo.size()>0) {       
            mapa.removeObject(codigo.get(codigo.size()-1));
            codigo.remove(codigo.size()-1);
            codigoIngresado = codigoIngresado.substring(0, codigoIngresado.length()-1);
            canFireB = false;
        } else if (!Greenfoot.isKeyDown("backspace")) {
            canFireB = true;
        }
    }
    
    public void salir() {
        for (int i = 0; i < codigo.size(); i++) {
            mapa.removeObject(codigo.get(i));
        }
        mapa.pause();
        mapa.removeObject(this);
    }
}
