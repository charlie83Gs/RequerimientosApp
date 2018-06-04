package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.R;

public class AgregarUsuario extends AppCompatActivity {

    Spinner tiposUsuario;
    TextView nameUser;
    TextView passwordUser;
    TextView correo;
    TextView telef;

    Button addUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        tiposUsuario = (Spinner)findViewById(R.id.spinner_tipo_usuario);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tiposUsuarios,android.R.layout.simple_spinner_item);
        tiposUsuario.setAdapter(adapter);

        //Evento del boton de agregar User
        addUsers = (Button) findViewById(R.id.button_agregar_usuario);
        addUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameUser = (TextView) findViewById(R.id.nameAddUser);
                passwordUser = (TextView) findViewById(R.id.contrasennaAddUser);
                correo = (TextView) findViewById(R.id.correoAddUser);
                int tipUsual = tiposUsuario.getSelectedItemPosition();
                telef = (TextView) findViewById(R.id.numeroAddUser);
                int numberTel = Integer.parseInt(telef.getText().toString());
                MysqlDbDriver mysql = MysqlDbDriver.getInstance();
                mysql.addUsers(nameUser.getText().toString(),passwordUser.getText().toString(),correo.getText().toString(),numberTel,tipUsual);
                Toast.makeText(getApplicationContext(), "Usuario Agregado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
