
package obligatorio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Obligatorio {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String[][]tablero= usoTableroPredeterminado();
        ArrayList<int[]> movimientos = new ArrayList<>();
        boolean inicio=(Interfaz.inicioJuego());
        if(!inicio){

        }else{
            String opcion=Interfaz.tableroJugar();
            if(opcion.equalsIgnoreCase("a")){
            tablero = LeoTableroDatosTxt(movimientos); // Pasa el ArrayList como argumento
                Interfaz.imprimir(tablero);
                
            }else{
                if(opcion.equalsIgnoreCase("b")){
                    Interfaz.imprimir(tablero);
                    int[] movimiento3 = {4,4};
                    int[] movimiento2 = {5,6};
                    int[] movimiento1 = {5,4};
                    movimientos.add(movimiento1);
                    movimientos.add(movimiento2);
                    movimientos.add(movimiento3);
                }else{
                    tablero=generoTableroRandom();
                    int nivel=Interfaz.pedirNivel();
                    movimientos = creadorNivel(nivel,tablero.length,tablero[0].length);
                    Interfaz.imprimir(tablero); 
                }
                
            }
        }
        boolean deseo=true;
        int nivel=movimientos.size();
        while(deseo){
            String si= Interfaz.preguntarjuego();
            if("X".equals(si)){
                System.out.println("Juego Terminado");
                deseo=false;
            }else{
                if("H".equals(si)){
                    Interfaz.mostrarPasosHechos(movimientos, nivel);
                    System.out.println("Entre H");
                }else{
                    if("S".equals(si)){
                        Interfaz.mostrarParaTerminar(movimientos);
                    }else{
                        if("-1".equals(si)){
                            // ir para atras
                        }else{
                            int[]movimiento=Interfaz.pedirCordenadas(si);
                            movimientos.add(movimiento);
                            System.out.println("1: "+movimiento[0] + " 2: "+movimiento[1]);
                            llamarCambio(movimiento, tablero);
                        }
                    }
                }
            }
        }
    }
    
    public static String [][] generoTableroRandom() throws FileNotFoundException {
        String[] posibles = {"-", "/" , "\\", "|"};
        String[] posiblesColores = {"A", "R"};
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

    public static String[][] LeoTableroDatosTxt(ArrayList<int[]> movimientos) throws FileNotFoundException {
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

        // Leer el nivel y los movimientos
        int nivel = Integer.parseInt(input.nextLine());
        for (int i = 0; i < nivel; i++) {
            String movimientoLinea = input.nextLine();
            String[] movimientoString = movimientoLinea.split(" ");
            int[] movimiento = new int[movimientoString.length];
            for (int j = 0; j < movimientoString.length; j++) {
                movimiento[j] = Integer.parseInt(movimientoString[j]);
            }
            movimientos.add(movimiento);
        }

        // Cerrar el Scanner
        input.close();

        // Imprime el nivel y los movimientos
        System.out.println("Nivel: " + nivel);
        System.out.println("Movimientos: " + movimientos);

        return tablero1;
    }

    
    //tablero predeterminado
    public static  String [][] usoTableroPredeterminado() throws FileNotFoundException {
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
    
    //Cambio color \
    public static String[][] cambioDiagonalDerecha(String[][] mat, int x, int y){
        String[][] matRetorno = new String[mat.length][mat[0].length];
        int empiezoX;
        int empiezoY;
        if(y-x > -1){
            empiezoX = 0;
            empiezoY = y-x;
        }else{
            empiezoX = x;
            empiezoY = y;
        }
        
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                String datoActual=mat[i][j];
                String primerCaracter=datoActual.charAt(0)+"";
                String segundoCaracter=datoActual.charAt(1)+"";
                
                if(i == empiezoX && j == empiezoY){
                    if("R".equals(segundoCaracter)){
                        matRetorno[i][j] = primerCaracter + "A";
                    }else{
                        matRetorno[i][j] = primerCaracter + "R";
                    }
                }else{
                    matRetorno[i][j] = datoActual;
                }
            }
            empiezoX++;
            empiezoY++;
            }
        
            return matRetorno;
        }
    
    //Cambio color /
    public static String[][] cambioDiagonalIzquierda(String[][] mat, int x, int y){
        String[][] matRetorno = new String[mat.length][mat[0].length];
        int empiezoX;
        int empiezoY;
        if(y-x > -1){
            empiezoX = 0;
            empiezoY = y+x;
        }else{
            empiezoX = x;
            empiezoY = y;
        }
        
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                String datoActual=mat[i][j];
                String primerCaracter=datoActual.charAt(0)+"";
                String segundoCaracter=datoActual.charAt(1)+"";
                
                if(i == empiezoX && j == empiezoY){
                    if("R".equals(segundoCaracter)){
                        matRetorno[i][j] = primerCaracter + "A";
                    }else{
                        matRetorno[i][j] = primerCaracter + "R";
                    }
                }else{
                    matRetorno[i][j] = datoActual;
                }
            }
            empiezoX++;
            empiezoY--;
            }
            
        return matRetorno;
        }
    
    public static String[][] llamarCambio(int[] coord, String[][] mat){
        int x = coord[0];
        int y = coord[1];
        
        String datoActual = mat[x][y];
        String primerCaracter = datoActual.charAt(0)+"";
        
        switch (primerCaracter){
            case "-":
                mat = cambioHorizontal(mat, x);
                break;
            
            case "|":
                mat = cambioVertical(mat, y);
                break;
                
            case "\\":
                mat = cambioDiagonalDerecha(mat, x, y);
                break;
                
            case "/":
                mat = cambioDiagonalIzquierda(mat, x, y);
                break;
        }
        return mat;
    }
    }
    

