import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class AnalisisGramatical implements Serializable{
    Gramatica gram;
    
    public AnalisisGramatical(Gramatica g){
        gram=g;
    }
    
    AnalisisGramatical(List<List<String>> Lista_Reglas){
        ArrayList<String> AuxiliarT = new ArrayList<>();
        ArrayList<String> AuxiliarNT = new ArrayList<>();
        
        
        for(int i=0;i<Lista_Reglas.get(0).size();i++){
            if(!AuxiliarNT.contains(Lista_Reglas.get(0).get(i))){
                AuxiliarNT.add(Lista_Reglas.get(0).get(i));
                //System.out.println("Añadido a la lista de NO terminales: "+Lista_Reglas.get(0).get(i));
            }
            
        }
        
       
        for(int i=0;i<Lista_Reglas.get(0).size();i++){
            for(int j=1;j<Lista_Reglas.size();j++){
                
                if(!(Lista_Reglas.get(j).get(i).contentEquals(""+SimbolosEspeciales.EPSILON))&&!(Lista_Reglas.get(j).get(i).contentEquals("%"))){
                    if(!(AuxiliarNT.contains(Lista_Reglas.get(j).get(i)))){
                        AuxiliarT.add(Lista_Reglas.get(j).get(i));
                        //System.out.println("Añadido a la lista de terminales: "+Lista_Reglas.get(j).get(i));
                    }
                }
            }
        }
        AuxiliarT.add("$");
        
        gram= new Gramatica(Lista_Reglas,AuxiliarNT,AuxiliarT);
        
    }
    /*if(primer_no_terminal == Gramatica.get(0).get(i)){
                    Lista_first.add('$');
                }*/
    /*public ArrayList First(List<List<String>> Gramatica, List<String> Regla){
        
        ArrayList<String> Lista_first = new ArrayList<String>();
        if(esTerminal(Regla.get(0))){
            Lista_first.add(Regla.get(0));
        }else{
            Lista_first=First(Gramatica,Regla.get(0));
        }
        return Lista_first;
    }*/
    public ArrayList First(Gramatica g, String No_Terminal){ 
        
        return First(g.Reglas,No_Terminal);
    }
    
    public ArrayList First(Gramatica g,ArrayList<String> Lado_Derecho){
      
        
        ArrayList<String> Lista_first = new ArrayList<>();
        if("".contentEquals(Lado_Derecho.get(0))){
            Lista_first.add(Lado_Derecho.get(0));
            return Lista_first;
        }
        if("".contentEquals(Lado_Derecho.get(0))){
            return Lista_first;
        }
        Lista_first = First(g,Lado_Derecho.get(0));
        return Lista_first;
    }
    
    
    public ArrayList First(List<List<String>> Gramatica, String No_Terminal){ 
        
        int j,epsilons=0;
        boolean BanderaEpsilon=true;
        //System.out.println("Calculando el first de "+No_Terminal);
        ArrayList<String> Auxiliar;
        ArrayList<String> Lista_first = new ArrayList<>();
        if(gram.listaTerminales.contains(No_Terminal)){
            
            Lista_first.add(No_Terminal);
            return Lista_first;
        }
        if("".contentEquals(No_Terminal)){
            return Lista_first;
        }
        if((""+SimbolosEspeciales.EPSILON).contentEquals(No_Terminal)){
            Lista_first.add("EPSILON");
            return Lista_first;
        }
        
        for(int i=0;i<Gramatica.get(0).size();i++){
            //System.out.println(No_Terminal+"<:>"+ Gramatica.get(0).get(i));
            if(No_Terminal.contentEquals(Gramatica.get(0).get(i))){ 
                
                if(gram.listaTerminales.contains(Gramatica.get(1).get(i))){ 
                    if(!ListaContieneSimbolo(Lista_first,Gramatica.get(1).get(i))){   
                        
                        Lista_first.add(Gramatica.get(1).get(i));
                    }
                }else{
                    if(Gramatica.get(1).get(i).contentEquals(""+SimbolosEspeciales.EPSILON)){
                        
                        if(!ListaContieneSimbolo(Lista_first,""+SimbolosEspeciales.EPSILON)){                   
                            Lista_first.add("EPSILON");
                        }
                        
                    }else{
                        
                        if(gram.listaNo_Terminales.contains(Gramatica.get(1).get(i))){
                            
                            for(j=1;j<Gramatica.size()-1;j++){
                                if(Gramatica.get(j).get(i).contentEquals(No_Terminal)){
                                    
                                    j++;
                                    if(!(j == Gramatica.size())){
                                        j=Gramatica.size();
                                        BanderaEpsilon=false;
                                    }    
                                }
                                if(BanderaEpsilon){
                                    
                                    Auxiliar=First(Gramatica,Gramatica.get(j).get(i));


                                    if((ListaContieneSimbolo(Auxiliar,"EPSILON"))){
                                        epsilons++;
                                        Lista_first=Pegar_Listas_SIN_E(Lista_first,Auxiliar);
                                    }else{
                                        Lista_first=Pegar_Listas(Lista_first,Auxiliar);
                                        

                                        j=Gramatica.size();
                                        
                                    } 
                                }
                                
                            }
                            
                        }
                    }
                }
            }
            
        }
       
        /*System.out.println("Se termino de calcular el first de "+No_Terminal);
        System.out.print("First de "+No_Terminal+": { "); 
        for(int i=0;i<Lista_first.size();i++){
            if(i==0)
                System.out.print(""+Lista_first.get(i));
            else
                System.out.print(", "+Lista_first.get(i));
        }
        System.out.print("}");
        System.out.println("");
           /**/
        
        
        return Lista_first;
    }
    public ArrayList Follow(List<List<String>> Gramatica, String No_Terminal){
        
        
        
        ArrayList<String> Lista_Auxiliar;
        ArrayList<String> Lista_follow = new ArrayList<>();
        if(No_Terminal.contentEquals(Gramatica.get(0).get(0))){
            Lista_follow.add("$");
            //Caso 1
            //System.out.println("Caso 1 en follow de :"+No_Terminal);
        }
        //System.out.println("Calculando el follow de "+No_Terminal);
     
        
        for(int i=0;i<Gramatica.get(0).size();i++){
            
            for(int j=1;j<Gramatica.size();j++){
                //System.out.println(No_Terminal+" <:"+Gramatica.get(0).get(i)+":> "+Gramatica.get(j).get(i));
                
                if(Gramatica.get(j).get(i).contentEquals(No_Terminal)){
                    //System.out.println("Entre con: "+Gramatica.get(j).get(i));
                   
                    if((j+1)==Gramatica.size()||Gramatica.get(j+1).get(i).contentEquals("%")){
                        //Caso 4
                        //System.out.println("Caso 4 en follow de :"+No_Terminal);
                        if(!(No_Terminal.contentEquals(Gramatica.get(0).get(i)))){
                            Lista_Auxiliar=Follow(Gramatica,Gramatica.get(0).get(i));
                            Lista_follow=Pegar_Listas(Lista_follow, Lista_Auxiliar);
                        }
                            
                    }else{
                        Lista_Auxiliar=First(Gramatica,Gramatica.get(j+1).get(i));
                        if(Lista_Auxiliar.contains("EPSILON")) {
                            //Caso 3
                            //System.out.println("Caso 3 en follow de :"+No_Terminal);
                            if(!(No_Terminal.contentEquals(Gramatica.get(0).get(i)))){
                                Lista_follow=Pegar_Listas_SIN_E(Lista_follow,Lista_Auxiliar);
                                Lista_Auxiliar=Follow(Gramatica,Gramatica.get(0).get(i));
                                Lista_follow=Pegar_Listas(Lista_follow,Lista_Auxiliar);
                            }
                        }else{
                            //Caso 2
                            //System.out.println("Caso 2 en follow de :"+No_Terminal);
                            Lista_follow=Pegar_Listas(Lista_follow,Lista_Auxiliar);
                            
                            
                        }
                                
                    }
                    
                }
               
            }
     
        }
        //System.out.println("Se termino de calcular el follow de "+No_Terminal);
        return Lista_follow;
    }
    
    public boolean ListaContieneSimbolo(ArrayList<String> Lista, String simbolo){
        for(int i=0;i<Lista.size();i++){
            if(simbolo.contentEquals(Lista.get(i))){
                return true;
            }
        }
        return false;
        
    }
    public ArrayList Pegar_Listas(ArrayList<String> Lista, ArrayList<String> Lista2){
        if(Lista2.size()<1){
            return Lista;
        }
        for(int i=0;i<Lista2.size();i++){
            if(!ListaContieneSimbolo(Lista,Lista2.get(i))){   
                Lista.add(Lista2.get(i));
            }
        }
        return Lista;
    }
    public ArrayList Pegar_Listas_SIN_E(ArrayList<String> Lista, ArrayList<String> Lista2){
        if(Lista2.size()<1){
            return Lista;
        }
        for(int i=0;i<Lista2.size();i++){
            if(!Lista2.get(i).contentEquals("EPSILON")){
                if(!ListaContieneSimbolo(Lista,Lista2.get(i))){   
                    Lista.add(Lista2.get(i));
                }
            }
        }
        return Lista;
    }
    
    public String Encontrar_En_Tabla(Tabla_LL1 t,String Simbolo_fila,String Simbolo_columna){
        String regreso;
        int j;
        int columna=t.Columnas.size(), fila=(t.Columnas.size()+t.Filas.size());
        for(int i=0;i<t.Columnas.size();i++){
            //System.out.println("<col>"+t.listaTerminales.get(i));
            if(t.Columnas.get(i).contentEquals(Simbolo_columna)){
                columna=i;
                //System.out.println("Columna: "+columna);
            }
        }
        
        for(j=0;j<t.Filas.size();j++){
            //System.out.println("<fil>"+t.listaNo_Terminales.get(j));
            if(t.Filas.get(j).contentEquals(Simbolo_fila)){

                fila=j;
                //System.out.println("Fila: "+fila);
            }
            
        }
        
        
        regreso=t.Tabla[fila][columna];
       
        return regreso;
    }
    
    public boolean Cadena_Es_Aceptada(Tabla_LL1 t1,String cadena){
        boolean No_Aceptada=true;
        String Accion="";
        ArrayList<String> Pila=new ArrayList<String>();
        cadena=cadena.concat("$");
        Pila.add("$");
        Pila.add(t1.Filas.get(0)); 
        int h=0;
        int tabs;
        while(No_Aceptada){
            System.out.println("");
            
            
            if(Pila.size()>=3){
                System.out.print("Pila: "+Pila+"\t");
            }else{
                System.out.print("Pila: "+Pila+"\t\t");
            }
            
            if(cadena.length()>=15){tabs=1;}else if(cadena.length()>7){
                tabs=2;
            }else{
                tabs=3;
            }
            
            System.out.print("Cadena: "+cadena);
            for(int j=0;j<tabs;j++){
                System.out.print("\t");
            }
            System.out.print("Accion: "+Encontrar_En_Tabla(t1,Pila_top(Pila),Primer_No_Terminal(cadena)));
            //System.out.println("---: "+Sin_coma(Encontrar_En_Tabla(t1,Pila_top(Pila),Primer_No_Terminal(cadena))));
            
            Accion=Sin_coma(Encontrar_En_Tabla(t1,Pila_top(Pila),Primer_No_Terminal(cadena)));
            
            Pila=Pila_pop(Pila);
            if(!(Accion.contentEquals("pop"))){
                if(Accion.contentEquals("ACEPTAR")){
                    return true;
                }
                if(!(Accion.contentEquals("EPSILON"))){
                    Pila=Pila_Agregar_Cadena(Pila,Accion);
                }
                
            }else{
                cadena=Pop_Cadena(cadena);
            }/*
            h++;
            if(h==2){
                No_Aceptada=false;
            }*/
            
        }
    
        return false;
    }
    private String Primer_No_Terminal(String cadena) {
       //System.out.println("Cadena: "+ cadena);
       String Auxiliar="";
       if(cadena.contentEquals("$")){
           return "$";
       }
       for(int i=0;i<cadena.length();i++){
            
            if((cadena.charAt(i)+"").contentEquals(",")){
                return Auxiliar;
            }
            
            Auxiliar=Auxiliar.concat(cadena.charAt(i)+"");
            //System.out.println("Aux: "+Auxiliar);
       }
       return "";
    }
    
    
    private static String Sin_coma(String s){
        String Auxiliar="";
        for(int i=0;i<s.length();i++){
            if((s.charAt(i)+"").contentEquals("¬")){
                return Auxiliar;
            }else{
                Auxiliar=Auxiliar.concat(s.charAt(i)+"");
            }
        }
        return Auxiliar;
    }
    
    private static String Invertir_String(String s){
        String Auxiliar ="";
        for(int i=s.length()-1;i>-1;i--){
            Auxiliar=Auxiliar.concat(""+s.charAt(i));
        }
        return Auxiliar;
    }
    public static String Pop_Cadena(String cadena){
        String Auxiliar="";
        for(int i=0;i<cadena.length();i++){
            if(((cadena.charAt(i))+"").contentEquals(",")){
                for(int j=i+1;j<cadena.length();j++){
                    Auxiliar=Auxiliar.concat(cadena.charAt(j)+""); 
                }
                return Auxiliar;
            }
                 
        }
        
        return "Error";
    }
    
    public static ArrayList<String> Pila_pop(ArrayList<String> Pila){
        ArrayList<String> Auxiliar= new ArrayList<>();
        
        for(int i=0;i<Pila.size();i++){
            if(i==Pila.size()-1){
                return Auxiliar;
            }
            Auxiliar.add(Pila.get(i));
        }
        
        return Auxiliar;
    }
    public static String Pila_top(ArrayList<String> Pila){
        String top;
      
        top=Pila.get(Pila.size()-1);
        return top;
 
    }
    
    private ArrayList<String> Pila_Agregar_Cadena(ArrayList<String> Pila,String cadena) {
       
        
        String Aux_cad=cadena;
        int num=num_Simbolos(cadena);
        for(int i=0;i<num;i++){
            Pila.add(Ultimo_Terminal(Aux_cad));
           
            Aux_cad=Resto_Ultimo_Terminal(Aux_cad);
            
        }
        //System.out.println("Pila: "+Pila);
        return Pila;
        
    }
    private int num_Simbolos(String cadena){
        int aux=1;
        if(cadena.length()==0){
            return 0;
        }
        for(int i=0;i<cadena.length();i++){
            if((cadena.charAt(i)+"").contentEquals(",")){
                aux++;
            }
        }
        return aux;
    }
    private String Resto_Ultimo_Terminal(String cadena){
        
        String Auxiliar="";
        for(int i=cadena.length()-1;i>=0;i--){
           
            if(i==0||(cadena.charAt(i)+"").contentEquals(",")){
                for(int j=0;j<i;j++){
                    Auxiliar=Auxiliar.concat(cadena.charAt(j)+"");
                    
                }
                return Auxiliar;
            }
        }
        return "";
    }
    
    private String Ultimo_Terminal(String cadena) {
     
        if(cadena.length()==0){return "";}
        if(!cadena.contains(",")){
            return cadena;
        }
        String Auxiliar="";
        for(int i=cadena.length()-1;i>0;i--){
           
            if(i==0||(cadena.charAt(i)+"").contentEquals(",")){
                for(int j=i+1;j<cadena.length();j++){
                    Auxiliar=Auxiliar.concat(cadena.charAt(j)+"");
                    
                }
                return Auxiliar;
            }
        }
        return "";
    }

    
    public static void ImpLista(ArrayList<String> Lista) {
        System.out.println("");
        System.out.print("{");
        for(int i=0;i<Lista.size();i++){
            if (i==0){
                System.out.print(Lista.get(i));
            }else{
                System.out.print(","+Lista.get(i));
            }
            
        }
        System.out.print("}");
        System.out.println("");
    }

    

    

    
}