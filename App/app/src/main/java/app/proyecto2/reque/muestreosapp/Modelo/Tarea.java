package app.proyecto2.reque.muestreosapp.Modelo;

public class Tarea {
    int id;
    String nombre;
    TipoTarea tipo;

    public Tarea(int id, String nombre, TipoTarea tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoTarea getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarea tipo) {
        this.tipo = tipo;
    }
}
