import greenfoot.*;
import java.util.List;

public class Jugador1 extends Jugador
{
    private Mapa mapa;
    private boolean canFireQ = true;
    private boolean canFireE = true;
    private boolean canFireP = true;
    private boolean poderDisparar = false;
    int directionDisparo=2;
    
    private GifImage gifArriba = new GifImage("personaje-arriba-v3.gif");
    private GifImage gifDerecha = new GifImage("personaje-derecha-v3.gif");
    private GifImage gifAbajo = new GifImage("personaje-abajo-v3.gif");
    private GifImage gifIzquierda = new GifImage("personaje-izquierda-v3.gif");
    
    public void act() 
    {        
        if (!pause) {
            mapa = (Mapa)getWorld();
            moverse();
            
            //if (mapa.isLava()) lava();
            if (!buscandoLlave && mapa.isHayUltimaPista()) buscandoLlave = true;
        }
        
        acciones();
    }
    
    public void moverse() {
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX()+1,getY());
            setImage(gifDerecha.getCurrentImage());
            directionDisparo=2;
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX()-1,getY());
            setImage(gifIzquierda.getCurrentImage());
            directionDisparo=3;
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(),getY()-1);
            setImage(gifArriba.getCurrentImage());
            directionDisparo=0;
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(),getY()+1);
            setImage(gifAbajo.getCurrentImage());
            directionDisparo=1;
        }
        setDisparo(directionDisparo);
    }
    
    public void acciones() {        
        if (Greenfoot.isKeyDown("e") && canFireE && !getIntersectingObjects(Objeto.class).isEmpty()) { // Acciones varias
            if (getIntersectingObjects(Caja.class).size() > 0) { // Abrir caja
                Caja caja = (Caja)getIntersectingObjects(Caja.class).get(0);                
                caja.accion(mapa);
            }
            
            if (getIntersectingObjects(Puerta.class).size() > 0) { // Abrir puerta
                Puerta puerta = (Puerta)getIntersectingObjects(Puerta.class).get(0);                
                if(!puerta.isAbierto()) puerta.ingresarCodigo(this);
            }
            
            if (getIntersectingObjects(Municion.class).size() > 0) { // Obtener municion
                Municion municion = (Municion)getIntersectingObjects(Municion.class).get(0);
                municion.obtenerMunicion(mapa);
            }
            canFireE = false;
        } else if (!Greenfoot.isKeyDown("q")) {
            canFireE = true;
        }
        
        if (Greenfoot.isKeyDown("q") && canFireQ) { // Colocar bomba            
            if (Integer.parseInt(mapa.getInformacion(1).getExtra()) > 0) {
                mapa.addObject(new Bomba(), getX(), getY()); 
                mapa.setInformacion(Integer.parseInt(mapa.getInformacion(1).getExtra())-1,1);
            }
            canFireQ = false;
        } else if (!Greenfoot.isKeyDown("q")) {
            canFireQ = true;
        }
        
        if (Greenfoot.isKeyDown("p") && canFireP) { // Pause           
            mapa.pause(1);
            canFireP = false;
        } else if (!Greenfoot.isKeyDown("p")) {
            canFireP = true;
        }
    }
    
     public void setDisparo(int direction){
        if(poderDisparar && Greenfoot.isKeyDown("SPACE")){
            Greenfoot.playSound("TiroFirerun.mp3");
            Disparo d1 = new Disparo(direction);
            mapa.addObject(d1, getX(),getY());
            poderDisparar= false;
        }
        if (!poderDisparar && !Greenfoot.isKeyDown("SPACE")){
            poderDisparar = true;
        }
    }
    
    public void lava() {
        if (!mapa.getColorAt(getX(),getY()).equals(new Color(239, 184, 16))) {
            System.out.println("Te has quemado");
            Greenfoot.stop();
        }
    }
    
    public void setLocation(int x,int y) {
        int oldX = getX();
        int oldY = getY();
        super.setLocation(x, y);
        
        // Buscando la llave
        if (buscandoLlave) {
            if (x>=40 && x<=60 && y>=340 && y<=360) {
                mapa.addObject(new Llave(),350,350);
                buscandoLlave = false;
            }
        }
        
        // Colision con limites del mapa
        if(x >= 775 || x<=25 || y<=25 || y>=575) {
            super.setLocation(oldX, oldY);
        }
        
        // Colision con muros
        if (!getIntersectingObjects(Muro.class).isEmpty()) {
            super.setLocation(oldX, oldY);
        }
        
        // Colision con puertas
        if (!getIntersectingObjects(Puerta.class).isEmpty()) {
            Puerta puerta = (Puerta)getIntersectingObjects(Puerta.class).get(0);
            
            if (!puerta.isAbierto()) {
                if (puerta.getRotacion()==0 && Math.abs(puerta.getX()-x)<55) super.setLocation(oldX, oldY);
                if (puerta.getRotacion()==90 && Math.abs(puerta.getY()-y)<55) super.setLocation(oldX, oldY);
            }
        }
    }
}