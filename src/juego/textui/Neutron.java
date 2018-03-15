package juego.textui;

import juego.control.*;
import java.util.Scanner;
import juego.util.*;
import juego.modelo.*;

/**
 * Juego Neutron en modo texto.
 * <p>
 *
 * @author <A HREF="mailto:mmd0099@alu.ubu.es">Marcos Millan Diez</A>
 * @author <A HREF="mailto:aag0121@alu.ubu.es">Adrian Aguado Garcia</A>
 * @version 2.0 05122015
 */
public class Neutron {
	/**
	 * Constante simbólica NUMERO_ARGUMENTOS de tipo entero que representa el
	 * número de argumentos pasados al programa.
	 * 
	 */
	private static final int NUMERO_ARGUMENTOS = 4;

	/**
	 * Constante simbólica TAMAÑO_TABLERO de tipo entero que representa el
	 * tamaño del tablero por defecto.
	 */
	private static final int TAMAÑO_TABLERO = 5;

	/**
	 * Constante simbólica JUGADOR_NEGRO de tipo String que representa el nombre
	 * del jugador de fichas negras.
	 * 
	 */
	private static final String JUGADOR_ROJAS = "JugadorRojas";

	/**
	 * Constante simbólica JUGADOR_ROJO de tipo String que representa el nombre
	 * del jugador de fichas rojas.
	 * 
	 */
	private static final String JUGADOR_AZULES = "JugadorAzules";

	/**
	 * Funcion principal del juego.
	 * 
	 * @param args
	 *            argumentos pasados al programa.
	 */
	public static void main(String[] args) {
		String coordenadasJugada;
		Jugada jugada = null;
		int tamaño = 0;
		String nombre1 = null, nombre2 = null, tipoArbitro = null;
		Arbitro arbitro = null;
		Tablero tablero = null;
		Scanner scanner = new Scanner(System.in);

		if (args.length == NUMERO_ARGUMENTOS) {
			nombre1 = args[0]; // Nombre Jugador 1
			nombre2 = args[1]; // Nombre Jugador 2
			tamaño = Integer.parseInt(args[2]); // Tamaño
			tipoArbitro = args[3]; // Tipo arbitro que quiere el usuario
			if(tamaño != 5 && tamaño != 7 && tamaño != 9){
				System.out.println("Error. Vuelva ejecutar");
				System.exit(0);
			}

			// Switch para controlar el tipo de arbitro
			switch (tipoArbitro) {
			case "clasico":
				System.out.println("Cargando el tablero y jugadores (Juego clasico).\n");
				Tablero tablero1 = new Tablero(tamaño, tamaño);
				ArbitroNeutron arbitro1 = new ArbitroNeutron(tablero1, nombre2, nombre1);
				arbitro = arbitro1;
				tablero = tablero1;
				break;

			case "coneutron":
				System.out.println("Cargando el tablero y jugadores (Juego con neutron).\n");
				Tablero tablero2 = new Tablero(tamaño, tamaño);
				ArbitroCoNeutron arbitro2 = new ArbitroCoNeutron(tablero2, nombre2, nombre1);
				arbitro = arbitro2;
				tablero = tablero2;
				break;
			case "restrictivo":
				System.out.println("Cargando el tablero y jugadores (Juego restrictivo).\n");
				Tablero tablero3 = new Tablero(tamaño, tamaño);
				ArbitroNeutronRestrictivo arbitro3 = new ArbitroNeutronRestrictivo(tablero3, nombre2, nombre1);
				arbitro = arbitro3;
				tablero = tablero3;
				break;
			case "default":
				break;
			}

		} else if (args.length == 0) {
			System.out.println("Cargando el tablero y jugadores por defecto (Modo clasico).\n");
			Tablero tablero0 = new Tablero(TAMAÑO_TABLERO, TAMAÑO_TABLERO);
			ArbitroNeutron arbitro0 = new ArbitroNeutron(tablero0, JUGADOR_AZULES, JUGADOR_ROJAS);
			tablero = tablero0;
			arbitro = arbitro0;
		} else {
			Ayuda();
			System.exit(0);
		}

		// BUCLE PARA JUGAR
		do {
			mostrarTablero(tablero);
			// TURNO
			System.out.println("\nEl turno es de " + arbitro.consultarTurno().obtenerJugadorConTurno().consultarNombre()
					+ " con fichas " + arbitro.consultarTurno().obtenerJugadorConTurno().obtenerColor().toChar()
					+ " de color " + arbitro.consultarTurno().obtenerJugadorConTurno().obtenerColor().name());
			if (arbitro.consultarTurno().estaMoviendoNeutron()) {
				System.out.println("Debe mover el neutron.");
			} else {
				System.out.println("Debe mover los electrones.");
			}
			// INTRODUCCION DE JUGADAS
			try {
				do {
					System.out.println("Introduzca una  jugada:");
					coordenadasJugada = scanner.nextLine();
					while (!ConversorJugada.validar(coordenadasJugada, tamaño)) {
						System.out.println("Jugada incorrecta sintacticamente, introduce otra: ");
						coordenadasJugada = scanner.nextLine();
					}
					jugada = ConversorJugada.convertir(coordenadasJugada, tablero);
				} while (!arbitro.esMovimientoLegal(jugada));
			} catch (CeldasFueraTableroException e) {
				System.out.println("Celdas fuera del tablero.\n");
			}
			arbitro.jugar(jugada);

			// JUEGO TERMINADO
		} while (!arbitro.estaAcabado());
		if (arbitro.consultarGanador() != null)
			System.out.println("Partida acabada. El ganador es " + arbitro.consultarGanador().consultarNombre());
		if (scanner != null) {
			scanner.close();
		}
	}

	/**
	 * Funcion Ayuda, de tipo private que muestra la sintaxis de invocacion.
	 */
	private static void Ayuda() {
		System.out.println("Ejecucion incorrecta");
		System.out.println(
				"\tEjemplo de ejecucion: java juego.textui.Neutron <nombreJugador1> <nombreJugador2> <tamaño> <tipoArbitro> ");
		System.out.println("La variable tamaño debe de estar entre 5 y 9.");
		System.out.println("La variable arbitro debe ser clasico, coneutron o restrictivo.");
	}

	/**
	 * Metodo privado que muestra el tablero en pantalla.
	 * 
	 * @param tablero
	 *            tablero a mostrar
	 */
	private static void mostrarTablero(Tablero tablero) {
		int filas = tablero.obtenerNumeroFilas();
		int columnas = tablero.obtenerNumeroColumnas();
		for (int i = 0; i < filas; i++) {
			System.out.print("\t");
			System.out.print(i + "\t");
			for (int j = 0; j < columnas; j++) {
				if (tablero.obtenerCelda(i, j).obtenerPieza() == null)
					System.out.print('-');
				else {
					System.out.print(tablero.obtenerCelda(i, j).obtenerPieza().obtenerColor().toChar());
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("\t");
		System.out.print("\t");
		char caracter = 'a';
		for (int k = 0; k < columnas; k++) {
			System.out.print(caracter);
			caracter++;
		}
		System.out.println();
	}
}// Neutron
