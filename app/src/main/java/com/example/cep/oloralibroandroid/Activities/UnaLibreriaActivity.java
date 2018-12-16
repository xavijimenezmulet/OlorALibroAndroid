package com.example.cep.oloralibroandroid.Activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIR_SEPAR;

public class UnaLibreriaActivity extends AppCompatActivity
{

	@SuppressLint("LongLogTag")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_una_libreria);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.main));

		Bundle extras = getIntent().getExtras();
		int positionLib = extras.getInt("nombreLib");

		Libreria libreria = new Libreria();
		libreria = Utilitats.librerias.get(positionLib);

		ImageView img = (ImageView)findViewById(R.id.ImgLibrerias);
		TextView txtTitle = (TextView)findViewById(R.id.TxtTitleLib) ;
		TextView txtDireccion = (TextView)findViewById(R.id.TxtDireccion) ;
		TextView txtCorreo = (TextView)findViewById(R.id.TxtCorreo) ;
		TextView txtTelefono = (TextView)findViewById(R.id.TxtTelefono) ;
		TextView txtTitDireccion = (TextView)findViewById(R.id.TxtTitDireccion) ;
		TextView txtTitCorreo = (TextView)findViewById(R.id.TxtTitCorreo) ;
		TextView txtTitTelefono = (TextView)findViewById(R.id.TxtTitTelefono) ;
		Button BtnActividades = (Button)findViewById(R.id.BtnActividades);

		if (libreria!=null)
		{
			try
			{
				txtTitDireccion.setText("Dirección: ");
				txtTitCorreo.setText("Correo: ");
				txtTitTelefono.setText("Teléfono: ");

				txtTitle.setText(libreria.getNombre());
				txtCorreo.setText(libreria.getCorreo());
				txtDireccion.setText(libreria.getDireccion());
				txtTelefono.setText(libreria.getTelefono());

				//Busca la ruta de la imagen y lo guarda en una variable
				String dir = Environment.getExternalStorageDirectory() + DIR_SEPAR + libreria.getImagen();
				String ruta = Environment.getExternalStorageDirectory() + DIR_SEPAR;

				if (dir.length() > ruta.length())
				{
					Log.d("imagen: ", dir);
					//Inserto la imagen y cambio su resoluciónhola
					img.setImageDrawable(Drawable.createFromPath(dir));
					img.setScaleType(ImageView.ScaleType.FIT_XY);
				} else
				{
					//En caso que no haya imagen que ponga una por defecto
					img.setImageResource(R.drawable.oloralibro);
					img.setScaleType(ImageView.ScaleType.FIT_XY);
				}
			}catch (Exception e)
			{
				Log.d("ERROR Al rellenar info de libreria: ", e.getMessage());
			}
		}
		else{
			txtTitle.setText("NO HAY LIBRERÍAS DISPONIBLES");
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
				Intent intent = new Intent(UnaLibreriaActivity.this, MainActivity.class);
				startActivity(intent);
				onPause();
				retorno =  true;
				break;
			case R.id.Salir:
			case R.id.SalirIcon:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.mensaje_salir)
						.setTitle(R.string.advertencia)
						.setCancelable(false)
						.setNegativeButton(R.string.cancelar,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								})
						.setPositiveButton(R.string.salir,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										System.exit(0);// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				retorno = true;
				break;

			case R.id.Librerias:
			case R.id.LibreriasIcon:
				intent = new Intent(UnaLibreriaActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
			case R.id.RankingIcon:
				intent = new Intent(UnaLibreriaActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(UnaLibreriaActivity.this, PerfilActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Logout:
				builder = new AlertDialog.Builder(this);
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
										Intent intent = new Intent(UnaLibreriaActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(UnaLibreriaActivity.this, ActividadesGActivity.class);
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
