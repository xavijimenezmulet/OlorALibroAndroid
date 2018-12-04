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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		eng = true;
		esp = false;
		cat = false;

		if(Utilitats.usuarios.size()==0)
		{
			try
			{
				Utilitats.cargarTodo();
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		}

		final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		final Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
		ImgLogin = (ImageView)findViewById(R.id.ImgLogin);
		ImgLogin.setImageResource(R.drawable.loginlogofinal);
		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView.setHint(R.string.prompt_password);

		spanish_button = (ImageButton)findViewById(R.id.spanish_button);
		spanish_button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mEmailView.setHint("Email (Usuario)");
				mPasswordView.setHint("Contraseña");
				mEmailSignInButton.setText("Entrar");
				mEmailSignUpButton.setText("Registrarse");
				textViewSubrayado.setText(R.string.esp_olvidado);
				eng = false;
				cat = false;
				esp = true;

			}
		});

		catalan_button = (ImageButton)findViewById(R.id.catalan_button);
		catalan_button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				mEmailView.setHint("Email (Usuari)");
				mPasswordView.setHint("Contrassenya");
				mEmailSignInButton.setText("Entrar");
				mEmailSignUpButton.setText("Registrar-se");
				textViewSubrayado.setText(R.string.cat_olvidat);
				eng = false;
				cat = true;
				esp = false;
			}
		});

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

		textViewSubrayado = (TextView)findViewById(R.id.textViewSubrayado);
		textViewSubrayado.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

			}
		});



		mEmailSignInButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				attemptLogin(eng, esp,cat);
			}
		});

		mEmailSignUpButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
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

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String email = mEmailView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !Utilitats.isPasswordValid(password))
		{
			if(eng){
				mPasswordView.setError(getString(R.string.error_invalid_password));
			}
			else if(esp){
				mPasswordView.setError("Esta contraseña es invalida");
			}
			else if (cat){
				mPasswordView.setError("Aquesta contrassenya és invalida");
			}
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(email))
		{
			if(eng){
				mEmailView.setError(getString(R.string.error_field_required));
			}
			else if(esp){
				mEmailView.setError("Esta celda es requerida");
			}
			else if(cat){
				mEmailView.setError("Aquesta casella és requerida");
			}
			focusView = mEmailView;
			cancel = true;
		}
		else if (TextUtils.isEmpty(password))
		{
			if(eng){
				mPasswordView.setError(getString(R.string.error_field_required));
			}
			else if(esp){
				mPasswordView.setError("Esta celda es requerida");
			}
			else if(cat){
				mPasswordView.setError("Aquesta casella és requerida");
			}
			focusView = mPasswordView;
			cancel = true;
		}
		else if (!Utilitats.isEmailValid(email))
		{
			if(eng)
			{
				mEmailView.setError(getString(R.string.error_invalid_email));
			}
			else if(esp){
				mEmailView.setError("Este Email no es valido");
			}
			else if (cat){
				mEmailView.setError("Aquest Email no és incorrecte");
			}
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel)
		{
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else
		{
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.

			if(Utilitats.isUserValid(email, password)){
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
			else{
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
				if(eng)
				{
					dlgAlert.setMessage("Invalid username or password!");
					dlgAlert.setTitle("Warning");
				}
				else if(esp){
					dlgAlert.setMessage("Usuario o contraseña invalidos!");
					dlgAlert.setTitle("Advertencia");
				}
				else if (cat){
					dlgAlert.setMessage("Usuari o contrassenya incorrectes");
					dlgAlert.setTitle("Advertència");
				}
				dlgAlert.setIcon(R.drawable.caution);
				dlgAlert.setPositiveButton("OK", null);
				dlgAlert.setCancelable(true);
				dlgAlert.create().show();

			}

		}

	}



}

