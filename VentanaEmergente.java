import greenfoot.*;

public class VentanaEmergente extends Actor
{
    protected Texto texto;
    protected Boton boton;
    
    public void act()
    {
        
    }
    
    public void salir() {
        World world = getWorld();
        
        world.removeObject(boton);
        world.removeObject(this);
    }
    
    public void salir(World world) {
        Mapa mapa = (Mapa)world;
        mapa.pause(false);
        world.removeObject(texto);
        world.removeObject(boton);
        world.removeObject(this);
    }
    
    public Texto getTexto() {
        return this.texto;
    }
    public void setTexto(Texto texto) {
        this.texto = texto;
        getWorld().addObject(texto,650,350);
    }
}
