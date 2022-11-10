import greenfoot.*;
import java.util.ArrayList;

public class Tutorial extends Mapa
{
    protected String texto;
    protected int paso=1;
    protected boolean[] canFire = new boolean[2]; // [left,right]
    
    protected Texto mensaje,instruccion;
    protected ArrayList<Flecha> flechas = new ArrayList<Flecha>();
    protected ArrayList<int[]> coordenadasFlechas = new ArrayList<int[]>();
    
    public Tutorial()
    {
        super(40, 5, 2, new GreenfootImage("tutorial-normal-v2.png"), new GreenfootImage("tutorial-lava-v2.png"), new GreenfootImage("tutorial-agrietado-v1.png"));
        preparar();
        instrucciones(paso);
    }
    
    public void act(){
        if (tiempoLava.getSegundos() == 5 && !mapaAgrietado) agrietarMapa();
        
        if (paso > 1 && Greenfoot.isKeyDown("left") && canFire[0]) {
            paso--;
            instrucciones(paso);
            canFire[0] = false;
        } else if (!Greenfoot.isKeyDown("left")) {
            canFire[0] = true;
        }
        
        if (paso < 19 && Greenfoot.isKeyDown("right") && canFire[1]) {
            paso++;
            instrucciones(paso);
            canFire[1] = false;
        } else if (!Greenfoot.isKeyDown("right")) {
            canFire[1] = true;
        }
    }
    
    public void preparar() {        
        // Puertas
        this.crearPuerta(0,1,250,50);
        this.crearPuerta(0,2,450,250);
        this.crearPuerta(90,3,750,250);
        
        // Estructura
        addObject(new Muro(), 450, 50);
        cajaX = new Caja("X",0);
        addObject(cajaX, 350, 20);
        addObject(new Caja("3", 0), 650, 20);
        
        addObject(new Caja("1", 270), 20, 150);
        addObject(new Muro(), 250, 150);
        addObject(new Muro(), 450, 150);
        
        addObject(new Muro(), 50, 250);
        addObject(new Muro(), 150, 250);
        addObject(new Muro(), 250, 250);
        addObject(new Muro(), 650, 250);
        
        addObject(new Caja("2", 0), 50, 320);
        addObject(new Muro(), 450, 350);
        addObject(new Municion(), 550, 350);
        addObject(new Muro(), 650, 350);
        addObject(new Trofeo(), 750, 350);
        
        addObject(new Muro(), 50, 450);
        addObject(new Muro(), 150, 450);
        addObject(new Muro(), 250, 450);
        addObject(new Muro(), 350, 450);
        addObject(new Muro(), 450, 450);
        addObject(new Muro(), 550, 450);
        addObject(new Muro(), 650, 450);
        addObject(new Muro(), 750, 450);
        
        coordenadasMunicion[0] = 550;
        coordenadasMunicion[1] = 350;
        coordenadasLlave[0] = 150;
        coordenadasLlave[1] = 150;
        
        // Informacion  
        crearCodigos(285);
          
        // Enemigos
        int[] posicion;
        spawns.add(new Spawn(new Enemigo2(0,posicion=new int[]{50,0},posicion=new int[]{350,0},true), 50, 340));      
        spawns.add(new Spawn(new Enemigo1(1,posicion=new int[]{550,50},posicion=new int[]{550,150},posicion=new int[]{750,150},posicion=new int[]{750,50},1), 550, 50));
        
        for (Spawn spawn : spawns) addObject(spawn.getEnemigo(), spawn.getPosicionX(), spawn.getPosicionY());
           
        // Jugadores
        addObject(new Jugador1(), 50, 50);
        
        // Instrucciones iniciales
        addObject(new Texto("🡰  Usa la flecha izquierda para devolver",15,Color.BLACK),110,590);
        addObject(new Texto("Usa la flecha derecha para continuar  🡲",15,Color.BLACK),690,590);
    }
    
    public void instrucciones(int paso) {
        if (flechas.size() > 0) {
            for (Flecha flecha : flechas) removeObject(flecha);
            flechas.clear();
            coordenadasFlechas.clear();
        }
        removeObject(mensaje);
        removeObject(instruccion);
        
        int[] posicion = new int[2];
        switch(paso) {
            case 1:
                texto = "Usa   'W'(🡱),   'A'(🡰),   'S'(🡳),   'D'(🡲), para moverte por el mapa";
                break;
            case 2:
                texto = "CUIDADO CON LA LAVA, si este contador es '0' el mapa se llenara de lava";
                flechas.add(new Flecha(180));
                coordenadasFlechas.add(posicion = new int[]{970,170});
                break;
            case 3:
                texto = "Las zonas amarillas son las unicas que no se queman, buscalas para no arder en llamas";
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{150,100});
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{350,300});
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{550,300});
                break;
            case 4:
                texto = "Puedes ponerle pause al juego con la tecla 'P'";
                break;
            case 5:
                texto = "Apreta la tecla 'M' cerca a una caja para obtener su codigo";
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{40,180});
                flechas.add(new Flecha(135));
                coordenadasFlechas.add(posicion = new int[]{970,260});
                break;
            case 6:
                texto = "Acercate a una puerta y apreta la tecla 'M' para ingresar su codigo correspondiente\nEl tiempo no corre mientras se ingresa el codigo";
                flechas.add(new Flecha(45));
                coordenadasFlechas.add(posicion = new int[]{200,40});
                flechas.add(new Flecha(135));
                coordenadasFlechas.add(posicion = new int[]{970,260});
                break;
            case 7:
                texto = "Esta caja esta bloqueada (por ahora), busca como desbloquearla";
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{380,40});
                break;
            case 8:
                texto = "Usa la tecla 'espacio' para disparar, la bala va hacia la ultima direccion que se mueve el personaje";
                break;
            case 9:
                texto = "Cuentas con una cantidad limitada de balas y bombas, usalas con inteligencia";
                flechas.add(new Flecha(135));
                coordenadasFlechas.add(posicion = new int[]{960,70});
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{980,150});
                break;
            case 10:
                texto = "Mata al enemigo y ve por la caja para obtener su codigo";
                flechas.add(new Flecha(315));
                coordenadasFlechas.add(posicion = new int[]{20,330});
                break;
            case 11:
                texto = "CUIDADO, cada cierto tiempo el enemigo se reanima y ademas aparece otro";
                break;
            case 12:
                texto = "Usa la tecla 'N' para colocar una bomba, solo explota si un enemigo la toca.\nMejor alejate cuando esto suceda, la bomba tiene un daño de area que puede afectarte";
                break;
            case 13:
                texto = "Puedes recargar apretando la tecla 'M' sobre la caja de munición";
                flechas.add(new Flecha(135));
                coordenadasFlechas.add(posicion = new int[]{960,70});
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{980,150});
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{550,300});
                break;
            case 14:
                texto = "Ve por la siguiente caja";
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{680,40});
                break;
            case 15:
                texto = "Parece que la caja bloqueada acaba de cambiar, busca que trae en su interior";
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{380,40});
                break;
            case 16:
                texto = "Los mensajes de las pistas quedan aca";
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{980,420});
                break;
            case 17:
                texto = "La llave sirve para poder abrir la ultima puerta, sigue la pista para encontrarla";
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{550,320});
                break;
            case 18:
                texto = "Ve por la llave";
                flechas.add(new Flecha(90));
                coordenadasFlechas.add(posicion = new int[]{150,120});
                break;
            case 19:
                texto = "¿Que tal si el codigo esta al reves?";
                flechas.add(new Flecha(225));
                coordenadasFlechas.add(posicion = new int[]{980,370});
                break;
        }
        
        mensaje = new Texto(texto,20,Color.BLACK);
        addObject(mensaje,400,530);
        instruccion = new Texto("Paso "+paso+"/19",15,Color.BLACK);
        addObject(instruccion,400,590);
        if (flechas.size() > 0) {
           for (int i =0; i<flechas.size(); i++) addObject(flechas.get(i),coordenadasFlechas.get(i)[0],coordenadasFlechas.get(i)[1]); 
        }
    }
}
