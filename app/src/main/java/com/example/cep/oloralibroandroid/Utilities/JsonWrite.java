package com.example.cep.oloralibroandroid.Utilities;

import com.example.cep.oloralibroandroid.Clases.Usuario;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;

import static com.example.cep.oloralibroandroid.Utilities.Utilitats.DIRECTORY;

public class JsonWrite
{
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


}

