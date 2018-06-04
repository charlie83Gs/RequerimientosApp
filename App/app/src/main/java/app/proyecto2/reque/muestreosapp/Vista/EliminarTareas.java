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

public class EliminarTareas extends AppCompatActivity {
    Spinner mostrarTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_tareas);

        //Cargar tareas de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> tareas = mysql.mostrarTareas();
        mostrarTareas = (Spinner)findViewById(R.id.spinner_tareas_Eliminar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tareas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarTareas.setAdapter(adapter);

        //Evento del boton de confirmacion
        Button confirmarEliminacion = (Button) findViewById(R.id.button_eliminar_tarea);
        confirmarEliminacion.setOnClickListener(new View.OnClickListener() {
            TextView nuevoNom = findViewById(R.id.text_nuevoNombreTarea);
            @Override
            public void onClick(View view) {

                String tareaSeleccionada = mostrarTareas.getSelectedItem().toString();
                int idTar = Integer.parseInt(tareaSeleccionada.substring(3,4));

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.eliminarTarea(idTar);

                Toast.makeText(getApplicationContext(), "Tarea eliminada", Toast.LENGTH_LONG).show();
            }
        });
    }
}
