package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private int[] images ={R.drawable.round_corners, R.drawable.round_corners2, R.drawable.round3, R.drawable.round4, R.drawable.round5,
            R.drawable.round6, R.drawable.round7, R.drawable.round8, R.drawable.round9};
    public GridMainAdapterAct(Context contexto, ArrayList<Actividad> actividades){
        super(contexto, R.layout.grid_acts_layout, actividades);
        this.actividades = actividades;
        c = contexto;
    }

    public View getView(int posicion, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(c);
        View objeto = inflater.inflate(R.layout.grid_acts_layout, null);

        objeto.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT,300));
//---------------Ponemos el background a las celdas para que se vaya repitiendo cada 9 actividades
       if(posicion < 9)
        {
            objeto.setBackgroundResource(images[posicion]);
        }
        else
        {
            objeto.setBackgroundResource(images[posicion%9]);
        }

        TextView TxtGrdAct1 = (TextView)objeto.findViewById(R.id.TxtGrdAct1);
        TxtGrdAct1.setText(this.actividades.get(posicion).getNombre());

        TextView TxtGrdAct2 = (TextView)objeto.findViewById(R.id.TxtGrdAct2);
        TxtGrdAct2.setText(this.actividades.get(posicion).getTipo());

        TextView TxtGrdAct3 = (TextView)objeto.findViewById(R.id.TxtGrdAct3);
        TxtGrdAct3.setText(this.actividades.get(posicion).getFecha());


        return (objeto);
    }

}
