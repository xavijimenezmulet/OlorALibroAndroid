package com.example.cep.oloralibroandroid.Utilities;

import android.os.Environment;


import com.example.cep.oloralibroandroid.Clases.Libreria;
import com.example.cep.oloralibroandroid.Clases.Libro;
import com.example.cep.oloralibroandroid.Clases.Opinion;
import com.example.cep.oloralibroandroid.Clases.Actividad;
import com.example.cep.oloralibroandroid.Clases.Puntuacion;
import com.example.cep.oloralibroandroid.Clases.Rango;
import com.example.cep.oloralibroandroid.Clases.Usuario;
import com.example.cep.oloralibroandroid.Clases.Visita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * CLASE UTILITDADES, NOS VA A SERVIR PARA TRAER LOS DATOS DE LOS JSON EN LISTAS Y ASÍ PODER TRABAJAR CON ELLAS
 * AL IGUAL DE OTROS METODOS IMPLANTADOS PARA NO SOBRESCRIBIR CODIGO
 */
public class Utilitats
{
	//ATRIBUTOS (JSON ---> LISTAS PARA TRABAJAR
	public static final String DIR_SEPAR = File.separator;
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() +
			DIR_SEPAR + "JSON" + DIR_SEPAR;

	public static Puntuacion puntuacion = new Puntuacion();
	public static int posicionUsuario;
	public static Usuario usuarioConectado = new Usuario();
	public static Rango rango = new Rango();
	public static ArrayList<Libreria> librerias = new ArrayList<>();
	public static ArrayList<Libro> libros = new ArrayList<>();
	public static ArrayList<Opinion> opiniones = new ArrayList<>();
	public static ArrayList<Actividad> actividades = new ArrayList<>();
	public static ArrayList<Usuario> usuarios = new ArrayList<>();
	public static ArrayList<Visita> visitas = new ArrayList<>();


	//GETTERS& SETTERS
	public static int getPosicionUsuario()
	{
		return posicionUsuario;
	}

	public static void setPosicionUsuario(int posicionUsuario)
	{
		Utilitats.posicionUsuario = posicionUsuario;
	}

	public static void setUsuarioConectado(Usuario usuarioConectado)
	{
		Utilitats.usuarioConectado = usuarioConectado;
	}

	public static Usuario getUsuarioConectado()
	{
		return usuarioConectado;
	}

	public static ArrayList<Libreria> getLibrerias()
	{
		return librerias;
	}

	public static ArrayList<Libro> getLibros()
	{
		return libros;
	}

	public static ArrayList<Opinion> getOpiniones()
	{
		return opiniones;
	}

	public static ArrayList<Actividad> getActividades()
	{
		return actividades;
	}

	public static ArrayList<Usuario> getUsuarios()
	{
		return usuarios;
	}

	public static ArrayList<Visita> getVisitas()
	{
		return visitas;
	}

	/**
	 * NOS DEVUELVE A PARTIR DE UN NOMBRE QUE LE PASAMOS POR PARAMETRO EL JSON EN STRING COMPLETA
	 * @param nombreJSON
	 * @return
	 */
	public static String devolverStringJson(String nombreJSON)
	{

		String s = "";
		try
		{
			FileReader fr =
					new FileReader(DIRECTORY + nombreJSON);
			BufferedReader br = new BufferedReader(fr);


			String linia = br.readLine();
			while(linia != null)
			{
				s += linia;
				linia = br.readLine();
			}

			fr.close();

		}
		catch(Exception e)
		{
			e.toString();
		}
		return s;
	}

	/**
	 * LEE EL FICHERO JSON DE ACTIVIDADES
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerActividades() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("actividades.json");

		try{

			JSONArray jsonArray = new JSONArray(s);

			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject object = jsonArray.getJSONObject(i);
				Actividad actividad = new Actividad();

				actividad.setId((int)object.get("id"));
				actividad.setNombre((String)object.get("nombre"));
				actividad.setDescripcion((String)object.get("descripcion"));
				actividad.setLugar((String)object.get("lugar"));
				actividad.setTipo((String)object.get("tipo"));
				actividad.setFecha((String)object.get("fecha"));
				actividad.setHora((String)object.get("hora"));
				//ArrayList<String> auxiliar = new ArrayList<>();

				ArrayList<String> alibrerias = new ArrayList<>();
				if (!object.isNull("librerias"))
				{
					JSONArray jlibrerias =(JSONArray) object.get("librerias");

					for (int j = 0; j < jlibrerias.length(); j++)
					{
						String aux = jlibrerias.getString(j);
						//JSONObject object1 = jlibrerias.getJSONObject(j);
						alibrerias.add(aux);
					}
				}
				actividad.setLibrerias(alibrerias);
				ArrayList<Opinion> opiniones = new ArrayList<>();
				if (!object.isNull("opiniones"))
				{
					JSONArray jopiniones = (JSONArray) object.get("opiniones");

					for (int j = 0; j < jopiniones.length(); j++)
					{
						JSONObject object1 = jopiniones.getJSONObject(j);
						Opinion opinion = new Opinion();

						JSONObject object2 = (JSONObject) object1.get("user");
						Usuario user = new Usuario();
						user.setUsername((String) object2.get("username"));
						user.setNombre((String) object2.get("nombre"));
						user.setApellidos((String) object2.get("apellidos"));
						user.setCiudad((String) object2.get("ciudad"));
						user.setPassword((String) object2.get("password"));
						user.setPuntos((int) object2.get("puntos"));
						user.setRank((String) object2.get("rank"));
						user.setDescuento((float) object2.getDouble("descuento"));

						opinion.setUser(user);

						opinion.setFecha((String) object1.get("fecha"));
						opinion.setComentario((String) object1.get("comentario"));

						opiniones.add(opinion);

					}


				}
				actividad.setOpiniones(opiniones);
				ArrayList<Visita> visitas = new ArrayList<>();
				if (!object.isNull("visitas"))
				{
					JSONArray jvisitas = (JSONArray) object.get("visitas");


					for (int j = 0; j < jvisitas.length(); j++)
					{
						JSONObject object1 = jvisitas.getJSONObject(j);
						Visita visita = new Visita();

						visita.setUser((String) object1.get("user"));
						visita.setFecha((String) object1.get("fecha"));
						visitas.add(visita);
					}


				}
				actividad.setVisitas(visitas);
				actividades.add(actividad);

			}
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * LEE EL FICHERO JSON DE OPINIONES
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerOpiniones() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("opiniones.json");

		try{

			JSONArray jsonArray = new JSONArray(s);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Opinion opinion = new Opinion();

				JSONObject object1= (JSONObject) object.get("user");
				Usuario user = new Usuario();
				user.setUsername((String)object1.get("username"));
				user.setNombre((String)object1.get("nombre"));
				user.setApellidos((String)object1.get("apellidos"));
				user.setCiudad((String)object1.get("ciudad"));
				user.setPassword((String)object1.get("password"));
				user.setPuntos((int)object1.get("puntos"));
				user.setRank((String)object1.get("rank"));
				user.setDescuento((float)object1.getDouble("descuento"));

				opinion.setUser(user);
				opinion.setFecha((String)object.get("fecha"));
				opinion.setComentario((String)object.get("comentario"));

				opiniones.add(opinion);
			}

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * LEE EL FICHERO JSON DE VISITAS
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerVisitas() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("visitas.json");

		try{

			JSONArray jsonArray = new JSONArray(s);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Visita visita = new Visita();

				visita.setUser((String)object.get("user"));
				visita.setFecha((String)object.get("fecha"));

				visitas.add(visita);

			}

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * LEE EL FICHERO JSON DE LIBROS
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerLibros() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("libros.json");

		try{

			JSONArray jsonArray = new JSONArray(s);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Libro libro = new Libro();

				libro.setId((int)object.get("id"));
				libro.setTitulo((String)object.get("id"));
				libro.setAutor((String)object.get("id"));
				libro.setAnyo((int)object.get("anyo"));
				libro.setPrecio((int)object.get("precio"));
				libro.setPortada((String)object.get("portada"));
				JSONArray jgeneros =(JSONArray) object.get("genero");
				ArrayList<String> generos = new ArrayList<>();
				for(int j=0;j<jgeneros.length();j++){
					JSONObject object1 = jgeneros.getJSONObject(j);
					generos.add(object1.toString());
				}
				libro.setGenero(generos);
				libros.add(libro);

			}

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * LEE EL FICHERO JSON DE LIBRERIAS
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerLibreria() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("librerias.json");
		try{
			JSONArray jsonArray = new JSONArray(s);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Libreria libreria = new Libreria();

				libreria.setId((int)object.get("id"));
				libreria.setNombre((String)object.get("nombre"));
				libreria.setDireccion((String)object.get("direccion"));
				libreria.setCorreo((String)object.get("correo"));
				libreria.setTelefono((String)object.get("telefono"));
				libreria.setCorreo((String)object.get("correo"));
				libreria.setImagen((String)object.get("imagen"));

				JSONArray jlibros =(JSONArray) object.get("libros");
				ArrayList<Libro> libs = new ArrayList<>();
				for(int j=0; j< jlibros.length();j++){
					JSONObject object1 = jlibros.getJSONObject(j);
					Libro libro = new Libro();

					libro.setId((int)object1.get("id"));
					libro.setTitulo((String)object1.get("titulo"));
					libro.setAutor((String)object1.get("autor"));
					libro.setAnyo((int)object1.get("anyo"));
					libro.setPortada((String)object1.get("portada"));
					libro.setPrecio((int)object1.get("precio"));

					JSONArray jgenero =(JSONArray) object1.get("genero");
					ArrayList<String> generos = new ArrayList<>();
					for(int k =0;k<jgenero.length();k++){
						String object2 = jgenero.getString(k);
						generos.add(object2.toString());
					}
					libro.setGenero(generos);
					libs.add(libro);
				}
				libreria.setLibros(libs);
				ArrayList<String> lacts = new ArrayList<>();
				if (!object.isNull("actividades"))
				{
					JSONArray jactividades =(JSONArray) object.get("actividades");

					for (int j = 0; j < jactividades.length(); j++)
					{
						String aux = jactividades.getString(j);
						lacts.add(aux);
					}
				}
				libreria.setActividades(lacts);
				librerias.add(libreria);

			}


		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}


	/**
	 * LEE EL FICHERO JSON DE USUARIOS
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerUsuarios() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("usuarios.json");

		try{

			JSONArray jsonArray = new JSONArray(s);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Usuario user = new Usuario();
				user.setUsername((String)object.get("username"));
				user.setNombre((String)object.get("nombre"));
				user.setApellidos((String)object.get("apellidos"));
				user.setCiudad((String)object.get("ciudad"));
				user.setPassword((String)object.get("password"));
				user.setPuntos((int)object.get("puntos"));
				user.setRank((String)object.get("rank"));
				user.setDescuento((float)object.getDouble("descuento"));

				usuarios.add(user);

			}

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * LEE EL FICHERO JSON DE PUNTUACION
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerPuntuacion() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("puntuacion.json");
		s = "[" + s + "]";

		try{

			JSONArray jsonArray = new JSONArray(s);
			JSONObject json = jsonArray.getJSONObject(0);
			Puntuacion pun = new Puntuacion();
			pun.setPuntosActividad((int)json.get("puntosActividad"));
			pun.setPuntosLibros((int)json.get("puntosLibros"));
			pun.setPuntosLibreria((int)json.get("puntosLibreria"));
			pun.setPuntosLogin((int)json.get("puntosLogin"));
			pun.setPuntosComentar((int)json.get("puntosComentar"));

			puntuacion = new Puntuacion(pun);


		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * LEE EL FICHERO JSON DE RANGO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void leerRango() throws FileNotFoundException,
			IOException, ParseException{
		String s = devolverStringJson("rangos.json");
		s = "[" + s + "]";

		try{

			JSONArray jsonArray = new JSONArray(s);
			JSONObject json = jsonArray.getJSONObject(0);
			JSONArray rangs = new JSONArray("rangos");
			ArrayList<String> r = new ArrayList<>();
			for(int i=0;i<rangs.length();i++){
				JSONObject object = rangs.getJSONObject(i);
				r.add(object.toString());
			}

			rango.setRangos(r);

		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}

	/**
	 * CARGA TODOS LOS JSON LANZA EXCEPCIONES QUE SE CAZAN EN EL LOGIN ACTIVITY
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void cargarTodo() throws FileNotFoundException,
			IOException, ParseException{
		leerUsuarios();
		leerLibreria();
		leerLibros();
		leerOpiniones();
		leerVisitas();
		leerPuntuacion();
		leerRango();
		leerVisitas();
		leerActividades();

	}

	/**
	 * CONECTA EL USUARIO
	 * @param user
	 */
	public static void conectarUsuario(Usuario user){

		usuarioConectado = user;

	}

	/**
	 * NOS AÑADE A LA LISTA EL USUARIO CONECTADO
	 */
	public static void anyadirUsuarioConectadoLista(){
		usuarios.get(posicionUsuario).convertirUsuario(usuarioConectado);
	}

	/**
	 * NO FUNCIONA BIEN POR EL MOMENTO, PERO LO QUE SE INTENTA ES QUE COMRPUEBE SIN FALLOS UN EMAIL BIEN ESCRITO
	 * @param email
	 * @return
	 */
	public static Boolean iiiisValidEmail(String email){
		Boolean verdadero = false;

		Pattern patronEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$");

		Matcher mEmail = patronEmail.matcher(email.toLowerCase());
		if (mEmail.matches()){
			verdadero = true;
		}

		return verdadero;
	}

	/**
	 * NOS GENERA UN DESCUENTOS UTILIZNDO LA LISTA DE RANGOS
	 * @param rango
	 * @return
	 */
	public static float generarDescuento(String rango){
		float descuento=0.0f;
		ArrayList<String> r = Utilitats.rango.getRangos();
		int i =0;
		Boolean verdadero = false;
		do{
			if(r.get(i).toString() == rango){
				verdadero = true;
			}
			else{
				i++;
			}
		} while (!verdadero);
		switch(i){
			case 0:
				descuento = 0.0f;
				break;
			case 1:
				descuento = 3.0f;
				break;
			case 2:
				descuento = 6.0f;
				break;
			case 3:
				descuento = 9.0f;
				break;
			case 4:
				descuento = 12.0f;
				break;
			case 5:
				descuento = 15.0f;
				break;
			case 6:
				descuento = 18.0f;
				break;
			case 7:
				descuento = 21.0f;
				break;
			case 8:
				descuento = 24.0f;
				break;
			case 9:
				descuento = 27.0f;
				break;
			case 10:
				descuento = 30.0f;
				break;
		}

		return descuento;
	}

	/**
	 * VALIDA QUE SE LA CONTRASEÑA VALIDA O NO
	 * @param password
	 * @return
	 */
	public static boolean isPasswordValid(String password)
	{
		Boolean verdadero = false;
		if(password.length()>3){
			verdadero = true;
		}
		//NOS DEVUELVE QUE EL PASSWORD SEA MAYOR DE 4 CARACTERES
		return verdadero;
	}

	/**
	 * NOS VALIDA QUE SEA EL EMAIL VALIDO O NO
	 * @param email
	 * @return
	 */
	public static boolean isEmailValid(String email)
	{
		Boolean verdadero = false;
		//MIRA QUE CONTENGA UNA @ EN EL MAIL
		if(email.contains("@") && !email.endsWith("@") && !email.startsWith("@")){
			verdadero = true;
		}
		return verdadero;
	}

	/**
	 * COMPARA DOS CONTRASEÑAS Y NOS DICE SI SON IGUALES O NO
	 * @param password
	 * @param password2
	 * @return
	 */
	public static boolean isPasswordTheSame(String password, String password2){
		Boolean verdadero = false;

		if(password.equals(password2)){
			verdadero = true;
		}

		return verdadero;
	}

	/**
	 * NOS DICE SI UN USUARIO ES VALIDO O NO
	 * @param email
	 * @param password
	 * @return
	 */
	public static boolean isUserValid(String email, String password){
		Boolean verdadero = false;
		int i = 0;
		ArrayList<Usuario> users = Utilitats.getUsuarios();

		do{
			Usuario usuario = users.get(i);
			if(usuario.Equals(email, password)){
				verdadero = true;
				usuario.setPuntos(Utilitats.puntuacion.getPuntosLogin());
				usuario.setRank(Utilitats.rango.asignarRango(usuario.getPuntos()));
				usuario.setDescuento(Utilitats.generarDescuento(usuario.getRank()));					//NOS GUARDA EN UTILITATS EL USUARIO CONECTADO
				Utilitats.conectarUsuario(usuario);                										//ASÍ PODEMOS TRABAJAR CON ÉL
				Utilitats.setPosicionUsuario(i);
				JsonWrite.crearJsonUsuarios();
			}
			else{
				i++;
			}
		} while(i < Utilitats.getUsuarios().size() && !verdadero);

		return verdadero;

	}

	/**
	 * NOS DICE SI EL USUARIO EXISTE YA O NO 
	 * @param email
	 * @return
	 */
	public static boolean userExists(String email){
		Boolean verdadero = false;
		int i = 0;
		ArrayList<Usuario> users = Utilitats.getUsuarios();

		do{
			Usuario usuario = users.get(i);
			if(usuario.mailValid(email)){
				verdadero = true;
			}
			else{
				i++;
			}
		} while(i < Utilitats.getUsuarios().size() && !verdadero);

		return verdadero;

	}

}
