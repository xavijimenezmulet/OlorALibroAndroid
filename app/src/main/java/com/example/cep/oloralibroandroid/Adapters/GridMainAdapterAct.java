package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.R;

import java.io.File;
import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIR_SEPAR;

public class GridMainAdapterAct extends ArrayAdapter
{
    private ArrayList<Actividad> actividades;
    private Context c;
    public GridMainAdapterAct(Context contexto, ArrayList<Actividad> actividades){
        super(contexto, R.layout.grid_acts_layout, actividades);
        this.actividades = actividades;
        c = contexto;
    }

    public View getView(int posicion, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(c);
        View objeto = inflater.inflate(R.layout.grid_acts_layout, null);

        TextView TxtGrdAct1 = (TextView)objeto.findViewById(R.id.TxtGrdAct1);
        TxtGrdAct1.setText(this.actividades.get(posicion).getNombre());

        TextView TxtGrdAct2 = (TextView)objeto.findViewById(R.id.TxtGrdAct2);
        TxtGrdAct2.setText(this.actividades.get(posicion).getTipo());

        TextView TxtGrdAct3 = (TextView)objeto.findViewById(R.id.TxtGrdAct3);
        TxtGrdAct3.setText(this.actividades.get(posicion).getFecha());


        return (objeto);
    }

}
