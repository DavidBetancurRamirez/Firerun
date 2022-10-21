import greenfoot.*;

public class Muro extends Objeto
{
    protected static boolean fuego = false;
    
    public void act()
    {
        remover2();
        if (fuego) {
            setImage(new GreenfootImage("Muro-lava-v1.png"));
        }
        else {
            setImage(new GreenfootImage("Muro-normal-v3.png"));
        }
    }
}
