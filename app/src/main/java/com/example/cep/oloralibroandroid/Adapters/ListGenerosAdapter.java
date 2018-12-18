package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

public class ListGenerosAdapter extends ArrayAdapter
{
	ArrayList generos;
	public ListGenerosAdapter(Context context, ArrayList generos){
		super(context, R.layout.list_generos_layout, generos);
		this.generos = generos;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View objeto = inflater.inflate(R.layout.list_generos_layout, null);

		TextView txtGenero = (TextView) objeto.findViewById(R.id.TxtListGeneros);
		txtGenero.setText(generos.get(posicion).toString());

		return objeto;
	}
}
