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

public class EliminarOperacion extends AppCompatActivity {
    Spinner mostrarOps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_operacion);

        //Cargar operaciones de la BD y mostrarlas en el spinner
        MysqlDbDriver mysql = MysqlDbDriver.getInstance();
        ArrayList<String> operaciones = mysql.mostrarOperaciones();
        mostrarOps = (Spinner)findViewById(R.id.spinner_ops_eliminar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mostrarOps.setAdapter(adapter);

        //Evento del boton de confirmacion
        Button confirmarEliminacion = (Button) findViewById(R.id.button_eliminar_operacion);
        confirmarEliminacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OperacionSeleccionada = mostrarOps.getSelectedItem().toString();
                int idOp = Integer.parseInt(OperacionSeleccionada.substring(3,4));

                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.eliminarOperacion(idOp);
                Toast.makeText(getApplicationContext(), "Operación eliminada", Toast.LENGTH_LONG).show();
            }
        });
    }
}
