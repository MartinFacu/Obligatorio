/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio;
import java.util.*;
public class Obligatorio {

    public static void main(String[] args) {
        String[][] mat = new String[3][3];
        boolean inicio=(Interfaz.inicioJuego());
        if(!inicio){

        }else{
            imprimir(mat);
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
                for(int h=0;h<matImprimir[0].length;h++){
                    System.out.print("+---");
                    if(h==matImprimir[0].length-1){
                        System.out.print("+");
                    }
                }
                System.out.println("");
                System.out.print(i+1 + " | ");
                for(int j=0; j< matImprimir[0].length;j++){
                    System.out.print(matImprimir[i][j] + " | ");
                }
                System.out.println("");
        }
        
    }
    
}
