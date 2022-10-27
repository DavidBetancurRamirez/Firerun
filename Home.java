import greenfoot.*;

public class Home extends World
{
    public Home()
    {
        super(1000, 600, 1);
        crearBotones();
    }
    
    public void crearBotones() {
        addObject(new Boton(0),500,350);
        addObject(new Boton(1),500,500);
    }
}
