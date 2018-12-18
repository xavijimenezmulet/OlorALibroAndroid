package com.example.cep.oloralibroandroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

public class GridRankingAdapter extends ArrayAdapter
{

	private ArrayList<Usuario> usuario;
	private Context c;
	public GridRankingAdapter(Context contexto, ArrayList<Usuario> usuario){
		super(contexto, R.layout.grid_acts_layout, usuario);
		this.usuario = usuario;
		c = contexto;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){

		LayoutInflater inflater = LayoutInflater.from(c);
		View objeto = inflater.inflate(R.layout.grid_ranking_layout, null);

		try{


			TextView TxtText1 = (TextView)objeto.findViewById(R.id.text1);
			TxtText1.setText(this.usuario.get(posicion).getNombre());
			Log.d("TEXT1", (String) TxtText1.getText());

			TextView TxtText2 = (TextView)objeto.findViewById(R.id.text2);
			TxtText2.setText(Integer.toString(this.usuario.get(posicion).getPuntos()));
			Log.d("TEXT2", (String) TxtText2.getText());


			TextView TxtText3 = (TextView)objeto.findViewById(R.id.text3);
			TxtText3.setText(this.usuario.get(posicion).getRank());
			Log.d("TEXT3", (String) TxtText3.getText());

		}catch (Exception e){
			Log.d("Excepcion Adapter", e.getMessage());
		}

		return (objeto);
	}

}
