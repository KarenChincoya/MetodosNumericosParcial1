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
public class BiseccionMejorada {
    private double xl;
    private double xu;
    private double es; //Tolerancia o nivel aceptable. Es = (0.5*10^(2-n)) 100%
    private int imax;//iteraciones maximas
    private double cifrasSig;
    /* es = tolerancia*/

    public BiseccionMejorada(double xl, double xu, double cifrasSig){
        this.xl = xl;
        this.xu = xu;
        this.es = 0.5 * Math.pow(10.0, 2.0 - cifrasSig);   
    }
    public void ejecutarAlgoritmo(){
        double xrold;
        double xr = 0.0;
        int iter=0;
        double ea=0.0;
        double producto;
        boolean avanzar = true;
        do {
            xrold = xr;
            xr = (xl+xu)/2;
            if(xr!=0){
                ea = Math.abs(((xr-xrold)/xr)*100);
            }
            producto = this.evaluar(xl)*this.evaluar(xr);
            if(producto<0){
                xu = xr;
            }else if(producto>0){
                xl = xr;
            } else{
                ea = 0;
            }
            if(ea<es){//Con el mismo criterio de paro, quitamos    || (iter+1)>=(imax)
                avanzar = false;
            }
            System.out.println("Iteracion: "+iter+" ; Raíz aproximada: "+xr+" ; error aproximado: "+ea);
            iter++;   
        }while(avanzar);
        System.out.println("*********************************\n");
             //avanzar
        System.out.println("\nResultados:\n");
        System.out.println("\t\tRaiz aproximada: "+xr);
        System.out.println("\t\tNúmero de iteraciones: "+iter);
    }
    private double evaluar(double x){
        return Math.pow(x,10)-1;
    }
    
    public void setIteraciones(){
        double diferencia = this.xu - this.xl;
        System.out.println("Resultado verdadero de iteraciones: "+Math.log((this.xu-this.xl)/this.es)/(Math.log(2)));
        System.out.println("\t\tValor de tolerancia a ocupar: "+this.es);
        this.imax = (int) (Math.log(diferencia/this.es)/(Math.log(2)));
        System.out.println("Resultado de iteraciones: "+this.imax);
    }
    
    public int getIteraciones(){
        return this.imax;
    }
    public void setEs(double es){
        this.es = es;
        System.out.println("Tolerancia registrada: "+this.es);
    }
    
    public static void main(String[] args) {
        double xl3 = 0;
        double xu3 = 1.3;
        double ea3 = 0.01;        
        
        BiseccionMejorada biseccion = new BiseccionMejorada(xl3, xu3, 0); /*criterio de paro 3 cifras*/
        biseccion.setEs(ea3);
        biseccion.setIteraciones();
        System.out.println("Iteraciones máximas = "+biseccion.getIteraciones());
        long time = System.nanoTime();
        biseccion.ejecutarAlgoritmo();
        long time2 = System.nanoTime();
        System.out.println("Tiempo en segundos: "+(time2-time)*Math.pow(10.0, -9.0));
    }
}
