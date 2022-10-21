import greenfoot.*;

public class Bomba extends Armas
{
    public void act()
    {
        validarColisionMina();
    }
    private void validarColisionMina(){
        Actor Enes = getOneIntersectingObject(Enemigo.class);
        if(Enes!=null){
            Greenfoot.playSound("explosion.mp3");
            getWorld().removeObject(Enes);
             setImage("explosion firerun.png");
           Greenfoot.delay(8);
            getWorld().removeObject(this);     
        }
    }
}
