package com.example.cep.oloralibroandroid.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.cep.oloralibroandroid.Adapters.GridLibrosAdapter;
import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.Clases.Libro;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import java.util.ArrayList;

public class LibroActivity extends AppCompatActivity
{
	private GridView GrdLibros;
	private Libreria libreria = new Libreria();



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_libro);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.libros));
//------Agafem la llibreria de la que venim
		Bundle bundle = getIntent().getExtras();
		final int pos = bundle.getInt("posicion");
		libreria = Utilitats.librerias.get(pos);

		GrdLibros = (GridView)findViewById(R.id.GrdLibros);

		GridLibrosAdapter gridLibrosAdapter = new GridLibrosAdapter(this, libreria.getLibros());

		GrdLibros.setAdapter(gridLibrosAdapter);
//-----Event per obrir la informacio d'un llibre al clicarlo a la grid
		GrdLibros.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
			{
				Intent intent = new Intent(LibroActivity.this, DatosLibrosActivity.class);
				intent.putExtra("Pos", i);
				intent.putExtra("PosLib", pos);
				startActivity(intent);
			}
		});

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
				Intent intent = new Intent(LibroActivity.this, MainActivity.class);
				startActivity(intent);
				onPause();
				retorno =  true;
				break;
			case R.id.Librerias:
			case R.id.LibreriasIcon:
				intent = new Intent(LibroActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
				intent = new Intent(LibroActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(LibroActivity.this, PerfilActivity.class);
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
										Intent intent = new Intent(LibroActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(LibroActivity.this, ActividadesGActivity.class);
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
