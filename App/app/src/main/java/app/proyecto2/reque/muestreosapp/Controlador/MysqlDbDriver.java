package app.proyecto2.reque.muestreosapp.Controlador;

import android.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import app.proyecto2.reque.muestreosapp.Modelo.TipoUsuario;
import app.proyecto2.reque.muestreosapp.Modelo.Usuario;

public class MysqlDbDriver {
    private static final MysqlDbDriver ourInstance = new MysqlDbDriver();

    private static final String ip = "192.168.1.8";
    private static final String port = "3306";
    private static final String dataBase = "requemuestreos";
    private Connection connection;

    public static MysqlDbDriver getInstance() {
        return ourInstance;
    }

    private MysqlDbDriver() {

    }

    public boolean createConnection( String Usuario, String Contraseña){
        boolean exito = false;

        try {
            if(connection != null){
                connection.close();
            }

            Class.forName("com.mysql.jdbc.Driver");
            // "jdbc:mysql://IP:PUERTO/DB", "USER", "PASSWORD");
            // Si estás utilizando el emulador de android y tenes el mysql en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/"+dataBase, Usuario, Contraseña);
            exito = true;
        } catch (SQLException se) {
            Log.e("Database","oops! No se puede conectar. Error: ",se);
        } catch (ClassNotFoundException e) {
            Log.d("Database:","oops! No se encuentra la clase. Error: " + e.getMessage());
        }

        return  exito;

    }

    public boolean closeConnection(){
        boolean exito = false;
        try {
            if(connection != null){
                connection.close();
            }
            exito = true;
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());
        }
    return exito;
    }

    /***
     * valida el usuario en la base de datos
     * @param correo
     * @param password
     * @return usuario si la sesion se inicio correctamente
     */
    public Usuario validate_login(String correo, String password){
        int tipo = 0;
        int telefono = 0;
        int id = 0;
        try {
            CallableStatement statement = connection.prepareCall("{CALL validate_login(?,?,?,?,?)}");
            statement.setString(1,correo);
            statement.setString(2,password);
            statement.registerOutParameter(3, Types.INTEGER);
            statement.registerOutParameter(4, Types.INTEGER);
            statement.registerOutParameter(5, Types.INTEGER);

            statement.execute();

            telefono = statement.getInt(3);
            tipo = statement.getInt(4);
            id = statement.getInt(5);


            statement.close();
        } catch (SQLException e) {
            Log.e("Login","Error al valdar session",e);
        }catch (Exception e){
            Log.e("Login","Error al valdar session",e);
        }
        if(tipo != 0){
            return new Usuario(id,String.valueOf(telefono),correo, TipoUsuario.fromInteger(tipo));

        }
        return null;
    }

}
