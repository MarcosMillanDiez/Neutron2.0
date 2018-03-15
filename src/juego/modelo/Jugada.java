package juego.modelo;
/**
 * Celdas que componen un jugada con un origen y un destino.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */
public class Jugada {
	
	/**
	 * Atributo origen de tipo Celda que representa el origen de la jugada.
	 */
	private Celda origen;
	/**
	 * Atributo destino de tipo Celda que representa el destino de la jugada.
	 */
	private Celda destino;
	
	/**
	 * Constructor de la clase Jugada.
	 * 
	 * @param origen
	 * 				celda origen de la jugada
	 * @param destino
	 * 				celda destino de la jugada
	 */
	public Jugada(Celda origen, Celda destino){
		this.origen=origen;
		this.destino=destino;
	}
	
	/**
	 * Devuelve el origen de la jugada.
	 * 
	 * @return	origen
	 */
	public Celda consultarOrigen(){
		return this.origen;
	}
	
	/**
	 * Devuelve el destino de la jugada.
	 * 
	 * @return	destino
	 */
	public Celda consultarDestino(){
		return this.destino;
	}
	
	/**
	 * Metodo toString.
	 */
	public String toString(){
		return "Celda origen: " + this.origen + " Celda destino: " + this.destino;
		
	}

}//Jugada
