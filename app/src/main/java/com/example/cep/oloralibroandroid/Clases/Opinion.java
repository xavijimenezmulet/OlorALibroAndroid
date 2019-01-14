package com.example.cep.oloralibroandroid.Clases;

/**
 * CLASE OPINION
 */
public class Opinion
{
	//ATRIBUTOS
	private Usuario user;
	private String fecha;
	private String comentario;

	//GETTERS
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

	//SETTERS
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
