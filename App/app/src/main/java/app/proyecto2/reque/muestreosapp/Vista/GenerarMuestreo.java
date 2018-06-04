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

public class GenerarMuestreo extends AppCompatActivity {
    //static int idMuestreo;
    Spinner mostrarOps;
    Spinner mostrarProyectos;
    Spinner mostrarAnalistas;
    Spinner mostrarTrabajadores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_muestreo);

        MysqlDbDriver mysql = MysqlDbDriver.getInstance();

        //Cargar operaciones de la BD y mostrarlas en el spinner
        ArrayList<String> operaciones = mysql.mostrarOperaciones();
        mostrarOps = (Spinner)findViewById(R.id.spinner_operaciones_muestreo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarOps.setAdapter(adapter);

        //Cargar proyectos de la BD y mostrarlas en el spinner
        ArrayList<String> proyectos = mysql.mostrarProyectos();
        mostrarProyectos = (Spinner)findViewById(R.id.spinner_proyectos_muestreo);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,proyectos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarProyectos.setAdapter(adapter2);


        //Cargar analistas de la BD y mostrarlos en el spinner
        ArrayList<String> analistas = mysql.mostrarAnalistas();
        mostrarAnalistas = (Spinner)findViewById(R.id.spinner_analistas_muestreo);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,analistas);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarAnalistas.setAdapter(adapter3);


        //Cargar trabajadores de la BD y mostrarlos en el spinner
        ArrayList<String> trabajadores = mysql.mostrarTrabajadores();
        mostrarTrabajadores = (Spinner)findViewById(R.id.spinner_trabajadores_muestreo);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,trabajadores);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarTrabajadores.setAdapter(adapter4);


        //Evento del boton de confirmacion
        Button confirmarGeneracionM = (Button) findViewById(R.id.button_generar_muestreo);
        confirmarGeneracionM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String operacionSeleccionada = mostrarOps.getSelectedItem().toString();
                int idOp = Integer.parseInt(operacionSeleccionada.substring(3,4));

                String proyectoSeleccionado = mostrarProyectos.getSelectedItem().toString();
                int idPro = Integer.parseInt(proyectoSeleccionado.substring(3,4));

                TextView cantidadOps = (TextView) findViewById(R.id.text_cantidad_observaciones);
                int cantidadObservaciones = Integer.parseInt(cantidadOps.getText().toString());

                TextView rangoM = (TextView) findViewById(R.id.text_rango_minutos);
                int rangoMinutos = Integer.parseInt(rangoM.getText().toString());

                TextView horaIni = (TextView) findViewById(R.id.text_hora_inicio);
                String horaInicio = horaIni.getText().toString();

                TextView horaFin = (TextView) findViewById(R.id.text_hora_final);
                String horaFinalizacion = horaFin.getText().toString();

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.generarMuestreo(idOp,idPro,cantidadObservaciones,rangoMinutos,horaInicio,horaFinalizacion);

                Toast.makeText(getApplicationContext(), "Muestreo generado", Toast.LENGTH_LONG).show();
            }
        });


        //Accion del boton (+) agregar horas restringidas al muestreo
        FloatingActionButton agregarHoraRest = findViewById(R.id.floatingActionButton_agregarHoraRestringida);
        agregarHoraRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView text_horaRest = (TextView) findViewById(R.id.text_horaRest);
                String horaRestringida = text_horaRest.getText().toString();

                TextView text_Duracion = (TextView) findViewById(R.id.text_DuracionMin);
                int duracionMin = Integer.parseInt(text_Duracion.getText().toString());

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                int idMuestreo = mysql.ultimoMuestreo_generado();
                mysql.agregarHoraRestringida(idMuestreo,horaRestringida,duracionMin);

                Toast.makeText(getApplicationContext(), "Hora restringida agregada al muestreo", Toast.LENGTH_LONG).show();
            }
        });


        //Accion del boton (+) agregar analistas al muestreo
        FloatingActionButton agregarAnalistas = findViewById(R.id.floatingActionButton_agregar_analistasM);
        agregarAnalistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textAnalista = mostrarAnalistas.getSelectedItem().toString();
                int idAnalista = Integer.parseInt(textAnalista.substring(3,4));

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                int idMuestreo = mysql.ultimoMuestreo_generado();
                mysql.asociar_analista_muestreo(idMuestreo,idAnalista);

                Toast.makeText(getApplicationContext(), "Analista asignado al muestreo", Toast.LENGTH_LONG).show();
            }
        });


        //Accion del boton (+) agregar trabajadores al muestreo
        FloatingActionButton agregarTrabajadores = findViewById(R.id.floatingActionButton_trabajadores_muestreo);
        agregarTrabajadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textTrabajador = mostrarTrabajadores.getSelectedItem().toString();
                int idTrabajador = Integer.parseInt(textTrabajador.substring(3,4));

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                int idMuestreo = mysql.ultimoMuestreo_generado();
                mysql.asociar_trabajador_muestreo(idMuestreo,idTrabajador);

                Toast.makeText(getApplicationContext(), "Trabajador asignado al muestreo", Toast.LENGTH_LONG).show();
            }
        });

    }
}
