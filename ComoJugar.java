import greenfoot.*;

public class ComoJugar extends VentanaEmergente
{
    private boolean primerVez=true;
    private GifImage gif = new GifImage("como-se-juega-v3.gif");
    
    public void act()
    {
        setImage(gif.getCurrentImage());
        if (primerVez) {
            this.boton = new Boton(7,this);
            getWorld().addObject(boton,800,120);
            primerVez=false;
        }
        if (Greenfoot.isKeyDown("enter")) salir();
    }
}
