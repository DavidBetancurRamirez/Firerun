import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Disparo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Disparo extends Armas
{
    int position;
    
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
                setLocation(getX(), getY()-1); //arriba
                setImage("bala arriba.png");
                break;
            case 1:
                setLocation(getX(), getY()+1); //abajo
                setImage("bala abajo.png");
                break;
            case 2:
                setLocation(getX()+1, getY()); //derecha
                setImage("bala derecha.png");
                break;
            case 3: 
                setLocation(getX()-1, getY()); //izq
                setImage("bala.png");
                break;
        }
    }
    
    public void remover (){
        Actor bala = getOneIntersectingObject(Enemigo.class);
        if(bala!=null){
            Greenfoot.playSound("muerte.mp3");
            getWorld().removeObject(bala);
            getWorld().removeObject(this);
        }
        else if (getY()==0){
            getWorld().removeObject(this);
        }
        else if (getX()==800){
            getWorld().removeObject(this);
        }
      else if((getX()>=getWorld().getWidth()-5) || (getX()<=5)){
            getWorld().removeObject(this);
        }
        else if ((getY()>=getWorld().getHeight()-5) || (getY()<=5)){
            getWorld().removeObject(this);
        }
    }
}
