package app.proyecto2.reque.muestreosapp.Controlador;

import android.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Modelo.TipoUsuario;
import app.proyecto2.reque.muestreosapp.Modelo.Usuario;

public class MysqlDbDriver {
    private static final MysqlDbDriver ourInstance = new MysqlDbDriver();

    private static final String ip = "192.168.43.20";

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

    public void agregarTarea(String nombre, String tipo){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL agregarTarea(?,?)}");

            statement.setString(1,nombre);
            statement.setString(2,tipo);

            statement.execute();
            statement.close();

            System.out.println("agregado");

        } catch (SQLException e) {
            Log.e("Login","Error al agregar tarea",e);
        }
    }

    public ArrayList<String> mostrarTareas(){
        ArrayList<String> tareas = new ArrayList<String>();
        try {
            String query = "select id,nombre from tarea";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int idT = rs.getInt("id");
                String nombreT = rs.getString("nombre");

                String tar = "Id:"+String.valueOf(idT)+" "+nombreT;

                tareas.add(tar);
                System.out.println(nombreT);
            }
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error al agregar tarea",e);
        }
        return tareas;
    }

    public void editarTarea(int id,String nombre, String tipo){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL editarTarea(?,?,?)}");

            statement.setInt(1,id);
            statement.setString(2,nombre);
            statement.setString(3,tipo);

            statement.execute();
            statement.close();

            System.out.println("editado");

        } catch (SQLException e) {
            Log.e("Login","Error al editar tarea",e);
        }
    }

    public void eliminarTarea(int id){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL eliminarTarea(?)}");

            statement.setInt(1,id);

            statement.execute();
            statement.close();

            System.out.println("Eliminado");

        } catch (SQLException e) {
            Log.e("Login","Error al eliminar Tarea",e);
        }
    }

    public void agregarOperacion(String nombre){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL agregarOperacion(?)}");

            statement.setString(1,nombre);

            statement.execute();
            statement.close();

            System.out.println("agregada");

        } catch (SQLException e) {
            Log.e("Login","Error al agregar operacion",e);
        }
    }

    public int ultimaOp_agregada(){
        int idT = 0;
        try {
            String query = "select max(id) from operacion";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            rs.next();
            idT = rs.getInt("max(id)");
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return idT;
    }

    public void asociar_operacion_tarea(int idOp,int idTar){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL asociar_tarea_operacion(?,?)}");

            statement.setInt(1,idOp);
            statement.setInt(2,idTar);

            statement.execute();
            statement.close();

            System.out.println("agregada");

        } catch (SQLException e) {
            Log.e("Login","Error al agregar operacion",e);
        }
    }

    public ArrayList<String> mostrarOperaciones(){
        ArrayList<String> operaciones = new ArrayList<String>();
        try {
            String query = "select id,nombre from operacion";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int idO = rs.getInt("id");
                String nombreO = rs.getString("nombre");
                String tar = "Id:"+String.valueOf(idO)+" "+nombreO;
                operaciones.add(tar);
            }
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error al agregar tarea",e);
        }
        return operaciones;
    }

    public void editarOperacion(int id,String nombre){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL editarOperacion(?,?)}");

            statement.setInt(1,id);
            statement.setString(2,nombre);

            statement.execute();
            statement.close();

            System.out.println("editado");

        } catch (SQLException e) {
            Log.e("Login","Error al editar operacion",e);
        }
    }

    public void eliminarOperacion(int id){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL eliminarOperacion(?)}");

            statement.setInt(1,id);

            statement.execute();
            statement.close();

            System.out.println("eliminado");

        } catch (SQLException e) {
            Log.e("Login","Error al eliminar operacion",e);
        }
    }


    public ArrayList<String> mostrarProyectos(){
        ArrayList<String> operaciones = new ArrayList<String>();
        try {
            String query = "select id,nombre from proyecto";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int idPro = rs.getInt("id");
                String nombrePro = rs.getString("nombre");

                String proyecto = "Id:"+String.valueOf(idPro)+" "+nombrePro;

                operaciones.add(proyecto);
                System.out.println(nombrePro);
            }
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error al agregar tarea",e);
        }
        return operaciones;
    }

    public int ultimoProyecto_agregado(){
        int idProyecto = 0;
        try {
            String query = "select max(id) from proyecto";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            rs.next();
            idProyecto = rs.getInt("max(id)");
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return idProyecto;
    }

    public String descripcionPro(int idPro){
        String descrip = "";
        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL descripcionPro(?)}");
            statement.setInt(1,idPro);

            ResultSet rs = statement.executeQuery();
            rs.next();
            descrip = rs.getString("descripcion");
            statement.close();

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return descrip;
    }



    public void agregarProyecto(String nombre,String descripcion){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL agregarProyecto(?,?)}");

            statement.setString(1,nombre);
            statement.setString(2,descripcion);

            statement.execute();
            statement.close();

            System.out.println("proyecto agregado");

        } catch (SQLException e) {
            Log.e("Login","Error al agregar proyecto",e);
        }
    }


    public void asociar_operacion_proyecto(int idOp,int idPro){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL asociar_operacion_proyecto(?,?)}");

            statement.setInt(1,idOp);
            statement.setInt(2,idPro);

            statement.execute();
            statement.close();

            System.out.println("agregada");

        } catch (SQLException e) {
            Log.e("Login","Error al agregar operacion",e);
        }
    }


    public void editarProyecto(int idPro,String nombrePro,String descripcionPro){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL editarProyecto(?,?,?)}");

            statement.setInt(1,idPro);
            statement.setString(2,nombrePro);
            statement.setString(3,descripcionPro);

            statement.execute();
            statement.close();

            System.out.println("proyecto editado");

        } catch (SQLException e) {
            Log.e("Login","Error al editar proyecto",e);
        }
    }



    public void eliminarProyecto(int idPro){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL eliminarProyecto(?)}");

            statement.setInt(1,idPro);

            statement.execute();
            statement.close();

            System.out.println("proyecto eliminado");

        } catch (SQLException e) {
            Log.e("Login","Error al eliminar proyecto",e);
        }
    }



    public void generarMuestreo(int id_operacion,int id_proyecto,int cantidad_obs,int rango_min,String hora_ini,String hora_fin){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL generarMuestreo(?,?,?,?,?,?)}");

            statement.setInt(1,id_operacion);
            statement.setInt(2,id_proyecto);
            statement.setInt(3,cantidad_obs);
            statement.setInt(4,rango_min);
            statement.setString(5,hora_ini);
            statement.setString(6,hora_fin);

            statement.execute();
            statement.close();

            System.out.println("Muestreo generado");

        } catch (SQLException e) {
            Log.e("Login","Error al generar muestreo",e);
        }
    }

    public int ultimoMuestreo_generado(){
        int idMuestreo = 0;
        try {
            String query = "select max(id) from muestreo";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            rs.next();
            idMuestreo = rs.getInt("max(id)");
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return idMuestreo;
    }

    public int agregarHoraRestringida(int idMuestreo,String hora,int duracion){

        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL agregarHoraRestringida(?,?,?)}");

            statement.setInt(1,idMuestreo);
            statement.setString(2,hora);
            statement.setInt(3,duracion);

            statement.execute();
            statement.close();

            System.out.println("Hora restringida agregada");

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return idMuestreo;
    }

    public ArrayList<String> mostrarAnalistas(){
        ArrayList<String> operaciones = new ArrayList<String>();
        try {
            String query = "select id,nombre from usuario where tipoUsuario=2";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int idAnalista = rs.getInt("id");
                String nombreAnalista = rs.getString("nombre");

                String analista = "Id:"+String.valueOf(idAnalista)+" "+nombreAnalista;

                operaciones.add(analista);
            }
            st.close();
        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return operaciones;
    }

    public void asociar_analista_muestreo(int idMuestreo,int idUsuario){
        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL asociar_analista_muestreo(?,?)}");

            statement.setInt(1,idMuestreo);
            statement.setInt(2,idUsuario);

            statement.execute();
            statement.close();

            System.out.println("Asociacion realizada");

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
    }


    public ArrayList<String> mostrarTrabajadores(){
        ArrayList<String> trabajadores = new ArrayList<String>();
        try {
            String query = "select id,apodo from trabajador";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int idTrabajador = rs.getInt("id");
                String apodoTrabajador = rs.getString("apodo");

                String analista = "Id:"+String.valueOf(idTrabajador)+" "+apodoTrabajador;

                trabajadores.add(analista);
            }
            st.close();
        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }
        return trabajadores;
    }

    public void asociar_trabajador_muestreo(int idMuestreo,int idTrabajador){
        try {
            CallableStatement statement = null;
            statement = connection.prepareCall("{CALL asociar_trabajador_muestreo(?,?)}");

            statement.setInt(1,idMuestreo);
            statement.setInt(2,idTrabajador);

            statement.execute();
            statement.close();

            System.out.println("Asociacion realizada");

        } catch (SQLException e) {
            Log.e("Login","Error",e);
        }

    public void addUsers(String pNombre,String pPassword, String pCorreo, int pTelefono, int pTipo){
        try {
            CallableStatement statement = connection.prepareCall("{CALL create_user(?,?,?,?,?)}");
            statement.setString(1,pNombre);
            statement.setString(2,pPassword);
            statement.setString(3,pCorreo);
            statement.setInt(4,pTelefono);
            statement.setInt(5,pTipo);

            statement.execute();

            System.out.println("Usuario Agregado");
        } catch (SQLException e) {
            Log.e("Login","Error al agregar usuario",e);
        }
    }

    public void addTrabajador(String apodo, String puesto){
        try {
            CallableStatement statement = connection.prepareCall("{CALL create_trabajador(?,?)}");
            statement.setString(1,apodo);
            statement.setString(2,puesto);

            statement.execute();

            System.out.println("Trabajador Agregado");
        } catch (SQLException e) {
            Log.e("Login","Error al agregar trabajador",e);
        }
    }

    public ArrayList<String> mostrarTrabajdores(){
        ArrayList<String> trabajadores = new ArrayList<String>();
        try {
            String query = "select apodo from trabajador";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String nombreT = rs.getString("apodo");

                trabajadores.add(nombreT);
                System.out.println(nombreT);
            }
            st.close();

        } catch (SQLException e) {
            Log.e("Login","Error al tratar de recuperar trabajadores",e);
        }
        return trabajadores;
    }

    public void editarTrabajador(String trabajador,String newapodo, String newpuesto){
        int idTrabajador = -1;
        try {
            CallableStatement statement = connection.prepareCall("{CALL getIdTrabajador(?,?)}");
            statement.setString(1,trabajador);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.execute();
            idTrabajador = statement.getInt(2);

            if(!newapodo.isEmpty()){
                statement = connection.prepareCall("{CALL editarTrabajadorApodo(?,?)}");
                statement.setInt(1,idTrabajador);
                statement.setString(2,newapodo);
                statement.execute();
            }
            if(!newpuesto.isEmpty()){
                statement = connection.prepareCall("{CALL editarTrabajadorPuesto(?,?)}");
                statement.setInt(1,idTrabajador);
                statement.setString(2,newpuesto);
                statement.execute();
            }
            System.out.println("Trabajador Editado");
        } catch (SQLException e) {
            Log.e("Login","Error al editar trabajador",e);
        }

    }

    public void eliminarTrabajador(String trabajador){
        int idTrabajador = -1;
        try {
            CallableStatement statement = connection.prepareCall("{CALL getIdTrabajador(?,?)}");
            statement.setString(1,trabajador);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.execute();
            idTrabajador = statement.getInt(2);

            statement = connection.prepareCall("{CALL  eliminarTrabajador(?)}");
            statement.setInt(1,idTrabajador);
            statement.execute();

            System.out.println("Trabajador Eliminado");
        } catch (SQLException e) {
            Log.e("Login","Error al eliminar trabajador",e);
        }

    }

}
