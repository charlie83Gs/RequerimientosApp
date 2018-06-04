package app.proyecto2.reque.muestreosapp.hepler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.proyecto2.reque.muestreosapp.Modelo.Muestreo;
import app.proyecto2.reque.muestreosapp.R;

public class MuestreoAdapter extends ArrayAdapter<Muestreo> {

    public MuestreoAdapter(Context context, ArrayList<Muestreo> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Muestreo muestreo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.muestreo_item, parent, false);
        }
        // Lookup view for data population
        TextView tvId = (TextView) convertView.findViewById(R.id.tvIdMuestreo);
        TextView tvObserv = (TextView) convertView.findViewById(R.id.tvObervaciones);
        TextView tvIni = (TextView) convertView.findViewById(R.id.tvHoraInicio);
        TextView tvFin = (TextView) convertView.findViewById(R.id.tvHoraFin);
        // Populate the data into the template view using the data object
        if(muestreo != null) {
            tvId.setText("id: " + String.valueOf(muestreo.getId()));
            tvObserv.setText("Muestras: " + String.valueOf(muestreo.getCantidadObservacioness()));
            tvIni.setText("inicio: " + muestreo.getHoraInicio());
            tvFin.setText("fin: " + muestreo.getHoraFinalizacion());
        }
        // Return the completed view to render on screen
        return convertView;
    }


}
