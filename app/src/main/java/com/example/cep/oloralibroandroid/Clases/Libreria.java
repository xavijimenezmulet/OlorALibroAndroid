package com.example.cep.oloralibroandroid.Clases;

import java.util.ArrayList;

public class Libreria
{
	public int id ;
	public String nombre;
	public String direccion;
	public String correo;
	public String telefono;
	public String imagen;
	public ArrayList<Libro> libros;
	public ArrayList<String> actividades;

	public Libreria(int id, String nombre, String direccion, String correo, String telefono, String imagen, ArrayList<Libro> libros, ArrayList<String> actividades)
	{
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.imagen = imagen;
		this.libros = libros;
		this.actividades = actividades;
	}

	public int getId()
	{
		return id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public String getDireccion()
	{
		return direccion;
	}

	public String getCorreo()
	{
		return correo;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public String getImagen()
	{
		return imagen;
	}

	public ArrayList<Libro> getLibros()
	{
		return libros;
	}

	public ArrayList<String> getActividades()
	{
		return actividades;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public void setCorreo(String correo)
	{
		this.correo = correo;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public void setImagen(String imagen)
	{
		this.imagen = imagen;
	}

	public void setLibros(ArrayList<Libro> libros)
	{
		this.libros = libros;
	}

	public void setActividades(ArrayList<String> actividades)
	{
		this.actividades = actividades;
	}

	public Boolean Equals(Libreria l)
	{
		Boolean igual = false;

		if (l.nombre == this.nombre && l.direccion == this.direccion)
		{
			igual = true;
		}
		return igual;
	}
}
