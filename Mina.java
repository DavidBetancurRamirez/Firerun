import greenfoot.*;

public class Mina extends Arma
{
    public void act()
    {
        validarColision();
    }
    
    private void validarColision(){
        Enemigo enemigo = (Enemigo)getOneIntersectingObject(Enemigo.class); 
        
        if (enemigo!=null && enemigo.getVivo()){
            getWorld().addObject(new Explosion(),this.getX(),this.getY());
            getWorld().removeObject(this);
        }
    }
}
