package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class EliminarTrabajador extends AppCompatActivity {
    Spinner mostrarTrabajadores;
    Button confirmarEliminarTrabajador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_trabajador);

        //Cargar tareas de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> trabajo = mysql.mostrarTrabajdores();
        mostrarTrabajadores = (Spinner)findViewById(R.id.spinnerElimTrabajador);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,trabajo);
        mostrarTrabajadores.setAdapter(adapter2);

        //Evento del boton de confirmacion
        confirmarEliminarTrabajador = (Button) findViewById(R.id.button_eliminar_trabajador);
        confirmarEliminarTrabajador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String trabajador = mostrarTrabajadores.getSelectedItem().toString();
                mysql.eliminarTrabajador(trabajador);
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_LONG).show();
            }
        });
    }
}
