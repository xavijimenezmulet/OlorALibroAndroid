package com.example.cep.oloralibroandroid.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Libro;
import com.example.cep.oloralibroandroid.R;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIR_SEPAR;

public class DatosLibrosActivity extends AppCompatActivity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_libro);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.libros));

		Libro libro = new Libro();

		ImageView imgPortada = (ImageView)findViewById(R.id.ImgPortada);
		TextView txtTitulo = (TextView)findViewById(R.id.TxtTitulo) ;
		TextView txtAutor = (TextView)findViewById(R.id.TxtAutor) ;
		TextView txtAnyo = (TextView)findViewById(R.id.TxtAnyo) ;
		TextView txtPrecio = (TextView)findViewById(R.id.TxtPrecio) ;

		if (libro != null)
		{
			try
			{
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
