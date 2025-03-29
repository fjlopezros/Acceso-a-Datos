package tema1.serializarJDOM;

public class Clase {
    private String rollno;
    private String nombre;
    private String apellido;
    private String apodo;
    private int notas;
    private final String ESTUDIANTE = "Estudiante";
    private final String ROLLNO = "rollno";
    private final String NOMBRE = "nombre";
    private final String APELLIDO = "apellido";
    private final String APODO = "apodo";
    private final String NOTAS = "notas";

    public Clase(String rollno, String nombre, String apellido, String apodo, int notas) {
        this.rollno = rollno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.notas = notas;
    }

    // Getters and Setters
    public String getESTUDIANTE() {
        return ESTUDIANTE;
    }

    public String getROLLNO() {
        return ROLLNO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public String getAPODO() {
        return APODO;
    }

    public String getNOTAS() {
        return NOTAS;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getNotas() {
        return notas;
    }

    public void setNotas(int notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "rollno='" + rollno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", apodo='" + apodo + '\'' +
                ", notas=" + notas +
                '}';
    }
}
