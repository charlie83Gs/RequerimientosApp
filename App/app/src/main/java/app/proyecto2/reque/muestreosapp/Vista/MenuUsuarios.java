package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.proyecto2.reque.muestreosapp.R;

public class MenuUsuarios extends AppCompatActivity {

    Button buttonAgregarUsuario;
    Button buttonEditarUsuario;
    Button buttonEliminarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuarios);

        //Accion para el boton de agregar usuarios
        buttonAgregarUsuario = (Button) findViewById(R.id.button_agregar_usuarios);
        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuUsuarios.this,AgregarUsuario.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de editar usuarios
        buttonEditarUsuario = (Button) findViewById(R.id.button_editar_usuarios);
        buttonEditarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuUsuarios.this,EditarUsuario.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de eliminar usuarios
        buttonEliminarUsuario = (Button) findViewById(R.id.button_eliminar_usuarios);
        buttonEliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuUsuarios.this,EliminarUsuario.class);
                startActivity(intent);
            }
        });
    }
}
