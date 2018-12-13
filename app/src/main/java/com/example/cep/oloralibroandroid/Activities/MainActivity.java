package com.example.cep.oloralibroandroid.Activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.cep.oloralibroandroid.Adapters.GridMainAdapter;
import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
	private ImageView ImgWelcome;
	private GridView GrdMain;
	private ArrayList<Libreria> l = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.main));





		ImgWelcome = (ImageView)findViewById(R.id.ImgWelcome);
		ImgWelcome.setImageResource(R.drawable.imageninicio);


		GrdMain = (GridView)findViewById(R.id.GrdMain);

		cargarUltimasSeisLibrerias(l);
		GridMainAdapter gridMainAdapter = new GridMainAdapter(this, l);
		GrdMain.setAdapter(gridMainAdapter);

		//Para acceder a la información de la librería desde el main
		GrdMain.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
			{
				try
				{
					Intent i = new Intent(MainActivity.this, activity_una_libreria.class);
					i.putExtra("nombreLib", position);
					startActivity(i);
				}catch (Exception e){
					Log.d("ERROR CLIC MAIN: ", e.getMessage());
				}
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
				Intent intent = new Intent(MainActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
			case R.id.RankingIcon:
				intent = new Intent(MainActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(MainActivity.this, PerfilActivity.class);
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
										Intent intent = new Intent(MainActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(MainActivity.this, ActividadesGActivity.class);
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

	public void cargarUltimasSeisLibrerias(ArrayList<Libreria> librerias){
		ArrayList <Libreria> libs = Utilitats.getLibrerias();
		int contador = 0;
		int i = libs.size()-1;
		Boolean verdadero = false;
		if(libs ==null){
			do{
				librerias.add(new Libreria());
				contador++;
			} while(contador!=6);
		}
		else
		{
			do
			{
				Libreria ll= libs.get(i);
				librerias.add(ll);
				i--;
				contador++;
			} while (contador != 6 && i!=-1);
			if(contador!=6){
				do{
					librerias.add(new Libreria());
					contador++;
				} while(contador!=6);
			}
		}
	}


}
