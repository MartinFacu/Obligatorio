
package obligatorio;
import java.util.*;
import java.io.*;

public class Interfaz {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Obligatorio tablero1;
        tablero1 = new Obligatorio();
        
        tablero1.tablero = Obligatorio.usoTableroPredeterminado();
        tablero1.movimientos = new ArrayList<>();
        //String[][]tablero= Obligatorio.usoTableroPredeterminado();
        //ArrayList<int[]> movimientos = new ArrayList<>();
        boolean inicio=(Obligatorio.inicioJuego());
        if(!inicio){

        }else{
            
            String opcion=Obligatorio.tableroJugar();
            if(opcion.equalsIgnoreCase("a")){
            tablero1.tablero = Obligatorio.LeoTableroDatosTxt(tablero1.movimientos); // Pasa el ArrayList como argumento
                Obligatorio.imprimir(tablero1.tablero);
                
            }else{
                if(opcion.equalsIgnoreCase("b")){
                    Obligatorio.imprimir(tablero1.tablero);
                    int[] movimiento3 = {4,4};
                    int[] movimiento2 = {5,6};
                    int[] movimiento1 = {5,4};
                    tablero1.movimientos.add(movimiento1);
                    tablero1.movimientos.add(movimiento2);
                    tablero1.movimientos.add(movimiento3);
                }else{
                    tablero1.tablero= Obligatorio.generoTableroRandom();
                    int nivel=Obligatorio.pedirNivel();
                    tablero1.movimientos = Obligatorio.creadorNivel(nivel,tablero1.tablero.length,tablero1.tablero[0].length);
                    Obligatorio.imprimir(tablero1.tablero); 
                }
                
            }
        }
        boolean deseo=true;
        int nivel=tablero1.movimientos.size();
        while(deseo){
            String si= Obligatorio.preguntarjuego();
            if("X".equals(si)){
                System.out.println("Juego Terminado");
                deseo=false;
            }else{
                if("H".equals(si)){
                    Obligatorio.mostrarPasosHechos(tablero1.movimientos, nivel);
                    System.out.println("Entre H");
                }else{
                    if("S".equals(si)){
                        Obligatorio.mostrarParaTerminar(tablero1.movimientos);
                    }else{
                        if("-1".equals(si)){
                            // ir para atras
                        }else{
                            int[]movimiento=Obligatorio.pedirCordenadas(si);
                            String [][] tableroModificado = tablero1.tablero;
                            tablero1.movimientos.add(movimiento);
                            System.out.println("1: "+movimiento[0] + " 2: "+movimiento[1]);
                            tableroModificado = Obligatorio.llamarCambio(movimiento, tableroModificado);
                            Obligatorio.imprimirCompuesto(tablero1.tablero, tableroModificado);
                            tablero1.tablero=tableroModificado;
                            Obligatorio.imprimir(tablero1.tablero);
                        }
                                
                                
                    }
                }
            }
        }
    }

}
