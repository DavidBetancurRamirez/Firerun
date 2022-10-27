import greenfoot.*;
import java.util.List;

public class Jugador1 extends Jugador
{
    private Mapa mapa;
    private int velocidad = 2;
    private boolean[] canFire = new boolean[4]; // [e,q,p,espace]
    private int direccionDisparo=2;
    
    private GifImage gifArriba = new GifImage("personaje-arriba-v3.gif");
    private GifImage gifDerecha = new GifImage("personaje-derecha-v3.gif");
    private GifImage gifAbajo = new GifImage("personaje-abajo-v3.gif");
    private GifImage gifIzquierda = new GifImage("personaje-izquierda-v3.gif");
    
    public void act() 
    {        
        if (!pause) {
            mapa = (Mapa)getWorld();
            moverse();
            
            if (mapa.isLava()) lava();
            if (!buscandoLlave && mapa.isHayUltimaPista()) buscandoLlave = true;
        }
        
        acciones();
    }
    
    public void moverse() {
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX()+velocidad,getY());
            setImage(gifDerecha.getCurrentImage());
            direccionDisparo=2;
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX()-velocidad,getY());
            setImage(gifIzquierda.getCurrentImage());
            direccionDisparo=3;
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(),getY()-velocidad);
            setImage(gifArriba.getCurrentImage());
            direccionDisparo=0;
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(),getY()+velocidad);
            setImage(gifAbajo.getCurrentImage());
            direccionDisparo=1;
        }
        setDisparo(direccionDisparo);
    }
    
    public void acciones() {        
        if (Greenfoot.isKeyDown("e") && canFire[0] && !getIntersectingObjects(Objeto.class).isEmpty()) { // Acciones varias
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
            canFire[0] = false;
        } else if (!Greenfoot.isKeyDown("q")) {
            canFire[0] = true;
        }
        
        if (Greenfoot.isKeyDown("q") && canFire[1]) { // Colocar bomba
            if (Integer.parseInt(mapa.getInformacion(1).getExtra()) > 0) {
                mapa.addObject(new Bomba(), getX(), getY());
                mapa.setInformacion(Integer.parseInt(mapa.getInformacion(1).getExtra())-1,1);
            }
            canFire[1] = false;
        } else if (!Greenfoot.isKeyDown("q")) {
            canFire[1] = true;
        }
        
        if (Greenfoot.isKeyDown("p") && canFire[2]) { // Pause           
            mapa.pause(1);
            canFire[2] = false;
        } else if (!Greenfoot.isKeyDown("p")) {
            canFire[2] = true;
        }
    }
    
     public void setDisparo(int direction){
        if(canFire[3] && Greenfoot.isKeyDown("SPACE")){
            if (Integer.parseInt(mapa.getInformacion(0).getExtra()) > 0) {
                Greenfoot.playSound("TiroFirerun.mp3");
                Disparo disparo = new Disparo(direction);
                mapa.addObject(disparo, getX(),getY());
                mapa.setInformacion(Integer.parseInt(mapa.getInformacion(0).getExtra())-1,0);
            }
            canFire[3]= false;
        }
        if (!canFire[3] && !Greenfoot.isKeyDown("SPACE")){
            canFire[3] = true;
        }
    }
    
    public void lava() {
        if (!mapa.getColorAt(getX(),getY()).equals(new Color(239, 184, 16))) {
            Greenfoot.setWorld(new GameOver());
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