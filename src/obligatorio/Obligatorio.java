package obligatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Obligatorio {

    public String[][] tablero;
    public ArrayList<int[]> movimientosAGanar;
    public ArrayList<int[]> movimientosHechos;
    public ArrayList<int[]> movimientosParaRetroceder;

    public static String[][] generoTableroRandom() throws FileNotFoundException {
        String[] posibles = {"-", "/", "\\", "|"};
        String[] posiblesColores = {"A", "R"};
        int filas = pedirFilas();
        int columnas = pedirColumnas();
        String[][] tableroRandom = new String[filas][columnas];
        double colorElegido = Math.random() * 2;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tableroRandom[i][j] = posibles[(int) (Math.random() * 4)] + posiblesColores[(int) colorElegido];
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
        input.close();

        return tablero1;
    }

    //tablero predeterminado
    public static String[][] usoTableroPredeterminado() throws FileNotFoundException {
        String[][] tableroPredeterminado = {
            {"|A", "|A", "-R", "/A", "|R", "-R"},
            {"-R", "/A", "/A", "|A", "-R", "-R"},
            {"-R", "-R", "|A", "-R", "/R", "-R"},
            {"\\R", "-R", "|R", "\\R", "|A", "|R"},
            {"\\R", "/R", "/R", "|A", "/A", "\\A"}
        };
        return tableroPredeterminado;
    }

    public static ArrayList<int[]> creadorNivel(int nivel, int filas, int col) {
        ArrayList<int[]> muestro = new ArrayList<int[]>();
        for (int i = 0; i < nivel; i++) {
            boolean esta = true;
            while (esta) {
                esta = false;
                int[] movimiento = new int[2];
                int randomNum1 = (int) (Math.random() * (filas)) + 1;
                int randomNum2 = (int) (Math.random() * (col)) + 1;
                movimiento[0] = randomNum1;
                movimiento[1] = randomNum2;

                for (int j = 0; j < muestro.size(); j++) {
                    int[] elem = muestro.get(j);
                    if (elem[0] == randomNum1 && elem[1] == randomNum2) {
                        esta = true;
                    }
                }
                if (esta == false) {
                    muestro.add(movimiento);
                }
            }
        }

        return muestro;
    }

    public static String[][] cambioHorizontal(String[][] mat, int i) {
        String[][] matRetorno = new String[mat.length][mat[0].length];

        for (int a = 0; a < mat.length; a++) {
            for (int b = 0; b < mat[0].length; b++) {
                String datoActual = mat[a][b];
                String primerCaracter = datoActual.charAt(0) + "";
                String segundoCaracter = datoActual.charAt(1) + "";

                if (a == i) {
                    if ("R".equals(segundoCaracter)) {
                        matRetorno[a][b] = primerCaracter + "A";
                    } else {
                        matRetorno[a][b] = primerCaracter + "R";
                    }
                } else {
                    matRetorno[a][b] = datoActual;
                }
            }
        }

        return matRetorno;
    }

    //Cambio color |
    public static String[][] cambioVertical(String[][] mat, int j) {
        String[][] matRetorno = new String[mat.length][mat[0].length];

        for (int a = 0; a < mat.length; a++) {
            for (int b = 0; b < mat[0].length; b++) {
                String datoActual = mat[a][b];
                String primerCaracter = datoActual.charAt(0) + "";
                String segundoCaracter = datoActual.charAt(1) + "";

                if (b == j) {
                    if ("R".equals(segundoCaracter)) {
                        matRetorno[a][b] = primerCaracter + "A";
                    } else {
                        matRetorno[a][b] = primerCaracter + "R";
                    }
                } else {
                    matRetorno[a][b] = datoActual;
                }
            }
        }

        return matRetorno;
    }

    //Cambio color \
    public static String[][] cambioDiagonalDerecha(String[][] mat, int i, int j) {
        String[][] matRetorno = new String[mat.length][mat[0].length];
        int empiezoj = j;
        int empiezoi = i;
        if (!(j == 0 || i == 0)) {
            boolean sigo = true;
            while (sigo) {
                empiezoj--;
                empiezoi--;
                if (empiezoj == 0 || empiezoi == 0) {
                    sigo = false;
                }
            }
        }
        for (int a = 0; a < mat.length; a++) {
            for (int b = 0; b < mat[0].length; b++) {
                String datoActual = mat[a][b];
                String primerCaracter = datoActual.charAt(0) + "";
                String segundoCaracter = datoActual.charAt(1) + "";
                if (a == empiezoi && b == empiezoj) {
                    if ("R".equals(segundoCaracter)) {
                        matRetorno[a][b] = primerCaracter + "A";
                        empiezoi++;
                        empiezoj++;
                    } else {
                        matRetorno[a][b] = primerCaracter + "R";
                        empiezoj++;
                        empiezoi++;
                    }
                } else {
                    matRetorno[a][b] = datoActual;
                }
            }

        }

        return matRetorno;
    }

    //Cambio color /
    public static String[][] cambioDiagonalIzquierda(String[][] mat, int i, int j) {
        String[][] matRetorno = new String[mat.length][mat[0].length];
        int empiezoj = j;
        int empiezoi = i;
        if (!(i == 0 || j == mat[0].length)) {
            boolean sigo = true;
            while (sigo) {
                empiezoj++;
                empiezoi--;
                if (empiezoj == mat[0].length-1 || empiezoi == 0) {
                    sigo = false;
                }
            }
        }
        for (int a = 0; a < mat.length; a++) {
            for (int b = 0; b < mat[0].length; b++) {
                String datoActual = mat[a][b];
                String primerCaracter = datoActual.charAt(0) + "";
                String segundoCaracter = datoActual.charAt(1) + "";
                if (b == empiezoj && a == empiezoi) {
                    if ("R".equals(segundoCaracter)) {
                        matRetorno[a][b] = primerCaracter + "A";
                        empiezoi++;
                        empiezoj--;
                    } else {
                        matRetorno[a][b] = primerCaracter + "R";
                        empiezoi++;
                        empiezoj--; 
                    }
                } else {
                    matRetorno[a][b] = datoActual;
                }
            }

        }

        return matRetorno;
    }

    public static String[][] llamarCambio(int[] coord, String[][] mat) {
        
        int i = coord[0] - 1;
        int j = coord[1] - 1;
        String datoActual = mat[i][j];
        String primerCaracter = datoActual.charAt(0) + "";

        switch (primerCaracter) {
            case "-":
                mat = cambioHorizontal(mat, i);
                break;

            case "|":
                mat = cambioVertical(mat, j);
                break;

            case "\\":
                mat = cambioDiagonalDerecha(mat, i, j);
                break;

            case "/":
                mat = cambioDiagonalIzquierda(mat, i, j);
                break;
        }
        return mat;
    }

    public static int pedirEntero() {
        Scanner in = new Scanner(System.in);
        String dato = "";
        while (!esNumero(dato)) {

            dato = in.nextLine();
            if (!esNumero(dato)) {
                System.out.println("Ingrese un numero");
            }
        }
        int entero = Integer.parseInt(dato);
        return entero;
    }

    public static int pedirNivel() {
        int nivel =0;
        while (nivel<1 || nivel>8){
            System.out.println("Ingrese nivel en el que deseájugar (del 1 al 8)");
            nivel = pedirEntero();
        }
        return nivel;
    }

    public static int pedirFilas() {
        int filas =0;
        while (filas > 9 || filas < 3){
            System.out.println("Ingrese numero de filas (del 3 al 9)");
            filas = pedirEntero();
        }
        return filas;
    }

    public static int pedirColumnas() {
        int columnas =0;
        while (columnas > 9 || columnas < 3){
            System.out.println("Ingrese numero de columnas (del 3 al 9)");
            columnas = pedirEntero();
        }
        return columnas;
    }

    public static boolean verSiJuega() {
        Scanner in = new Scanner(System.in);
        boolean muestro = true;
        boolean datoBien = false;
        while (!datoBien) {
            String deseo = in.nextLine();
            if (deseo.equalsIgnoreCase("No")) {
                datoBien = true;
                muestro = false;
            } else {
                if (deseo.equalsIgnoreCase("Si")) {
                    datoBien = true;
                    muestro = true;
                } else {
                    System.out.println("Ingrese una de las opciones");
                    datoBien = false;
                }
            }
        }
        return muestro;
    }
 
    public static void imprimirMasYGuiones(String[][] matImprimir) {
        for (int h = 0; h < matImprimir[0].length; h++) {
            System.out.print("+---");
            if (h == matImprimir[0].length - 1) {
                System.out.print("+");
            }
        }
    }
    public static void imprimir(String[][] matImprimir) {
        for (int i = 0; i < matImprimir[0].length; i++) {
            if (i == 0) {
                System.out.print("    " + (i + 1));
            } else {
                System.out.print("   " + (i + 1));
            }
        }
        System.out.println("");
        for (int i = 0; i < matImprimir.length; i++) {
            System.out.print("  ");
            imprimirMasYGuiones(matImprimir);
            System.out.println("");
            System.out.print(i + 1 + " |");
            recorroFilaMatrizConColores(matImprimir, i);
            System.out.println("");
        }
        System.out.print("  ");
        imprimirMasYGuiones(matImprimir);
        System.out.println("");
    }
    public static void imprimirCompuesto(String[][] matImprimir, String[][] matImprimir2) {
        imprimoNumerosArriba(matImprimir);
        for (int i = 0; i < matImprimir.length; i++) {
            System.out.print("  ");
            imprimirMasYGuiones(matImprimir);
            System.out.print("  ==>    ");
            imprimirMasYGuiones(matImprimir2);
            System.out.println("");
            System.out.print(i + 1 + " |");
            recorroFilaMatrizConColores(matImprimir, i);
            System.out.print("  ==>  ");
            System.out.print(i + 1 + " |");
            recorroFilaMatrizConColores(matImprimir2, i);
            System.out.println("");
        }
        System.out.print("  ");
        imprimirMasYGuiones(matImprimir);
        System.out.print("  ==>    ");
        imprimirMasYGuiones(matImprimir2);
        System.out.println("");
    }
    public static String tableroJugar() {
        Scanner in = new Scanner(System.in);
        System.out.println("¿De que manera desea jugar?");
        System.out.println("a) Tablero externo");
        System.out.println("b) Tablero predefinido");
        System.out.println("c) Tablero al azar");
        boolean bool = false;
        String muestro = "";
        while (bool == false) {
            String deseo = in.nextLine();
            if (deseo.equalsIgnoreCase("a") || deseo.equalsIgnoreCase("b") || deseo.equalsIgnoreCase("c")) {
                muestro = deseo;
                bool = true;
            } else {
                System.out.println("Ingrese opcion a, b o c");
            }
        }
        return muestro;
    }
    public static String preguntarjuego(int filas, int columnas) {
        Scanner in = new Scanner(System.in);
        String si = "12";
        String dato = "";
        while (!verificarQueVaHacer(si, filas, columnas)) {
            System.out.println("Para mostrar todos los pasos realizados ingrese H");
            System.out.println("Para mostrar los pasos para resolver el tablero ingrese S");
            System.out.println("Para terminar esta partida ingrese X");
            System.out.println("Para retroceder un paso ingrese -1(con una vez ya retrocede)");
            System.out.println("Para hacer un movimiento ingrese la coordenada i (vertical)");
            si = in.nextLine();
            dato = si.toUpperCase();
            if (!verificarQueVaHacer(si, filas, columnas)) {
                System.out.println("Ingrese una opcion valida");
                System.out.println("");
            }
        }
        return dato;
    }
    public static int[] pedirCordenadas(String cordenada1, int filas, int columnas) {
        int cord1 = Integer.parseInt(cordenada1);
        while (cord1 > filas) {
            System.out.println("Ingrese cordenada i (vertical) nuveamenrte");
            cord1 = pedirEntero();
        }
        int cord2 = 10;
        while (cord2 > columnas) {
            System.out.println("Ingrese cordenada j (horizontal)");
            cord2 = pedirEntero();
        }
        int[] cordenadas = {cord1, cord2};
        return cordenadas;
    }
    public static void mostrarPasosHechos(ArrayList<int[]> movimientos, int nivel) {
        for (int j = 0; j < movimientos.size(); j++) {
            int[] elem = movimientos.get(j);
            for (int i = 0; i < elem.length; i++) {
                System.out.print(elem[i] + " ");
            }
            System.out.println("");
        }
    }
    public static void mostrarParaTerminar(ArrayList<int[]> movimientos) {
        for (int j = movimientos.size() - 1; j >= 0; j--) {
            int[] elem = movimientos.get(j);
            for (int i = 0; i < elem.length; i++) {
                System.out.print(elem[i] + " ");
            }
            System.out.println("");
        }
    }
    public static boolean verificarQueVaHacer(String entrada, int filas, int columnas) {
        boolean verificado = false;
        entrada = entrada.toUpperCase();
        if ((entrada.equals("X") || entrada.equals("S") || entrada.equals("H")
                || esNumero(entrada)) && (entrada.equals("-1") || entrada.length() == 1)) {
            verificado = true;
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
    public static ArrayList<int[]> eliminarUltimoMovimiento(ArrayList<int[]> movimientos) {
        movimientos.remove(movimientos.size() - 1);
        return movimientos;
    }
    public static boolean verificarIgualdadDelUltimoMovimiento(ArrayList<int[]> movimientos, int[] coords) {
        int[] ultiMov = movimientos.get(movimientos.size() - 1);
        boolean verificacion = false;
        if ((ultiMov[0] == coords[0]) && ultiMov[1] == coords[1]) {
            verificacion = true;
        }
        return verificacion;
    }
    public static boolean gano(String[][] mat) {
        String primerDato = mat[0][0];
        String colorPrimerDato = primerDato.charAt(1) + "";
        boolean verificacion = true;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                String datoActual = mat[i][j];
                String colorDatoActual = datoActual.charAt(1) + "";
                if (!(colorDatoActual.equals(colorPrimerDato))) {
                    verificacion = false;
                }
            }
        }
        if (verificacion) {
            System.out.println("Gano");
        }
        return verificacion;
    }

    public static String[][] rellenoTableroModificado(String[][] tableroModificado, String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            tableroModificado[i] = tablero[i].clone();
        }
        return tableroModificado;
    }

    public static void recorroFilaMatrizConColores(String[][] matriz, int i) {
        for (int j = 0; j < matriz[0].length; j++) {
            String datoActual = matriz[i][j];
            String segundoCaracter = datoActual.charAt(1) + "";
            if ("R".equals(segundoCaracter)) {
                System.out.print("\u001B[31m");
                System.out.print(" " + datoActual.charAt(0));
                System.out.print("\u001B[0m");
            } else {
                System.out.print("\u001B[34m");
                System.out.print(" " + datoActual.charAt(0));
                System.out.print("\u001B[0m");
            }
            System.out.print(" |");
        }
    }

    public static void imprimoNumerosArriba(String[][] matriz) {
        for (int h = 0; h < 2; h++) {
            for (int i = 0; i < matriz[0].length; i++) {
                if (i == 0) {
                    System.out.print("    " + (i + 1));
                } else {
                    System.out.print("   " + (i + 1));
                }
            }
            for (int a = 0; a < 9; a++) {
                System.out.print(" ");
            }
        }
        System.out.println("");
    }
}
