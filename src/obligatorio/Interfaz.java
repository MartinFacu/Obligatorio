package obligatorio;

import java.util.*;
import java.io.*;

public class Interfaz {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Obligatorio tablero1;
        tablero1 = new Obligatorio();
        System.out.println("Bienvenido a Soliflips");
        System.out.println("Desea jugar? (Si/No)");
        boolean inicio = (Obligatorio.verSiJuega());
        while (inicio) {
            tablero1.tablero = Obligatorio.usoTableroPredeterminado();
            tablero1.movimientosAGanar = new ArrayList<>();
            tablero1.movimientosHechos = new ArrayList<>();
            tablero1.movimientosParaRetroceder = new ArrayList<>();
            long tiempoInicioEnMilis = System.currentTimeMillis();
            String opcion = Obligatorio.tableroJugar();
            if (opcion.equalsIgnoreCase("a")) {
                tablero1.tablero = Obligatorio.LeoTableroDatosTxt(tablero1.movimientosAGanar); // Pasa el ArrayList como argumento
                Obligatorio.imprimir(tablero1.tablero);

            } else {
                if (opcion.equalsIgnoreCase("b")) {
                    int[] movimiento3 = {4, 4};
                    int[] movimiento2 = {5, 6};
                    int[] movimiento1 = {5, 4};
                    tablero1.movimientosAGanar.add(movimiento1);
                    tablero1.movimientosAGanar.add(movimiento2);
                    tablero1.movimientosAGanar.add(movimiento3);
                    Obligatorio.imprimir(tablero1.tablero);
                } else {
                    tablero1.tablero = Obligatorio.generoTableroRandom();
                    //Obligatorio.imprimir(tablero1.tablero);
                    int nivel = Obligatorio.pedirNivel();
                    tablero1.movimientosAGanar = Obligatorio.creadorNivel(nivel, tablero1.tablero.length, tablero1.tablero[0].length);
                    for (int i = 0; i < tablero1.movimientosAGanar.size(); i++) {
                        int[] mov = tablero1.movimientosAGanar.get(i);
                        System.out.println("mov0 :" + mov[0] + "mov1 :" + mov[1]);
                        tablero1.tablero = Obligatorio.llamarCambio(mov, tablero1.tablero);
                        //Obligatorio.imprimir(tablero1.tablero);
                        Obligatorio.mostrarParaTerminar(tablero1.movimientosAGanar);
                    }
                    Obligatorio.imprimir(tablero1.tablero);
                    
                }
            }

            boolean deseo = true;
            int nivel = tablero1.movimientosAGanar.size();
            while (deseo) {
                int filas = tablero1.tablero.length;
                int columnas = tablero1.tablero[0].length;
                String si = Obligatorio.preguntarjuego(filas, columnas);
                if ("X".equals(si)) {
                    System.out.println("Juego Terminado");
                    deseo = false;
                } else {
                    if ("H".equals(si)) {
                        if (!(tablero1.movimientosHechos.isEmpty())){
                            Obligatorio.mostrarPasosHechos(tablero1.movimientosHechos, nivel);
                        }else{
                            System.out.println("No hay pasos hechos");
                        }
                    } else {
                        if ("S".equals(si)) {
                            Obligatorio.mostrarParaTerminar(tablero1.movimientosAGanar);
                        } else {
                            if ("-1".equals(si)) {
                                if (!(tablero1.movimientosParaRetroceder.isEmpty())){
                                    int[] movimiento = tablero1.movimientosParaRetroceder.get(tablero1.movimientosParaRetroceder.size() - 1);
                                    tablero1.tablero = Obligatorio.llamarCambio(movimiento, tablero1.tablero);
                                    tablero1.movimientosParaRetroceder = Obligatorio.eliminarUltimoMovimiento(tablero1.movimientosParaRetroceder);
                                    tablero1.movimientosAGanar = verificoYEliminoRepetido(tablero1.movimientosAGanar, movimiento);
                                    tablero1.movimientosHechos.add(movimiento);
                                    Obligatorio.imprimir(tablero1.tablero);
                                } else {
                                    System.out.println("No se puede retroceder mas");
                                }
                            } else {

                                int[] movimiento = Obligatorio.pedirCordenadas(si, filas, columnas);
                                tablero1.movimientosHechos.add(movimiento);
                                tablero1.movimientosParaRetroceder.add(movimiento);
                                String[][] tableroModificado = new String[tablero1.tablero.length][];
                                tableroModificado = Obligatorio.rellenoTableroModificado(tableroModificado, tablero1.tablero);
                                tablero1.movimientosAGanar = verificoYEliminoRepetido(tablero1.movimientosAGanar, movimiento);
                                tablero1.tablero = Obligatorio.llamarCambio(movimiento, tablero1.tablero);
                                Obligatorio.imprimirCompuesto(tableroModificado, tablero1.tablero);
                                deseo = !(Obligatorio.gano(tablero1.tablero));
                            }
                        }
                    }
                }
            }
            long tiempoFinEnMilis = System.currentTimeMillis();
            System.out.println("Tiempo de juego : " + ((tiempoFinEnMilis - tiempoInicioEnMilis) / 1000) + "s");
            System.out.println("Desea jugar volver a jugar? (Si/No)");
            inicio = (Obligatorio.verSiJuega());
        }
        }

    public static ArrayList<int[]> verificoYEliminoRepetido(ArrayList<int[]> movimientos, int[] coords) {
        if (Obligatorio.verificarIgualdadDelUltimoMovimiento(movimientos, coords)) {
            movimientos = Obligatorio.eliminarUltimoMovimiento(movimientos);
        } else {
            movimientos.add(coords);
        }
        return movimientos;
    }
}
