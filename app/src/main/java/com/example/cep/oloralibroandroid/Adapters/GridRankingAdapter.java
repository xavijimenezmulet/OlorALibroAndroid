package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.R.layout.grid_ranking_layout;

public class GridRankingAdapter extends ArrayAdapter
{

	private ArrayList<Usuario> usuaris;
	private Context c;

	public GridRankingAdapter(Context contexto, ArrayList<Usuario> usuaris)
	{
		super(contexto, R.layout.grid_ranking_layout, usuaris);
		this.usuaris = usuaris;
		c = contexto;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){

		LayoutInflater inflater = LayoutInflater.from(c);
		View objeto = inflater.inflate(R.layout.grid_ranking_layout, null);

		TextView text1 = (TextView)objeto.findViewById(R.id.text1);
		text1.setText(this.usuaris.get(posicion).getNombre());

		TextView text2 = (TextView)objeto.findViewById(R.id.text2);
		text2.setText(this.usuaris.get(posicion).getPuntos());

		TextView text3 = (TextView)objeto.findViewById(R.id.text3);
		text3.setText(this.usuaris.get(posicion).getRank());

		return (objeto);
	}

}
