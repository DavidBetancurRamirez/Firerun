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
    private ArrayList<Texto2> codigo = new ArrayList<Texto2>();
    
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
        if (Greenfoot.isKeyDown("escape")) salir();
    }
    
    public void ingresarDigito() {        
        for (int i = 0; i < 10; i++) {
            if (Greenfoot.isKeyDown(String.valueOf(i)) && canFire[i]) {
                if (codigoIngresado.length() < 4) {
                    codigoIngresado = codigoIngresado + i;                    
                    codigo.add(new Texto2(String.valueOf(i),200));
                    getWorld().addObject(codigo.get(codigoIngresado.length()-1),100+(codigoIngresado.length()*120),300);
                }
                canFire[i] = false;
            } else if (!Greenfoot.isKeyDown(String.valueOf(i))) canFire[i] = true;
        }
        
        if (codigoIngresado.length() >= 4) {
            Mapa mapa = (Mapa)getWorld();
            String codigoPuerta;
            
            if (puerta==7) {
                codigoPuerta = mapa.getCodigo(6).getCodigo();
                StringBuilder codigoPuertaInvertido = new StringBuilder(codigoPuerta);
                codigoPuerta = codigoPuertaInvertido.reverse().toString();
            } else {
                codigoPuerta = mapa.getCodigo(puerta).getCodigo();
            }
            
            if (codigoIngresado.equals(codigoPuerta)) {
                
                if (puerta!=7) mapa.getPuerta(puerta).abrir();
                else {
                    if (jugador.isPortaLlave()) mapa.getPuerta(puerta).abrir(); 
                    else mapa.setMensaje("Debes portar la llave para ganar");
                }
                
            } else mapa.setMensaje("El codigo es incorrecto");
            
            salir();
        }
    }
    
    public void borrar() {
        if (Greenfoot.isKeyDown("backspace") && canFireB) { // Borrar numero          
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
