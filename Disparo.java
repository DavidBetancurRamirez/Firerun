import greenfoot.*;

public class Disparo extends Arma
{
    private int velocidad=5;
    private int position;
    
    public Disparo(int direction){
        position = direction;
    }
    public void act()
    {
        MovimientoBala();
        remover();
    }
    
    public void MovimientoBala(){
        switch (position){
            case 0:
                setLocation(getX(), getY()-velocidad); //arriba
                setImage("bala arriba.png");
                break;
            case 1:
                setLocation(getX(), getY()+velocidad); //abajo
                setImage("bala abajo.png");
                break;
            case 2:
                setLocation(getX()+velocidad, getY()); //derecha
                setImage("bala derecha.png");
                break;
            case 3: 
                setLocation(getX()-velocidad, getY()); //izq
                setImage("bala.png");
                break;
        }
    }
    
    public void remover () {
        Enemigo enemigo = (Enemigo)getOneIntersectingObject(Enemigo.class);
        
        if(enemigo!=null && enemigo.getVivo()){
            Greenfoot.playSound("muerte.mp3");
            getWorld().removeObject(this);
            enemigo.setVivo(false);
        }else if(getY()==0 || getX()>=800 || (getX()>=getWorld().getWidth()-5) || (getX()<=5) || (getY()>=getWorld().getHeight()-5) || (getY()<=5)) {
            getWorld().removeObject(this);
        }
    }
}
