package tema1.excepciones;

public class RangeExcepcion extends Exception{
    public RangeExcepcion(){
        super("Rango no soportado");
    }
    public RangeExcepcion(String msg){
        super(msg);
    }
}
