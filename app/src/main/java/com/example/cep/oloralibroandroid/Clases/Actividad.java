package com.example.cep.oloralibroandroid.Clases;

import java.util.ArrayList;

public class Actividad
{
	private int id;
	private String nombre;
	private String descripcion;
	private String lugar;
	private String tipo;
	private String fecha;
	private String hora;
	private ArrayList<String> librerias;
	private ArrayList<Opinion> opiniones;
	private ArrayList<Visita> visitas;



	public int getId()
	{
		return id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public String getLugar()
	{
		return lugar;
	}

	public String getTipo()
	{
		return tipo;
	}

	public String getFecha()
	{
		return fecha;
	}

	public String getHora()
	{
		return hora;
	}

	public ArrayList<Visita> getVisitas()
	{
		return visitas;
	}

	public ArrayList<String> getLibrerias()
	{
		return librerias;
	}

	public ArrayList<Opinion> getOpiniones()
	{
		return opiniones;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public void setLugar(String lugar)
	{
		this.lugar = lugar;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

	public void setHora(String hora)
	{
		this.hora = hora;
	}

	public void setVisitas(ArrayList<Visita> visitas)
	{
		this.visitas = visitas;
	}

	public void setLibrerias(ArrayList<String> librerias)
	{
		this.librerias = librerias;
	}

	public void setOpiniones(ArrayList<Opinion> opiniones)
	{
		this.opiniones = opiniones;
	}

	public Boolean Equals(Actividad a)
	{
		Boolean igual = false;

		if (a.nombre == this.nombre && a.lugar == this.lugar && a.fecha == this.fecha && a.hora == this.hora)
		{
			igual = true;
		}
		return igual;
	}
}
