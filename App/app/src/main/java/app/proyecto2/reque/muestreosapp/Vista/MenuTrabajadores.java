package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.proyecto2.reque.muestreosapp.R;

public class MenuTrabajadores extends AppCompatActivity {

    Button buttonAgregarTrabajador;
    Button buttonEditarTrabajador;
    Button buttonEliminarTrabajador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_trabajadores);


        //Accion para el boton de agregar operaciones
        buttonAgregarTrabajador = (Button) findViewById(R.id.button_agregar_trabajadores);
        buttonAgregarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuTrabajadores.this,AgregarTrabajador.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de editar operaciones
        buttonEditarTrabajador = (Button) findViewById(R.id.button_editar_trabajadores);
        buttonEditarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuTrabajadores.this,EditarTrabajador.class);
                startActivity(intent);
            }
        });

        //Accion para el boton de eliminar operaciones
        buttonEliminarTrabajador = (Button) findViewById(R.id.button_eliminar_trabajadores);
        buttonEliminarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuTrabajadores.this,EliminarTrabajador.class);
                startActivity(intent);
            }
        });
    }
}
