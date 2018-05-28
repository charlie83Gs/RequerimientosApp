package app.proyecto2.reque.muestreosapp.Modelo;

public class HoraRestringida {
    int id;
    Tiempo hora;
    int duracionMinutos;

    public HoraRestringida(int id, Tiempo hora, int duracionMinutos) {
        this.id = id;
        this.hora = hora;
        this.duracionMinutos = duracionMinutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tiempo getHora() {
        return hora;
    }

    public void setHora(Tiempo hora) {
        this.hora = hora;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
}
