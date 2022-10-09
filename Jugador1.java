import greenfoot.*;
import java.util.List;

public class Jugador1 extends Jugador
{
    protected GifImage gifArriba = new GifImage("personaje-arriba-v2.gif");
    protected GifImage gifDerecha = new GifImage("personaje-derecha-v2.gif");
    protected GifImage gifAbajo = new GifImage("personaje-abajo-v2.gif");
    protected GifImage gifIzquierda = new GifImage("personaje-izquierda-v2.gif");
    
    protected boolean canFireQ = true;
    protected boolean canFireE = true;
    
    public void act() 
    {
        acciones();
    }
    
    public void acciones() {
        // Moverse
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            setLocation(getX()+1,getY());
            setImage(gifDerecha.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            setLocation(getX()-1,getY());
            setImage(gifIzquierda.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
            setLocation(getX(),getY()-1);
            setImage(gifArriba.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {
            setLocation(getX(),getY()+1);
            setImage(gifAbajo.getCurrentImage());
        }
        
        // Otros
        if (Greenfoot.isKeyDown("q") && canFireQ) { // Colocar bomba
            World world = getWorld();
            Mapa1 mapa1 = (Mapa1)getWorld();
            
            if (Integer.parseInt(mapa1.getInformacion(1).getExtra()) > 0) {
                getWorld().addObject(new Bomba(), getX(), getY()); 
                mapa1.setInformacion(Integer.parseInt(mapa1.getInformacion(1).getExtra())-1,1);
            }
            canFireQ = false;
        } else if (!Greenfoot.isKeyDown("q")) {
            canFireQ = true;
        }
        
        if (Greenfoot.isKeyDown("e") && canFireE && !getIntersectingObjects(Objeto.class).isEmpty()) { // Acciones varias           
            World world = getWorld();
            Mapa1 mapa1 = (Mapa1)getWorld();
            
            if (getIntersectingObjects(Caja.class).size() > 0) {
                Caja caja = (Caja)getIntersectingObjects(Caja.class).get(0);
                
                if (caja.getPuerta() != "X" && !caja.isAbierto()) {
                    caja.abrir();
                    mapa1.setCodigo(caja.getCodigo(), Integer.parseInt(caja.getPuerta()));
                }
            }
            
            if (getIntersectingObjects(Municion.class).size() > 0) {
                Municion municion = (Municion)getIntersectingObjects(Municion.class).get(0);
                
                municion.obtenerMunicion(mapa1);
            }
            canFireE = false;
        } else if (!Greenfoot.isKeyDown("q")) {
            canFireE = true;
        }
    }
    
    public void setLocation(int x,int y) {
        int oldX = getX();
        int oldY = getY();
        super.setLocation(x, y);
        
        // Colision con limites del mapa
        if(x >= 775 || x<=25 || y<=25 || y>=575) {
            super.setLocation(oldX, oldY);
        }
        
        // Colision con muros
        if (!getIntersectingObjects(Muro.class).isEmpty()) {
            super.setLocation(oldX, oldY);
        }
    }
}