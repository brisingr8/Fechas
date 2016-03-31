package utiles;

public class Menu {

	private String[] cadena;
	private String titulo;
	private int opciones;

	public Menu(String titulo, String[] cadena) {
		this.titulo = titulo;
		setOpciones(cadena);
		setNumOpciones(cadena);
	}

	private void setOpciones(String[] cadena) {
		int incremento = 0;
		this.cadena = new String[cadena.length + 1];
		for (String opcion : cadena)
			this.cadena[incremento++] = opcion;
		this.cadena[incremento] = "Salir";
	}

	private void setNumOpciones(String[] cadena) {
		opciones = cadena.length + 1;
	}
	public int getNumOpciones(){
		return opciones;
	}
	public void mostrar() {
		System.out.println(titulo);
		for (int i = 1; i <= opciones; i++) {
			System.out.println(i + ") " + cadena[i-1]);
		}
	}

	public int gestionar() {
		mostrar();
		return opcionValida();
	}

	private int opcionValida() {
		int opcion;
		do {
			opcion = Teclado.leerEntero("\nIntroduce opción válida: ");
		} while (opcion < 1 || opcion > opciones);
		return opcion;
	}

}