package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

public class GridMainAdapter extends ArrayAdapter
{
	private ArrayList<Libreria> librerias;

	public GridMainAdapter(Context contexto, ArrayList<Libreria> librerias ){
		super(contexto, R.layout.grid_main_layout, librerias);
		this.librerias = librerias;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){

		LayoutInflater inflater = LayoutInflater.from(getContext());
		View objeto = inflater.inflate(R.layout.grid_main_layout, null);

		ImageView ImgGrdMain = (ImageView) objeto.findViewById(R.id.ImgGrdMain);
		ImgGrdMain.setImageResource(R.drawable.defaultlibreria);

		TextView TxtGrdMain1 = (TextView)objeto.findViewById(R.id.TxtGrdMain1);
		TxtGrdMain1.setText(this.librerias.get(posicion).getNombre());

		TextView TxtGrdMain2 = (TextView)objeto.findViewById(R.id.TxtGrdMain2);
		TxtGrdMain1.setText(this.librerias.get(posicion).getDireccion());

		return (objeto);
	}
}
