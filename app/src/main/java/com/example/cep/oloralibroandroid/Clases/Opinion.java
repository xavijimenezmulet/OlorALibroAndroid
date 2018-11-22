package com.example.cep.oloralibroandroid.Clases;

public class Opinion
{
	public Usuario user;
	public String fecha;
	public String comentario;

	public Usuario getUser()
	{
		return user;
	}

	public String getFecha()
	{
		return fecha;
	}

	public String getComentario()
	{
		return comentario;
	}

	public void setUser(Usuario user)
	{
		this.user = user;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

	public void setComentario(String comentario)
	{
		this.comentario = comentario;
	}


}
