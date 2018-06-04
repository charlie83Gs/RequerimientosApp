package app.proyecto2.reque.muestreosapp.Vista;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;


public class EditarOperacion extends AppCompatActivity {
    Spinner mostrarOps;
    public static int idOp;
    Spinner mostrarTareas;
    TextView nuevoNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_operacion);


        //Cargar operaciones de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> operaciones = mysql.mostrarOperaciones();
        mostrarOps = (Spinner)findViewById(R.id.spinner_operaciones_Editar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarOps.setAdapter(adapter);


        //Evento para cuando se selecciona una operacion del spinner
        mostrarOps.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSeleccionado = mostrarOps.getSelectedItem().toString();

                nuevoNombre = (TextView) findViewById(R.id.text_nuevoNombreOp);
                nuevoNombre.setText(itemSeleccionado.substring(5));

                String idOperacion = itemSeleccionado.substring(3,4);
                idOp = Integer.parseInt(idOperacion);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Evento del boton de confirmacion
        Button confirmarEditar = (Button) findViewById(R.id.button_editar_operacion);
        confirmarEditar.setOnClickListener(new View.OnClickListener() {
            TextView nuevoNom = findViewById(R.id.text_nuevoNombreOp);
            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.editarOperacion(idOp,String.valueOf(nuevoNom.getText()));
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_LONG).show();
            }
        });


        //Cargar tareas de la BD y mostrarlas en el spinner
        ArrayList<String> tareas = mysql.mostrarTareas();
        mostrarTareas = (Spinner)findViewById(R.id.spinner_tareas_editarOp);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tareas);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarTareas.setAdapter(adapter2);

        //Accion del boton (+) agregar tareas a la operacion
        FloatingActionButton agregarOp = findViewById(R.id.floatingActionButton_agregarTarOp_editar);
        agregarOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mostrarTareas.getSelectedItem().toString();
                int idTarea_seleccionada = Integer.parseInt(text.substring(3,4));
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.asociar_operacion_tarea(idOp,idTarea_seleccionada);

                String nombreTarea = text.substring(5);
                String nombreOp = String.valueOf(nuevoNombre.getText());
                Toast.makeText(getApplicationContext(), "Tarea: "+nombreTarea+" asignada a la Operacion: "+nombreOp, Toast.LENGTH_LONG).show();
            }
        });


    }
}
