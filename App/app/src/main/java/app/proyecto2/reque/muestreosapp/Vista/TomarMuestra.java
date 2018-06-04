package app.proyecto2.reque.muestreosapp.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.Controlador.Session;
import app.proyecto2.reque.muestreosapp.Modelo.Muestreo;
import app.proyecto2.reque.muestreosapp.R;

public class TomarMuestra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_muestra);
        int id = Integer.valueOf(Session.getInstance().getSessionDataValue("muestreoId"));

        Muestreo m = MysqlDbDriver.getInstance().getMuestreo(id);
    }
}
