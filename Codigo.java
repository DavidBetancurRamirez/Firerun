import greenfoot.*;

public class Codigo extends Texto2
{
    protected String codigo;
    
    public Codigo(String texto, int tamaño, String codigo) {
        super(texto+" "+codigo, tamaño);
        this.codigo = codigo;
    }
    
    public void act()
    {
        
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo, int puerta) {
        this.codigo = codigo;
        setTexto("Codigo puerta"+puerta+": "+codigo);
    }
}
