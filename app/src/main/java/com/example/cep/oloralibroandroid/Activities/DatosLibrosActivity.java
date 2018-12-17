package com.example.cep.oloralibroandroid.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Libro;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIR_SEPAR;

public class DatosLibrosActivity extends AppCompatActivity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datos_libros);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.libros));

		Bundle bundle = getIntent().getExtras();
		int pos = bundle.getInt("Pos");
		int posLib = bundle.getInt("PosLib");
		Libro libro = new Libro();
		libro = Utilitats.librerias.get(posLib).getLibros().get(pos);

		ImageView imgPortada = (ImageView)findViewById(R.id.ImgPortada);
		TextView txtTitulo = (TextView)findViewById(R.id.TxtTitulo) ;
		TextView txtAutor = (TextView)findViewById(R.id.TxtAutor) ;
		TextView txtAnyo = (TextView)findViewById(R.id.TxtAnyo) ;
		TextView txtPrecio = (TextView)findViewById(R.id.TxtPrecio) ;
		ListView listGeneros = (ListView)findViewById(R.id.ListGeneros);

		if (libro != null)
		{
			try
			{
				txtTitulo.setText(libro.getTitulo());
				txtAutor.setText(libro.getAutor());
				txtAnyo.setText(String.valueOf(libro.getAnyo()));
				txtPrecio.setText(String.valueOf(libro.getPrecio()));

				//Busca la ruta de la imagen y lo guarda en una variable
				String dir = Environment.getExternalStorageDirectory() + DIR_SEPAR + libro.getPortada();
				String ruta = Environment.getExternalStorageDirectory() + DIR_SEPAR;

				if (dir.length() > ruta.length())
				{
					Log.d("imagen: ", dir);
					//Inserto la imagen y cambio su resoluciónhola
					imgPortada.setImageDrawable(Drawable.createFromPath(dir));
					imgPortada.setScaleType(ImageView.ScaleType.FIT_XY);
				} else
				{
					//En caso que no haya imagen que ponga una por defecto
					imgPortada.setImageResource(R.drawable.oloralibro);
					imgPortada.setScaleType(ImageView.ScaleType.FIT_XY);
				}
			}catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
