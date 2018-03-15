package juego.util;
/**
 * Excepcion, las coordenadas introducidas no son validas.
 * <p>
 * 
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 1.0 25102015
 */
public class CoordenadasIncorrectasException extends Exception  {
	
	/**
	 * Constructor sin argumentos.
	 */
	public CoordenadasIncorrectasException(){
		super("¡Las coordenadas introducidas no son validas!");
	}
		
	/**
	 * Constructor con texto descriptivo.
	 * 
	 * @param mensaje texto descriptivo
	 */
	public CoordenadasIncorrectasException(String mensaje){
		super(mensaje);
	}
		
	/**
	 * Constructor con excepcion encadenada.
	 * 
	 * @param causa 
	 * 				Excepcion encadenada
	 */
	public CoordenadasIncorrectasException(Throwable causa){
		super(causa);
	}
			
	/**
	 * Constructor con texto descriptivo y excepcion encadenada.
	 * 
	 * @param mensaje 
	 * 				Texto descriptivo
	 * @param causa 
	 * 				Excepción encadenada
	 */
	public CoordenadasIncorrectasException(String mensaje, Throwable causa){
		super(mensaje, causa);
	}
}//CoordenadasIncorrectasException
