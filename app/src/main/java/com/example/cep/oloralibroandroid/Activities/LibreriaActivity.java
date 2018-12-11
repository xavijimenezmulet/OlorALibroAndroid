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

import com.example.cep.oloralibroandroid.Adapters.GridMainAdapter;
import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.R;
import com.example.cep.oloralibroandroid.Utilities.Utilitats;

import static java.lang.String.valueOf;

public class LibreriaActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_librerias);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setLogo(R.drawable.enano);
		actionBar.setSubtitle(getString(R.string.librerias));


		final GridView GrdMain = (GridView)findViewById(R.id.GrdTotesLlib);


		final GridMainAdapter gridMainAdapter = new GridMainAdapter(this, Utilitats.librerias);
		GrdMain.setAdapter(gridMainAdapter);

		/* Pasar a otra view
		* intent = new Intent(LibreriaActivity.this, activity_una_libreria.class);
			startActivity(intent);
			onPause();
			retorno =true;
			break;
		* */
		GrdMain.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
			{

				//TextView nombreCoche = (TextView) view.findViewById(R.id.TxtGrdMain1);

				Libreria li = (Libreria) adapterView.getItemAtPosition(position);

				Intent i = new Intent(LibreriaActivity.this, activity_una_libreria.class);
				//Bundle n =  i.getExtras();
				//nombre.toString()

				i.putExtra("nombreLib", li.getNombre());
				startActivity(i);

				/**
				 * final Coche item = getItem(position);
				 Glide.with(imagenCoche.getContext())
				 .load(item.getIdDrawable())
				 .into(imagenCoche);

				 nombreCoche.setText(item.getNombre());
				 * @Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Coche item = (Coche) parent.getItemAtPosition(position);

				Intent intent = new Intent(this, ActividadDetalle.class);
				intent.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

				ActivityOptionsCompat activityOptions =
				ActivityOptionsCompat.makeSceneTransitionAnimation(
				this,
				new Pair<View, String>(view.findViewById(R.id.imagen_coche),
				ActividadDetalle.VIEW_NAME_HEADER_IMAGE)
				);

				ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
				} else
				startActivity(intent);
				}
				 */

				/*public View getView(int position, View view, ViewGroup viewGroup) {

				if (view == null) {
					LayoutInflater inflater = (LayoutInflater) context
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.grid_item, viewGroup, false);
				}

				ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen_coche);
				TextView nombreCoche = (TextView) view.findViewById(R.id.nombre_coche);

				final Coche item = getItem(position);
				Glide.with(imagenCoche.getContext())
						.load(item.getIdDrawable())
						.into(imagenCoche);

				nombreCoche.setText(item.getNombre());

				return view*/

				//Object nombre = adapterView.setTag(valueOf(position));
				//Object nombre = adapterView.getItemAtPosition(position);
				//String nombre = GrdMain.getItemAtPosition(position).toString();

				//.getIntent().getExtras();


			}
		});
	}




	/*public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*tv2=(TextView)findViewById(R.id.tv2);
		b2=(Button)findViewById(R.id.b2);

		Bundle parametros = this.getIntent().getExtras();
		String datos = parametros.getString("datos");
		tv2.setText(datos);*/
	/*}*/

	/*public void segunda_pantalla(View view){
		Intent i=new Intent(this, segunda_pantalla.class);
		startActivity(i);
	}*/

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
				Intent intent = new Intent(LibreriaActivity.this, MainActivity.class);
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
				retorno =true;
				break;
			case R.id.Ranking:
			case R.id.RankingIcon:
				intent = new Intent(LibreriaActivity.this, RankingActivity.class);
				startActivity(intent);
				onPause();
				retorno =true;
				break;
			case R.id.Perfil:
				intent = new Intent(LibreriaActivity.this, PerfilActivity.class);
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
										Intent intent = new Intent(LibreriaActivity.this, LoginActivity.class);
										startActivity(intent);
										finish();// metodo que se debe implementar
									}
								});
				alert = builder.create();
				alert.show();
				retorno =true;
				break;
			case R.id.Actividadesg:
				intent = new Intent(LibreriaActivity.this, ActividadesGActivity.class);
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
