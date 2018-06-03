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

        tiposTareas = (Spinner)findViewById(R.id.spinner_tiposTarea2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiposTarea,android.R.layout.simple_spinner_item);
        tiposTareas.setAdapter(adapter);

        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> tareas = mysql.mostrarTareas();
        mostrarTareas = (Spinner)findViewById(R.id.spinner_seleccionar_tarea);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tareas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarTareas.setAdapter(adapter2);



        mostrarTareas.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String msupplier = mostrarTareas.getSelectedItem().toString();


                nuevoNombre = (TextView) findViewById(R.id.text_nuevoNombreTarea);
                nuevoNombre.setText(msupplier.substring(5));

                String idTar = msupplier.substring(3,4);
                idTarea = Integer.parseInt(idTar);

                //Log.e("Selected item : ",idTarea);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        confirmarEditar = (Button) findViewById(R.id.button_editar_tarea);
        confirmarEditar.setOnClickListener(new View.OnClickListener() {
            TextView nuevoNom = findViewById(R.id.text_nuevoNombreTarea);
            @Override
            public void onClick(View view) {
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String text = tiposTareas.getSelectedItem().toString();
                mysql.editarTarea(idTarea,String.valueOf(nuevoNom.getText()),text);
            }
        });
    }
}
