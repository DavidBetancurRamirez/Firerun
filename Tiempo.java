import greenfoot.*;

public class Tiempo extends Texto1
{
    private int segundos = 0;
    private int minutos = 0;
    private int tiempoEntreLava = 30;
    private int ultimoSegundo, duracionMilise, duracionSe, tiempoPausado, tiempoParaLava;
    private int[] tiempoPrePause = new int[3]; // [minutos,segundos,tiempoParaLava]
    private long tiempoInicial, tiempoTranscurrido;
    private boolean aumentar,pausado;
    private boolean bandera = true;
    
    public Tiempo(String texto, int tamaño, int tiempoEntreLava, long tiempoInicial, boolean aumentar) {
        super(texto,tamaño);
        this.tiempoEntreLava = tiempoEntreLava;
        this.tiempoInicial = tiempoInicial;
        this.tiempoTranscurrido = tiempoInicial;
        this.aumentar = aumentar;
    }
    
    public void act()
    {
        Mapa mapa = (Mapa)getWorld();
        tiempoTranscurrido = System.currentTimeMillis();
        
        if (mapa.isPause()) pausado = true;
        else {
            if (pausado) {
                tiempoInicial = System.currentTimeMillis();
                tiempoPrePause[0] = minutos;
                tiempoPrePause[1] = segundos;
                tiempoPrePause[2] = tiempoEntreLava-tiempoParaLava;
                pausado=false;
            }
            
            if(aumentar) {
                duracionMilise = (int)(tiempoTranscurrido - tiempoInicial);
                duracionSe = duracionMilise / 1000;
                this.aumentarTiempo(duracionSe+(tiempoPrePause[1]*(tiempoPrePause[0]+1)));
            } else {
                duracionMilise = (int)(tiempoInicial - tiempoTranscurrido);
                duracionSe = duracionMilise / 1000;
                this.disminuirTiempo(duracionSe+tiempoEntreLava-tiempoPrePause[2]);
            } 
        }
    }
    
    public void aumentarTiempo(int tiempo) {
        segundos = tiempo;
        
        while(segundos!=0 && segundos>=59) {
            segundos-=59;
            if (segundos==1) bandera=true;
            if(segundos==0 && bandera) {
                minutos++; 
                bandera = false;
                break;
            }
        }
        
        setTexto(String.format("%02d", minutos)+":"+String.format("%02d", segundos));
    }
    
    public void disminuirTiempo(int tiempo) {   
        World world = getWorld();
        Mapa1 mapa1 = (Mapa1)getWorld();
            
        segundos = tiempo;
        
        while(segundos<=0) {
            if (segundos>=-5 && segundos<=0) {
                if (!mapa1.isLava()) mapa1.cambiarMapa();
                
                if (segundos==-5) {
                    mapa1.cambiarMapa();
                    segundos=tiempoEntreLava;
                    break;
                }
                segundos=0;
                break;
            } else {
                segundos+=tiempoEntreLava+5;
            }
        }
        
        tiempoParaLava = segundos;
        setTexto("Lava: "+String.format("%02d", segundos));
    }
    
    public int getSegundos() {
        return this.segundos;
    }
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
}
