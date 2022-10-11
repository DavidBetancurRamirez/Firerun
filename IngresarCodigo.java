import greenfoot.*;
import java.util.ArrayList;

public class IngresarCodigo extends VentanaEmergente
{
    private int puerta;
    private String codigoIngresado = "";
    private ArrayList<Texto2> codigo = new ArrayList<Texto2>();
    private boolean[] canFire = new boolean[10];
    
    public IngresarCodigo(int puerta) {
        for(int i = 0; i < 10; i++) canFire[i] = true;
        this.puerta = puerta;
    }
    
    public void act()
    {
        ingresarDigito();
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
            String codigoPuerta = mapa.getCodigo(puerta).getCodigo();
            
            for (int i = 0; i < codigo.size(); i++) {
                    mapa.removeObject(codigo.get(i));
                }
            mapa.removeObject(this);
            
            if (codigoIngresado.equals(codigoPuerta)) mapa.getPuerta(puerta).abrir();
            else mapa.setMensaje("El codigo es incorrecto");
        }
    }
}
