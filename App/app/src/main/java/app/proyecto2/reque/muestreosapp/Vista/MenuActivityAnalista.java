package app.proyecto2.reque.muestreosapp.Vista;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Controlador.MysqlDbDriver;
import app.proyecto2.reque.muestreosapp.Controlador.Session;
import app.proyecto2.reque.muestreosapp.Modelo.Muestreo;
import app.proyecto2.reque.muestreosapp.R;
import app.proyecto2.reque.muestreosapp.hepler.MuestreoAdapter;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MenuActivityAnalista extends AppCompatActivity {
    private ListView muestreos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_analista);
        muestreos = (ListView) findViewById(R.id.list_proyecto_analista);


        final ArrayList<Muestreo> workingOn = MysqlDbDriver.getInstance().getMuestreoUsuario(Session.getInstance().getUsuario().getId());

        muestreos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //Log.d("List View",workingOn.get(position).getHoraInicio());
                Session.getInstance().setSessionDataValue("muestreoId",
                        String.valueOf(workingOn.get(position).getId()));
                Intent intent = new Intent( MenuActivityAnalista.this,TomarMuestra.class);
                startActivity(intent);
            }
        });
        try {
            TextView emptyText = (TextView)findViewById(android.R.id.empty);
            muestreos.setEmptyView(emptyText);
            MuestreoAdapter adapter = new MuestreoAdapter(this, workingOn);

            muestreos.setAdapter(adapter);
        }catch (Exception e){
            Log.e("Adapter", "Error adapter: ",e );
        }

    }
}
