package Fecha;

import java.util.Calendar;
import java.util.Date;

import utiles.Menu;
import utiles.Teclado;

public class TestFecha {
	static String[] opciones = {"Sumar dos fechas", "Dia de la semana", 
			"Mes del Año", "Tiempo Transcurrido entre dos fechas", "fecha Futura", "Mayoría de Edad", "Cambiar Fecha", "Mostrar fecha trabajada."};
	static Menu menu = new Menu("Trabajando con cadenas: ",  opciones);
	
	public static void main(String[] args)
			throws FechaTranscurridaException, FormatoFechaNoValido, FechaNoValidaException {
		int opcion = 0;
		String msg = "Introduzca una fecha";
		Fecha fecha = instanciarFecha(msg);
		do{
			opcion = menu.gestionar();
			switch(opcion){
				
			case 1:
				sumar(fecha);
				break;
			case 2:
				diaSemana(fecha);
				break;
			case 3:
				mesAnno(fecha);
				break;
			case 4:
				tiempoTranscurrido(fecha);
				break;
			case 5:
				esFutura(fecha);
				break;
			case 6:
				esMayordeEdad(fecha);
				break;
			case 7:
				msg = "Introduce una nueva fecha.";
				fecha = instanciarFecha(msg);
				break;
			case 8:
				System.out.println(fecha.toString());
				break;
			case 9:
				System.out.println("Saliendo de programa");
				break;
			default:
				System.out.println("Opción no válida.");
			}
			
			
		}while(opcion != menu.getNumOpciones());
		
	}

	private static Fecha instanciarFecha(String msg) {
		try {
			return new Fecha(Teclado.leerCadena(msg));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static void sumar(Fecha fecha) throws FechaNoValidaException, FormatoFechaNoValido {
		String msg = "Introduzca la fecha que desea sumar: ";
		Fecha fech = fecha.sumarFechas(instanciarFecha(msg));
		System.out.println(fech.toString());

	}

	private static void tiempoTranscurrido(Fecha fecha) throws FormatoFechaNoValido, FechaNoValidaException {
		try {
			String cadeno = Teclado.leerCadena("Introduce una fecha correcta: ");
			long dias_transcurridos = fecha.tiempoTranscurridoDi(new Fecha(cadeno));
			long annos_transcurridos = fecha.tiempoTranscurridoAnnos(new Fecha(cadeno));
			System.out
					.println("Han transcurrido " + annos_transcurridos + " años,  o " + dias_transcurridos + " días.");
		} catch (FormatoFechaNoValido e) {
			System.out.println(e.getMessage());
		}

	}

	private static void esMayordeEdad(Fecha fecha) {
		if (fecha.esMayordeEdad()) {
			System.out.println("El sujeto es mayor de edad.");
		} else {
			System.out.println("El sujeto es menor de edad.");
		}

	}

	private static void esFutura(Fecha fecha) {
		if (fecha.esFutura(fecha)) {
			System.out.println("La fecha es futura.");
		} else {
			System.out.println("La fecha es pasada");
		}

	}

	private static void mesAnno(Fecha fecha) {

		switch (fecha.getMes()) {

		case 0:
			System.out.println("Enero");
			break;

		case 1:
			System.out.println("Febrero");
			break;

		case 2:
			System.out.println("Marzo");
			break;

		case 3:
			System.out.println("Abril");
			break;

		case 4:
			System.out.println("Mayo");
			break;

		case 5:
			System.out.println("Junio");
			break;

		case 6:
			System.out.println("Julio");
			break;

		case 7:
			System.out.println("Agosto");
			break;

		case 8:
			System.out.println("Septiembre");
			break;

		case 9:
			System.out.println("Octubre");
			break;

		case 10:
			System.out.println("Noviembre");
			break;

		case 11:
			System.out.println("Diciembre");
			break;
		}
	}

	@SuppressWarnings("static-access")
	private static void diaSemana(Fecha fecha) {
		switch (fecha.cal.get(Calendar.DAY_OF_WEEK)) {

		case 1:
			System.out.println("Domingo");
			break;

		case 2:
			System.out.println("Lunes");
			break;

		case 3:
			System.out.println("Martes");
			break;

		case 4:
			System.out.println("Miércoles");
			break;

		case 5:
			System.out.println("Jueves");
			break;

		case 6:
			System.out.println("Viernes");
			break;

		case 7:
			System.out.println("Sábado");
			break;
		}

	}

}
