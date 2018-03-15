package juego.control;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.modelo.Jugada;
import juego.modelo.Tablero;
import juego.util.Sentido;

/**
 * Clase arbitro con la siguiente restriccion: los electrones no pueden
 * colocarse en la fila de inicio del jugador contrario.
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

public class ArbitroNeutronRestrictivo extends ArbitroNeutron {

	/**
	 * Constructor de la clase ArbitroNeutronRestrictivo.
	 * 
	 * @param tablero
	 *            tablero para jugar
	 * @param nombreAzules
	 *            nombre del jugador con piezas Azules
	 * @param nombreRojas
	 *            nombre del jugador con piezas RojasS
	 * @return
	 */
	public ArbitroNeutronRestrictivo(Tablero tablero, String nombreAzules, String nombreRojas) {
		super(tablero, nombreAzules, nombreRojas);
	}

	/**
	 * Metodo que consulta, dada una jugada, si es legal o no. Adaptado a las
	 * restricciones de la clase.
	 * 
	 * @param jugada
	 *            jugada a consultar si es legal o no
	 */
	@Override
	public boolean esMovimientoLegal(Jugada jugada) {
		Celda origen = null;
		Celda destino = null;
		destino = jugada.consultarDestino();
		origen = jugada.consultarOrigen();
		if (!origen.estaVacia() && destino.estaVacia()) {
			if (destino == ultimaCelda(jugada)) {
				if (consultarTurno().estaMoviendoNeutron()
						&& jugada.consultarOrigen().obtenerPieza().obtenerColor() == Color.AMARILLO) {
					return true;
				}
				if (consultarTurno().estaMoviendoElectron() && jugada.consultarOrigen().obtenerPieza()
						.obtenerColor() == turno.obtenerJugadorConTurno().obtenerColor()) {
					return true;
				}
				if (consultarTurno().estaMoviendoElectron() && jugada.consultarOrigen().obtenerPieza()
						.obtenerColor() == turno.obtenerJugadorConTurno().obtenerColor()) {
					return true;
				}
				return false;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * Metodo que obtiene la ultima celda dado un sentido.
	 * 
	 * @param jugada
	 *            jugada de la partida
	 * 
	 * @return ultimaCelda
	 */

	@Override
	protected Celda ultimaCelda(Jugada jugada) {
		int despFila = 0, despColum = 0;
		Sentido sentido = null;
		Celda ultimaCelda;
		Celda origen = jugada.consultarOrigen();
		int fila = origen.obtenerFila();
		int columna = origen.obtenerColumna();
		ultimaCelda = jugada.consultarOrigen();
		sentido = calcularSentido(jugada);
		if (sentido != null) {
			despFila = sentido.obtenerDesplazamientoFila();
			despColum = sentido.obtenerDesplazamientoColumna();
		}
		while (sentido != null && consultarTablero().estaEnTablero(fila + despFila, columna + despColum)
				&& consultarTablero().obtenerCelda(fila + despFila, columna + despColum).estaVacia()
				&& turno.estaMoviendoElectron() && !turno.obtenerJugadorSinTurno().consultarConfiguracion()
						.contiene(tablero.obtenerCelda(fila + despFila, columna + despColum))) {
			fila += despFila;
			columna += despColum;
			ultimaCelda = consultarTablero().obtenerCelda(fila, columna);
		}
		while (sentido != null && consultarTablero().estaEnTablero(fila + despFila, columna + despColum)
				&& consultarTablero().obtenerCelda(fila + despFila, columna + despColum).estaVacia()
				&& !turno.estaMoviendoElectron()) {
			fila += despFila;
			columna += despColum;
			ultimaCelda = consultarTablero().obtenerCelda(fila, columna);
		}
		return ultimaCelda;
	}
}//ArbitroNeutronRestrictivo
