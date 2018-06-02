package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.proyecto2.reque.muestreosapp.R;


public class MenuTareas extends AppCompatActivity {

    Button buttonAgregarTareas;
    Button buttonEditarTareas;
    Button buttonEliminarTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tareas);

        //Accion para el boton de agregar tareas
        buttonAgregarTareas = (Button) findViewById(R.id.button_agregar_tareas);
        buttonAgregarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuTareas.this,AgregarTareas.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de editar tareas
        buttonEditarTareas = (Button) findViewById(R.id.button_editar_tareas);
        buttonEditarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuTareas.this,EditarTareas.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de eliminar tareas
        buttonEliminarTareas = (Button) findViewById(R.id.button_eliminar_tareas);
        buttonEliminarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuTareas.this,EliminarTareas.class);
                startActivity(intent);
            }
        });

    }
}
