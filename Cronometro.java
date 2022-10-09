import greenfoot.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Cronometro extends Texto1
{
    protected int miliSegundos = 0, segundos = 0, minutos = 0, tiempoLava = 20, ultimoSegundo, duracionMilise, duracionSe;
    protected long tiempoInicial, tiempoTranscurrido;
    protected boolean bandera = true;
    protected boolean aumentar;
    
    public Cronometro(String texto, int tamaño, long tiempoInicial, boolean aumentar) {
        super(texto,tamaño);
        this.tiempoInicial = tiempoInicial;
        this.tiempoTranscurrido = tiempoInicial;
        this.aumentar = aumentar;
    }
    
    public void act()
    {
        tiempoTranscurrido = System.currentTimeMillis();
        if(aumentar) {
            duracionMilise = (int)(tiempoTranscurrido - tiempoInicial);
            duracionSe = duracionMilise / 1000;
            this.aumentarTiempo(duracionSe);
        } else {
            duracionMilise = (int)(tiempoInicial - tiempoTranscurrido);
            duracionSe = duracionMilise / 1000;
            this.disminuirTiempo(duracionSe+tiempoLava);
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
                if (!mapa1.lava) mapa1.cambiarMapa();
                
                if (segundos==-5) {
                    mapa1.cambiarMapa();
                    segundos=tiempoLava;
                    break;
                }
                segundos=0;
                break;
            } else {
                segundos+=tiempoLava+5;
            }
        }
        
        setTexto("Lava: "+String.format("%02d", segundos));
    }
    
    public int getSegundos() {
        return this.segundos;
    }
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
}
