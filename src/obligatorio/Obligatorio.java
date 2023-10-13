
package obligatorio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Obligatorio {
    public String[][] tablero;
    public ArrayList<int[]> movimientos;
    
    public static String [][] generoTableroRandom() throws FileNotFoundException {
        String[] posibles = {"-", "/" , "\\", "|"};
        String[] posiblesColores = {"A", "R"};
        int filas = pedirFilas();
        int columnas = pedirColumnas();
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
                int randomNum1 = (int) (Math.random() * (filas)) + 1;
                int randomNum2 = (int) (Math.random() * (col)) + 1; 
                movimiento[0] = randomNum1;
                movimiento[1] = randomNum2;
            
                for(int j = 0; j < muestro.size(); j++){
                    int[] elem = muestro.get(j);
                    if(elem[0] == randomNum1 && elem[1] == randomNum2){
                        esta = true;
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
        int empiezoX = x;
        int empiezoY = y;
        if(!(x == 0 || y == 0)){
            boolean sigo = true;
            while(sigo){
                empiezoX --;
                empiezoY --;
                if(empiezoX == 0 || empiezoY == 0){
                    sigo = false;
                }
            }
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
        System.out.println("diagonal izquierda");
        String[][] matRetorno = new String[mat.length][mat[0].length];
        int empiezoX = x;
        int empiezoY = y;
        if(!(x == 0 || y == mat[0].length -1)){
            boolean sigo = true;
            while(sigo){
                empiezoX --;
                empiezoY ++;
                System.out.println(empiezoX);
                System.out.println(empiezoY);
                if(empiezoX == 0 || empiezoY == mat[0].length -1){
                    sigo = false;
                }
            }
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
        int x = coord[0] -1;
        int y = coord[1] -1;
        
        System.out.println("x: " +x);
        System.out.println("y: "+y);
        
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
    
    public static int pedirEntero(){
        Scanner in = new Scanner(System.in);
        String dato = "";
        while (!esNumero(dato)){
            
            dato=in.nextLine();
            if(!esNumero(dato)){
                System.out.println("Ingrese un numero");
            }
        }
        int entero=Integer.parseInt(dato);
        return entero; 
    }
    
    public static int pedirNivel(){
        System.out.println("Ingrese nivel en el que deseájugar");
        int nivel= pedirEntero();
        return nivel; 
    }
    
    public static int pedirFilas(){
        System.out.println("Ingrese numero de filas");
        int filas= pedirEntero();
        return filas; 
    }
    
    public static int pedirColumnas(){
        System.out.println("Ingrese numero de columnas");
        int columnas= pedirEntero();
        return columnas; 
    }
    
    public static boolean inicioJuego(){
        Scanner in = new Scanner(System.in);
        boolean muestro = true;
        boolean datoBien=false;
        while(!datoBien){
            System.out.println("Desea jugar");
            String deseo = in.nextLine();
        if(deseo.equalsIgnoreCase("No")){
            datoBien=true;
            muestro = false;
        }else{
            if(deseo.equalsIgnoreCase("Si")){
                datoBien=true;
                muestro=true;
            }else{
                datoBien=false;
            }
        }
        }
        return muestro;
    }
    
    public static void imprimirMasYGuiones(String[][] matImprimir){
        for(int h=0;h<matImprimir[0].length;h++){
                    System.out.print("+---");
                    if(h==matImprimir[0].length-1){
                        System.out.print("+");
                    }
                }
    }
    
    public static void imprimir(String[][] matImprimir){
        
        for(int i=0;i<matImprimir[0].length;i++){
            if(i==0){
                System.out.print("    "+(i+1));
            }else{
                System.out.print("   "+(i+1));
            }
            
        }
        System.out.println("");

        for(int i=0;i<matImprimir.length;i++){
            System.out.print("  ");
                imprimirMasYGuiones(matImprimir);
                System.out.println("");
                System.out.print(i+1 + " |");
                for(int j=0; j< matImprimir[0].length;j++){
                    String datoActual=matImprimir[i][j];
                    String segundoCaracter=datoActual.charAt(1)+"";
                    if("R".equals(segundoCaracter)){
                        System.out.print("\u001B[31m");
                        System.out.print(" "+datoActual.charAt(0));
                        System.out.print("\u001B[0m");
                    }else{
                        System.out.print("\u001B[34m");
                        System.out.print(" "+datoActual.charAt(0));
                        System.out.print("\u001B[0m");
                    }
                    System.out.print(" |");
                }
                System.out.println("");
        }
        System.out.print("  ");
        imprimirMasYGuiones(matImprimir); 
        System.out.println("");
    }
    
    public static void imprimirCompuesto(String[][] matImprimir, String[][] matImprimir2){
        
        for (int h=0; h<2; h++){
            for(int i=0;i<matImprimir[0].length;i++){
                if(i==0){
                    System.out.print("    "+(i+1));
                }else{
                    System.out.print("   "+(i+1));
                }
            }
            for(int a=0;a<9;a++){
            System.out.print(" ");
            }
        }
        System.out.println("");

        for(int i=0;i<matImprimir.length;i++){
            System.out.print("  ");
                imprimirMasYGuiones(matImprimir);
                System.out.print("  ==>    ");
                imprimirMasYGuiones(matImprimir2);
                System.out.println("");
                System.out.print(i+1 + " |");
                for(int j=0; j< matImprimir[0].length;j++){
                    String datoActual=matImprimir[i][j];
                    String segundoCaracter=datoActual.charAt(1)+"";
                    if("R".equals(segundoCaracter)){
                        System.out.print("\u001B[31m");
                        System.out.print(" "+datoActual.charAt(0));
                        System.out.print("\u001B[0m");
                    }else{
                        System.out.print("\u001B[34m");
                        System.out.print(" "+datoActual.charAt(0));
                        System.out.print("\u001B[0m");
                    }
                    System.out.print(" |");
                }
                System.out.print("  ==>  ");
                System.out.print(i+1 + " |");
                for(int j2=0; j2< matImprimir2[0].length;j2++){
                    String datoActual=matImprimir2[i][j2];
                    String segundoCaracter=datoActual.charAt(1)+"";
                    if("R".equals(segundoCaracter)){
                        System.out.print("\u001B[31m");
                        System.out.print(" "+datoActual.charAt(0));
                        System.out.print("\u001B[0m");
                    }else{
                        System.out.print("\u001B[34m");
                        System.out.print(" "+datoActual.charAt(0));
                        System.out.print("\u001B[0m");
                    }
                    System.out.print(" |");
                }
                System.out.println("");
        }
        System.out.print("  ");
        imprimirMasYGuiones(matImprimir);
        System.out.print("  ==>    ");
        imprimirMasYGuiones(matImprimir2);
        System.out.println("");
    }
    
    public static String tableroJugar(){
        Scanner in = new Scanner(System.in);
        System.out.println("¿De que manera desea jugar?");
        System.out.println("a) Tablero externo");
        System.out.println("b) Tablero predefinido");
        System.out.println("c) Tablero al azar");
        boolean bool = false;
        String muestro = "";
        while(bool == false){
            String deseo = in.nextLine();
            if(deseo.equalsIgnoreCase("a") || deseo.equalsIgnoreCase("b") || deseo.equalsIgnoreCase("c")){
                muestro = deseo;
                bool = true;
            }else{
                System.out.println("Ingrese opcion a, b o c");
            }
        }
        return muestro;
    }
    
    public static String preguntarjuego(int filas, int columnas){
        Scanner in = new Scanner(System.in);
        String si="12";
        String dato="";
        while(!verificarQueVaHacer(si, filas, columnas)){
        System.out.println("Para mostrar todos los pasos realizados ingrese H");
        System.out.println("Para mostrar los pasos para resolver el tablero ingrese S");
        System.out.println("Para terminar el juego ingrese X");
        System.out.println("Para retroceder un paso ingrese -1 y a continuacion otro -1");
        System.out.println("Para hacer un movimiento ingrese la primera coordenada");
        si=in.nextLine();
        dato=si.toUpperCase();
        if(!verificarQueVaHacer(si, filas, columnas)){
            System.out.println("Ingrese una opcion valida");
            System.out.println("");
        }
        }
        return dato;
    }
    
    public static int[] pedirCordenadas(String cordenada1, int filas, int columnas){
        int cord1=Integer.parseInt(cordenada1);
        int cord2=10;
        while(cord2 > filas){
            System.out.println("Ingrese cordenada 2");
            cord2=pedirEntero();
        }
        int[] cordenadas={cord1, cord2};
        return cordenadas;
    }
    
    public static void mostrarPasosHechos(ArrayList<int[]> movimientos, int nivel){
        if(movimientos.size()>nivel){
            for(int j=nivel;j<movimientos.size();j++){
                int[]elem = movimientos.get(j);
                for(int i=0; i<elem.length; i++){
                    System.out.print(elem[i] + " ");
                }
                System.out.println("");
            }
        }
    }
    
    public static void mostrarParaTerminar(ArrayList<int[]> movimientos){
        for(int j=movimientos.size()-1;j>=0;j--){
            int[]elem = movimientos.get(j);
            for(int i=0; i<elem.length; i++){
                System.out.print(elem[i] + " ");
            }
            System.out.println("");
        }
    }
    
    public static boolean verificarQueVaHacer (String entrada, int filas, int columnas){
        boolean verificado=false;
        entrada = entrada.toUpperCase();
        if ((entrada.equals("-1") || entrada.equals("X") || entrada.equals("S") || entrada.equals("H") ||
                esNumero(entrada))&& entrada.length()==1) {
            verificado=true;
        }
        return verificado;
    }
    
    public static boolean esNumero(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
    

