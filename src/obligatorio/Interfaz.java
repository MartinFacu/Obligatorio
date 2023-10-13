package obligatorio;
import java.util.*;
import java.io.*;

public class Interfaz {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Obligatorio tablero1;
        tablero1 = new Obligatorio();
        
        tablero1.tablero = Obligatorio.usoTableroPredeterminado();
        tablero1.movimientosAGanar = new ArrayList<>();
        tablero1.movimientosHechos = new ArrayList<>();
        //String[][]tablero= Obligatorio.usoTableroPredeterminado();
        //ArrayList<int[]> movimientos = new ArrayList<>();
        boolean inicio=(Obligatorio.inicioJuego());
        if(!inicio){

        }else{
            
            String opcion=Obligatorio.tableroJugar();
            if(opcion.equalsIgnoreCase("a")){
            tablero1.tablero = Obligatorio.LeoTableroDatosTxt(tablero1.movimientosAGanar); // Pasa el ArrayList como argumento
                Obligatorio.imprimir(tablero1.tablero);
                
            }else{
                if(opcion.equalsIgnoreCase("b")){
                    Obligatorio.imprimir(tablero1.tablero);
                    int[] movimiento3 = {4,4};
                    int[] movimiento2 = {5,6};
                    int[] movimiento1 = {5,4};
                    tablero1.movimientosAGanar.add(movimiento1);
                    tablero1.movimientosAGanar.add(movimiento2);
                    tablero1.movimientosAGanar.add(movimiento3);
                    int nivel=3;
                }else{
                    tablero1.tablero= Obligatorio.generoTableroRandom();
                    int nivel=Obligatorio.pedirNivel();
                    tablero1.movimientosAGanar = Obligatorio.creadorNivel(nivel,tablero1.tablero.length,tablero1.tablero[0].length);
                    for(int i = 0; i < tablero1.movimientosAGanar.size(); i++){
                        int[] mov = tablero1.movimientosAGanar.get(i);
                        tablero1.tablero = Obligatorio.llamarCambio(mov, tablero1.tablero);
                    }
                    Obligatorio.imprimir(tablero1.tablero); 
                }
                
            }
        
        boolean deseo=true;
        int nivel=tablero1.movimientosAGanar.size();
        while(deseo){
            int filas= tablero1.tablero.length;
            int columnas= tablero1.tablero[0].length;
            String si= Obligatorio.preguntarjuego(filas, columnas);
            if("X".equals(si)){
                System.out.println("Juego Terminado");
                deseo=false;
            }else{
                if("H".equals(si)){
                    Obligatorio.mostrarPasosHechos(tablero1.movimientosHechos, nivel);
                }else{
                    if("S".equals(si)){
                        Obligatorio.mostrarParaTerminar(tablero1.movimientosAGanar);
                    }else{
                        if("-1".equals(si)){
                            if(!(tablero1.movimientosHechos.isEmpty())&&tablero1.movimientosAGanar.size()>nivel){
                            int[] movimiento= tablero1.movimientosAGanar.get(tablero1.movimientosAGanar.size() - 1);
                            String [][] tableroModificado = new String[tablero1.tablero.length][];
                            for (int i = 0; i < tablero1.tablero.length; i++) {
                                tableroModificado[i] = tablero1.tablero[i].clone();
                            }
                            tablero1.tablero = Obligatorio.llamarCambio(movimiento, tablero1.tablero);
                            Obligatorio.imprimirCompuesto( tableroModificado, tablero1.tablero);
                            tablero1.movimientosAGanar=verificoYEliminoRepetido(tablero1.movimientosAGanar, movimiento);
                            tablero1.movimientosHechos.add(movimiento);
                            }else{
                                System.out.println("No se puede retroceder mas");
                            }
                        }else{
                            
                            int[]movimiento=Obligatorio.pedirCordenadas(si, filas, columnas);
                            tablero1.movimientosHechos.add(movimiento);
                            String [][] tableroModificado = new String[tablero1.tablero.length][];
                            for (int i = 0; i < tablero1.tablero.length; i++) {
                                tableroModificado[i] = tablero1.tablero[i].clone();
                            }
                            tablero1.movimientosAGanar=verificoYEliminoRepetido(tablero1.movimientosAGanar, movimiento);
                            System.out.println("1: "+movimiento[0] + " 2: "+movimiento[1]);
                            tablero1.tablero = Obligatorio.llamarCambio(movimiento, tablero1.tablero);
                            Obligatorio.imprimirCompuesto( tableroModificado, tablero1.tablero);
                            //tablero1.tablero=tableroModificado;
                            Obligatorio.imprimir(tablero1.tablero);
                            
                        }
                                
                                
                    }
                }
            }
        }
        }
    }
    public static ArrayList<int[]> verificoYEliminoRepetido(ArrayList<int[]> movimientos, int[] coords){
        if(Obligatorio.verificarIgualdadDelUltimoMovimiento(movimientos, coords)){
            movimientos = Obligatorio.eliminarUltimoMovimiento(movimientos);
        }else{
            movimientos.add(coords);
        }
        return movimientos;    
    }
}
