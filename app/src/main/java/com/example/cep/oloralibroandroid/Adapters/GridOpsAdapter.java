package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Opinion;
import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.actividades;

public class GridOpsAdapter extends ArrayAdapter
{
	private ArrayList<Opinion> opiniones;
	private Context c;
	public GridOpsAdapter(Context contexto, ArrayList<Opinion> opiniones){
		super(contexto, R.layout.grid_ops_layout, opiniones);
		this.opiniones = opiniones;
		c = contexto;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){

		LayoutInflater inflater = LayoutInflater.from(c);
		View objeto = inflater.inflate(R.layout.grid_ops_layout, null);

		TextView TxtGrdop1 = (TextView)objeto.findViewById(R.id.TxtGrdop1);
		TxtGrdop1.setText(this.opiniones.get(posicion).getUser().getNombre());

		TextView TxtGrdop2 = (TextView)objeto.findViewById(R.id.TxtGrdop2);
		TxtGrdop2.setText(this.opiniones.get(posicion).getFecha());

		TextView TxtGrdop3 = (TextView)objeto.findViewById(R.id.TxtGrdop3);
		TxtGrdop3.setText(this.opiniones.get(posicion).getComentario());


		return (objeto);
	}

}