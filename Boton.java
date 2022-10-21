import greenfoot.*;

public class Boton extends Actor
{
    private int accion;
    
    public Boton(int accion) {
        switch (accion) {
            case 0: // Play
                //setImage(new GreenfootImage("-v1.png"));
                System.out.println("Aun no");
                break;
            case 1: // Instrucciones
                //setImage(new GreenfootImage("-v1.png"));
                System.out.println("Aun no");
                break;
            case 2: // Continuar
                setImage(new GreenfootImage("continuar-v2.png"));
                break;
            case 3: // Reintentar
                setImage(new GreenfootImage("reintentar-v2.png"));
                break;
            case 4: // Volver
                setImage(new GreenfootImage("volver-v2.png"));
                break;
            default:                
                System.out.println("Aun no");
        }
        this.accion = accion;
    }
    
    public void act()
    {
        clickMouse();
    }
    
    public void clickMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if (mouse!=null && mouse.getButton()==1 && Greenfoot.mouseClicked(null)) { // Click izquierdo
            Boton botonAccion = (Boton)mouse.getActor();
            
            switch (botonAccion.getAccion()) {
                case 0: // Play
                    System.out.println("Play");
                    break;
                case 1: // Instrucciones
                    System.out.println("Instrucciones");
                    break;
                case 2: // Continuar
                    Mapa mapa = (Mapa)getWorld();
                    mapa.pause(1);
                    break;
                case 3: // Reintentar
                    System.out.println("Reintentar");
                    break;
                case 4: // Volver
                    System.out.println("Volver");
                    break;
                default:                
                    System.out.println("Aun no");
            }
        }
    }
    
    public int getAccion() {
        return this.accion;
    }
}
