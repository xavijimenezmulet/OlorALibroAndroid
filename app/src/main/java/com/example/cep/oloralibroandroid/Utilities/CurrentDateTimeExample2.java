package com.example.cep.oloralibroandroid.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

//Metodo para devolver la fecha y hora actual

public class CurrentDateTimeExample2 {
	public static String fechaActual() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String data = formatter.format(date);
		//System.out.println(formatter.format(date));

		return data;
	}
}
