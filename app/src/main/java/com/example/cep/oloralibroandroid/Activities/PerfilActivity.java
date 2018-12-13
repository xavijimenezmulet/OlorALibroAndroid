package com.example.cep.oloralibroandroid.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.JsonWrite;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

public class PerfilActivity extends AppCompatActivity
{
	private AutoCompleteTextView email_signup1;
	private EditText password1;
	private EditText repite_password3;
	private EditText repite_password4;
	private AutoCompleteTextView nombre1;
	private AutoCompleteTextView apellidos1;
	private AutoCompleteTextView ciudad1;
	private TextView puntos1;
	private TextView rank1;
	private TextView descuento1;
	private Button perf_editar_button;
	private Button perf_aceptar_button;
	private TextInputLayout inputPassword2;
	private TextInputLayout inputPassword3;
	private TextInputLayout inputPassword4;
	private Button cambiar_contrasenya;

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
		email_signup1.setEnabled(false);
		password1 = (EditText)findViewById(R.id.password1);
		password1.setEnabled(false);
		//password1.setText(Utilitats.usuarioConectado.getPassword());
		repite_password3 = (EditText)findViewById(R.id.repite_password3);
		repite_password3.setEnabled(false);
		repite_password4 = (EditText)findViewById(R.id.repite_password4);
		repite_password4.setEnabled(false);
		nombre1 = (AutoCompleteTextView)findViewById(R.id.nombre1);
		nombre1.setText(Utilitats.usuarioConectado.getNombre());
		nombre1.setEnabled(false);
		apellidos1 = (AutoCompleteTextView)findViewById(R.id.apellidos1);
		apellidos1.setText(Utilitats.usuarioConectado.getApellidos());
		apellidos1.setEnabled(false);
		ciudad1 = (AutoCompleteTextView)findViewById(R.id.ciudad1);
		ciudad1.setText(Utilitats.usuarioConectado.getCiudad());
		ciudad1.setEnabled(false);
		puntos1 = (TextView)findViewById(R.id.puntos1);
		puntos1.setText(String.valueOf(Utilitats.usuarioConectado.getPuntos() + " puntos"));
		rank1 = (TextView)findViewById(R.id.rank1);
		rank1.setText(Utilitats.usuarioConectado.getRank() + " (Rango)");
		descuento1 = (TextView)findViewById(R.id.descuento1);
		descuento1.setText(String.valueOf(Utilitats.usuarioConectado.getDescuento() + "% de descuento"));
		perf_editar_button = (Button) findViewById(R.id.perf_editar_button);
		perf_aceptar_button = (Button) findViewById(R.id.perf_aceptar_button);
		inputPassword2 = (TextInputLayout)findViewById(R.id.inputPassword2);
		inputPassword3 = (TextInputLayout)findViewById(R.id.inputPassword3);
		inputPassword4 = (TextInputLayout)findViewById(R.id.inputPassword4);
		cambiar_contrasenya = (Button)findViewById(R.id.cambiar_constrasenya);
		inputPassword2.setHint(null);
		perf_editar_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				perf_editar_button.setEnabled(false);
				perf_aceptar_button.setEnabled(true);
				perf_editar_button.setBackgroundColor(getResources().getColor(R.color.gris));
				perf_aceptar_button.setBackgroundColor(getResources().getColor(R.color.negro));
				email_signup1.setEnabled(true);
				password1.setHint("Click a Editar Contraseña para cambiarla");
				nombre1.setEnabled(true);
				apellidos1.setEnabled(true);
				ciudad1.setEnabled(true);
				cambiar_contrasenya.setEnabled(true);
				cambiar_contrasenya.setBackgroundColor(getResources().getColor(R.color.negro));
				cambiar_contrasenya.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View view)
					{
						AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilActivity.this);
						dlgAlert.setMessage("Estás apunto de modificar la contraseña ¿Estás seguro?");
						dlgAlert.setTitle("Advertencia");
						dlgAlert.setCancelable(false);
						dlgAlert.setPositiveButton("Sí", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialogInterface, int i)
							{
								cambiar_contrasenya.setEnabled(false);
								cambiar_contrasenya.setBackgroundColor(getResources().getColor(R.color.gris));
								password1.setHint(null);
								inputPassword2.setEnabled(true);
								inputPassword2.setVisibility(TextInputLayout.VISIBLE);
								inputPassword2.setPasswordVisibilityToggleEnabled(true);
								inputPassword2.setHint("Contraseña actual");
								inputPassword3.setEnabled(true);
								inputPassword3.setVisibility(TextInputLayout.VISIBLE);
								inputPassword3.setHint("Nueva Contraseña");
								inputPassword4.setEnabled(true);
								inputPassword4.setVisibility(TextInputLayout.VISIBLE);
								inputPassword4.setHint("Repite Nueva Contraseña");
								password1.setEnabled(true);
								repite_password3.setEnabled(true);
								repite_password3.setVisibility(EditText.VISIBLE);
								repite_password4.setEnabled(true);
								repite_password4.setVisibility(EditText.VISIBLE);

							}
						});
						dlgAlert.setNegativeButton("No", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialogInterface, int i)
							{
								dialogInterface.cancel();
							}
						});
						dlgAlert.setIcon(R.drawable.caution);
						AlertDialog alert = dlgAlert.create();
						alert.show();

					}
				});
				perf_aceptar_button.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View view)
					{
						AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(PerfilActivity.this);
						dlgAlert.setMessage("¿Deseas guardar o descartar los datos?");
						dlgAlert.setTitle("Advertencia");
						dlgAlert.setCancelable(false);
						dlgAlert.setPositiveButton("Guardar", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialogInterface, int i)
							{
								email_signup1.setError(null);
								String email = email_signup1.getText().toString();
								String password_vieja = password1.getText().toString();
								String password_nueva = repite_password3.getText().toString();
								String nueva_repetida = repite_password4.getText().toString();
								boolean cancel = false;
								View focusView = null;
								if(Utilitats.userExists(email) && !email.equals(Utilitats.usuarioConectado.getUsername())){
									email_signup1.setError("Este Usuario ya existe");
									focusView=email_signup1;
									cancel = true;
								}
								if(repite_password3.isEnabled())
								{
									if (!password_vieja.equals(Utilitats.usuarioConectado.getPassword()))
									{
										password1.setError("Esta contraseña no coincide con la actual");
										focusView=password1;
										cancel=true;
									} else if (password_vieja.length() < 4)
									{
										password1.setError("Esta contraseña es demasiado corta");
										focusView = password1;
										cancel = true;
									}
									if(password_nueva.length()<4){
										repite_password3.setError("Esta contraseña es demasiado corta");
									}
									if(nueva_repetida.length()<4){
										repite_password4.setError("Esta contraseña es demasiado corta");
									}
									if(!password_nueva.equals(nueva_repetida)){
										repite_password3.setError("La contrasña no coincide con la repetida");
									}

								}
								if(cancel){
									focusView.requestFocus();
								}
								else
								{
									Usuario u = new Usuario();
									u.setUsername(email_signup1.getText().toString());
									if (!repite_password4.isEnabled())
									{
										u.setPassword(Utilitats.usuarioConectado.getPassword());
									} else
									{
										u.setPassword(repite_password4.getText().toString());
									}
									u.setNombre(nombre1.getText().toString());
									u.setApellidos(apellidos1.getText().toString());
									u.setCiudad(ciudad1.getText().toString());
									u.setPuntos(Utilitats.usuarioConectado.getPuntos());
									u.setRank(Utilitats.usuarioConectado.getRank());
									u.setDescuento(Utilitats.usuarioConectado.getDescuento());
									if (!u.equals(Utilitats.usuarioConectado))
									{
										Utilitats.usuarioConectado.convertirUsuario(u);
										JsonWrite.crearJsonUsuarios();
									}
									email_signup1.setEnabled(false);
									password1.setEnabled(false);
									password1.setText(null);
									inputPassword2.setPasswordVisibilityToggleEnabled(false);
									inputPassword3.setPasswordVisibilityToggleEnabled(false);
									inputPassword4.setPasswordVisibilityToggleEnabled(false);
									inputPassword2.setHint(null);
									repite_password3.setVisibility(EditText.INVISIBLE);
									repite_password3.setText(null);
									repite_password4.setText(null);
									repite_password4.setVisibility(EditText.INVISIBLE);
									inputPassword3.setHint(null);
									inputPassword4.setHint(null);
									nombre1.setEnabled(false);
									apellidos1.setEnabled(false);
									ciudad1.setEnabled(false);
									perf_aceptar_button.setEnabled(false);
									perf_aceptar_button.setBackgroundColor(getResources().getColor(R.color.negro));
									perf_editar_button.setEnabled(true);
									perf_aceptar_button.setBackgroundColor(getResources().getColor(R.color.gris));

								}

							}
						});
						dlgAlert.setNegativeButton("Descartar Cambios", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialogInterface, int i)
							{
								email_signup1.setEnabled(false);
								password1.setEnabled(false);
								password1.setHint(null);
								inputPassword2.setPasswordVisibilityToggleEnabled(false);
								inputPassword3.setPasswordVisibilityToggleEnabled(false);
								inputPassword4.setPasswordVisibilityToggleEnabled(false);
								repite_password3.setVisibility(EditText.INVISIBLE);
								repite_password4.setVisibility(EditText.INVISIBLE);
								nombre1.setEnabled(false);
								apellidos1.setEnabled(false);
								ciudad1.setEnabled(false);
								perf_aceptar_button.setEnabled(false);
								perf_aceptar_button.setBackgroundColor(getResources().getColor(R.color.gris));
								perf_editar_button.setEnabled(true);
								perf_aceptar_button.setBackgroundColor(getResources().getColor(R.color.negro));
							}
						});
						dlgAlert.setIcon(R.drawable.caution);
						AlertDialog alert = dlgAlert.create();
						alert.show();

					}
				});
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
