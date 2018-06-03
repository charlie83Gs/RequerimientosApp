package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class EditarTareas extends AppCompatActivity {
    public static int idTarea;
    Spinner tiposTareas;
    Spinner mostrarTareas;
    TextView nuevoNombre;
    Button confirmarEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tareas);

        //Cargar los tipos de tareas en el spinner
        tiposTareas = (Spinner)findViewById(R.id.spinner_tiposTarea2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiposTarea,android.R.layout.simple_spinner_item);
        tiposTareas.setAdapter(adapter);

        //Cargar tareas de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> tareas = mysql.mostrarTareas();
        mostrarTareas = (Spinner)findViewById(R.id.spinner_seleccionar_tarea);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tareas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarTareas.setAdapter(adapter2);

        //Evento para cuando se selecciona una tarea del spinner
        mostrarTareas.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSeleccionado = mostrarTareas.getSelectedItem().toString();

                nuevoNombre = (TextView) findViewById(R.id.text_nuevoNombreTarea);
                nuevoNombre.setText(itemSeleccionado.substring(5));

                String idTar = itemSeleccionado.substring(3,4);
                idTarea = Integer.parseInt(idTar);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Evento del boton de confirmacion
        confirmarEditar = (Button) findViewById(R.id.button_editar_tarea);
        confirmarEditar.setOnClickListener(new View.OnClickListener() {
            TextView nuevoNom = findViewById(R.id.text_nuevoNombreTarea);
            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String text = tiposTareas.getSelectedItem().toString();
                mysql.editarTarea(idTarea,String.valueOf(nuevoNom.getText()),text);
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_LONG).show();
            }
        });
    }
}
