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
public class FalsaPosicion {
    private double xl;
    private double xu;
    private double es;
    
    public FalsaPosicion(double xl, double xu, double cifrasSig) {
       this.xl = xl;
       this.xu = xu;
       double tolerancia = 0.5 * Math.pow(10.0, 2.0 - cifrasSig);
       System.out.println("Tolerancia = "+tolerancia);
    }
    public void setEs(double es){
        this.es = es;
    }
    private double evaluar(double x){
//        return Math.pow(x, 2.0)+(1.0/x)-6.0;        
        return Math.pow(x,10)-1;
    }
    private double getXr(double xl, double xu){
        return xu-((this.evaluar(xu)*(xl-xu))/(this.evaluar(xl)-this.evaluar(xu)));
    }  
    public void ejecutarAlgoritmo(){
       double xr;
       double ea = 0; //error relativo porcentual aproximado
       double xrold = 0.0;
       double producto;
       int iteracion=0;
       boolean avanzar = true;
       double fxl=0.0;
       double fxr = 0.0;
       
       do{
          //1. calculas xr
          xr = this.getXr(xl, xu);
                    
          producto = this.evaluar(xr)*this.evaluar(xr);
         
          if(producto==0.0){
            avanzar = false;
          }else{
              if(producto<0.0){
                xu = xr;
              }else if(producto>0.0){
                xl = xr;
              } 
                  //calcular el error relativo porcentual
                ea = ((xr-xrold)/xr)*100.0;
                ea = Math.abs(ea);
                System.out.println("Iteracion: "+iteracion+" ; "+" ea = "+ea+" ; tolerancia = "+es);

                if(ea<es){
                    avanzar = false;
                }
          }
          xrold = xr;
          iteracion++;
       }while(avanzar==true);
          
        System.out.println("*********************************\n");
        System.out.println("raiz aproximada: "+xr);
        System.out.println("Iteraciones: "+iteracion);
    }
    public static void main(String[] args) {
        double xl2 = 0;
        double xu2 = 1.3;
        double es2 = 0.01;

        FalsaPosicion f = new FalsaPosicion(xl2, xu2, 3);
        f.es = es2;
        long time = System.nanoTime();
        f.ejecutarAlgoritmo();
        long time2 = System.nanoTime();
        System.out.println((time2-time)*Math.pow(10.0, -9.0));
    }
    
}
