import greenfoot.*;

public class Tiempo extends Texto
{
    private int segundos, minutos, segundosTotales, ultimoSegundo=0;
    private int tiempoEntreLava;
    private int duracionMilise, duracionSe, tiempoPausado, tiempoParaLava;
    private int[] tiempoPrePause = new int[3]; // [minutos,segundos,tiempoParaLava]
    private long tiempoInicial, tiempoTranscurrido;
    private boolean aumentar,pausado;
    private boolean bandera = true;
    
    public Tiempo(String texto, int tamaño, Color colorLetra, int tiempoEntreLava, long tiempoInicial, boolean aumentar) {
        super(texto,tamaño,colorLetra);
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
                this.aumentarTiempo(duracionSe+(tiempoPrePause[1]+(tiempoPrePause[0]*60)));
            } else {
                duracionMilise = (int)(tiempoInicial - tiempoTranscurrido);
                duracionSe = duracionMilise / 1000;
                this.disminuirTiempo(duracionSe+tiempoEntreLava-tiempoPrePause[2]);
            } 
        }
    }
    
    public void aumentarTiempo(int tiempo) {
        segundos = tiempo;
        
        while(segundos!=0 && segundos>59) {
            segundos-=60;
            if (segundos==1) bandera=true;
            if(segundos==0 && bandera) {
                minutos++; 
                bandera = false;
                break;
            }
        }
        
        if (ultimoSegundo != segundos+(60*minutos)) {
            segundosTotales++;
            ultimoSegundo = segundos+(60*minutos);
        }

        setTexto(String.format("%02d", minutos)+":"+String.format("%02d", segundos));
    }
    
    public void disminuirTiempo(int tiempo) {   
        World world = getWorld();
        Mapa mapa = (Mapa)getWorld();
            
        segundos = tiempo;
        
        while(segundos<=0) {
            if (segundos>=-5 && segundos<=0) {
                if (!mapa.isLava()) mapa.cambiarMapa();
                
                if (segundos==-5) {
                    mapa.cambiarMapa();
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
    
    public int getSegundosTotales() {
        return this.segundosTotales;
    }
    
    public int getMinutos() {
        return this.minutos;
    }
}
