import greenfoot.*;

public class Boton extends Actor
{
    private int accion, nivel;
    private boolean bandera = true;
    
    private World world;
    private Mapa mapa;
    private VentanaEmergente ventanaEmergente;
    
    public Boton() {}
    
    public Boton(int accion) {
        this.accion = accion;
        selectImagen();
    }
    
    public Boton(int accion, int nivel) {
        this.accion = accion;
        this.nivel = nivel;
        selectImagen();
    }
    
    public Boton(int accion, VentanaEmergente ventanaEmergente) {
        this.accion = accion;
        this.nivel = nivel;
        this.ventanaEmergente = ventanaEmergente;
        selectImagen();
    }
    
    public void act()
    {
        clickMouse();
    }
    
    public void clickMouse() {
        if (Greenfoot.mouseClicked(this)) {
            world = getWorld();
            
            switch (this.accion) {
                case 0: // Play
                    Greenfoot.setWorld(new SeleccionarNivel());
                    break;
                case 1: // Como jugar
                    world.addObject(new ComoJugar(),500,300);
                    break;
                case 2: // Continuar
                    mapa = (Mapa)getWorld();
                    mapa.pause(1);
                    break;
                case 3: // Reintentar
                    reintentar();                        
                    break;
                case 4: // Volver (En cualquier momento)                    
                    if (world instanceof Mapa) Greenfoot.setWorld(new SeleccionarNivel());
                    else Greenfoot.setWorld(new Home());
                    break;
                case 5: // Niveles
                    selectNivelNumero();
                    break;
                case 6: // Como jugar
                    world.addObject(new ComoJugar(),500,300);
                case 7: // Volver (Durante juego, ventana como jugar)
                    if (ventanaEmergente!=null) ventanaEmergente.salir();
                    break;
                case 8: // Volver (ventana mensaje)
                    if (ventanaEmergente!=null) ventanaEmergente.salir(world);
                    break;
            } 
            Greenfoot.playSound("sonidos-varios-v1.mp3");  
        }
    }
    
    public void selectImagen() {
        switch (accion) {
            case 0: // Jugar
                setImage(new GreenfootImage("play-v3.png"));
                break;
            case 1: // Como jugar (Inicio)
                setImage(new GreenfootImage("como-jugar-v3.png"));
                break;
            case 2: // Continuar
                setImage(new GreenfootImage("continuar-v2.png"));
                break;
            case 3: // Reintentar
                setImage(new GreenfootImage("reintentar-v2.png"));
                break;
            case 4: // Volver (En cualquier momento)
                setImage(new GreenfootImage("volver-v3.png"));
                break;
            case 5: // Nivel
                setImage(new GreenfootImage("nivel-v1.png"));
                break;
            case 6: // Como jugar (En pause)
                setImage(new GreenfootImage("como-jugar-v2.png"));
                break;
            case 7: // Volver (Durante juego, ventana como jugar)
                setImage(new GreenfootImage("volver-v3.png"));
                break;
            case 8: // Volver (Durante juego, ventana como jugar)
                setImage(new GreenfootImage("volver-v3.png"));
                break;
        }
    }
    
    public void selectNivelNumero() {
        if (this.nivel == 0) Greenfoot.setWorld(new Tutorial());
        else if (this.nivel == 1) Greenfoot.setWorld(new Mapa1());
        else System.out.println("Error al reintentar nivel");
    }
    
    public void selectNivelMundo() {
        if (mapa instanceof Tutorial) Greenfoot.setWorld(new Tutorial());
        else if (mapa instanceof Mapa1) Greenfoot.setWorld(new Mapa1());
        else System.out.println("Error al reintentar nivel");
    }
    
    public void reintentar() {
        if (bandera) world = getWorld();
                        
        if (world instanceof FinalJuego) {
            FinalJuego finalJuego = (FinalJuego)world;
            mapa = finalJuego.getMapa();
        } else if (world instanceof Mapa) {
            mapa = (Mapa)world;
        }
        
        bandera = true;
        selectNivelMundo();
    }
    
    public void reintentar(World world) {
        this.world = world;
        bandera = false;
        reintentar();
    }
    
    public int getAccion() {
        return this.accion;
    }
}
