/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Obligatorio {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> movimientos = new ArrayList<String>();
        boolean inicio=(Interfaz.inicioJuego());
        if(!inicio){

        }else{
            if(Interfaz.tableroElegido()){
                Interfaz.imprimir(LeoTablero1());
            }else{
                Interfaz.imprimir(generoTableroRandom()); 
            }
        }
        boolean deseo=true;
        while(deseo){
            String si= Interfaz.preguntarjuego();
            if("X".equals(si)){
                System.out.println("Juego Terminado");
                deseo=false;
            }else{
                if("H".equals(si)){
                    //mostrar lista movimientos
                }else{
                    if("S".equals(si)){
                        //mostrar solucion
                    }else{
                        if("-1".equals(si)){
                            // ir para atras
                        }else{
                            int[]movimiento=Interfaz.pedirCordenadas(si);
                            System.out.println("1: "+movimiento[0] + " 2: "+movimiento[1]);
                        }
                    }
                }
            }
        }
    }
    
    public static String [][] generoTableroRandom() throws FileNotFoundException {
        String[] posibles = {"-", "/" , "\\", "|"};
        Scanner in = new Scanner(System.in);
        int filas = in.nextInt();
        in.nextLine();
        int columnas = in.nextInt();
        in.nextLine();
        // Inicializar la matriz
        String[][] tableroRandom = new String[filas][columnas];

        // Leer y almacenar los datos en la matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tableroRandom[i][j] = posibles[(int) (Math.random() * 4)];
            }
        }
        return tableroRandom;
    }
    
    public static String [][] LeoTablero1() throws FileNotFoundException {
           // Crear un Scanner para leer el archivo
        Scanner input = new Scanner(new File(".\\Test\\datos.txt"));

        // Leer la primera línea para obtener las dimensiones
        String tamanioMatriz = input.nextLine();
        String[] arrayDimensionesMatriz = tamanioMatriz.split(" ");
        int filas = Integer.parseInt(arrayDimensionesMatriz[0]);
        int columnas = Integer.parseInt(arrayDimensionesMatriz[1]);

        // Inicializar la matriz
        String[][] tablero1 = new String[filas][columnas];

        // Leer y almacenar los datos en la matriz
        for (int i = 0; i < filas; i++) {
            String linea = input.nextLine();
            String[] lugaresCompuestos = linea.split(" ");
            for (int j = 0; j < columnas; j++) {
                tablero1[i][j] = lugaresCompuestos[j];
            }
        }

        // Cerrar el Scanner
        input.close(); 
        return tablero1;
    }
    
}
