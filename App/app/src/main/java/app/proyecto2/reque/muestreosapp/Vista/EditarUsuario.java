package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import app.proyecto2.reque.muestreosapp.R;

public class EditarUsuario extends AppCompatActivity {

    Spinner tiposUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        tiposUsuario = (Spinner)findViewById(R.id.spinner_tipo_usuario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiposUsuarios,android.R.layout.simple_spinner_item);
        tiposUsuario.setAdapter(adapter);
    }
}
