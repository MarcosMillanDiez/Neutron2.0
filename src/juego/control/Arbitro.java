package juego.control;

import juego.modelo.*;
import juego.util.CeldasFueraTableroException;

import java.util.List;

/**
 * Interfaz arbitro.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25112015
 */
public interface Arbitro {
	/**
	 * Metodo que consulta el turno de la partida.
	 * 
	 * @return objeto de tipo Turno que representa el turno de la partida
	 */
	Turno consultarTurno();

	/**
	 * Metodo que especifica el procedimiento para jugar la partida.
	 * 
	 * @param jugada
	 *            objeto de tipo jugada que representa la jugada de la partida
	 */
	void jugar(Jugada jugada);

	/**
	 * Metodo que devuelve el tablero asignado al arbitro.
	 * 
	 * @return atributo de tipo Tablero
	 */
	Tablero consultarTablero();

	/**
	 * Metodo booelano que comprueba si la partida ha finalizado.
	 * 
	 * @return true si acaba la partida, false si no
	 */
	boolean estaAcabado();

	/**
	 * Metodo que consulta si ya existe un ganador de la partida.
	 * 
	 * @return objeto de tipo Jugador que representa el ganador de la partida
	 */
	Jugador consultarGanador();

	/**
	 * Metodo que comprueba si es legal colocar una pieza en la posicion del
	 * tablero indicada.
	 * 
	 * @param jugada
	 *            objeto de tipo Jugada que representa la jugada actual de la
	 *            partida
	 * @return true si es legal, falso si no
	 * @throws CeldasFueraTableroException 
	 */
	boolean esMovimientoLegal(Jugada jugada) throws CeldasFueraTableroException;

	/**
	 * Metodo que, dado un origen, nos devuelve la lista de celdas validas a la
	 * que se posible mover nuestra pieza.
	 * 
	 * @param origen
	 *            origen del movimiento
	 * @return lista de celdas destino
	 */
	List<Celda> obtenerCeldasValidas(Celda origen);

}
