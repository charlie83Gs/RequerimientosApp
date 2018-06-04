package app.proyecto2.reque.muestreosapp.Controlador;

import java.util.HashMap;

import app.proyecto2.reque.muestreosapp.Modelo.Usuario;

public class Session {
    private static final Session ourInstance = new Session();
    private Usuario usuario;
    private HashMap<String, String> sessionData;

    public static Session getInstance() {
        return ourInstance;
    }

    private Session() {
        sessionData = new HashMap<>();
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String  getSessionDataValue(String key) {
        return sessionData.get(key);
    }

    public void setSessionDataValue(String key, String value) {

        this.sessionData.put(key,value);
    }

    public HashMap<String, String> getSessionData() {
        return sessionData;
    }


}