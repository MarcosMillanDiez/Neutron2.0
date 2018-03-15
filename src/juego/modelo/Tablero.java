package juego.modelo;

import java.util.ArrayList;
import juego.util.CoordenadasIncorrectasException;

/**
 * 
 * Clase que construye el tablero del juego.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 2.0 25112015
 */
public class Tablero {
	/**
	 * Atributo listaCeldas de tipo List.
	 */
	private ArrayList<ArrayList<Celda>> listaTablero;

	/**
	 * Atributo celda de tipo Array unidimensional
	 */
	private Celda[] celdasElec;

	/**
	 * Constructor de las clase Tablero al que le llega un numero de filas y de
	 * columnas e inicializa el tablero.
	 * 
	 * @param filas
	 * @param columnas
	 */
	public Tablero(int filas, int columnas) {
		listaTablero = new ArrayList<ArrayList<Celda>>(filas);
		for (int i = 0; i < filas; i++) {
			ArrayList<Celda> tablerocolumnas = new ArrayList<Celda>();
			for (int j = 0; j < columnas; j++) {
				tablerocolumnas.add(j, new Celda(i, j));
			}
			listaTablero.add(i, tablerocolumnas);
		}
	}

	/**
	 * Metodo que coloca una pieza en una determinada celda del tablero.
	 * 
	 * @param pieza
	 *            pieza a colocar
	 * @param celda
	 *            celda donde ira la pieza
	 */
	public void colocar(Pieza pieza, Celda celda) throws CoordenadasIncorrectasException {
		if (this.estaEnTablero(celda.obtenerFila(), celda.obtenerColumna())) {
			pieza.colocar(celda);
			celda.establecerPieza(pieza);

		} else {
			throw new CoordenadasIncorrectasException();

		}
	}

	/**
	 * Metodo que dada una jugada, realiza un movimiento.
	 * 
	 * @param jugada
	 *            origen y destino de la jugada
	 */
	public void mover(Jugada jugada) {
		Celda origen = jugada.consultarOrigen();
		Celda destino = jugada.consultarDestino();
		moverPieza(origen, destino);
	}

	/**
	 * Metodo privado que recibe el origen y comprueba si esta no es null, sino
	 * es null establece la pieza en el origen.
	 * 
	 * @param origen
	 *            celda origen
	 * @param destino
	 *            celda destino
	 */
	private void moverPieza(Celda origen, Celda destino) {
		if (origen.obtenerPieza() == null) {
		} else {
			destino.establecerPieza(origen.obtenerPieza());
		}
		origen.establecerPieza(null);
	}

	/**
	 * Obtiene un celda de un tablero dados su fila y su columna.
	 * 
	 * @param fila
	 *            fila
	 * @param columna
	 *            columna
	 * @return tablero[fila][columna] celda del tablero solicitada
	 */
	public Celda obtenerCelda(int fila, int columna) {
		Celda celdaC = listaTablero.get(fila).get(columna);
		if (celdaC != null) {
			return listaTablero.get(fila).get(columna);
		} else
			return null;
	}

	/**
	 * Metodo booleano que dadas una fila y una columna devuelve true si éstas
	 * pertenecen al tablero. En cualquier otro caso devuelve false.
	 * 
	 * @param fila
	 *            fila a comprobar
	 * @param columna
	 *            columna a comprobar
	 * @return valor booleano
	 */
	public boolean estaEnTablero(int fila, int columna) {
		return (fila >= 0 && fila < obtenerNumeroFilas() && columna >= 0 && columna < obtenerNumeroColumnas());
	}

	/**
	 * Metodo que devuelve el numero de filas.
	 * 
	 * @return filas
	 */
	public int obtenerNumeroFilas() {
		return listaTablero.size();
	}

	/**
	 * Metodo que devuelve el numero de columnas.
	 * 
	 * @return columnas
	 */
	public int obtenerNumeroColumnas() {
		return listaTablero.get(0).size();
	}

	/**
	 * Metodo toString que evuelve el tablero en modo cadena.
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < this.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < this.obtenerNumeroColumnas(); j++) {
				if (obtenerCelda(i, j).estaVacia()) {
					str += "-";
				} else
					str += obtenerCelda(i, j).obtenerPieza().obtenerColor().toChar();
			}
			str += "\n";
		}
		return str;
	}

	/**
	 * Metodo que devuelve el conjunto array de celdas de donde se encuentran
	 * nuestras piezas (electrones).
	 * 
	 * @param color
	 *            color
	 * 
	 * @return Celda
	 */
	public Celda[] obtenerCeldasElectron(Color color) {
		celdasElec = new Celda[this.obtenerNumeroFilas()];
		int z = 0;
		for (int i = 0; i < this.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < this.obtenerNumeroColumnas(); j++) {
				if (obtenerCelda(i, j).obtenerPieza().esNeutron() == false && obtenerCelda(i, j).estaVacia() == false) {
					celdasElec[z] = (this.obtenerCelda(i, j));
					z++;
				}
			}
		}
		return celdasElec;
	}

	/**
	 * Metodo que devuelve la celda(unica) donde esta el neutron colocado.
	 * 
	 * @return Celda celda donde se encuentra el neutron
	 */
	public Celda obtenerCeldaNeutron() {
		for (int i = 0; i < this.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < this.obtenerNumeroColumnas(); j++) {
				if (!obtenerCelda(i, j).estaVacia() && obtenerCelda(i, j).obtenerPieza().esNeutron()) {
					return obtenerCelda(i, j);
				}
			}
		}
		return null;
	}
}// Tablero
