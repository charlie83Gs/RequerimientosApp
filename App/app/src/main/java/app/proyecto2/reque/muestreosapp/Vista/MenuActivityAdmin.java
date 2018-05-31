package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.proyecto2.reque.muestreosapp.R;


public class MenuActivityAdmin extends AppCompatActivity {


    Button button_tarea_admin;
    Button button_operacion_admin;
    Button button_trabajador_admin;
    Button button_proyecto_admin;
    Button button_muestreo_admin;
    Button button_usuario_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);


        //Accion del boton Operaciones
        button_operacion_admin = (Button) findViewById(R.id.button_operacion_admin);
        button_operacion_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityAdmin.this,MenuOperaciones.class);
                startActivity(intent);
            }
        });


        //Accion del boton Tareas
        button_tarea_admin = (Button) findViewById(R.id.button_tarea_admin);
        button_tarea_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityAdmin.this,MenuTareas.class);
                startActivity(intent);
            }
        });

        //Accion del boton Trabajadores
        button_trabajador_admin = (Button) findViewById(R.id.button_trabajador_admin);
        button_trabajador_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityAdmin.this,MenuTrabajadores.class);
                startActivity(intent);
            }
        });

        //Accion del boton Proyectos
        button_proyecto_admin = (Button) findViewById(R.id.button_proyecto_admin);
        button_proyecto_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityAdmin.this,MenuProyectos.class);
                startActivity(intent);
            }
        });

        //Accion del boton Muestreos
        button_muestreo_admin = (Button) findViewById(R.id.button_muestreo_admin);
        button_muestreo_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityAdmin.this,GenerarMuestreo.class);
                startActivity(intent);
            }
        });

        //Accion del boton Usuarios
        button_usuario_admin = (Button) findViewById(R.id.button_usuario_admin);
        button_usuario_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityAdmin.this,MenuUsuarios.class);
                startActivity(intent);
            }
        });

    }
}
