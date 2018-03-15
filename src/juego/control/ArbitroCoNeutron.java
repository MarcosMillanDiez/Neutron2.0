package juego.control;

import juego.modelo.Celda;
import juego.modelo.Jugador;
import juego.modelo.Tablero;

/**
 * Clase arbitro con la siguiente restriccion: para que un jugador resulte
 * ganador debe colocar el neutron en la fila de inicio de los electrones del
 * contrincante.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 27112015
 * 
 * @see juego.modelo.Celda
 * @see juego.modelo.Color
 * @see juego.modelo.Jugador
 * @see juego.modelo.Pieza
 * @see juego.modelo.Tablero
 */
public class ArbitroCoNeutron extends ArbitroNeutron {

	/**
	 * Constructor de la clase ArbitroCoNeutron.
	 * 
	 * @param tablero
	 * @param nombreAzules
	 * @param nombreRojas
	 */
	public ArbitroCoNeutron(Tablero tablero, String nombreAzules, String nombreRojas) {
		super(tablero, nombreAzules, nombreRojas);
	}

	/**
	 * Metodo que consulta el ganador de la partida.
	 * 
	 * @return Jugador ganador de la partida
	 */
	@Override
	public Jugador consultarGanador() {
		Celda neutron = consultarTablero().obtenerCeldaNeutron();
		if (turno.obtenerJugadorConTurno().consultarConfiguracion().contiene(neutron)) {
			return turno.obtenerJugadorSinTurno();
		} else {
			if (turno.obtenerJugadorSinTurno().consultarConfiguracion().contiene(neutron)) {
				return turno.obtenerJugadorConTurno();
			} else {
				return null;
			}
		}
	}

}//ArbitroCoNeutron
