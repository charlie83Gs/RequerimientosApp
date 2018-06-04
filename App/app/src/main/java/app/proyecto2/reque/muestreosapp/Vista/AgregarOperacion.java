package app.proyecto2.reque.muestreosapp.Vista;

import android.support.design.widget.FloatingActionButton;
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

public class AgregarOperacion extends AppCompatActivity {
    TextView TextVnombreOperacion;
    Spinner mostrarTareas;
    String nombreOp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_operacion);

        //Cargar tareas de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> tareas = mysql.mostrarTareas();
        mostrarTareas = (Spinner)findViewById(R.id.spinner_tareas_agregarOp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tareas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarTareas.setAdapter(adapter);



        //Accion del boton agregar operacion
        Button confirmarAgregacion = (Button) findViewById(R.id.button_agregar_operacion);
        confirmarAgregacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextVnombreOperacion = (TextView) findViewById(R.id.text_nombreOperacion_agregar);
                nombreOp = String.valueOf(TextVnombreOperacion.getText());
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.agregarOperacion(nombreOp);

                //mostrar mensaje
                Toast.makeText(getApplicationContext(), "Operacion "+nombreOp+" agregada", Toast.LENGTH_LONG).show();

            }
        });

        //Accion del boton (+) agregar tareas a la operacion
        FloatingActionButton agregarT = findViewById(R.id.floatingActionButton_agregarT_Op);
        agregarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mostrarTareas.getSelectedItem().toString();
                int idTarea_seleccionada = Integer.parseInt(text.substring(3,4));
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                int idOperacion = mysql.ultimaOp_agregada();
                mysql.asociar_operacion_tarea(idOperacion,idTarea_seleccionada);

                String nombreTarea = text.substring(5);
                Toast.makeText(getApplicationContext(), "Tarea: "+nombreTarea+" asignada a la Operacion: "+nombreOp, Toast.LENGTH_LONG).show();
            }
        });

    }
}
