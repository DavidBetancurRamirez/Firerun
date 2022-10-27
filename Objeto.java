import greenfoot.*;

public class Objeto extends Actor
{    
    public void act()
    {
        
    }
    
     public void remover2(){
        Actor obj = getOneIntersectingObject(Disparo.class);
        
        if(obj!=null)  getWorld().removeObject(obj);
    }
}
