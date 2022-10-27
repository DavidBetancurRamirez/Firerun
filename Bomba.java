import greenfoot.*;

public class Bomba extends Arma
{
    public void act()
    {
        validarColision();
    }
    
    private void validarColision(){
        Enemigo enemigo = (Enemigo)getOneIntersectingObject(Enemigo.class);
        
        if (enemigo!=null && enemigo.getVivo()){
            Greenfoot.playSound("explosion.mp3");
            setImage("explosion firerun.png");
            enemigo.setVivo(false);
            Greenfoot.delay(8);
            getWorld().removeObject(this);
        }
    }
}
