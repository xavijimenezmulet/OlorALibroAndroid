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

import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.Clases.Libro;
import com.example.cep.oloralibroandroid.R;

import java.io.File;
import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIR_SEPAR;

public class GridLibrosAdapter extends ArrayAdapter
{
	private ArrayList<Libro> libros;
	private Context c;
	public GridLibrosAdapter(Context contexto, ArrayList<Libro> libros){
		super(contexto, R.layout.grid_main_layout, libros);
		this.libros = libros;
		c = contexto;
	}

	public View getView(int posicion, View convertView, ViewGroup parent){

		LayoutInflater inflater = LayoutInflater.from(c);
		View objeto = inflater.inflate(R.layout.grid_main_layout, null);

		ImageView ImgGrdMain = (ImageView) objeto.findViewById(R.id.ImgGrdMain);
		String dir = Environment.getExternalStorageDirectory() + DIR_SEPAR + this.libros.get(posicion).getPortada();
		File file = new File(dir);
		ImgGrdMain.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));

		TextView TxtGrdMain1 = (TextView)objeto.findViewById(R.id.TxtGrdMain1);
		TxtGrdMain1.setText(this.libros.get(posicion).getTitulo());

		TextView TxtGrdMain2 = (TextView)objeto.findViewById(R.id.TxtGrdMain2);
		TxtGrdMain2.setText(String.valueOf(this.libros.get(posicion).getPrecio()));



		return (objeto);
	}
}
