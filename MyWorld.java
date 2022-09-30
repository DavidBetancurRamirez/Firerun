import greenfoot.*;

public class MyWorld extends World
{
    protected int tamañoNumPuerta = 20;
    protected int tamañoInfo = 30;
    protected boolean lava = false;
    protected boolean canFire = true;
    protected Muro [] muros = new Muro[14];
    
    protected Puerta puertaPruebas = new Puerta(0,1);
    protected Caja cajaPruebas = new Caja(180);
    
    public MyWorld()
    {
        super(1000, 600, 1);
        preparar();
    }
    
    public void act() {
        cambiarMapa();
    }
    
    public void preparar() {
        for(int i=0;i<muros.length;i++) {
            muros[i] = new Muro();
        }       
        // Estructura
        addObject(muros[0], 650, 50);
        
        addObject(cajaPruebas, 250, 179);
        addObject(muros[1], 450, 150);
        addObject(new Caja(270), 520, 150);
        addObject(puertaPruebas, 650, 150);
        addObject(new Texto("1",tamañoNumPuerta), 650, 150);
        
        addObject(new Puerta(90,3), 50, 250);
        addObject(new Texto("3",tamañoNumPuerta), 50, 250);
        addObject(muros[2], 150, 250);
        addObject(muros[3], 250, 250);
        addObject(new Puerta(90,4), 350, 250);
        addObject(new Texto("4",tamañoNumPuerta), 350, 250);
        addObject(muros[4], 450, 250);        
        addObject(muros[5], 550, 250); 
        addObject(muros[6], 650, 250);
        
        addObject(new Municion(), 50, 350);
        addObject(new Caja(0), 150, 320);
        addObject(muros[7], 250, 350);
        addObject(new Caja(90), 379, 350);
        addObject(muros[8], 450, 350);
        addObject(new Trofeo(), 550, 350);
        addObject(new Puerta(0,7), 650, 350);
        
        addObject(muros[9], 50, 450);
        addObject(new Puerta(90,5), 150, 450);
        addObject(new Texto("5",tamañoNumPuerta), 150, 450);
        addObject(muros[10], 250, 450);
        addObject(new Puerta(90,6), 350, 450);
        addObject(new Texto("6",tamañoNumPuerta), 350, 450);
        addObject(muros[11], 450, 450);        
        addObject(muros[12], 550, 450); 
        addObject(muros[13], 650, 450);
                
        addObject(new Caja(0), 250, 520);
        addObject(new Puerta(0,2), 450, 550);
        addObject(new Texto("2",tamañoNumPuerta), 450, 550);
        addObject(new Caja(0), 650, 520);
        
        // Enemigos
        addObject(new Enemigo2(), 50, 50);
        addObject(new Enemigo1(), 550, 50);
        addObject(new Enemigo2(), 50, 550);
        addObject(new Enemigo1(), 750, 550);
        
        // Zona informacion
        addObject(new Texto("Tiempo:",tamañoInfo), 900, 30);
        addObject(new Texto("00:00",tamañoInfo), 900, 60);
        addObject(new Texto("Balas: 3",tamañoInfo), 900, 130);
        addObject(new Texto("Bombas: 1",tamañoInfo), 900, 200);
        addObject(new Texto("Lava: 20",tamañoInfo), 900, 270);
        addObject(new Texto("Mensajes",tamañoInfo), 900, 320);
        
        // Jugador
        addObject(new Jugador1(), 750, 50);
    }
    
    public void cambiarInformacion(int opcion) {
        if (opcion == 1) Texto.color2 = new Color(135,161,171);
        else if (opcion == 2) Texto.color2 = new Color(250,99,81);
    }
    
    public void cambiarMapa() {
        // Probar cambio mapa
        if (Greenfoot.isKeyDown("m") && canFire) {
            if (lava) {
                setBackground(new GreenfootImage("Mapa1-normal-v3.png"));
                for(int i=0;i<muros.length;i++) {
                    muros[i].setImage(new GreenfootImage("Muro-normal-v3.png"));
                }
                Puerta.fuego = false;
                cambiarInformacion(1);
            } else {
                setBackground(new GreenfootImage("Mapa1-lava-v5.png"));
                for(int i=0;i<muros.length;i++) {
                    muros[i].setImage(new GreenfootImage("Muro-lava-v1.png"));
                }
                Puerta.fuego = true;
                cambiarInformacion(2);
            }
            lava = !lava;
            canFire = false;
        } else if (!Greenfoot.isKeyDown("m")) {
            canFire = true;
        }
        
        // Probar animacion puertas
        if (Greenfoot.isKeyDown("n")) {
            if (!lava) puertaPruebas.abrir();
        }
        
        // Probar animacion cajas
        if (Greenfoot.isKeyDown("b")) {
            cajaPruebas.abrir();
        }
    }
}
