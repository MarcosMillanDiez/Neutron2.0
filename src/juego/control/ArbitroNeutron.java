package juego.control;

import java.util.ArrayList;
import java.util.List;

import juego.modelo.*;
import juego.util.CeldasFueraTableroException;
import juego.util.CoordenadasIncorrectasException;
import juego.util.Sentido;

/**
 * Clase que controla las normas y movimiento del juego.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 2.0 25112015
 */
public class ArbitroNeutron implements Arbitro {
	/**
	 * Atributo tablero de tipo Tablero.
	 */
	protected Tablero tablero;
	/**
	 * Atributo jugadorAzul de tipo Jugador.
	 */
	private Jugador jugadorAzul;
	/**
	 * Atributo jugadorRojo de tipo Jugador.
	 */
	private Jugador jugadorRojo;
	/**
	 * Atributo turno de tipo Turno.
	 */
	protected Turno turno;

	/**
	 * Constructor de la clase ArbitroNeutron.
	 * 
	 * Asigna el tablero, genera e inicia el turno, inicializa y establece la
	 * configuracion de cada jugador, genera las piezas y coloca electrones y
	 * neutrones.
	 * 
	 * @param tablero
	 *            tablero para jugar
	 * @param nombreAzules
	 *            nombre del jugador con piezas Azules
	 * @param nombreRojas
	 *            nombre del jugador con piezas Rojas
	 */
	public ArbitroNeutron(Tablero tablero, String nombreAzules, String nombreRojas) {
		// Tablero
		this.tablero = tablero;
		// Configuracion
		Configuracion ListaConfigRojas = new Configuracion();
		Configuracion ListaConfigAzules = new Configuracion();
		for (int i = 0; i < tablero.obtenerNumeroColumnas(); i++) {
			for (int j = 0; j < tablero.obtenerNumeroFilas(); j++) {
				if (i == 0) {
					ListaConfigAzules.añadir(tablero.obtenerCelda(i, j));
				} else if (i == tablero.obtenerNumeroFilas() - 1) {
					ListaConfigRojas.añadir(tablero.obtenerCelda(i, j));
				}
			}
		}
		// Turno
		turno = new Turno(nombreRojas, nombreAzules);
		this.jugadorRojo = turno.obtenerJugadorConTurno();
		this.jugadorAzul = turno.obtenerJugadorSinTurno();
		// Establece configuracion
		jugadorRojo.establecerConfiguracion(ListaConfigRojas);
		jugadorAzul.establecerConfiguracion(ListaConfigAzules);
		// Coloca en tablero
		colocarElectrones(tablero);
		colocarNeutron(tablero);

	}

	/**
	 * Metodo privado para colocar las piezas (Eletrones) en el tablero.
	 * 
	 * @param tablero
	 *            tablero en el que jugamos
	 */
	private void colocarElectrones(Tablero tablero) {
		int filas = tablero.obtenerNumeroFilas();
		int columnas = tablero.obtenerNumeroColumnas();
		for (int i = 0; i < columnas; i++) {
			Pieza piezaElectron = new Pieza(Color.AZUL);
			Celda celdaElectron = tablero.obtenerCelda(0, i);
			try {
				tablero.colocar(piezaElectron, celdaElectron);
			} catch (CoordenadasIncorrectasException e) {
				System.out.println("Te estas saliendo del tablero.\n");
			}
			Pieza piezaElectron2 = new Pieza(Color.ROJO);
			Celda celdaElectron2 = tablero.obtenerCelda(filas - 1, i);
			try {
				tablero.colocar(piezaElectron2, celdaElectron2);
			} catch (CoordenadasIncorrectasException e) {
				System.out.println("Te estas saliendo del tablero.\n");

			}
		}
	}

	/**
	 * Metodo privado para colocar las piezas (Neutron) en el tablero.
	 * 
	 * @param tablero
	 *            tablero en el que jugamos
	 */
	private void colocarNeutron(Tablero tablero) {
		int posicionFila = (tablero.obtenerNumeroFilas() / 2);
		int posicionColum = (tablero.obtenerNumeroColumnas() / 2);
		Celda celdaNeutron = tablero.obtenerCelda(posicionFila, posicionColum);
		Pieza piezaNeutron = new Pieza(Color.AMARILLO);
		try {
			tablero.colocar(piezaNeutron, celdaNeutron);
		} catch (CoordenadasIncorrectasException e) {
			System.out.println("Te estas saliendo del tablero.\n");

		}
	}

	/**
	 * Consultar el turno actual.
	 * 
	 * @return turno turno
	 */
	public Turno consultarTurno() {
		return turno;
	}

	/**
	 * Metodo para jugar.
	 * 
	 * @param jugada
	 *            jugada introducidad por el usuario.
	 */
	public void jugar(Jugada jugada) {
		if (turno.estaMoviendoNeutron() == true) {
			consultarTablero().mover(jugada);
			turno.cambiarMovimientoDeNeutronAElectron();
		} else {
			consultarTablero().mover(jugada);
			turno.cambiarTurno();
		}

	}

	/**
	 * Metodo para consultar el tablero.
	 * 
	 * @return tablero
	 */
	public Tablero consultarTablero() {
		return tablero;
	}

	/**
	 * Metodo boolean que devuelve true si la partida esta finalizada.
	 * 
	 * @return valor booleano
	 */
	public boolean estaAcabado() {
		if (consultarGanador() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que devuelve el nombre del jugador ganador.
	 * 
	 * Pasandole la jugada a este metodo, y utilizando el calcularSentido()
	 * calcularDiferenciaFila() y calculardiferenciaColum() calcula la ultima
	 * celda posible en ese sentido. Para que sea la ultima celda posible se
	 * tiene que cumplir que: la siguiente celda este en tablero y que este
	 * vacia.
	 * 
	 * @return Jugador ganador de la partida
	 */
	public Jugador consultarGanador() {
		Celda neutron = consultarTablero().obtenerCeldaNeutron();
		if (turno.obtenerJugadorConTurno().consultarConfiguracion().contiene(neutron)) {
			return turno.obtenerJugadorConTurno();
		} else {
			if (turno.obtenerJugadorSinTurno().consultarConfiguracion().contiene(neutron)) {
				return turno.obtenerJugadorSinTurno();
			} else {
				return null;
			}
		}
	}

	/**
	 * Metodo protected que obtiene la ultima celda dado un sentido.
	 * 
	 * Pasandole la jugada a este metodo, y utilizando el calcularSentido()
	 * calcularDiferenciaFila() y calculardiferenciaColum() calcula la ultima
	 * celda posible en ese sentido. Para que sea la ultima celda posible se
	 * tiene que cumplir que: la siguiente celda este en tablero y que este
	 * vacia.
	 * 
	 * @param jugada
	 *            jugada de la partida
	 * @return ultimaCelda
	 */
	protected Celda ultimaCelda(Jugada jugada) {
		int despFila = 0, despColum = 0;
		Sentido sentido = null;
		Celda ultimaCelda = null;
		Celda origen = jugada.consultarOrigen();
		int fila = origen.obtenerFila();
		int columna = origen.obtenerColumna();
		ultimaCelda = jugada.consultarOrigen();
		sentido = calcularSentido(jugada);
		if (sentido != null) {
			despFila = sentido.obtenerDesplazamientoFila();
			despColum = sentido.obtenerDesplazamientoColumna();
		}
		while (sentido != null && tablero.estaEnTablero(fila + despFila, columna + despColum)
				&& tablero.obtenerCelda(fila + despFila, columna + despColum).estaVacia()) {
			fila += despFila;
			columna += despColum;
			ultimaCelda = tablero.obtenerCelda(fila, columna);
		}
		return ultimaCelda;
	}

	/**
	 * Metodo privado que calcula la diferencia de columna en un movimiento.
	 * 
	 * Este metodo calcula la columna origen y destino de la jugada que le
	 * pases, resta destino menos origen, para saber cuantas columnas hay que
	 * desplazarse y hacia que sentido.
	 * 
	 * @param jugada
	 *            jugada de la partida
	 * 
	 * @return valor entero
	 */
	private int calcularDiferenciaColum(Jugada jugada) {
		int columOrigen = 0;
		columOrigen = jugada.consultarOrigen().obtenerColumna();
		int columDestino = 0;
		columDestino = jugada.consultarDestino().obtenerColumna();
		int diferenciaColum = 0;
		diferenciaColum = columDestino - columOrigen;
		return diferenciaColum;
	}

	/**
	 * Metodo que calcula la diferencia de fila en un movimiento.
	 * 
	 * Este metodo calcula la filas origen y destino de la jugada que le pases,
	 * resta destino menos origen, para saber cuantas filas hay que desplazarse
	 * y hacia que sentido.
	 * 
	 * @param jugada
	 *            jugada de la partida
	 * @return valor entero
	 */
	private int calcularDiferenciaFila(Jugada jugada) {
		int filaOrigen = 0;
		filaOrigen = jugada.consultarOrigen().obtenerFila();
		int filaDestino = 0;
		filaDestino = jugada.consultarDestino().obtenerFila();
		int diferenciaFila = 0;
		diferenciaFila = filaDestino - filaOrigen;
		return diferenciaFila;
	}

	/**
	 * Metodo que calcula el sentido.
	 * 
	 * Usando las funciones calcularDiferenciaFila() y calcularDiferenciaColum()
	 * y comparando sus resultados, obtengo cual es el sentido de la jugada.
	 * 
	 * @param jugada
	 *            jugada de la partida
	 * @return Sentido
	 */
	protected Sentido calcularSentido(Jugada jugada) {
		Sentido sentido = null;
		if ((calcularDiferenciaFila(jugada) * (-1)) == calcularDiferenciaColum(jugada)
				&& calcularDiferenciaFila(jugada) < 0 && calcularDiferenciaColum(jugada) > 0) { // -1,1
			sentido = Sentido.DIAGONAL_SO_NE_ARRIBA;
		} else if ((calcularDiferenciaFila(jugada)) == (calcularDiferenciaColum(jugada) * (-1))
				&& calcularDiferenciaFila(jugada) > 0 && calcularDiferenciaColum(jugada) < 0) { // 1,-1
			sentido = Sentido.DIAGONAL_SO_NE_ABAJO;
		} else if ((calcularDiferenciaFila(jugada)) == calcularDiferenciaColum(jugada)
				&& calcularDiferenciaFila(jugada) < 0 && calcularDiferenciaColum(jugada) < 0) { // -1,-1
			sentido = Sentido.DIAGONAL_NO_SE_ARRIBA;
		} else if ((calcularDiferenciaFila(jugada)) == calcularDiferenciaColum(jugada)
				&& calcularDiferenciaFila(jugada) > 0 && calcularDiferenciaColum(jugada) > 0) { // 1,1
			sentido = Sentido.DIAGONAL_NO_SE_ABAJO;
		} else if (calcularDiferenciaFila(jugada) > 0 && calcularDiferenciaColum(jugada) == 0) { // ABAJO
			sentido = Sentido.ABAJO;
		} else if (calcularDiferenciaFila(jugada) < 0 && calcularDiferenciaColum(jugada) == 0) { // ARRIBA
			sentido = Sentido.ARRIBA;
		} else if (calcularDiferenciaFila(jugada) == 0 && calcularDiferenciaColum(jugada) < 0) { // IZQ
			sentido = Sentido.IZQUIERDA;
		} else if (calcularDiferenciaFila(jugada) == 0 && calcularDiferenciaColum(jugada) > 0) {// DERECHA
			sentido = Sentido.DERECHA;
		}
		return sentido;

	}

	/**
	 * Metodo que determina la legalidad de una jugada.
	 * 
	 * Comprueba si una jugada es legal utilizando la celda origen y destino de
	 * la jugada. Si la celda destino de jugada es igual a la la variable
	 * ultimaCelda que calculamos en ultimaCelda() y origen contiene pieza y
	 * destino esta vacia podremos afirmar que la jugada es legal.
	 * 
	 * @param jugada
	 *            jugada a comprobar
	 * @return valor boolean
	 */
	public boolean esMovimientoLegal(Jugada jugada) throws CeldasFueraTableroException {
		Celda origen = null;
		Celda destino = null;
		destino = jugada.consultarDestino();
		origen = jugada.consultarOrigen();
		if (!origen.estaVacia() && destino.estaVacia()) {
			if (destino == ultimaCelda(jugada)) {
				if (tablero.estaEnTablero(origen.obtenerFila(), origen.obtenerColumna()) == false) {
					throw new CeldasFueraTableroException();
				}
				if (tablero.estaEnTablero(destino.obtenerFila(), destino.obtenerColumna()) == false) {
					throw new CeldasFueraTableroException();
				}
				if (consultarTurno().estaMoviendoNeutron()
						&& jugada.consultarOrigen().obtenerPieza().obtenerColor() == Color.AMARILLO) {
					return true;
				}
				if (!consultarTurno().estaMoviendoNeutron() && jugada.consultarOrigen().obtenerPieza()
						.obtenerColor() == turno.obtenerJugadorConTurno().obtenerColor()) {
					return true;
				}
				if (!consultarTurno().estaMoviendoNeutron() && jugada.consultarOrigen().obtenerPieza()
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
	 * calcula la celdaPosibleDestino dandole una celda de origen y un sentido.
	 *
	 * @param origen
	 * @param sentido
	 * @return celdaDestinoPosible
	 */
	private Celda calcularCeldaPosibleDestino(Celda origen, Sentido sentido) {
		Celda celdaDestinoPosible = null;
		int despFila = 0, despColum = 0;
		int fila = origen.obtenerFila();
		int columna = origen.obtenerColumna();
		for (int i = 0; i < 8; i++) {
			if (sentido != null) {
				despFila = sentido.obtenerDesplazamientoFila();
				despColum = sentido.obtenerDesplazamientoColumna();
				while (sentido != null && tablero.estaEnTablero(fila + despFila, columna + despColum)
						&& tablero.obtenerCelda(fila + despFila, columna + despColum).estaVacia()
						&& tablero.obtenerCelda(fila + despFila, columna + despColum) != tablero.obtenerCelda(fila,
								columna)) {
					fila += despFila;
					columna += despColum;
					celdaDestinoPosible = tablero.obtenerCelda(fila, columna);
				}
			}
		}
		return celdaDestinoPosible;
	}

	/**
	 * Este metodo sirve para calcular las celdas a las que se puede mover una
	 * ficha de forma automatica. Ayuda al entorno gráfico a colorear los
	 * posibles destinos
	 * 
	 * @param origen
	 *            origen de la jugada
	 * @return lista de destinos posibles a los que una pieza puede mover
	 * 
	 */
	public List<Celda> obtenerCeldasValidas(Celda origen) {
		List<Celda> listaDestinosPosibles = new ArrayList<Celda>();
		Sentido listaSentidos[] = Sentido.values();
		for (int i = 0; i < 8; i++) {
			if (calcularCeldaPosibleDestino(origen, listaSentidos[i]) != null) {
				listaDestinosPosibles.add(calcularCeldaPosibleDestino(origen, listaSentidos[i]));
			}
		}
		return listaDestinosPosibles;
	}

}// ArbitroNeutron
