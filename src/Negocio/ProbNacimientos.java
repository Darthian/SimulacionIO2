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
   
    public String probPaquetes(double time,int Num_Paquetes) {
        int contador=0,k = 0;
        if(Num_Paquetes==20){
            int result=1*100;
            return String.valueOf(result)+"%";
        }
        if(Num_Paquetes<20){      
        int j=20-Num_Paquetes;    
        String lk="PARA COMPLETAR UN ENVIO DEBE ESPERAR HASTA QUE LLEGUEN "+String.valueOf(j)+" PAQUETES MAS. SELECIONE EL TIEMPO QUE DESEA, EN QUE LLEGUEN ESTOS "+String.valueOf(j)+" PAQUETES :";
        return lk;    
        }
        while(Num_Paquetes>20){
            Num_Paquetes=Num_Paquetes-20;
            k=Num_Paquetes;
            contador=contador+1;
            }        
        if(k<20){      
            return "LOS PRIMEROS "+String.valueOf(contador*20)+" PAQUETES TIENEN LA PROBABILIDAD DEL 100%. QUEDAN "+String.valueOf(k)+ " PAQUETES POR ENVIAR. PARA COMPLETAR OTRO ENVIO DEBE ESPERAR HASTA QUE LLEGUEN "+String.valueOf(20-k)+" PAQUETES. SELECIONE EL TIEMPO QUE DESEA, EN QUE LLEGUEN ESTOS "+String.valueOf(20-k)+" PAQUETES :";
        }
          
        return "UN ENVIO SE GENERA CUANDO SE COMPLETAN 20 PAQUETES";
    }

    public double factorial_numero(int valor_numero){
        double resultado=1; /// Se declara una variable tipo double resultado

            for(int i=1; i<=valor_numero; i++){ //Se realiza un ciclo 
                resultado = resultado * i ; // se multiplica consecutivamente
            }

        return resultado; //Devuelve un resultado tipo double
    } 
    public String prob_num_paquetes(int time,int Num_Paquetes){    
            double lambda=0.125;//Equivale a 1/8
            double lt=Math.pow((lambda*time*60),Num_Paquetes);
            System.out.println("Lambda *tiempo ^ Numero paquetes= ");
            System.out.println(lt);
            double ex=Math.exp(-lambda*time*60);
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
