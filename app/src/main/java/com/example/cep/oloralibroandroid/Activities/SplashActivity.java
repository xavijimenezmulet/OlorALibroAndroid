package com.example.cep.oloralibroandroid.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


import com.example.cep.oloralibroandroid.R;

import java.io.File;
import java.io.FileWriter;

public class SplashActivity extends AppCompatActivity
{
	private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
	private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

	// Duración en milisegundos que se mostrará el splash
	private final int DURACION_SPLASH = 5000; // 5 segundos
	private ImageView ImgSplash;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);



		//	COM DEMANAR PERMISOS EN DISPOSITIUS AMB UNA VERSIÓ D'ANDROID 6.O O SUPERIOR

		// Here, thisActivity is the current activity

		// Controlem la versió d'Android que estem executant.
		if (android.os.Build.VERSION.SDK_INT >= 23)
		{
			// Si executem la versió Marshmallow (6.0) o posterior, haurem de demanar
			// permisos en temps d'execució

			// Comprovem si l'usuari ja ens ha donat permisos en una execió anterior
			if (ContextCompat.checkSelfPermission((Activity)this,
					Manifest.permission.READ_EXTERNAL_STORAGE)
					!= PackageManager.PERMISSION_GRANTED &&
					ContextCompat.checkSelfPermission((Activity)this,
							Manifest.permission.WRITE_EXTERNAL_STORAGE)
							!= PackageManager.PERMISSION_GRANTED)
			{

				// Si l'usuari no ens havia atorgat permisos, els hi demanem i
				// executem el nostre codi

				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
								Manifest.permission.WRITE_EXTERNAL_STORAGE},
						1);

				iniciar();
			}
			else
			{
				// Si l'usuari ja ens havia atorgat permisos en una execució anterior,
				// executem directament el nostre codi

				iniciar();
			}
		}
		else
		{

			// Si executem una versió anterior a la versió Marshmallow (6.0),
			// no cal demanar cap permís, i podem executar el nostre codi directament

			iniciar();
		}

	}

	protected void iniciar()
	{


		String directory = Environment.getExternalStorageDirectory() +
				File.separator + "JSON";
		String directory1 = Environment.getExternalStorageDirectory() +
				File.separator + "Imagenes";
		File f = new File(directory);
		File f2 = new File(directory1);

		if(!f.exists()){
			f.mkdirs();
		}

		if(!f2.exists()){
			f2.mkdirs();
		}



		ImgSplash = (ImageView)findViewById(R.id.ImgSplash);
		ImgSplash.setImageResource(R.drawable.splashandroidcopia);

		new Handler().postDelayed(new Runnable(){
			public void run(){
				// Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			};
		}, DURACION_SPLASH);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if(requestCode == 1){
			if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED &&
					grantResults[1]==PackageManager.PERMISSION_GRANTED){
				iniciar();
			}
		}
	}
}
