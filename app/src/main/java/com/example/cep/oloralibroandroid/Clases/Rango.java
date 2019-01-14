package com.example.cep.oloralibroandroid.Clases;

import java.util.ArrayList;

/**
 * CLASE RANGO
 */
public class Rango
{
	//ATRIBUTOS
	private ArrayList<String> rangos = new ArrayList<>();

	//CONSTRUCTORES
	public Rango(){
		rangos.add("Unranked");
		rangos.add("Soldado raso lector");
		rangos.add("Sargento lector");
		rangos.add("Teniente lector");
		rangos.add("Mayor lector");
		rangos.add("Teniente coronel lector");
		rangos.add("Coronel lector");
		rangos.add("General-mayor lector");
		rangos.add("General-teniente lector");
		rangos.add("General coronel lector");
		rangos.add("Generalísimo lector");
	}

	//getters
	public ArrayList<String> getRangos()
	{
		return rangos;
	}

	public void setRangos(ArrayList<String> rangos)
	{
		this.rangos = rangos;
	}

	/**
	 * ASIGNA LOS PUNTOS DEL USUARIO DEPENDIENDO DE LOS PUNTOS QUE TENGA
	 * @param puntos
	 * @return
	 */
	public String asignarRango(int puntos){
		String rango;

		if (puntos < 10)
		{
			rango = rangos.get(0);
		}
		else if (puntos >= 10 && puntos < 20)
		{
			rango = rangos.get(1);
		}
		else if (puntos >= 20 && puntos < 40)
		{
			rango = rangos.get(2);
		}
		else if (puntos >= 40 && puntos < 80)
		{
			rango = rangos.get(3);
		}
		else if (puntos >= 80 && puntos < 100)
		{
			rango = rangos.get(4);
		}
		else if (puntos >= 100 && puntos < 150)
		{
			rango = rangos.get(5);
		}
		else if (puntos >= 150 && puntos < 250)
		{
			rango = rangos.get(6);
		}
		else if (puntos >= 250 && puntos < 500)
		{
			rango = rangos.get(7);
		}
		else if (puntos >= 500 && puntos < 800)
		{
			rango = rangos.get(8);
		}
		else if (puntos >= 800 && puntos < 1200)
		{
			rango = rangos.get(9);
		}
		else if (puntos >= 1200)
		{
			rango = rangos.get(10);
		}
		else
		{
			rango = "Error";
		}

		return rango;
	}
}
