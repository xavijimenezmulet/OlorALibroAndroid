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
	//private ArrayList<Actividad> a = new ArrayList<>();

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

		GrdActs = (GridView)findViewById(R.id.GrdActs);
        //cargarActividades(Utilitats.actividades);
        GridMainAdapterAct gridActAdapter = new GridMainAdapterAct(this, Utilitats.actividades);
        GrdActs.setAdapter(gridActAdapter);

        GrdActs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Get the GridView selected/clicked item text
				TextView TxtGrdAct1 =(TextView) parent.findViewById(R.id.TxtGrdAct1);
				String selectedItem = TxtGrdAct1.getText().toString();
				//String selectedItem = parent.getItemAtPosition(position).toString();
				Intent intent = new Intent(getBaseContext(), VerActActivity.class);
				intent.putExtra("nomAct", selectedItem);
				startActivity(intent);
			}
		});
	}


/*
	GrdActs.setOnItemClickListener(new OnItemClickListener()
	{
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	long arg3) {
	if(position==1) {
		Intent intent = new Intent(GridViewExampleActivity.this, IndiaActivity.class);
		startActivity(intent);
	}
	else if(position==2)
	{
		Intent intent = new Intent(GridViewExampleActivity.this, BrazilActivity.class);
		startActivity(intent);

	}
	Toast.makeText(GridViewExampleActivity.this, mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
}
});
	*/
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
				Intent intent = new Intent(ActividadesGActivity.this, MainActivity.class);
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
				intent = new Intent(ActividadesGActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
			case R.id.RankingIcon:
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
										Intent intent = new Intent(ActividadesGActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				retorno =true;
				break;
			default:
				retorno = super.onOptionsItemSelected(item);
				break;

		}
		return retorno;
	}

}
