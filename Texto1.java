import greenfoot.*;

public class Texto1 extends Actor
{
    protected String texto, extra;
    protected int tamaño = 0;
    protected static Color color1, color2;
    
    static {
        color1 = Color.WHITE;
        color2 = new Color(135,161,171);
    }
    
    public Texto1(String texto, int tamaño, String extra){
        this.texto = texto+" "+extra;
        this.tamaño = tamaño;
        this.extra = extra;
        setImage(new GreenfootImage(this.texto, tamaño, color1, color2));
    }
    
    public Texto1(String texto, int tamaño){
        this.texto = texto;
        this.tamaño = tamaño;
        setImage(new GreenfootImage(this.texto, tamaño, color1, color2));
    }
    
    public void act()
    {
        setImage(new GreenfootImage(this.texto, tamaño, color1, color2));
    }
    
    public String getTexto() {
        return this.texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
        setImage(new GreenfootImage(this.texto, tamaño, color1, color2));
    }
    
    public String getExtra() {
        return this.extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
        
        String[] info = texto.split(" ");
        this.texto = info[0]+" "+extra;
        
        setImage(new GreenfootImage(texto, tamaño, color1, color2));
    }
}
