package app.proyecto2.reque.muestreosapp.Modelo;

public class Observacion {
    int id;
    int idMuestreo;
    int idTarea;
    String fecha;
    String comentario;

    public Observacion(int id, int idMuestreo, int idTarea, String fecha, String comentario) {
        this.id = id;
        this.idMuestreo = idMuestreo;
        this.idTarea = idTarea;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public int getIdMuestreo() {
        return idMuestreo;
    }


    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
