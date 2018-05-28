package app.proyecto2.reque.muestreosapp.Modelo;

public class Trabajador {

    int id;
    String apodo;
    String puesto;

    public Trabajador(int id, String apodo, String puesto) {
        this.id = id;
        this.apodo = apodo;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
