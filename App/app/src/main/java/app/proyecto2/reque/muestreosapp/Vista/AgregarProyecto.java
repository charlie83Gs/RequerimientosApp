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

public class AgregarProyecto extends AppCompatActivity {
    Spinner mostrarOps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_proyecto);

        //Accion del boton agregar proyecto
        Button confirmarAgregacion = (Button) findViewById(R.id.button_agregar_proyecto);
        confirmarAgregacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textNombrePro = (TextView) findViewById(R.id.text_nombrePro_agregar);
                String nombreProyecto = textNombrePro.getText().toString();

                TextView textDescripcion = (TextView) findViewById(R.id.text_descripcionPro_agregar);
                String descripcion = textDescripcion.getText().toString();

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.agregarProyecto(nombreProyecto,descripcion);

                //mostrar mensaje
                Toast.makeText(getApplicationContext(), "Proyecto agregado", Toast.LENGTH_LONG).show();
            }
        });


        //Cargar operaciones de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> operaciones = mysql.mostrarOperaciones();
        mostrarOps = (Spinner)findViewById(R.id.spinner_operacionesPro_agregar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarOps.setAdapter(adapter);

        //Accion del boton (+) agregar operaciones al proyecto
        FloatingActionButton agregarT = findViewById(R.id.floatingActionButton_agregarOp_agregarPro);
        agregarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mostrarOps.getSelectedItem().toString();

                int idOperacion_seleccionado = Integer.parseInt(text.substring(3,4));
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                int idProyecto = mysql.ultimoProyecto_agregado();

                mysql.asociar_operacion_proyecto(idOperacion_seleccionado,idProyecto);

                String nombreOp = text.substring(5);
                Toast.makeText(getApplicationContext(), "Operacion: "+nombreOp+" asignada al proyecto", Toast.LENGTH_LONG).show();
            }
        });
    }
}
