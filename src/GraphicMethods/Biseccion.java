/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicMethods;

/**
 *
 * @author karen
 */
public class Biseccion {
    
    private double xl;
    private double xu;
    private double es; //Tolerancia o nivel aceptable 
    public Biseccion(double xl, double xu, double cifrasSig) {
        this.xl = xl;
        this.xu = xu;
        this.es = 0.5 * Math.pow(10.0, 2.0 - cifrasSig);            
       System.out.println("Tolerancia = "+this.es);
    }
    public void setEs(double es){
        this.es = es;
    }
    private double evaluar(double x){
        return Math.pow(x,10)-1;
    }
    
    public double ejecutarAlgoritmo(){
        /*
            Paso 1: Elegir valores iniciales inferior xl y superior xu que encierren la raíz,
            comprobando que la función cambie de signo en el intervalo. Esto se verifica comprobando que f(xl) f(xu) <0
        */
        double producto = this.evaluar(xl)*this.evaluar(xu);
        if(producto<0){
            double xr;
            double ea = 0; //error relativo porcentual aproximado
            double xrold = 0.0;
            int iteracion=0;
            boolean avanzar = true;
            double fxl=0.0;
            double fxr = 0.0;
       
           do{
               System.out.println("Iteracion: "+iteracion);    
               /*
                Paso 2: Una aproximación de la raíz xr se determina mediante: xr = (xl+xu)/2
               */
               xr = (xl+xu)/2.0;
         
               producto = this.evaluar(xl)*this.evaluar(xr);
                
                if(producto==0){
                    avanzar = false;
                }else{
                    //calcular el error relativo porcentual
                    if(producto<0){
                        xu=xr;
                    }else{
                        xl = xr;
                    }
                    //Tolerancia calculada
                    ea = ((xr-xrold)/xr)*100.0;
                    ea = Math.abs(ea);
                    System.out.println("ea (error relativo porcentual) = "+ea+" ; tolerancia predefinida = "+es);
                    if(ea<es){/*
                            Cuando Ea - error relativo porcentual - es menor que el valor previamente fijado es, termina el cálculo
                        */
                        avanzar = false;
                    }
               }
        
                xrold = xr;
                iteracion++;
                
                System.out.println("*********************************\n");
             //avanzar
           }while(avanzar==true);
           System.out.println("\nResultados:\n");
           System.out.println("\t\tRaiz aproximada: "+xr);
           System.out.println("\t\tNúmero de iteraciones: "+iteracion);
        }
        return 0;
    }
    public static void main(String[] args) {
        double xl3 = 0;
        double xu3 = 1.3;
        double ea3 = 0.01;        
        
        Biseccion biseccion = new Biseccion(xl3, xu3, 0); /*criterio de paro 3 cifras*/
        biseccion.setEs(ea3);
        long time = System.nanoTime();
        biseccion.ejecutarAlgoritmo();
        long time2 = System.nanoTime();
        System.out.println("Tiempo en segundos: "+(time2-time)*Math.pow(10.0, -9.0));        
    }
}
