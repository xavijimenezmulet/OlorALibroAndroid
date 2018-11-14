package com.example.cep.oloralibroandroid.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.cep.oloralibroandroid.R;

public class SplashActivity extends AppCompatActivity
{
	// Duración en milisegundos que se mostrará el splash
	private final int DURACION_SPLASH = 5000; // 5 segundos
	private ImageView ImgSplash;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);

		ImgSplash = (ImageView)findViewById(R.id.ImgSplash);
		ImgSplash.setImageResource(R.drawable.splashandroidcopia);

		new Handler().postDelayed(new Runnable(){
			public void run(){
				// Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			};
		}, DURACION_SPLASH);
	}
}
