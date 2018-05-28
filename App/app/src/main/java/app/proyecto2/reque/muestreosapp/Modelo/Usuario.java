package app.proyecto2.reque.muestreosapp.Modelo;

public class Usuario {
    private String telefono;
    private String correo;
    private TipoUsuario tipo;
    private int id;

    /**
     * crea un usuario
     * @param id
     * @param telefono
     * @param correo
     * @param tipo
     */
    public Usuario(int id, String telefono, String correo, TipoUsuario tipo) {
        this.id = id;

        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public TipoUsuario getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
