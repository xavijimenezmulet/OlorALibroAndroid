package com.example.cep.oloralibroandroid.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.R;

public class SignUpActivity extends AppCompatActivity
{
	private AutoCompleteTextView email_signup2;
	private EditText password2;
	private EditText repite_prassword2;
	private AutoCompleteTextView nombre2;
	private AutoCompleteTextView apellidos2;
	private AutoCompleteTextView ciudad2;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		Usuario usuario = new Usuario();

		email_signup2 = (AutoCompleteTextView)findViewById(R.id.email_signup);
		password2 = (EditText)findViewById(R.id.password2);
		repite_prassword2 = (EditText)findViewById(R.id.repite_password2);
		nombre2 = (AutoCompleteTextView)findViewById(R.id.nombre2);
		apellidos2 = (AutoCompleteTextView)findViewById(R.id.apellidos2);
		ciudad2 = (AutoCompleteTextView)findViewById(R.id.ciudad2);

		final Button reg_sign_up_button = (Button)findViewById(R.id.reg_sign_up_button);

		reg_sign_up_button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				attemptSignUp();
			}
		});




	}

	private void attemptSignUp()
	{
		email_signup2.setError(null);
		password2.setError(null);
		repite_prassword2.setError(null);
		nombre2.setError(null);
		apellidos2.setError(null);
		ciudad2.setError(null);

		String email = email_signup2.getText().toString();
		String password = password2.getText().toString();
		String nombre = nombre2.getText().toString();
		String apellidos = apellidos2.getText().toString();
		String ciudad = ciudad2.getText().toString();

		boolean cancel = false;
		View focusView = null;


	}
}
