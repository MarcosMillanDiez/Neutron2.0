package juego.modelo;

/**
 * Clase pieza que define las piezas del juego.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */
public class Pieza {

	/**
	 * Atributo color de tipo Color que representa el color de la pieza.
	 */
	private Color color;
	/**
	 * Atributo celda de tipo Celda que representa la celda.
	 */
	private Celda celda;

	/**
	 * Constructor de la clase Pieza. Le asigna un color a la pieza.
	 * 
	 * @param color
	 */
	public Pieza(Color color) {
		this.color = color;

	}

	/**
	 * Metodo que devuelve el color de una pieza.
	 * 
	 * @return color
	 */
	public Color obtenerColor() {
		return color;
	}

	/**
	 * Metodo to String.
	 * 
	 */
	public String toString() {
		return "" + obtenerColor().toChar();
	}

	/**
	 * Metodo que devuelve la celda donde se encuentra una determinada pieza.
	 * 
	 * @return celdas
	 */
	public Celda obtenerCelda() {
		return celda;
	}

	/**
	 * Metodo al que le llega una celda y la asigna a la pieza.
	 * 
	 * @param celda
	 */
	public void colocar(Celda celda) {
		this.celda = celda;

	}

	/**
	 * Metodo que devuelve true si la pieza es un neutron.
	 * 
	 * @return
	 */
	public boolean esNeutron() {
		color = obtenerColor();
		if (color.toChar() == 'N') {
			return true;
		} else {
			return false;
		}
	}
	
}// Pieza
