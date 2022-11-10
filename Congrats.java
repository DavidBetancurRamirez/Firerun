import greenfoot.*;

public class Congrats extends FinalJuego
{
    private Tiempo tiempo;
    private GifImage gif = new GifImage("congratulations-v3.gif");
    
    public Congrats(Mapa mapa, Tiempo tiempo)
    {
        super(mapa); 
        this.tiempo = tiempo;
        int[] posicion;
        colocarTiempo();
        crearBotones(posicion=new int[]{735,735},posicion=new int[]{210,345});
    }
    
    public void act() {
        reintentarNivel();
        setBackground(gif.getCurrentImage());
    }
    
    public void colocarTiempo() {
        addObject(new Texto(String.format("%02d", tiempo.getMinutos())+" : "+String.format("%02d", tiempo.getSegundos()),50,Color.WHITE),250,480);
    }
}
