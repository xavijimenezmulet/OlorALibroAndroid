package com.example.cep.oloralibroandroid.Activities;

/**
 * IMPORTS
 */
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
import com.example.cep.oloralibroandroid.Utilities.JsonWrite;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import java.util.ArrayList;

/**
 * MAIN ACTIVITY (PRINCIPAL)
 */
public class MainActivity extends AppCompatActivity
{
	//REFERENCIAS
	private ImageView ImgWelcome;
	private GridView GrdMain;
	private ArrayList<Libreria> l = new ArrayList<>();

	/**
	 * ON CREATE DE MAIN ACTIVITY
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Creación y asignación de la actionBar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.main));

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			int conectado = extras.getInt("conectado");
			if (conectado == 1)
			{
				// Toast que nos muestra el nombre de bienvenida del nombre del usuario
				int puntos = Utilitats.puntuacion.getPuntosComentar();
				Toast.makeText(this, getString(R.string.bienvenido)
						+ Utilitats.usuarioConectado.getNombre()
						+ getString(R.string.exclamacion), Toast.LENGTH_LONG).show();
			}
		}
		// conexiones con las referencias
		ImgWelcome = (ImageView)findViewById(R.id.ImgWelcome);
		ImgWelcome.setImageResource(R.drawable.imageninicio);


		GrdMain = (GridView)findViewById(R.id.GrdMain);

		//cargamos las ultimas librerias y las adaptamos
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
					Intent i = new Intent(MainActivity.this, UnaLibreriaActivity.class);
					int pos = (Utilitats.librerias.size()-1 )- position;
					i.putExtra("nombreLib", pos);
					startActivity(i);
				}catch (Exception e){
					Log.d(getString(R.string.errorClick), e.getMessage());
				}
			}
		});
		
	}


	/**
	 * ON CREATE MENU DEL MAIN
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	/**
	 * SWITCH DE LAS OPCIONES DEL MENÚ DEL MAIN
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean retorno = super.onOptionsItemSelected(item);

		switch(item.getItemId()) {
			case R.id.IncioIcon:
			case R.id.Inicio:
				retorno =  true;
				break;
			case R.id.Librerias:
			case R.id.LibreriasIcon:
				Intent intent = new Intent(MainActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
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
										Intent intent = new Intent(MainActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
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

	/**
	 * NOS CARGA LAS ULTIMAS SEIS LIBRERIAS INTRODUCIDAS, SI NO EXISTEN O HAY MENOS DE 6 INTRODUCIDAS NOS
	 * CARGA IMAGEN POR DEFECTO Y NOS MUESTRA MENSAJE EN EL TITULO DE QUE NO EXISTE LIBRERIA
	 * @param librerias
	 */
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
