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
public class FalsaPosicionModificado {
    private double xl;
    private double xu;
    private double es;
    private int imax;
    
    public FalsaPosicionModificado(double xl, double xu, double cifrasSig) {
       this.xl = xl;
       this.xu = xu;
       this.es = 0.5 * Math.pow(10.0, 2.0 - cifrasSig);
    }
    public void setEs(double es){
        this.es = es;
    }
    private double evaluar(double x){
//        return Math.pow(x, 2.0)+(1.0/x)-6.0;        
        return Math.pow(x,10)-1;
    }
    private double getXr(double xl, double xu){
        double fl = this.evaluar(xl);
        double fu = this.evaluar(xu);
        return (xu-fu*(xl-xu))/(fl-fu);
    }  
    public void setMaximoIteraciones(){
        double diferencia = this.xu - this.xl;        
        this.imax = (int) (Math.log(diferencia/this.es)/(Math.log(2)));
    }
    public void ejecutarAlgoritmo(){
       double xr;
       double ea = 0; //error relativo porcentual aproximado
       double xrold = 0.0;
       double producto;
       int iteracion=0;
       boolean avanzar = true;
       double fl=0.0;
       double fr = 0.0;
       double fu = 0.0;
       int iu = 0;
       int il = 0;
       
       do{
          xr = this.getXr(xl, xu);
          xrold = xr;
          fl = this.evaluar(xl);
          fr = this.evaluar(xr);                    
          if(xr!=0.0){
              ea = ((xr-xrold)/xr)*100.0;
          }
          producto = fl*fr;          
          
          if(producto<0.0){
            xu = xr;
            fu = this.evaluar(xu);
            iu = 0;
            il = il++;
            if(il>=2){
                fl = fl/2;
            }
          }else if(producto>0.0){
            xl = xr;
            fl = this.evaluar(xl);
            il = 0;
            iu = iu++;
            if(iu>=2){
                fu = fu/2;
            }else {
              ea = 0;
            }
          }
          
          if(ea<es){//|| iteracion>=imax
              avanzar = false;
          }
          
          iteracion++;
       }while(avanzar==true);
          
        System.out.println("*********************************\n");
        System.out.println("raiz aproximada: "+xr);
        System.out.println("Iteraciones: "+iteracion);
    }
    public static void main(String[] args) {
        double xl2 = 0;
        double xu2 = 1.3;
        double es2 = 0.01; //setMaximoIteraciones

        FalsaPosicionModificado f = new FalsaPosicionModificado(xl2, xu2, 3);
        f.setEs(es2);
        long time = System.nanoTime();
        f.ejecutarAlgoritmo();
        long time2 = System.nanoTime();
        System.out.println((time2-time)*Math.pow(10.0, -9.0));
    }
}
