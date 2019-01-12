package com.example.cep.oloralibroandroid.Clases;

import com.example.cep.oloralibroandroid.R;

import java.util.ArrayList;

/**
 * CLASE LIBRERIA
 */
public class Libreria
{
	//ATRIBUTOS
	private int id ;
	private String nombre;
	private String direccion;
	private String correo;
	private String telefono;
	private String imagen;
	private ArrayList<Libro> libros;
	private ArrayList<String> actividades;

	//CONSTRUCTORES
	public Libreria()
	{
		this.nombre = "No hay tantas librerias";
		this.direccion = "No hay tantas librerias";
		this.imagen ="R.drawable.defaultlibreria";
	}

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

	//GETTERS
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

	//SETTERS
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

	//EQUALS
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
