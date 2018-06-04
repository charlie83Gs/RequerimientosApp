package app.proyecto2.reque.muestreosapp.Modelo;

public class Muestreo {
    int id;
    int idProyecto;
    int idOperacion;
    int cantidadObservaciones;
    int rangoMinutos;
    String horaInicio;
    String horaFinalizacion;

    public Muestreo(int id, int idProyecto, int idOperacion, int cantidadOBservaciones, int rangoMinutos, String horaInicio, String horaFinalizacion) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.idOperacion = idOperacion;
        this.cantidadObservaciones = cantidadObservaciones;
        this.rangoMinutos = rangoMinutos;
        this.horaInicio = horaInicio;
        this.horaFinalizacion = horaFinalizacion;
    }

    public int getId() {
        return id;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getCantidadObservacioness() {
        return cantidadObservaciones;
    }

    public void setCantidadObservaciones(int cantidadObservaciones) {
        this.cantidadObservaciones = cantidadObservaciones;
    }

    public int getRangoMinutos() {
        return rangoMinutos;
    }

    public void setRangoMinutos(int rangoMinutos) {
        this.rangoMinutos = rangoMinutos;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(String horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }
}
