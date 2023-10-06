
package obligatorio;
import java.util.*;

public class Interfaz {
    
    public static int pedirEntero(){
        Scanner in = new Scanner(System.in);
        int entero=in.nextInt();
        in.nextLine();
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
        System.out.println("Desea jugar");
        String deseo = in.nextLine();
        boolean muestro = true;
        if(deseo.equalsIgnoreCase("No")){
            muestro = false;
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
    
    public static String preguntarjuego(){
        Scanner in = new Scanner(System.in);
        System.out.println("Que desea hacer ahora?");
        String si=in.nextLine();
        String dato=si.toUpperCase();
        return dato;
    }
    
    public static int[] pedirCordenadas(String cordenada1){
        int cord1=Integer.parseInt(cordenada1);
        System.out.println("Ingrese cordenada 2");
        int cord2=pedirEntero();
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
        System.out.println("Entre pasos hechos");
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
}
