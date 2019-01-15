package com.example.cep.oloralibroandroid.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Adapters.ListGenerosAdapter;
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
//-----------Agafem el llibre que pasem al clicar en la grid de llibres
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
			//-----------Omplim les dades amb la informacio del llibre
				txtTitulo.setText(libro.getTitulo());
				txtAutor.setText(libro.getAutor());
				txtAnyo.setText(String.valueOf(libro.getAnyo()));
				txtPrecio.setText(String.valueOf(libro.getPrecio()));

				ListGenerosAdapter listGenerosAdapter = new ListGenerosAdapter(this,libro.getGenero());
				//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, libro.getGenero());
				listGeneros.setAdapter(listGenerosAdapter);
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean retorno = super.onOptionsItemSelected(item);

		switch(item.getItemId()) {
			case R.id.IncioIcon:
			case R.id.Inicio:
				Intent intent = new Intent(DatosLibrosActivity.this, MainActivity.class);
				startActivity(intent);
				onPause();
				retorno =  true;
				break;
			case R.id.Librerias:
			case R.id.LibreriasIcon:
				intent = new Intent(DatosLibrosActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
				intent = new Intent(DatosLibrosActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(DatosLibrosActivity.this, PerfilActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Logout:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.logout_desc)
						.setTitle(R.string.advertencia)
						.setCancelable(false)
						.setNegativeButton(R.string.cancelar,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								})
						.setPositiveButton(R.string.desconectar,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										Intent intent = new Intent(DatosLibrosActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(DatosLibrosActivity.this, ActividadesGActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			default:
				retorno = super.onOptionsItemSelected(item);
				break;
		}
		return retorno;
	}
}
