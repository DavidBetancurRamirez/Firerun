import greenfoot.*;
import java.util.ArrayList;

public class SeleccionarNivel extends World
{
    public SeleccionarNivel()
    {
        super(1000, 600, 1); 
        crearBotones();
    }
    
    public void crearBotones() {
        int tamañoNumero = 40;
        Color colorLetra = Color.WHITE;
        
        addObject(new Boton(5,0),130,150);
        addObject(new Texto("0",tamañoNumero,Color.WHITE),130,150);
        addObject(new Boton(5,1),255,150);
        addObject(new Texto("1",tamañoNumero,Color.WHITE),255,150);
        addObject(new Boton(4),500,368);
    }
}
