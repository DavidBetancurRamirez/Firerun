import greenfoot.*;

public class Mensaje extends VentanaEmergente
{
    private String texto;
    private boolean primerVez=true;
    
    private GifImage gif = new GifImage("mensaje-v1.gif");
    
    public Mensaje(String texto) {
        this.texto = texto;
    }
    
    public void act()
    {
        setImage(gif.getCurrentImage());
        if (primerVez) {
            this.boton = new Boton(8,this);
            setTexto(new Texto(texto,35,Color.BLACK));
            getWorld().addObject(boton,200,400);
            primerVez=false;
        }
        if (Greenfoot.isKeyDown("enter")) salir(getWorld());
    }
}
