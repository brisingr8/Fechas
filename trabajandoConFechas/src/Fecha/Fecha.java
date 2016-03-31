package Fecha;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Fecha {

	private static String cadena;
	private int dia;
	private int mes;
	private int anno;
	GregorianCalendar cal;
	private static final Pattern REGEX = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])[- /](0?[1-9]|1[012])[-/]\\d{4}");
	

	Fecha(String cadena) throws FormatoFechaNoValido, FechaNoValidaException {
		try{
			this.cadena = cadena;
			esValido();
		}catch(FormatoFechaNoValido e){
			System.out.println(e.getMessage());
		}
		

	}

	private void esValido() throws FormatoFechaNoValido, FechaNoValidaException {
		Matcher regex = REGEX.matcher(cadena);
		if (regex.matches()) {
			setDia();
			setMes();
			setAnno();
			setCalendar();
			

		} else {
			throw new FormatoFechaNoValido("La fecha no es válida");
		}

	}

	private void setCalendar() throws FechaNoValidaException {
		try {
			cal= new GregorianCalendar(anno, mes, dia);
			cal.setLenient(false);
			cal.getTime();
		} catch (Exception e) {
			throw new FechaNoValidaException("Fecha no válida.");
		}
		
	}

	Fecha(int dia, int mes, int anno) throws FechaNoValidaException, FormatoFechaNoValido {
		
		
		try{
			cadena = ""+dia+"/"+mes+"/"+anno;
			esValido();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	public Fecha(java.util.Date tiempo) {
		dia = tiempo.getDate();
		mes = tiempo.getMonth();
		anno = tiempo.getYear() + 1900;

		cal = new GregorianCalendar(anno, mes, dia);

	}

	
	private void setDia() {
		String num = cadena.substring(0, 2);
		dia = Integer.parseInt(num);

	}

	private void setMes() {
		String num = cadena.substring(3, 5);
		mes = Integer.parseInt(num) - 1;

	}

	private void setAnno() {
		String num = cadena.substring(6, 10);
		anno = Integer.parseInt(num);

	}

	boolean esMayordeEdad(){

		LocalDate fecha1=LocalDate.of(cal.get(GregorianCalendar.YEAR),
				cal.get(GregorianCalendar.MONTH)+1,
				cal.get(GregorianCalendar.DAY_OF_MONTH));
		
		LocalDate fecha2 = LocalDate.now();
		
		if(ChronoUnit.YEARS.between(fecha1,fecha2)<18)
			return false;
		return true;
		
	
	}
	boolean esFutura(Fecha fecha){
		GregorianCalendar aux = new GregorianCalendar();
		long fechainicial =  aux.getTimeInMillis();
		long fechaFinal =  fecha.cal.getTimeInMillis();
		if(fechainicial<fechaFinal)
			return true;
		return false;
	}

	Fecha sumarFechas(Fecha fecha) {
		GregorianCalendar sumada = new GregorianCalendar(fecha.anno, fecha.mes, fecha.dia);
		sumada.add(GregorianCalendar.DATE, dia);
		sumada.add(GregorianCalendar.MONTH, mes);
		sumada.add(GregorianCalendar.YEAR, anno);

		return new Fecha(sumada.getTime());
	}

	
	
	long tiempoTranscurridoDi(Fecha fecha) {

		LocalDate fecha1 = LocalDate.of(fecha.cal.get(GregorianCalendar.YEAR),
				fecha.cal.get(GregorianCalendar.MONTH) + 1, 
				fecha.cal.get(GregorianCalendar.DAY_OF_MONTH));

		LocalDate fecha2 = LocalDate.of(cal.get(GregorianCalendar.YEAR), 
				cal.get(GregorianCalendar.MONTH) + 1,
				cal.get(GregorianCalendar.DAY_OF_MONTH));

		return Math.abs(ChronoUnit.DAYS.between(fecha1, fecha2));

	}

	long tiempoTranscurridoAnnos(Fecha fecha) {

		LocalDate fecha1 = LocalDate.of(fecha.cal.get(GregorianCalendar.YEAR),
				fecha.cal.get(GregorianCalendar.MONTH) + 1, 
				fecha.cal.get(GregorianCalendar.DAY_OF_MONTH));

		LocalDate fecha2 = LocalDate.of(cal.get(GregorianCalendar.YEAR),
				cal.get(GregorianCalendar.MONTH) + 1,
				cal.get(GregorianCalendar.DAY_OF_MONTH));

		return Math.abs(ChronoUnit.YEARS.between(fecha1, fecha2));
	}

	@Override
	public String toString() {

		return "Fecha " + dia + "/" + (mes + 1) + "/" + anno;
	}

	public int getMes() {

		return mes;
	}

}
