package juego.modelo;

/**
 * Clase que construye las celdas del tablero.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */
public class Celda {

	/**
	 * Atributos fila, que representa la fila de la celda y Artibuto columna que
	 * representa la columna de la celda.
	 */
	private int fila, columna;

	/**
	 * Atributo pieza de tipo Pieza que representa la pieza de la celda.
	 */
	private Pieza pieza = null;

	/**
	 * Constructor de la clase Celda.
	 * 
	 * @param fila
	 *            fila de la celda.
	 * @param columna
	 *            columna de la celda.
	 */
	public Celda(int fila, int columna) {

		this.fila = fila;
		this.columna = columna;

	}

	/**
	 * Metodo que devuelve el objeto de tipo pieza atributo de la clase.
	 * 
	 * @return pieza devuelve la pieza actual de la celda.
	 */
	public Pieza obtenerPieza() {
		return pieza;
	}

	/**
	 * Metodo que asigna una pieza a la celda.
	 * 
	 * @param pieza
	 *            objeto de la clase Pieza para ser asignado a la celda.
	 */
	public void establecerPieza(Pieza pieza) {
		this.pieza = pieza;

	}

	/**
	 * Metodo que devuelve true si la celda esta vacia, false si no está vacia.
	 * 
	 * @return boolean
	 */
	public boolean estaVacia() {
		return pieza == null;
	}

	/**
	 * Metodo que devuelve la fila en la que esta la celda.
	 * 
	 * @return fila
	 */
	public int obtenerFila() {
		return fila;
	}

	/**
	 * Metodo que devuelve la columna en la que esta la celda.
	 * 
	 * @return columna
	 */
	public int obtenerColumna() {
		return columna;
	}

	/**
	 * Metodo que vacia un celda. Primero comprueba si esta vacia si lo está la
	 * pieza tendrá el valor null.
	 * 
	 */
	public void vaciar() {
		if (estaVacia() == true) {
			pieza = null;
		}
	}

	/**
	 * Metodo que devuelve la fila y la columna.
	 * 
	 * @return String
	 */
	public String toString() {
		return "(" + obtenerFila() + "/" + obtenerColumna() + ")";
	}

}// Celda
