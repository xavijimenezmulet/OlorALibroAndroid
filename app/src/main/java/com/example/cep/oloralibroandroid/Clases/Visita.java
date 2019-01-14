package com.example.cep.oloralibroandroid.Clases;

/**
 * CLASE VISITA
 */
public class Visita
{
	//ATRIBUTOS
	public String user;
	public String fecha ;

	//CONSTRUCTORES
	public Visita()
	{
	}

	public Visita(String user, String fecha)
	{
		this.user = user;
		this.fecha = fecha;
	}

	//GETTERS
	public String getUser()
	{
		return user;
	}

	public String getFecha()
	{
		return fecha;
	}

	//SETTERS
	public void setUser(String user)
	{
		this.user = user;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

	//EQUALS
	public Boolean Equals(Visita v)
	{
		Boolean igual = false;

		if (v.user.equals(this.user)  && v.fecha.equals(this.fecha))
		{
			igual = true;
		}
		return igual;
	}
}
