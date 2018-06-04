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

public class EliminarProyecto extends AppCompatActivity {
    Spinner mostrarProyectos;
    Button confirmarEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_proyecto);

        //Cargar proyectos de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> proyectos = mysql.mostrarProyectos();
        mostrarProyectos = (Spinner)findViewById(R.id.spinner_proyectos_eliminar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,proyectos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarProyectos.setAdapter(adapter);

        //Evento del boton de confirmacion
        confirmarEliminar = (Button) findViewById(R.id.button_eliminar_proyecto);
        confirmarEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String idPro = mostrarProyectos.getSelectedItem().toString();
                int idProyecto = Integer.parseInt(idPro.substring(3,4));

                mysql.eliminarProyecto(idProyecto);

                Toast.makeText(getApplicationContext(), "Proyecto eliminado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
