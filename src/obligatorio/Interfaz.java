
package obligatorio;
import java.util.*;

public class Interfaz {
    
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
        Scanner in = new Scanner(System.in);
        int cord1=Integer.parseInt(cordenada1);
        int cord2=in.nextInt();
        in.nextLine();
        int[] cordenadas={cord1, cord2};
        return cordenadas;
    }
    
}