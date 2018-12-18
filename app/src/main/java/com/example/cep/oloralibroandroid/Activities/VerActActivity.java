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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.cep.oloralibroandroid.Adapters.GridMainAdapter;
import com.example.cep.oloralibroandroid.Adapters.GridMainAdapterAct;
import com.example.cep.oloralibroandroid.Adapters.GridOpsAdapter;
import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.Clases.Opinion;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.JsonWrite;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.Utilities.CurrentDateTimeExample2.fechaActual;
//import static com.example.cep.oloralibroandroid.Utilities.Utilitats.usuarioConectado;

public class VerActActivity extends AppCompatActivity
{
	private GridOpsAdapter gridOpsAdapter;
	private ArrayList<Opinion> ops = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_acts);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.actividad));

		Bundle extras = getIntent().getExtras();
		final int nomAct = extras.getInt("nomAct");

		TextView tvnom = (TextView)findViewById(R.id.tvnom);
		TextView tvdesc = (TextView)findViewById(R.id.tvdesc);
		TextView tvlugar = (TextView)findViewById(R.id.tvlugar);
		TextView tvtipo = (TextView)findViewById(R.id.tvtipo);
		TextView tvfecha = (TextView)findViewById(R.id.tvfecha);
		TextView tvhora = (TextView)findViewById(R.id.tvhora);
		ListView listlibact = (ListView)findViewById(R.id.listlibact);
		final GridView GrdOpiniones = (GridView)findViewById(R.id.GrdOpiniones);
		final EditText EditOp	= (EditText)findViewById(R.id.EditOp);
		Button BtnC = (Button)findViewById(R.id.BtnC);

		tvnom.setText(Utilitats.actividades.get(nomAct).getNombre());
		tvdesc.setText(Utilitats.actividades.get(nomAct).getDescripcion());
		tvlugar.setText(Utilitats.actividades.get(nomAct).getLugar());
		tvtipo.setText(Utilitats.actividades.get(nomAct).getTipo());
		tvfecha.setText(Utilitats.actividades.get(nomAct).getFecha());
		tvhora.setText(Utilitats.actividades.get(nomAct).getHora());

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Utilitats.actividades.get(nomAct).getLibrerias());
		listlibact.setAdapter(adapter);

		ops = Utilitats.actividades.get(nomAct).getOpiniones();
		gridOpsAdapter = new GridOpsAdapter(this, ops);
		GrdOpiniones.setAdapter(gridOpsAdapter);

		listlibact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String Lib = Utilitats.actividades.get(nomAct).getLibrerias().get(position);
				int posLib = -1;
				for(int i = 0; i < Utilitats.librerias.size(); i++)
				{
					if(Utilitats.librerias.get(i).getNombre().equals(Lib))
					{
						posLib = i;
					}
				}
				if(posLib != -1)
				{
				Intent intent = new Intent(getBaseContext(), UnaLibreriaActivity.class);
				intent.putExtra("nombreLib", posLib);
				startActivity(intent);
				}
				else
				{
					Toast.makeText(VerActActivity.this, "No se ha encontrado la libreria.", Toast.LENGTH_SHORT).show();
				}

			}
		});

//---------------------Anyadir comentario-----------------
		BtnC.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String coment = EditOp.getText().toString();

				String data = fechaActual();
				Opinion op = new Opinion();
				op.setComentario(coment);
				op.setUser(Utilitats.usuarioConectado);
				op.setFecha(data);
				if(op.getComentario() != "")
				{
					ops.add(op);
					//Utilitats.actividades.get(nomAct).getOpiniones().add(op);
					EditOp.setText("");
					//ops = Utilitats.actividades.get(nomAct).getOpiniones();
					gridOpsAdapter.notifyDataSetChanged();
					GrdOpiniones.setAdapter(gridOpsAdapter);
					Utilitats.actividades.get(nomAct).setOpiniones(ops);
					JsonWrite.crearJsonActividades(nomAct);
					Utilitats.usuarioConectado.setPuntos(Utilitats.puntuacion.getPuntosComentar());
					Utilitats.usuarioConectado.setRank(Utilitats.rango.asignarRango(Utilitats.usuarioConectado.getPuntos()));
					Utilitats.usuarioConectado.setDescuento(Utilitats.generarDescuento(Utilitats.usuarioConectado.getRank()));
					JsonWrite.crearJsonUsuarios();
					int puntos = Utilitats.puntuacion.getPuntosComentar();
					Toast.makeText(VerActActivity.this, "Has sumado " + puntos + " puntos!", Toast.LENGTH_SHORT).show();
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
				Intent intent = new Intent(VerActActivity.this, MainActivity.class);
				startActivity(intent);
				onPause();
				retorno =  true;
				break;
			case R.id.Librerias:
			case R.id.LibreriasIcon:
				intent = new Intent(VerActActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
				intent = new Intent(VerActActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(VerActActivity.this, PerfilActivity.class);
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
										Intent intent = new Intent(VerActActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				finish();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(VerActActivity.this, ActividadesGActivity.class);
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