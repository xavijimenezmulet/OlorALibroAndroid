package com.example.cep.oloralibroandroid.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

public class PerfilActivity extends AppCompatActivity
{
	private AutoCompleteTextView email_signup1;
	private EditText password1;
	private EditText repite_password1;
	private AutoCompleteTextView nombre1;
	private AutoCompleteTextView apellidos1;
	private AutoCompleteTextView ciudad1;
	private TextView puntos1;
	private TextView rank1;
	private TextView descuento1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.perfil));

		email_signup1 = (AutoCompleteTextView)findViewById(R.id.email_signup1);
		email_signup1.setText(Utilitats.usuarioConectado.getUsername());
		password1 = (EditText)findViewById(R.id.password1);
		password1.setText(Utilitats.usuarioConectado.getPassword());
		repite_password1 = (EditText)findViewById(R.id.repite_password1);
		repite_password1.setText(Utilitats.usuarioConectado.getPassword());
		nombre1 = (AutoCompleteTextView)findViewById(R.id.nombre1);
		nombre1.setText(Utilitats.usuarioConectado.getNombre());
		apellidos1 = (AutoCompleteTextView)findViewById(R.id.apellidos1);
		apellidos1.setText(Utilitats.usuarioConectado.getApellidos());
		ciudad1 = (AutoCompleteTextView)findViewById(R.id.ciudad1);
		ciudad1.setText(Utilitats.usuarioConectado.getCiudad());
		puntos1 = (TextView)findViewById(R.id.puntos1);
		puntos1.setText(String.valueOf(Utilitats.usuarioConectado.getPuntos()));
		rank1 = (TextView)findViewById(R.id.rank1);
		rank1.setText(Utilitats.usuarioConectado.getRank());
		descuento1 = (TextView)findViewById(R.id.descuento1);
		descuento1.setText(String.valueOf(Utilitats.usuarioConectado.getDescuento()));
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
				Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
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
				intent = new Intent(PerfilActivity.this, LibreriaActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Ranking:
			case R.id.RankingIcon:
				intent = new Intent(PerfilActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
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
										Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(PerfilActivity.this, ActividadesGActivity.class);
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
