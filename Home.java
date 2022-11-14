import greenfoot.*;

public class Home extends World
{  
    public Home()
    {
        super(1000, 600, 1);
        crearBotones();
    }
    
    public void crearBotones() {
        addObject(new Boton(0),217,352);
        addObject(new Boton(1),317,490);
    }
}
