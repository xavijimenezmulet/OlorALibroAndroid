package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

public class GridRankingAdapter extends ArrayAdapter
{

	private ArrayList<Actividad> ranking;
	private Context c;
	public GridRankingAdapter(Context contexto, ArrayList<Actividad> ranking){
		super(contexto, R.layout.grid_acts_layout, ranking);
		this.ranking = ranking;
		c = contexto;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){

		LayoutInflater inflater = LayoutInflater.from(c);
		View objeto = inflater.inflate(R.layout.grid_acts_layout, null);

		TextView TxtGrdAct1 = (TextView)objeto.findViewById(R.id.TxtGrdAct1);
		TxtGrdAct1.setText(this.ranking.get(posicion).getNombre());

		TextView TxtGrdAct2 = (TextView)objeto.findViewById(R.id.TxtGrdAct2);
		TxtGrdAct2.setText(this.ranking.get(posicion).getTipo());

		TextView TxtGrdAct3 = (TextView)objeto.findViewById(R.id.TxtGrdAct3);
		TxtGrdAct3.setText(this.ranking.get(posicion).getFecha());


		return (objeto);
	}

}
