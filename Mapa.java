import greenfoot.*;
import java.util.ArrayList;

public class Mapa extends World
{
    protected long tiempoInicial;
    protected int tiempoEntreLava;
    protected int[] size = {20,25,17,14}; // [numPuerta,info,codigo,mensaje]
    protected int[] coordenadasMunicion = new int[2];
    protected int[] coordenadasLlave = new int[2];
    protected int[] municionInicial = new int[2];
    protected boolean pause;
    protected boolean hayUltimaPista = false;
    protected boolean buscandoLlave = false;
    protected boolean lava=true;
    protected boolean mapaAgrietado=false;
    
    protected Caja cajaX;
    protected Pause ventanaPause;
    protected Tiempo cronometro, tiempoLava;
    protected Texto mensaje = new Texto("Crees poder llegar al trofeo...\n sin quemarte",size[3],Color.BLACK);
    
    protected ArrayList<GreenfootImage> mapaFondo = new ArrayList<GreenfootImage>(); // [normal, lava]
    protected ArrayList<Spawn> spawns = new ArrayList<Spawn>();
    protected ArrayList<Texto> codigos = new ArrayList<Texto>();
    protected ArrayList<Texto> informacion = new ArrayList<Texto>(); // [balas, bombas]
    protected ArrayList<Puerta> puertas = new ArrayList<Puerta>();
    
    public Mapa(int tiempoEntreLava, int cantidadBalasIniciales, int cantidadBombasIniciales, GreenfootImage mapaNormal, GreenfootImage mapaLava, GreenfootImage mapaAgrietado)
    {
        super(1000, 600, 1);
        this.tiempoEntreLava = tiempoEntreLava;
        this.mapaFondo.add(mapaNormal);
        this.mapaFondo.add(mapaLava);
        this.mapaFondo.add(mapaAgrietado);
        this.municionInicial[0] = cantidadBalasIniciales;
        this.municionInicial[1] = cantidadBombasIniciales;
        pause(false);
        crearZonaInformacion();
        cambiarMapa();
    }
    
    public void crearZonaInformacion() {
        tiempoInicial = System.currentTimeMillis();
        
        addObject(new Texto("Tiempo:",size[1],Color.WHITE), 900, 20);
        tiempoLava = new Tiempo("Lava: "+tiempoEntreLava,size[1],Color.WHITE,tiempoEntreLava,tiempoInicial,false);
        tiempoLava.setSegundos(tiempoEntreLava);
        addObject(tiempoLava, 900, 170);
        cronometro = new Tiempo("00:00",size[1],Color.WHITE,tiempoEntreLava,tiempoInicial,true);
        addObject(cronometro, 900, 50);
        
        informacion.add(new Texto("Balas:",size[1],Color.WHITE,String.valueOf(municionInicial[0]),false));
        addObject(informacion.get(0), 900, 90);
        informacion.add(new Texto("Bombas:",size[1],Color.WHITE,String.valueOf(municionInicial[1]),false));
        addObject(informacion.get(1), 900, 130);
        
        addObject(new Texto("Codigos",size[1],Color.WHITE), 900, 220);
        
        addObject(new Texto("Mensaje",size[1],Color.WHITE), 900, 420);
        addObject(this.mensaje, 900, 465);
    }
    
    public void crearCodigos(int inicio) {
        for (int i=0; i<puertas.size(); i++) {
            codigos.add(new Texto("Codigo puerta"+(i+1)+":",size[2], Color.BLACK, "??", true));
            addObject(codigos.get(i), 900, inicio);
            inicio+=30;
        }
    }
    
    public void cambiarMapa() {
        if (lava) {
            Muro.fuego = false;
            Puerta.fuego = false;
            setBackground(mapaFondo.get(0));
        } else {
            Muro.fuego = true;
            Puerta.fuego = true;
            setBackground(mapaFondo.get(1));
        }
        lava=!lava;
        mapaAgrietado = false;
    }
    
    public void crearPuerta(int rotacion, int numPuerta, int posicionX, int posicionY) {
        Puerta puerta = new Puerta(rotacion,numPuerta);
        puertas.add(puerta);
        addObject(puerta,posicionX,posicionY);
        addObject(new Texto(String.valueOf(numPuerta),size[0],Color.WHITE),posicionX,posicionY);
    }
    
    public void pause() { // Intercalar pause
        pause = !pause;
        Enemigo.pause = !Enemigo.pause;
        Jugador.pause = !Jugador.pause;
        Arma.pause = !Arma.pause;
    }
    
    public void pause(boolean condicion) { // Pause segun condicion
        pause = condicion;
        Enemigo.pause = condicion;
        Jugador.pause = condicion;
        Arma.pause = condicion;
    }
    
    public void pause(int x) { // Sacar pantalla emergente de pause
        pause();
        
        if (pause) {
            ventanaPause = new Pause();
            addObject(ventanaPause,500,300);
        } else {
            ventanaPause.borrarBotones();
            removeObject(ventanaPause);
        }
    }
    
    public void buscarLlave(int x, int y) {
        if (x>=(coordenadasMunicion[0]-15) && x<=(coordenadasMunicion[0]+15) 
            && y>=(coordenadasMunicion[1]-15) && y<=(coordenadasMunicion[1]+15)) {
            addObject(new Llave(),coordenadasLlave[0],coordenadasLlave[1]);
            buscandoLlave = false;
        }
    }
    
    public void agrietarMapa() {
        if (!mapaAgrietado) {
            setBackground(mapaFondo.get(2));
            Greenfoot.playSound("sonido-lava-v2.mp3");
            mapaAgrietado = true;
        }
    }
    
    public boolean isLava() {
        return this.lava;
    }
    public void setLava() {
        this.lava=!lava;
        cambiarMapa();
    }
    
    public boolean isPause() {
        return this.pause;
    }
    public void setPause() {
        this.pause = !pause;
    }
    
    public boolean isHayUltimaPista() {
        return this.hayUltimaPista;
    }
    public void setHayUltimaPista() {
        this.hayUltimaPista = !hayUltimaPista;
    }
    
    public boolean isBuscandoLlave() {
        return this.buscandoLlave;
    }
    public void setBuscandoLlave(boolean buscar) {
        this.buscandoLlave = buscar;
    }
    
    public Texto getMensaje() {
        return this.mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje.setTexto(mensaje);
    }
    
    public Puerta getPuerta(int puerta) {
        return this.puertas.get(puerta-1);
    }
    
    public int cantidadPuertas() {
        return this.puertas.size();
    }
    
    public Texto getCodigo(int puerta) {
        return this.codigos.get(puerta-1);
    }
    public void setCodigo(String codigo, int puerta) {
        this.codigos.get(puerta-1).setCodigo(codigo, puerta);
    }
    
    public Texto getInformacion(int index) {
        return this.informacion.get(index);
    }
    public void setInformacion(int informacion, int index) {
        this.informacion.get(index).setExtra(String.valueOf(informacion));
    }
    
    public Tiempo getCronometro() {
        return this.cronometro;
    }
    
    public Spawn getSpawn(int indice) {
        return this.spawns.get(indice);
    }
    
    public Caja getCajaX() {
        return this.cajaX;
    }
    
    public int[] getMunicionInicial() {
        return this.municionInicial;
    }
}
