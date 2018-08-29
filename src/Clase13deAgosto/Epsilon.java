/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase13deAgosto;

/**
 *
 * @author karen
 */
public class Epsilon {
    
    public static void main(String[] args) {
        double epsilon = 1.0;
        boolean v=true;
        
        while(v==true){
            epsilon = epsilon/2;
            if(epsilon+1<1){
                v=false;
            }
        }
        System.out.println(epsilon);
    }
    
}
