package juego.control;

import juego.modelo.*;

/**
 * Clase que establece el turno de los jugadores.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0
 */

import juego.modelo.Jugador;

/**
 * Clase que controla las normas y movimiento del juego.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 2.0 17102015
 */

public class Turno {
	/**
	 * Atributo jugadorAzules de tipo Jugador.
	 */
	private Jugador jugadorAzules;

	/**
	 * Atributo jugadorRojas de tipo Jugador.
	 */
	private Jugador jugadorRojas;

	/**
	 * Atributo turno de tipo entero.
	 */
	private int turno;
	/**
	 * Atributo estaMoviendoNeutron de tipo boolean.
	 */
	private boolean estaMoviendoNeutron;
	/**
	 * Atributo estaMoviendoElectron de tipo boolean.
	 */

	/**
	 * Constructor de la clase Turno.Inicializa los jugadores.
	 * 
	 * @param nombreAzules
	 *            jugador de piezas azules
	 * @param nombreRojas
	 *            jugador de piezas rojas
	 */

	public Turno(String nombreRojas, String nombreAzules) {
		jugadorAzules = new Jugador(nombreAzules, Color.AZUL);
		jugadorRojas = new Jugador(nombreRojas, Color.ROJO);
		estaMoviendoNeutron = false;
	}

	/**
	 * Metodo que cambia el turno de los jugadores.
	 */
	public void cambiarTurno() {
		turno++;
		this.estaMoviendoNeutron = !this.estaMoviendoNeutron;
	}

	/**
	 * Obtiene el jugador que posee el turno en un determinado momento de la
	 * partida.
	 * 
	 * @return jugador
	 */

	public Jugador obtenerJugadorConTurno() {
		Jugador jugador;
		if (turno % 2 == 0) {
			jugador = jugadorRojas;
		} else {
			jugador = jugadorAzules;
		}
		return jugador;
	}

	/**
	 * Obtiene el jugador que no posee el turno en un determinado momento de la
	 * partida.
	 * 
	 * @return jugador
	 */

	public Jugador obtenerJugadorSinTurno() {
		Jugador jugador;
		if (turno % 2 == 0) {
			jugador = jugadorAzules;
		} else {
			jugador = jugadorRojas;
		}
		return jugador;
	}

	/**
	 * Devuelve un valor verdadero si la pieza a mover es un Neutron.
	 * 
	 * @return true / false
	 */

	public boolean estaMoviendoNeutron() {
		return this.estaMoviendoNeutron;
	}

	/**
	 * Devuelve un valor verdadero si la pieza a mover es un Eletron.
	 * 
	 * @return true / false
	 */

	public boolean estaMoviendoElectron() {
		return !this.estaMoviendoNeutron;
	}

	/**
	 * Cambia el movimiento de neutron a electron.
	 */

	public void cambiarMovimientoDeNeutronAElectron() {
		estaMoviendoNeutron = false;

	}
} // Turno
