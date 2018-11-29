package com.example.cep.oloralibroandroid.Clases;

public class Visita
{
	public String user;
	public String fecha ;

	public Visita()
	{
	}

	public Visita(String user, String fecha)
	{
		this.user = user;
		this.fecha = fecha;
	}

	public String getUser()
	{
		return user;
	}

	public String getFecha()
	{
		return fecha;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

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
