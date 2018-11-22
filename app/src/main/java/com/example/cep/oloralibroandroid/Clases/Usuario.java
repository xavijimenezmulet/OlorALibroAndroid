package com.example.cep.oloralibroandroid.Clases;

public class Usuario
{
	public String username;
	public String nombre;
	public String apellidos;
	public String ciudad ;
	public String password;
	public int puntos;
	public String rank;
	public float descuento;

	public Usuario()
	{

	}

	public Usuario(String username, String nombre, String apellidos, String ciudad, String password, int puntos, String rank, float descuento)
	{
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciudad = ciudad;
		this.password = password;
		this.puntos = puntos;
		this.rank = rank;
		this.descuento = descuento;
	}

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
		this.puntos = puntos;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public void setDescuento(float descuento)
	{
		this.descuento = descuento;
	}

	public Boolean Equals(Usuario u)
	{
		Boolean igual = false;

		if (u.username == this.username)
		{
			igual = true;
		}
		return igual;
	}
}
