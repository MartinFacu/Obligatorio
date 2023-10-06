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
        Scanner in = new Scanner(System.in);
        String[][]tablero=usoTableroPredeterminado();
        ArrayList<int[]> movimientos = new ArrayList<int[]>();
        boolean inicio=(Interfaz.inicioJuego());
        if(!inicio){

        }else{
            String opcion=Interfaz.tableroJugar();
            if(opcion.equalsIgnoreCase("a")){
                tablero=LeoTableroDatosTxt();
                Interfaz.imprimir(tablero);
                int nivel=in.nextInt();
                in.nextLine();
                movimientos = creadorNivel(nivel,tablero.length,tablero[0].length);
            }else{
                if(opcion.equalsIgnoreCase("b")){
                    Interfaz.imprimir(tablero); 
                }else{
                    tablero=generoTableroRandom();
                    Interfaz.imprimir(tablero); 
                }
                
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
        String[] posiblesColores = {"A", "R"};
        Scanner in = new Scanner(System.in);
        int filas = Interfaz.pedirFilas();
        int columnas = Interfaz.pedirColumnas();
        String[][] tableroRandom = new String[filas][columnas];
        double colorElegido = Math.random() * 2;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tableroRandom[i][j] = posibles[(int) (Math.random() * 4)] + posiblesColores[(int)colorElegido];
            }
        }
        return tableroRandom;
    }
    
    public static String [][] LeoTableroDatosTxt() throws FileNotFoundException {
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
    
    //tablero predeterminado
    public static String [][] usoTableroPredeterminado() throws FileNotFoundException {
        String[][] tableroPredeterminado = {
            {"|R", "\\A", "|R", "\\R", "/R", "|R"},
            {"-R", "/R", "-A", "\\R", "\\R", "/R"},
            {"\\R", "\\R", "/R", "-A", "\\R", "-R"},
            {"-R", "-R", "\\R", "-R", "|A", "-R"},
            {"\\R", "/R", "|R", "-R", "/R", "|A"}
       };
        return tableroPredeterminado;
    }
    
    //se crean coordenadas para el nivel del tablero
    public static ArrayList<int[]> creadorNivel(int nivel, int filas, int col){
        ArrayList<int[]> muestro = new ArrayList<int[]>();
        for(int i = 0; i < nivel; i++){
            boolean esta = true;
            while(esta){
                esta = false;
                int[] movimiento = new int[2];
                int randomNum1 = (int) Math.random() * (filas);
                int randomNum2 = (int) Math.random() * (col); 
                movimiento[1] = randomNum1;
                movimiento[2] = randomNum2;
            
                for(int j = 0; j < muestro.size() && esta; j++){
                    int[] elem = muestro.get(j);
                    if(elem[1] == randomNum1 && elem[2] == randomNum2){
                        esta = true;
                    }else{
                        esta=false;
                    }
                }
                if(esta == false){
                    muestro.add(movimiento);
                }
        }
        }
        
        return muestro;
    }
    
    //Cambio color -
    public static String[][] cambioHorizontal(String[][] mat, int x){
        String[][] matRetorno = new String[mat.length][mat[0].length];
        
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                String datoActual=mat[i][j];
                String primerCaracter=datoActual.charAt(0)+"";
                String segundoCaracter=datoActual.charAt(1)+"";
                
                if(i == x){
                    if("R".equals(segundoCaracter)){
                        matRetorno[i][j] = primerCaracter + "A";
                    }else{
                        matRetorno[i][j] = primerCaracter + "R";
                    }
                }else{
                    matRetorno[i][j] = datoActual;
                }
            }
            }
        
            return matRetorno;
        }
    
    //Cambio color |
    public static String[][] cambioVertical(String[][] mat, int y){
        String[][] matRetorno = new String[mat.length][mat[0].length];
        
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                String datoActual=mat[i][j];
                String primerCaracter=datoActual.charAt(0)+"";
                String segundoCaracter=datoActual.charAt(1)+"";
                
                if(j == y){
                    if("R".equals(segundoCaracter)){
                        matRetorno[i][j] = primerCaracter + "A";
                    }else{
                        matRetorno[i][j] = primerCaracter + "R";
                    }
                }else{
                    matRetorno[i][j] = datoActual;
                }
            }
            }
        
            return matRetorno;
        }
    }
    

