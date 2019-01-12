package com.example.cep.oloralibroandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.JsonWrite;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

/**
 * REGISTRO ACTIVITY
 */
public class SignUpActivity extends AppCompatActivity
{
	private AutoCompleteTextView email_signup2;
	private EditText password2;
	private EditText repite_prassword1;
	private AutoCompleteTextView nombre2;
	private AutoCompleteTextView apellidos2;
	private AutoCompleteTextView ciudad2;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		Usuario usuario = new Usuario();

		email_signup2 = (AutoCompleteTextView)findViewById(R.id.email_signup2);
		password2 = (EditText)findViewById(R.id.password2);
		repite_prassword1 = (EditText)findViewById(R.id.repite_password1);
		nombre2 = (AutoCompleteTextView)findViewById(R.id.nombre2);
		apellidos2 = (AutoCompleteTextView)findViewById(R.id.apellidos2);
		ciudad2 = (AutoCompleteTextView)findViewById(R.id.ciudad2);

		final Button reg_sign_up_button = (Button)findViewById(R.id.reg_sign_up_button);

		/**
		 * UNA VEZ RELLENADOS LOS CAMPOS EL USUARIO CLICKARA A SIGN UP Y SE PROCEDRÁ AL REGISTRO
		 */
		reg_sign_up_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				attemptSignUp();
			}
		});




	}

	/**
	 * PROCEDE AL REGISTRO
	 */
	private void attemptSignUp()
	{
		email_signup2.setError(null);
		password2.setError(null);
		repite_prassword1.setError(null);
		nombre2.setError(null);
		apellidos2.setError(null);
		ciudad2.setError(null);

		String email = email_signup2.getText().toString();
		String password = password2.getText().toString();
		String password_repite = repite_prassword1.getText().toString();
		String nombre = nombre2.getText().toString();
		String apellidos = apellidos2.getText().toString();
		String ciudad = ciudad2.getText().toString();

		boolean cancel = false;
		View focusView = null;

		/***
		 * LAS MISMAS VALIDACIONES QUE EN EL LOGIN
		 */
		if(TextUtils.isEmpty(email)){
			email_signup2.setError(getString(R.string.error_field_required));
			focusView = email_signup2;
			cancel = true;
		}
		else if(TextUtils.isEmpty(password)){
			password2.setError(getString(R.string.error_field_required));
			focusView = password2;
			cancel = true;
		}
		else if(TextUtils.isEmpty(password_repite)){
			repite_prassword1.setError(getString(R.string.error_field_required));
			focusView = repite_prassword1;
			cancel = true;
		}
		else if(TextUtils.isEmpty(nombre)){
			nombre2.setError(getString(R.string.error_field_required));
			focusView = nombre2;
			cancel = true;
		}
		else if(TextUtils.isEmpty(apellidos)){
			apellidos2.setError(getString(R.string.error_field_required));
			focusView = apellidos2;
			cancel = true;
		}
		else if(TextUtils.isEmpty((ciudad))){
			ciudad2.setError(getString(R.string.error_field_required));
			focusView = ciudad2;
			cancel = true;
		}
		else if(!Utilitats.isEmailValid(email)){
			email_signup2.setError(getString(R.string.error_invalid_email));
			focusView = email_signup2;
			cancel = true;
		}
		else if(!Utilitats.isPasswordTheSame(password, password_repite)){
			password2.setError(getString(R.string.error_passwords_not));
			repite_prassword1.setError(getString(R.string.error_passwords_not));
			focusView = password2;
			cancel = true;
		}


		if(cancel){
			focusView.requestFocus();
		}
		else{
			if(!Utilitats.userExists(email)){
				Usuario u = new Usuario();
				u.setUsername(email);
				u.setPassword(password);
				u.setNombre(nombre);
				u.setApellidos(apellidos);
				u.setCiudad(ciudad);
				Utilitats.usuarios.add(u);
				JsonWrite.crearJsonUsuarios();
				Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
			else{
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
				dlgAlert.setMessage("This user alredy exists!");
				dlgAlert.setTitle("Warning");
				dlgAlert.setIcon(R.drawable.caution);
				dlgAlert.setPositiveButton("OK", null);
				dlgAlert.setCancelable(true);
				dlgAlert.create().show();
			}
		}

	}
}
