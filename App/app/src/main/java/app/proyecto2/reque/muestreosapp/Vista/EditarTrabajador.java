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

public class EditarTrabajador extends AppCompatActivity {
    Spinner mostrarTrabajadores;
    Button confirmarEditarTrabajador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_trabajador);


        //Cargar tareas de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> trabajo = mysql.mostrarTrabajdores();
        mostrarTrabajadores = (Spinner)findViewById(R.id.spinTrabajoEdit);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,trabajo);
        mostrarTrabajadores.setAdapter(adapter2);



        //Evento del boton de confirmacion
        confirmarEditarTrabajador = (Button) findViewById(R.id.button_editar_trabajador);
        confirmarEditarTrabajador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView nuevoNom = findViewById(R.id.textoNuevoNombre);
                TextView nuevoRol = findViewById(R.id.textoNuevoRol);
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                String trabajador = mostrarTrabajadores.getSelectedItem().toString();
                mysql.editarTrabajador(trabajador,nuevoNom.getText().toString(),nuevoRol.getText().toString());
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_LONG).show();
            }
        });
    }


}
