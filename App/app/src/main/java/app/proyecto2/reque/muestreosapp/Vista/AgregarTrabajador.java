package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class AgregarTrabajador extends AppCompatActivity {
    TextView apodo;
    TextView puesto;

    Button addTrabajador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trabajador);

       // create_trabajador(IN pApodo VARCHAR(50),IN pPuesto VARCHAR(50)) button_agregar_trabajador


        //Evento del boton de agregar trabajador
        addTrabajador = (Button) findViewById(R.id.button_agregar_trabajador);
        addTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apodo = (TextView) findViewById(R.id.textoApodo);
                puesto = (TextView) findViewById(R.id.textoPuesto);
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.addTrabajador(apodo.getText().toString(),puesto.getText().toString());
                Toast.makeText(getApplicationContext(), "Trabajador Agregado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
