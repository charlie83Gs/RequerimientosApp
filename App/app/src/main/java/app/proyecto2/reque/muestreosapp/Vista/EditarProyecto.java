package app.proyecto2.reque.muestreosapp.Vista;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class EditarProyecto extends AppCompatActivity {
    Spinner mostrarProyectos;
    TextView nuevoNombre;
    static int idProyecto;
    Button confirmarEditar;
    TextView descripcion;
    Spinner mostrarOps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_proyecto);
        descripcion = (TextView) findViewById(R.id.text_descripcionPro_agregar);

        //Cargar proyectos de la BD y mostrarlos en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> proyectos = mysql.mostrarProyectos();
        mostrarProyectos = (Spinner)findViewById(R.id.spinner_proyectos_editar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,proyectos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarProyectos.setAdapter(adapter);

        //Evento para cuando se selecciona un proyecto del spinner
        mostrarProyectos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSeleccionado = mostrarProyectos.getSelectedItem().toString();
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                nuevoNombre = (TextView) findViewById(R.id.text_nuevoNombreProyec);
                nuevoNombre.setText(itemSeleccionado.substring(5));

                String idP = itemSeleccionado.substring(3,4);
                idProyecto = Integer.parseInt(idP);

                String descrip = mysql.descripcionPro(idProyecto);
                descripcion.setText(descrip);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Evento del boton de confirmacion
        confirmarEditar = (Button) findViewById(R.id.button_editar_proyecto);
        confirmarEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String nombrePro = nuevoNombre.getText().toString();
                String descripcionPro = descripcion.getText().toString();
                mysql.editarProyecto(idProyecto,nombrePro,descripcionPro);
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_LONG).show();
            }
        });


        //Cargar operaciones de la BD y mostrarlas en el spinner
        ArrayList<String> operaciones = mysql.mostrarOperaciones();
        mostrarOps = (Spinner)findViewById(R.id.spinner_operacionesPro_editar);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,operaciones);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarOps.setAdapter(adapter2);

        //Accion del boton (+) agregar operaciones al proyecto
        FloatingActionButton agregarT = findViewById(R.id.floatingActionButton_operaciones_editar);
        agregarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mostrarOps.getSelectedItem().toString();

                int idOperacion_seleccionado = Integer.parseInt(text.substring(3,4));
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();

                mysql.asociar_operacion_proyecto(idOperacion_seleccionado,idProyecto);

                String nombreOp = text.substring(5);
                Toast.makeText(getApplicationContext(), "Operacion: "+nombreOp+" asignada al proyecto", Toast.LENGTH_LONG).show();
            }
        });

    }
}
