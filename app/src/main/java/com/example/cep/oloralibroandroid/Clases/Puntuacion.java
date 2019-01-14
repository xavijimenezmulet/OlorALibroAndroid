package com.example.cep.oloralibroandroid.Clases;

/**
 * CLASE PUNTUACION
 */
public class Puntuacion
{
	//ATRIBUTOS
	private int puntosActividad;
	private int puntosLibros;
	private int puntosLibreria;
	private int puntosLogin;
	private int puntosComentar;

	//CONSTRUCTORES
	public Puntuacion()
	{
	}

	public Puntuacion(Puntuacion p)
	{
		this.puntosActividad = p.puntosActividad;
		this.puntosLibros = p.puntosLibros;
		this.puntosLibreria = p.puntosLibreria;
		this.puntosLogin = p.puntosLogin;
		this.puntosComentar = p.puntosComentar;
	}

	//SETTERS
	public void setPuntosActividad(int puntosActividad)
	{
		this.puntosActividad = puntosActividad;
	}

	public void setPuntosLibros(int puntosLibros)
	{
		this.puntosLibros = puntosLibros;
	}

	public void setPuntosLibreria(int puntosLibreria)
	{
		this.puntosLibreria = puntosLibreria;
	}

	public void setPuntosLogin(int puntosLogin)
	{
		this.puntosLogin = puntosLogin;
	}

	public void setPuntosComentar(int puntosComentar)
	{
		this.puntosComentar = puntosComentar;
	}

	//GETTERS
	public int getPuntosActividad()
	{
		return puntosActividad;
	}

	public int getPuntosLibros()
	{
		return puntosLibros;
	}

	public int getPuntosLibreria()
	{
		return puntosLibreria;
	}

	public int getPuntosLogin()
	{
		return puntosLogin;
	}

	public int getPuntosComentar()
	{
		return puntosComentar;
	}
}
