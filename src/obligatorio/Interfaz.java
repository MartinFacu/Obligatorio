/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    
}