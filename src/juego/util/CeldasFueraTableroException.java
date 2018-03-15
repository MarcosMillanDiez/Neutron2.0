package juego.util;

import juego.modelo.*;

/**
 * Excepcion, la celda especificada no se encuentra dentro del tablero de juego.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */

public class CeldasFueraTableroException extends Exception {

	/**
	 * Constructor sin argumentos.
	 */
	public CeldasFueraTableroException() {
		super("¡Las celdas se encuentran fuera del tablero!");
	}

	/**
	 * Constructor que encapsula la jugada.
	 * 
	 * @param jugada
	 */
	public CeldasFueraTableroException(Jugada jugada) {
		super(jugada.toString() + " es una jugada incorrecta.\n");
	}

	/**
	 * Constructor con texto descriptivo.
	 * 
	 * @param message
	 */
	public CeldasFueraTableroException(String mensaje) {
		super(mensaje);

	}

	/**
	 * Constructor con excepcion encadenada.
	 * 
	 * @param cause
	 */
	public CeldasFueraTableroException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con texto descriptivo y excepcion encadenada.
	 * 
	 * @param message
	 */
	public CeldasFueraTableroException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}

	/**
	 * Metodo String.
	 */
	public String getMessage() {
		return null;

	}

}//CeldasFueraTableroException
