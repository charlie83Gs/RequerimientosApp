package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.proyecto2.reque.muestreosapp.R;

public class MenuProyectos extends AppCompatActivity {

    Button buttonAgregarProyecto;
    Button buttonEditarProyecto;
    Button buttonEliminarProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyectos);

        //Accion para el boton de agregar proyectos
        buttonAgregarProyecto = (Button) findViewById(R.id.button_agregar_proyectos);
        buttonAgregarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuProyectos.this,AgregarProyecto.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de editar proyectos
        buttonEditarProyecto = (Button) findViewById(R.id.button_editar_proyectos);
        buttonEditarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuProyectos.this,EditarProyecto.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de eliminar proyectos
        buttonEliminarProyecto = (Button) findViewById(R.id.button_eliminar_proyectos);
        buttonEliminarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuProyectos.this,EliminarProyecto.class);
                startActivity(intent);
            }
        });
    }
}
