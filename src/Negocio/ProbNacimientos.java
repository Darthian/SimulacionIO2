/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author CORTES
 */
public class ProbNacimientos {
   
    public int For_Paq_May_20(int Num_Paquetes) {
        int k = 0;
/*        if(Num_Paquetes==20){
            int result=1*100;
            return String.valueOf(result)+"%";
        }*/
       return k; 
    }

    public double factorial_numero(int valor_numero){
        double resultado=1; /// Se declara una variable tipo double resultado

            for(int i=1; i<=valor_numero; i++){ //Se realiza un ciclo 
                resultado = resultado * i ; // se multiplica consecutivamente
            }

        return resultado; //Devuelve un resultado tipo double
    } 
    public String prob_num_paquetes(int time,int Num_Paquetes){    
            int lambda=time;//Equivale a 1/8
            double lt=Math.pow(lambda,Num_Paquetes);
            System.out.println("Lambda *tiempo ^ Numero paquetes= ");
            System.out.println(lt);
            double ex=Math.exp(-lambda);
            System.out.println("e^-Lambda *tiempo= ");
            System.out.println(ex);
            double fact=factorial_numero(Num_Paquetes);
            System.out.println("Factorial( Numero paquetes)= ");
            System.out.println(fact);
            double Pn=(lt*ex)/fact;
            System.out.println("Probabilidad= ");        
            System.out.println(Pn);        
            double k=Pn;
            System.out.println("eeeeee....voovovovovovccppppcccccc``c``pcoooooooo");
            System.out.println(k);
            System.out.println("jajajajajajaj");
            String porcen=String.valueOf(k*100)+"%";
            return "\nLA PROBABILIDAD DE QUE LLEGUEN "+String.valueOf(Num_Paquetes)+" PAQUETES EN "+String.valueOf(time)+" HORAS ES: "+porcen;
    
    }
 }
