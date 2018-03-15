package juego.modelo;

/**
 * Clase que define a los jugadores del juego.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */
public class Jugador {

	/**
	 * Atributo nombre de tipo String que representa el nombre del jugador.
	 */
	private String nombre;
	/**
	 * Atributo color de tipo Color que representa el color del jugador.
	 */
	private Color color;
	/**
	 * Atributo configuracion de tipo Configuracion que representa la
	 * configuracion inicial de la partida.
	 */
	private Configuracion configuracion;

	/**
	 * Constructor de la clase Jugador. Asigna el nombre y el color al jugador.
	 * 
	 * @param nombre
	 *            nombre del jugador
	 * @param color
	 *            color asignado al jugador
	 */
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}

	/**
	 * Metodo que obtiene el color asignado al jugador.
	 * 
	 * @return color
	 *
	 */
	public Color obtenerColor() {
		return color;
	}

	/**
	 * Metodo que obtiene el nombre del jugador.
	 * 
	 * @return nombre
	 */
	public String consultarNombre() {
		return nombre;
	}

	/**
	 * Metodo que genera una pieza de un color pasado por parametro.
	 * 
	 * @return pieza
	 */
	public Pieza generarPieza() {
		return new Pieza(color);
	}

	/**
	 * Metodo que permite consultar el valor de su configuracion.
	 * 
	 * @return configuracion
	 */
	public Configuracion consultarConfiguracion() {
		return configuracion;
	}

	/**
	 * Metodo que establece el valor de su configuracion.
	 * 
	 * @param configuracion
	 */
	public void establecerConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	/**
	 * Metodo toString.
	 */
	public String toString() {
		return consultarNombre() + ":" + obtenerColor();

	}
}// Jugador
