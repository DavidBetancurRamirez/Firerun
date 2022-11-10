import greenfoot.*;

public class Texto extends Actor
{
    protected String texto, extra;
    protected int tamaño;
    protected boolean codigo;
    protected Color colorLetra, colorFondo;
    
    public Texto(String texto, int tamaño, Color colorLetra) {
        this.texto = texto;
        this.tamaño = tamaño;
        this.colorLetra = colorLetra;
        setImage(new GreenfootImage(this.texto, tamaño, colorLetra, colorFondo));
    }
    
    public Texto(String texto, int tamaño, Color colorLetra, String extra, boolean codigo){
        this.texto = texto+" "+extra;
        this.tamaño = tamaño;
        this.colorLetra = colorLetra;
        this.extra = extra;
        this.codigo = codigo;
        setImage(new GreenfootImage(this.texto, tamaño, colorLetra, colorFondo));
    }
    
    public void act() {
        
    }
    
    public String getTexto() {
        return this.texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
        setImage(new GreenfootImage(texto, tamaño, colorLetra, colorFondo));
    }
    
    public void setCodigo(String codigo, int puerta) {
        if (this.codigo) {
            this.extra = codigo;
            setTexto("Codigo puerta"+puerta+": "+extra);
        }
    }
    
    public String getExtra() {
        return this.extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
        
        String[] info = texto.split(" ");
        this.texto = info[0]+" "+extra;
        
        setImage(new GreenfootImage(texto, tamaño, getColorLetra(), getColorFondo()));
    }
    
    public Color getColorLetra() {
        return colorLetra;
    }
    public void setColorLetra() {
        this.colorLetra = colorLetra;
    }
    
    public Color getColorFondo() {
        return colorFondo;
    }
    public void setColorFondo() {
        this.colorFondo = colorFondo;
    }
}
