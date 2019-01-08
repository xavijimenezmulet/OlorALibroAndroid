package com.example.cep.oloralibroandroid.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.JsonWrite;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import org.json.simple.parser.ParseException;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.*;

/**
 * PANTALLA DE LOGIN QUE NOS REDIRIGIRÁ A MAIN UNA VEZ COMPLETADO EL LOGIN O A REGISTRARSE
 */
public class LoginActivity extends Activity
{


	// REFERENCIAS
	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;
	private ImageView ImgLogin;
	private TextView textViewSubrayado;
	private ImageButton spanish_button;
	private ImageButton catalan_button;
	private ImageButton english_button;
	private Boolean eng;
	private Boolean esp;
	private Boolean cat;

	/**
	 * ON CREATE DEL LOGIN ACTIVIITY SIN ACTION BAR
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//como está em inglés le damos a eng true
		eng = true;
		esp = false;
		cat = false;

		//miramos que usuarios este vacía
		if(Utilitats.usuarios.size()==0)
		{
			try
			{
				//cargamos todos los JSON
				Utilitats.cargarTodo();
			} catch (IOException e)
			{
				//toast que nos muestra la excepción
				Toast.makeText(this, "Problema al cargar los JSON", Toast.LENGTH_LONG).show();
			} catch (ParseException e)
			{
				Toast.makeText(this, "Problema al parsear los JSON", Toast.LENGTH_LONG).show();
			}
		}

		//enlaces con los elementos del xml
		final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		final Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
		ImgLogin = (ImageView)findViewById(R.id.ImgLogin);
		ImgLogin.setImageResource(R.drawable.loginlogofinal);
		// Set up del login form
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView.setHint(R.string.prompt_password);

		//creamos el evento de traducción en español
		spanish_button = (ImageButton)findViewById(R.id.spanish_button);
		spanish_button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				//traducción del idioma a espalik y booleano esp a true
				mEmailView.setHint(R.string.emailSpanish);
				mPasswordView.setHint(R.string.constrasenyaSpanish);
				mEmailSignInButton.setText(R.string.entrarSpanish);
				mEmailSignUpButton.setText(R.string.registrarseSpanish);
				textViewSubrayado.setText(R.string.esp_olvidado);
				eng = false;
				cat = false;
				esp = true;

			}
		});

		//lo mismo para el catalán
		catalan_button = (ImageButton)findViewById(R.id.catalan_button);
		catalan_button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mEmailView.setHint(R.string.mailCatala);
				mPasswordView.setHint(R.string.contrasenyaCatala);
				mEmailSignInButton.setText(R.string.entrarCatala);
				mEmailSignUpButton.setText(R.string.registrarseCatala);
				textViewSubrayado.setText(R.string.cat_olvidat);
				eng = false;
				cat = true;
				esp = false;
			}
		});

		//lo mismo para el inglés
		english_button = (ImageButton)findViewById(R.id.english_button);
		english_button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mEmailView.setHint(getString(R.string.prompt_email));
				mPasswordView.setHint(getString(R.string.prompt_password));
				mEmailSignInButton.setText(R.string.action_sign_in);
				mEmailSignUpButton.setText(R.string.action_sign_up);
				textViewSubrayado.setText(R.string.text_contrasenya_olvidada);
				eng = true;
				cat = false;
				esp = false;
			}
		});

		/**
		 * INTENTAMOS PROGRAMAR LO DE OLVIDAR LA CONTRASEÑA PERO DE MOMENTO SE HA QUEDADO EN ESPERA
		 */
		textViewSubrayado = (TextView)findViewById(R.id.textViewSubrayado);
		textViewSubrayado.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

			}
		});


		/**
		 * AL DARLE A SIGNIN SI ESTÁ CORRECTO PROCEDERÁ AL REGISTRO, DE LO CONTRARIO NOS MOSTRARÁ
		 * LOS RESPECTIVOS ERRORES
		 */
		mEmailSignInButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				attemptLogin(eng, esp,cat);
			}
		});

		/**
		 * AL DARLE A SIGNUP NOS ABRIRÁ LA ACTIVITY DE REGISTRO Y ESTÁ QUEDARÁ PAUSADA PARA PODER
		 * TIRAR PARA ATRÁS
		 */
		mEmailSignUpButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				//intent que nos lleva de la login a la signup (activities)
				Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(intent);
				onPause();
			}
		});


	}

	/**
	 * METODO INTENTO DE REGISTRO, NOS COMPRUEBA QUE TODOS LOS DATOS SEAN CORRECTOS SI NO LO SON NOS
	 * MOSTRARÁ LOS RESPECTIVOS ERRORES SI ESTÁ OK PROCEDERÁ AL REGISTRO
	 */
	private void attemptLogin(Boolean eng, Boolean esp, Boolean cat)
	{

		// Resetea errores.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// guarda en las variables el contenido de email y password
		String email = mEmailView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// mira que el password sea correcto, si el usuario ha introducido uno
		if (!TextUtils.isEmpty(password) && !Utilitats.isPasswordValid(password))
		{
			if(eng){
				mPasswordView.setError(getString(R.string.error_invalid_password));
			}
			else if(esp){
				mPasswordView.setError(getString(R.string.contrasenyaInvSpa));
			}
			else if (cat){
				mPasswordView.setError(getString(R.string.contrasenyaInvCat));
			}
			focusView = mPasswordView;
			cancel = true;
		}

		// Valida que el usuario haya introducido un email
		if (TextUtils.isEmpty(email))
		{
			if(eng){
				mEmailView.setError(getString(R.string.error_field_required));
			}
			else if(esp){
				mEmailView.setError(getString(R.string.celdaSpanish));
			}
			else if(cat){
				mEmailView.setError(getString(R.string.casellaCatala));
			}
			focusView = mEmailView;
			cancel = true;
		}
		// Si no comprobamos lo mismo en la contraseña
		else if (TextUtils.isEmpty(password))
		{
			if(eng){
				mPasswordView.setError(getString(R.string.error_field_required));
			}
			else if(esp){
				mPasswordView.setError(getString(R.string.celdaSpanish));
			}
			else if(cat){
				mPasswordView.setError(getString(R.string.casellaCatala));
			}
			focusView = mPasswordView;
			cancel = true;
		}
		// Si no mira que no sea valido y muestra su error
		else if (!Utilitats.isEmailValid(email))
		{
			if(eng)
			{
				mEmailView.setError(getString(R.string.error_invalid_email));
			}
			else if(esp){
				mEmailView.setError(getString(R.string.emailNoSpanish));
			}
			else if (cat){
				mEmailView.setError(getString(R.string.emailNoCatala));
			}
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel)
		{
			// Nos hace focus al error si el cancel es true
			focusView.requestFocus();
		} else
		{
			// Si se cumple lo anterior se procede al intento de Login
			// Si él usuario es correcto lo conectamos y pasamos a la activity main
			if(Utilitats.isUserValid(email, password)){
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				intent.putExtra("conectado", 1);
				startActivity(intent);
				//finish();
			}
			// Si no mandamos un mensaje de alerta con su respectivo idioma
			else{
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
				if(eng)
				{
					dlgAlert.setMessage(R.string.invalidEng);
					dlgAlert.setTitle(R.string.warning);
				}
				else if(esp){
					dlgAlert.setMessage(R.string.invalidSpa);
					dlgAlert.setTitle(R.string.advertenciaSpa);
				}
				else if (cat){
					dlgAlert.setMessage(R.string.invalidCat);
					dlgAlert.setTitle(R.string.advertenciaCat);
				}
				dlgAlert.setIcon(R.drawable.caution);
				dlgAlert.setPositiveButton(R.string.ok, null);
				dlgAlert.setCancelable(true);
				dlgAlert.create().show();

			}

		}

	}

	/**
	 * AL CLICKAR ATRÁS DESPUÉS DE UN LOGOUT NOS INTERESA QUE NOS TIRE PARA ATRÁS Y SALGA DE TODAS
	 * LAS ACTIVIDADES
	 */
	@Override
	public void onBackPressed()
	{
		//cierra todas las actividades (FUNCIONA DESDE 4.1)
		finishAffinity();
	}



}

