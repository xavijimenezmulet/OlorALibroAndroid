package com.example.cep.oloralibroandroid.Clases;

import java.util.ArrayList;

/**
 * CLASE LIBRO
 */
public class Libro
{
	//ATRIBUTOS
	private int id;
	private String titulo;
	private String autor;
	private int anyo;
	private int precio;
	private String portada;
	private ArrayList<String> genero;

	//CONSTRUCTORES
	public Libro()
	{

	}

	public Libro(int id, String titulo, String autor, int anyo, int precio, String portada, ArrayList<String> genero)
	{
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.precio = precio;
		this.portada = portada;
		this.genero = genero;
	}

	//GETTERS
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

	public String getPortada()
	{
		return portada;
	}

	public ArrayList<String> getGenero()
	{
		return genero;
	}

	//SETTERS
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

	public void setPortada(String portada)
	{
		this.portada = portada;
	}

	public void setGenero(ArrayList<String> genero)
	{
		this.genero = genero;
	}

	//EQUALS
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
