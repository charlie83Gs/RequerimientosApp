package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import app.proyecto2.reque.muestreosapp.R;

public class AgregarTareas extends AppCompatActivity {

    Spinner tiposTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tareas);

        tiposTareas = (Spinner)findViewById(R.id.spinner_tiposTarea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiposTarea,android.R.layout.simple_spinner_item);
        tiposTareas.setAdapter(adapter);
    }


}
