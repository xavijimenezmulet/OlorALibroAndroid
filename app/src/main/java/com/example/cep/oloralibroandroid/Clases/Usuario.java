package com.example.cep.oloralibroandroid.Clases;

/**
 * CLASE USUARIO
 */
public class Usuario
{
	//ATRIBUTOS
	private String username;
	private String nombre;
	private String apellidos;
	private String ciudad ;
	private String password;
	private int puntos;
	private String rank;
	private float descuento;

	//CONSTRUCTORES
	public Usuario()
	{
		this.puntos = 0;
		this.rank = "Unranked";
		this.descuento = 0.0f;
	}

	public Usuario(Usuario u)
	{
		this.username = u.username;
		this.nombre = u.nombre;
		this.apellidos = u.apellidos;
		this.ciudad = u.ciudad;
		this.password = u.password;
		this.puntos = u.puntos;
		this.rank = u.rank;
		this.descuento = u.descuento;
	}

	//GETTERS
	public String getUsername()
	{
		return username;
	}

	public String getNombre()
	{
		return nombre;
	}

	public String getApellidos()
	{
		return apellidos;
	}

	public String getCiudad()
	{
		return ciudad;
	}

	public String getPassword()
	{
		return password;
	}

	public int getPuntos()
	{
		return puntos;
	}

	//SETTERS
	public String getRank()
	{
		return rank;
	}

	public float getDescuento()
	{
		return descuento;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	public void setCiudad(String ciudad)
	{
		this.ciudad = ciudad;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setPuntos(int puntos)
	{
		this.puntos += puntos;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public void setDescuento(float descuento)
	{
		this.descuento = descuento;
	}

	//EQUALS
	public Boolean Equals(String email, String password)
	{
		Boolean igual = false;

		if (email.equals(this.username) && password.equals(this.password))
		{
			igual = true;
		}
		return igual;
	}

	/**
	 * VALIDA SI TIENE EL MISMO MAIL
	 */
	public Boolean mailValid(String email){
		Boolean igual = false;

		if (email.equals(this.username))
		{
			igual = true;
		}
		return igual;
	}

	/**
	 * CONVIERTE A UN USUARIO EN EL MISMO
	 * @param u
	 */
	public void convertirUsuario(Usuario u){
		this.username = u.username;
		this.nombre = u.nombre;
		this.apellidos = u.apellidos;
		this.ciudad = u.ciudad;
		this.password = u.password;
		this.puntos = u.puntos;
		this.rank = u.rank;
		this.descuento = u.descuento;
	}
	/**
	public Boolean mismoUsuario(Usuario u){
		Boolean verdadero = false;

		if(this.username.equals(u.username) &&
				this.nombre.equals(u.nombre) &&
				this.apellidos.equals(u.apellidos) &&
				this.ciudad.equals(u.ciudad) &&
				this.puntos == u.puntos &&
				this.rank.equals(u.rank) &&
				this.descuento == u.descuento){
			verdadero = true;
		}

		return verdadero;
	}
	 **/
}
