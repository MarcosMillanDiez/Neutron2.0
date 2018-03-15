package juego.modelo;
import java.util.ArrayList; 
import java.util.List; 
  

/**
 * Configuracion de celdas de un jugador. Es el conjunto de celdas que
 * inicialmente estan ocupadas al inicio de la partida.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version .0 25102015
 */


public class Configuracion {
	/**
	 * Atributo lista que representa una lista de celdas
	 */
	private List<Celda> listaCeldasInicial; 

	/**
	 * Constructor con el que formamos configuracion inicial de la partida.
	 * 
	 */
	public Configuracion() {
		 listaCeldasInicial = new ArrayList<Celda>();

	}
	/**
	 * Añade una celda a la configuracion inicial.
	 * 
	 * @param celda
	 *            celda a añadir
	 */
	public void añadir(Celda celda) {
		listaCeldasInicial.add(celda); 		
	}

	/**
	 * Metodo que obtiene el conjunto de celdas que forman la configuración
	 * inicial.
	 * 
	 * @return listaCeldas
	 */
	public List<Celda> consultar() {
		return listaCeldasInicial;
	}

	/**
	 * Metodo que consulta si una celda esta contenida o no en la configuracion
	 * inicial.
	 * 
	 * @param celda
	 * @return boolean
	 */	
	public boolean contiene(Celda celda) {
		return listaCeldasInicial.contains(celda);
	}

	/**
	 * Metodo toString.
	 */
	public String toString() {
		return "";
	}

}// Configuracion
