package juego.modelo;
/**
 * Enumeracion que define el color de las piezas del juego y el caracter 
 * que representan.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */
public enum Color {
	/**
	 * Color rojo.
	 */
	ROJO('O'),
	/**
	 * Color azul.
	 */
	AZUL('X'),
	/**
	 * Color Amarillo.
	 */
	AMARILLO('N');
	
	/**
	 * Caracter que representa el color.
	 */
	private char caracter;
	
	/**
	 * Constructor de la enumeracion Color.
	 * 
	 * @param caracter
	 * 					tipo char que representa el color de la pieza.
	 */
	private Color(char caracter){
		this.caracter=caracter;
	}
	
	/**
	 * Metodo que devuelve el caracter asignado al color.
	 * 
	 * @return caracter
	 * 					color de la pieza
	 */
	public char toChar(){
		return caracter;
	}

}//Color
