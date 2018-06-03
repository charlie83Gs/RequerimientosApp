package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.proyecto2.reque.muestreosapp.R;

public class MenuOperaciones extends AppCompatActivity {

    Button buttonAgregarOperacion;
    Button buttonEditarOperacion;
    Button buttonEliminarOperacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_operaciones);


        //Accion para el boton de agregar operaciones
        buttonAgregarOperacion = (Button) findViewById(R.id.button_agregar_operaciones);
        buttonAgregarOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuOperaciones.this,AgregarOperacion.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de editar operaciones
        buttonEditarOperacion = (Button) findViewById(R.id.button_editar_operaciones);
        buttonEditarOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuOperaciones.this,EditarOperacion.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de eliminar operaciones
        buttonEliminarOperacion = (Button) findViewById(R.id.button_eliminar_operaciones);
        buttonEliminarOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuOperaciones.this,EliminarOperacion.class);
                startActivity(intent);
            }
        });

    }
}
