package com.example.cep.oloralibroandroid.Utilities;

import android.graphics.Path;

import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Opinion;
import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.Clases.Visita;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIRECTORY;

/**
 * CLASE JSONWRITE (SIRVE PARA ESCRIBIR LOS DATOS DE LA APLICACIÓN EN ARCHIVO JSON
 */
public class JsonWrite
{
	/**
	 * NOS SOBRESCRIBE EL FICHERO JSON DE USUARIOS POR EL ACTUALIZADO
	 */
	public static void crearJsonUsuarios(){


		try{
			if(Utilitats.usuarioConectado.getUsername()!=null)
			{
				Utilitats.anyadirUsuarioConectadoLista();
			}
			JSONArray jsonArray = new JSONArray();
			ArrayList<Usuario> users = Utilitats.getUsuarios();
			for (int i = 0; i < users.size(); i++) {
				JSONObject object = new JSONObject();
				Usuario user = users.get(i);
				object.put("username", user.getUsername() );
				object.put("nombre", user.getNombre() );
				object.put("apellidos", user.getApellidos() );
				object.put("ciudad", user.getCiudad() );
				object.put("password", user.getPassword() );
				object.put("puntos", user.getPuntos() );
				object.put("rank", user.getRank() );
				object.put("descuento", user.getDescuento() );

				jsonArray.add(object);
			}

			FileWriter fileWriter = new FileWriter(DIRECTORY + "usuarios.json");

			fileWriter.write(jsonArray.toJSONString());
			fileWriter.close();
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}


	}

	/**
	 * NOS SOBRECRIBE EL FICHERO JSON DE ACTIVIDADES Y LO ACTUALIZA AL MOMENTO
	 * @param posicion
	 */
	public static void crearJsonActividades(int posicion) 
	{

	try{


		JSONArray jsonArrayActs = new JSONArray();
		ArrayList<Actividad> acts = Utilitats.getActividades();
		JSONObject object;
		for (int i = 0; i < acts.size(); i++)
		{
			object = new JSONObject();
			Actividad act = acts.get(i);

			object.put("id", act.getId());
			object.put("nombre", act.getNombre());
			object.put("descripcion", act.getDescripcion());
			object.put("lugar", act.getLugar());
			object.put("tipo", act.getTipo());
			object.put("fecha", act.getFecha());
			object.put("hora", act.getHora());
			object.put("librerias", act.getLibrerias());
			for(int k = 0; k < act.getOpiniones().size(); k++)
			{
				JSONArray jsonArray = new JSONArray();
				ArrayList<Opinion> opiniones = Utilitats.actividades.get(posicion).getOpiniones();
				JSONObject object1 = new JSONObject();

				for (int j = 0; j < opiniones.size(); j++) 
				{
					JSONObject objectop = new JSONObject();
					Opinion op = opiniones.get(j);
					Usuario u = op.getUser();
					object1.put("username",u.getUsername() );
					object1.put("nombre", u.getNombre());
					object1.put("apellidos", u.getApellidos());
					object1.put("ciudad", u.getCiudad());
					object1.put("password", u.getPassword());
					object1.put("puntos", u.getPuntos());
					object1.put("rank", u.getRank());
					object1.put("descuento", u.getDescuento());

					//-------------------
					objectop.put("user", object1 );
					objectop.put("fecha", op.getFecha() );
					objectop.put("comentario", op.getComentario() );

					jsonArray.add(objectop);
				}
				object.put("opiniones", jsonArray);
			}
			object.put("visitas", act.getVisitas());


			jsonArrayActs.add(object);
		}

		FileWriter fileWriter = new FileWriter(DIRECTORY + "actividades.json");

		fileWriter.write(jsonArrayActs.toJSONString());
		fileWriter.close();

	}
	catch(Exception ex){
		System.out.println(ex.toString());
	}
	}
}

