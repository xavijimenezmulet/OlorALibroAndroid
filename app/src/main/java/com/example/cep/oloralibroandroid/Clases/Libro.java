package com.example.cep.oloralibroandroid.Clases;

import java.util.ArrayList;

public class Libro
{
	public int id;
	public String titulo;
	public String autor;
	public int anyo;
	public int precio;
	public ArrayList<String> genero;

	public int getId()
	{
		return id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public String getAutor()
	{
		return autor;
	}

	public int getAnyo()
	{
		return anyo;
	}

	public int getPrecio()
	{
		return precio;
	}

	public ArrayList<String> getGenero()
	{
		return genero;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public void setAutor(String autor)
	{
		this.autor = autor;
	}

	public void setAnyo(int anyo)
	{
		this.anyo = anyo;
	}

	public void setPrecio(int precio)
	{
		this.precio = precio;
	}

	public void setGenero(ArrayList<String> genero)
	{
		this.genero = genero;
	}

	public Boolean Equals(Libro l)
	{
		Boolean igual = false;

		if (l.titulo == this.titulo && l.autor == this.autor && l.anyo == this.anyo)
		{
			igual = true;
		}
		return igual;
	}
}
