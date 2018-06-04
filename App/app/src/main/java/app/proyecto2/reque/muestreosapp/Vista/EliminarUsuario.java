package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class EliminarUsuario extends AppCompatActivity {
    Spinner mostrarUsuarios;
    Button confirmarEliminarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);

        //Cargar tareas de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> usuario = mysql.mostrarUsuario();
        mostrarUsuarios = (Spinner)findViewById(R.id.spinnerElimUser);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,usuario);
        mostrarUsuarios.setAdapter(adapter2);

        //Evento del boton de confirmacion
        confirmarEliminarUsuario = (Button) findViewById(R.id.button_eliminar_User);
        confirmarEliminarUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String parUser = mostrarUsuarios.getSelectedItem().toString();
                mysql.eliminarUsuario(parUser);
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_LONG).show();
            }
        });


    }
}
