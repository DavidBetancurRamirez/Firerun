import greenfoot.*;

public class Texto2 extends Actor
{
    protected String texto = "";
    protected int tamaño = 0;
    protected static Color color1, color2;
    
    static {
        color2 = new Color(102,111,136);
    }
    
    public Texto2(String texto, int tamaño) {
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
        setImage(new GreenfootImage(texto, tamaño, color1, color2));
    }
    
    public Color getColor2() {
        return this.color2;
    }
    public void setColor2(Color color) {
        this.color2 = color;
        setImage(new GreenfootImage(texto, tamaño, color1, color2));
    }
}
