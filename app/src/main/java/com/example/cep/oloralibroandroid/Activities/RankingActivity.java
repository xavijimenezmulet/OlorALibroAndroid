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
import android.widget.GridView;


import com.example.cep.oloralibroandroid.Adapters.GridMainAdapter;
import com.example.cep.oloralibroandroid.Adapters.GridRankingAdapter;
import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.Clases.Visita;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

import static java.lang.Math.E;

public class RankingActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.ranking));

		try{
			GridView ranking = (GridView)findViewById(R.id.grdRankingUsuaris);
			ArrayList<Usuario> usuaris = new ArrayList<Usuario>();
			usuaris = Utilitats.usuarios;

			Collections.sort(usuaris, new Comparator<Usuario>()
			{
				@Override
				public int compare(Usuario usuario, Usuario u1)
				{
					return new Integer(u1.getPuntos()).compareTo(new Integer(usuario.getPuntos()));
				}
			});

			GridRankingAdapter grdRankingAdapter = new GridRankingAdapter(this,usuaris);
			ranking.setAdapter(grdRankingAdapter);
		}catch (Exception e){
			Log.d("Excepcion Adapter", e.getMessage());
		}


	}
	
	/*public static ArrayList<String> ActividadesVisitadas(){

		ArrayList<String> actVisitadas = new ArrayList<String>();

		try
		{
			//String[] actVisitadas = new String[0];

			for (int i=0;i<Utilitats.actividades.size();i++){

				String[] unaActVis = CalculoActVis(Utilitats.actividades.get(i));
				actVisitadas.add(String.valueOf(unaActVis));
			}
		}catch (Exception e){
			Log.i("ACTVIS ERROR",e.getMessage());
		}

		return actVisitadas;
	}

	public static String[] CalculoActVis(Actividad act){

		String[] unaActVis = new String[2];

		try{
			unaActVis[0] = act.getNombre();
			unaActVis[1]= String.valueOf(0);
			ArrayList<Visita> v = new ArrayList<>();
			v = act.getVisitas();
			ListIterator<Visita> iter = v.listIterator();
			int contador =0;
			while(iter.hasNext()){
				contador++;
				iter.next();
			}

			unaActVis[1] = String.valueOf(contador);
		}catch (Exception e){
			Log.i("CALCULO VIS ERROR",e.getMessage());
		}

		return unaActVis;
	}*/

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
				Intent intent = new Intent(RankingActivity.this, MainActivity.class);
				startActivity(intent);
				onPause();
				retorno =  true;
				break;
			case R.id.Librerias:
			case R.id.LibreriasIcon:
				intent = new Intent(RankingActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(RankingActivity.this, PerfilActivity.class);
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
										Intent intent = new Intent(RankingActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(RankingActivity.this, ActividadesGActivity.class);
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
