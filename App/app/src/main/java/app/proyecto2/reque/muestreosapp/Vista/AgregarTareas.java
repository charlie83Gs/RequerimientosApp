package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class AgregarTareas extends AppCompatActivity {

    Spinner tiposTareas;
    TextView nombreTarea;
    Button confirmarAgregacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tareas);

        tiposTareas = (Spinner)findViewById(R.id.spinner_tiposTarea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiposTarea,android.R.layout.simple_spinner_item);
        tiposTareas.setAdapter(adapter);



        confirmarAgregacion = (Button) findViewById(R.id.button_agregar_tarea);
        confirmarAgregacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreTarea = (TextView) findViewById(R.id.nombreTarea);
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String text = tiposTareas.getSelectedItem().toString();
                mysql.agregarTarea(String.valueOf(nombreTarea.getText()),text);
            }
        });
    }


}
