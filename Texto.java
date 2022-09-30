import greenfoot.*;

public class Texto extends Actor
{
    protected String texto = "";
    protected int tamaño = 0;
    protected static Color color1, color2;
    
    static {
        color1 = Color.WHITE;
        color2 = new Color(135,161,171);
    }
    
    public Texto(String texto, int tamaño){
        this.texto = texto;
        this.tamaño = tamaño;
        setImage(new GreenfootImage(texto, tamaño, color1, color2));
    }
    
    public void act()
    {
        setImage(new GreenfootImage(texto, tamaño, color1, color2));
    }
    
    public String getTexto() {
        return this.texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
