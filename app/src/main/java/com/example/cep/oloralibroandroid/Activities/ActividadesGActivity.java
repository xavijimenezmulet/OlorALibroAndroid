package com.example.cep.oloralibroandroid.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cep.oloralibroandroid.Adapters.GridMainAdapter;
import com.example.cep.oloralibroandroid.Adapters.GridMainAdapterAct;
import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;


import java.util.ArrayList;

public class ActividadesGActivity extends AppCompatActivity
{
	private GridView GrdActs;
	private Boolean check = false;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividades_g);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.actividades));

		GrdActs = (GridView) findViewById(R.id.GrdActs);
		GridMainAdapterAct gridActAdapter;
//---------------------------------Cargar actividades generales o de libreria, depende de si pasamos extras------------------
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			check = true;
			final int positionLib = extras.getInt("Libreria");
			ArrayList<Actividad> actividades = new ArrayList<>();
			for(int i = 0; i < Utilitats.librerias.get(positionLib).getActividades().size(); i++)
			{
				for (int j = 0; j < Utilitats.actividades.size(); j++)
				{
					if(Utilitats.librerias.get(positionLib).getActividades().get(i).equals(Utilitats.actividades.get(j).getNombre()))
					{
						actividades.add(Utilitats.actividades.get(j));
					}
				}
			}
			gridActAdapter = new GridMainAdapterAct(this, actividades);
			TextView TxtActsT = (TextView) findViewById(R.id.TxtActsT);
			TxtActsT.setText("Actividades de " + Utilitats.librerias.get(positionLib).getNombre());
		}
		else
		{
			gridActAdapter = new GridMainAdapterAct(this, Utilitats.actividades);

		}
		GrdActs.setAdapter(gridActAdapter);

//---------------------Abrir actividad---------------
        GrdActs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView TxtGrdAct1 =(TextView) parent.findViewById(R.id.TxtGrdAct1);
				Intent intent = new Intent(getBaseContext(), VerActActivity.class);
				//----------Si estamos viendo todas las actividades, pasamos directamente la posicion de la grid para ver la actividad
				if(check == false)
				{

					intent.putExtra("nomAct", position);
				}
				//--------Si vemos las actividades de una sola libreria, buscamos la posicion la actividad seleccionada en la llista de actividades
				else
				{
					String nombre = TxtGrdAct1.getText().toString();
					int pos = -1;
					for(int i = 0; i < Utilitats.actividades.size(); i++)
					{
						if (Utilitats.actividades.get(i).getNombre().equals(nombre))
						{
							pos = i;
						}
					}
					if (pos > -1)
					{
						intent.putExtra("nomAct", pos);
					}
				}
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean retorno = super.onOptionsItemSelected(item);

		switch(item.getItemId()) {
			case R.id.IncioIcon:
			case R.id.Inicio:
				Intent intent = new Intent(ActividadesGActivity.this, MainActivity.class);
				startActivity(intent);
				onPause();
				retorno =  true;
				break;
			case R.id.Librerias:
			case R.id.LibreriasIcon:
				intent = new Intent(ActividadesGActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
				intent = new Intent(ActividadesGActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(ActividadesGActivity.this, PerfilActivity.class);
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
										Intent intent = new Intent(ActividadesGActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				if(check = true)
				{
					intent = new Intent(ActividadesGActivity.this, ActividadesGActivity.class);
					startActivity(intent);
					this.finish();
				}
				retorno =true;
				break;
			default:
				retorno = super.onOptionsItemSelected(item);
				break;

		}
		return retorno;
	}

}
