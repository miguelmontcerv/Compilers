import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Gramatica {

    
    List<List<String>> Reglas= new ArrayList<>();
    String primer_no_terminal;
    ArrayList<String> listaNo_Terminales;
    ArrayList<String> listaTerminales;
    
    public Gramatica(List<List<String>> r,ArrayList<String> LNT,ArrayList<String> LT){
        listaNo_Terminales = LNT;
        listaTerminales=LT;
        Reglas=r;
        primer_no_terminal= r.get(0).get(0);
    }   
    public Gramatica (){//Agregado por el usuario
        
        //String cadena="E->TC;C->+TC|-TC|"+SimbolosEspeciales.EPSILON+";T->FD;D->*FD|/ FD|"+SimbolosEspeciales.EPSILON+";F->(E)|n;";
        /*
       
        System.out.println("Terminales: ");
        String t = "+,-,*,/,(,),num,";
        ArrayList<String> ter=Generar_lista_con_Cadena(t);
        System.out.println(ter);
        
        System.out.println("No_Terminales: ");
        String nt = "E,Ep,T,Tp,F,";
        ArrayList<String> nter=Generar_lista_con_Cadena(nt);
        System.out.println(nter);
                
        ArrayList<String> arr_Reglas=new ArrayList<>();
        arr_Reglas.add("T,Ep;");
        arr_Reglas.add("+,T,Ep|-,T,Ep|"+SimbolosEspeciales.EPSILON+";");
        arr_Reglas.add("F,Tp;");
        arr_Reglas.add("*,F,Tp|/,F,Tp|"+SimbolosEspeciales.EPSILON+";");
        arr_Reglas.add("(,E,)|num;");
    
                
        List<List<String>> Prueba = Convertir_String_a_Regla(arr_Reglas, ter, nter);
        
        Reglas=Prueba;
        listaTerminales=ter;
        listaNo_Terminales=nter;
        primer_no_terminal= Prueba.get(0).get(0);
        /**/
        
        Scanner sc = new Scanner(System.in);
        /*System.out.println("Ingrese separado por comas los simbolos Terminales de la gramatica"); 
        System.out.println("Para terminar de poner valores escriba una ','  al final");
        System.out.println("EJ: +,-,num,(,[, ");
        System.out.println("Terminales: ");*/
        String Terminales = JOptionPane.showInputDialog("Ingrese separado por comas los simbolos Terminales de la gramatica\nPara terminar de poner valores escriba una ','  al final\nEJ: +,-,num,(,[,\nTerminales: ");
        //System.out.println("");
        ArrayList<String> Term_Aux=Generar_lista_con_Cadena(Terminales);
        //System.out.println(Term_Aux);
        //JOptionPane.showMessageDialog(null,Term_Aux);
        /*System.out.println("Ingrese separado por comas los simbolos No-Terminales de la gramatica"); 
        System.out.println("Coloque por favor el No Terminal inicial al principio del listado");
        System.out.println("Para terminar de poner valores escriba una ','  al final");
        System.out.println("EJ: E,C,D,Ep,S, ");
        System.out.println("No_Terminales: ");*/
        String No_Terminales = JOptionPane.showInputDialog("Ingrese separado por comas los simbolos No-Terminales de la gramatican\nColoque por favor el No Terminal inicial al principio del listado\nPara terminar de poner valores escriba una ',' al final\nEJ: E,C,D,Ep,S\nNo_Terminales:");
        //System.out.println("");
        ArrayList<String> No_Term_Aux=Generar_lista_con_Cadena(No_Terminales);
        //System.out.println(No_Term_Aux);
        //JOptionPane.showMessageDialog(null,No_Term_Aux);
        String Aux_Regla;
        ArrayList<String> Array_Reglas = new ArrayList<>();
        String s = "";
        for(int i=0;i<No_Term_Aux.size();i++){
            /*System.out.println("Inglrese lo que genera el no terminal: "+No_Term_Aux.get(i));
            System.out.println("Separar los simbolos por comas, las reglas con '|' y terminar la cadena con ';' ");
            System.out.println("EJ: 'E->+TC|-TC' se pone como '+,T,C|-,T,C;'");*/
            Aux_Regla = JOptionPane.showInputDialog("Ingrese lo que genera el no terminal: "+No_Term_Aux.get(i)+"\nSeparar los simbolos por comas, las reglas con '|' y terminar la cadena con ';' "+"\nEJ: 'E->+TC|-TC' se pone como '+,T,C|-,T,C;'");
            Array_Reglas.add(Aux_Regla);
            JOptionPane.showMessageDialog(null,"Se capturo correctamente:)");
            //System.out.println("Se capturo correctamente");
        }
        //System.out.println("La regla mas larga fue: "+Regla_mas_larga(Array_Reglas, Term_Aux, No_Term_Aux));
        JOptionPane.showMessageDialog(null,"La regla mas larga fue: "+Regla_mas_larga(Array_Reglas, Term_Aux, No_Term_Aux));
        List<List<String>> Aux_Reglas=Convertir_String_a_Regla(Array_Reglas,Term_Aux,No_Term_Aux);
        
        Reglas=Aux_Reglas;
        listaTerminales=Term_Aux;
        listaNo_Terminales=No_Term_Aux;
    }
    
    
    public Gramatica(String cadena){ //requiere que haya informacion de los no terminales
        //"E->TC;C->+TC|-TC|"+epsilon+";T->FD;D->*FD|/FD|"+epsilon+";F->(E)|n;";
        if("".equals(cadena)){
            Reglas=null;
        }
        
        /*for(int i=0;i<cantidad_Reglas(cadena);i++){
            Reglas.add(new ArrayList());
        }*/
        
        for(int i=0;i<Regla_mas_larga(cadena)+1;i++){
            Reglas.add(new ArrayList());
        }
        
        String auxiliar="";
        String No_Terminal_Actual="";
        int j;
        String agregar;
        for(int i=0;i<cadena.length();i++){
            auxiliar=auxiliar+cadena.charAt(i);
            if(i==0){
                No_Terminal_Actual=""+auxiliar.charAt(i);
                auxiliar="";
            }
            if((cadena.charAt(i)+"").contentEquals("|")){
                
                Reglas.get(0).add(No_Terminal_Actual);
                //System.out.println(":   "+auxiliar);/**/
                //System.out.println("");/**/
                //System.out.print(No_Terminal_Actual+"-> ");/**/
                for(j=0;j<auxiliar.length()-1;j++){
                    
                    agregar=""+auxiliar.charAt(j);
                    Reglas.get(j+1).add(agregar);
                    //System.out.print(agregar+"  ");/**/                                                                    
                }
                //System.out.println(Regla_mas_larga(cadena));
                while(j<Regla_mas_larga(cadena)){
                    
                    Reglas.get(j+1).add("%");
                    j++;
                    //System.out.print("%"+"  ");/**/
                }
                /*System.out.println("");/**/                              
                auxiliar="";
            }
            if((cadena.charAt(i)+"").contentEquals(">")){
                
                auxiliar="";
            }
            if((cadena.charAt(i)+"").contentEquals(";")){
                // String cadena="E->TC;C->+TC;";
                Reglas.get(0).add(No_Terminal_Actual);
                //System.out.println(":   "+auxiliar);/**/
                //System.out.println("");/**/
                //System.out.print(No_Terminal_Actual+"-> ");/**/
                for(j=0;j<auxiliar.length()-1;j++){
                    agregar=""+auxiliar.charAt(j);
                    //System.out.println(j+1);
                    
                    Reglas.get(j+1).add(agregar);
                    //System.out.print(agregar+"  ");/**/                                        
                }               
                while(j<Regla_mas_larga(cadena)){
                    
                    Reglas.get(j+1).add("%");
                    //System.out.print("%"+"  ");/**/
                    j++;
                }
                /*System.out.println("");/**/
               
                auxiliar="";
                
                if(!(cadena.length()==(i+1))){
                    
                    No_Terminal_Actual=""+cadena.charAt(i+1);
                
                }
            }
            
        }
        primer_no_terminal=Reglas.get(0).get(0);
        ArrayList<String> AuxiliarT = new ArrayList<>();
        ArrayList<String> AuxiliarNT = new ArrayList<>();
 
        for(int i=0;i<Reglas.get(0).size();i++){
            if(!AuxiliarNT.contains(Reglas.get(0).get(i))){
                AuxiliarNT.add(Reglas.get(0).get(i));
                //System.out.println("Añadido a la lista de NO terminales: "+Lista_Reglas.get(0).get(i));
            }            
        }
        
        listaNo_Terminales=AuxiliarNT;        
       
        for(int i=0;i<Reglas.get(0).size();i++){
            for(int k=1;k<Reglas.size();k++){
                
                if(!(Reglas.get(k).get(i).contentEquals(""+SimbolosEspeciales.EPSILON))&&!(Reglas.get(k).get(i).contentEquals("%"))){
                    if(!(AuxiliarNT.contains(Reglas.get(k).get(i)))){
                        AuxiliarT.add(Reglas.get(k).get(i));
                        //System.out.println("Añadido a la lista de terminales: "+Lista_Reglas.get(k).get(i));
                    }
                }
            }
        }
        AuxiliarT.add("$");
        listaTerminales=AuxiliarT;
                        
    }
    
    private int Regla_mas_larga(String cadena){//"E->+AC|-AC|e;A->/BC|CD|DAAAs;";
        int longitud=0, auxiliar=0;
        String a="";
        for(int i=0;i<cadena.length();i++){
      
            if(cadena.charAt(i)=='>'){
                auxiliar=0;
                a="";
            }else{
                if(cadena.charAt(i)=='|'|cadena.charAt(i)==';'){
                    
                    if(auxiliar>longitud){
                        longitud=auxiliar;
                        
                    }
                    auxiliar=0;
                    a="";
                }else{
                    auxiliar++;
                    a=a+cadena.charAt(i);
                    
                } 
            }
            
        }
        return longitud;
    }    
    
    private static int Regla_mas_larga(ArrayList<String> Array_Reglas,ArrayList<String> Terminales ,ArrayList<String> No_Terminales) { 
        //EJ: 'E->+TC|-TC' se pone como '+,Tc,C|-,Tc,C;'
        int Acumulado=0;
        int mayor=0;
        
        for(int i=0;i<Array_Reglas.size();i++){
            for(int j=0;j<Array_Reglas.get(i).length();j++){
                System.out.println("-"+Array_Reglas.get(i).charAt(j));
                if(!(Array_Reglas.get(i).charAt(j)+"").contentEquals(",")){
                    
                    if((Array_Reglas.get(i).charAt(j)+"").contentEquals(";")  || (Array_Reglas.get(i).charAt(j)+"").contentEquals("|")){
                        if((Array_Reglas.get(i).charAt(j)+"").contentEquals("|")){
                            System.out.println("Se acumulo por: "+Array_Reglas.get(i).charAt(j));
                            Acumulado++;
                        }
                        if(Acumulado>mayor){
                            
                            mayor=Acumulado;
                            System.out.println("Mayor: "+mayor);
                            
                        }
                        Acumulado=0;
                    }
                }else{
                    System.out.println("Se acumulo por: "+Array_Reglas.get(i).charAt(j));
                    Acumulado++;
                }
            }
        }
        System.out.println("EL MAYOR FUE: "+mayor);
        return mayor;
    }

    public int cantidad_Reglas(String cadena) {
        int cant_reglas=0;
        for(int i=0;i<cadena.length();i++){
            if(!(cadena.charAt(i)=='>')){
                if(cadena.charAt(i)=='|'|cadena.charAt(i)==';'){
                    cant_reglas++;
                    
                }
            }
          }
        return cant_reglas;
    }
    
    public void imp_Gramatica(){
        List<List<String>> Gramatica = this.Reglas;
        String aux = "";
        for(int i=0;i<Gramatica.get(0).size();i++){
       
            for(int j=0;j<Gramatica.size();j++){
                if(j==0){
                    //System.out.print(Gramatica.get(j).get(i)+"->\t");
                    aux = aux + Gramatica.get(j).get(i)+"->\t\n";
                }else{
                    if(Gramatica.get(j).get(i).length()<6){
                        //System.out.print(Gramatica.get(j).get(i)+"\t\t");
                        aux = aux + Gramatica.get(j).get(i)+"->\t\t\n";
                    }else{
                        //System.out.print(Gramatica.get(j).get(i)+"\t");
                        aux = aux + Gramatica.get(j).get(i)+"->\t\n";
                    }
                        
                    
                    
                }
            }
            //System.out.println("");
            JOptionPane.showMessageDialog(null,aux);
        }
    }
    
    
    private static ArrayList<String> Generar_lista_con_Cadena(String cadena) {
        ArrayList<String> Auxiliar=new ArrayList<>();
        String aux_simb="";
        for(int i=0;i<cadena.length();i++){

            if((cadena.charAt(i)+"").contentEquals(",")){
                Auxiliar.add(aux_simb);
                aux_simb="";
            }else{
                aux_simb=aux_simb.concat(""+cadena.charAt(i));
            }         
            
            
        }
        return Auxiliar;
    }
    
    private static List<List<String>> Convertir_String_a_Regla(ArrayList<String> Array_Reglas,ArrayList<String> Terminales,ArrayList<String> No_Terminales) {
        /*
        arr_Reglas.add("+,-,T|-,num|hola;");
        arr_Reglas.add("+|num,T;");
        arr_Reglas.add("+,T|Sp,+;");
        */
        List<List<String>> Auxiliar = new ArrayList<>();;
        String Aux_Concat="";
        String No_Terminal_Actual="";
        ArrayList<String> simbolos= new ArrayList<>();
        
        int k=0;
        
        JOptionPane.showMessageDialog(null,"Regla mas larga: "+ Regla_mas_larga(Array_Reglas,Terminales ,No_Terminales));
        //System.out.println("Regla mas larga: "+ Regla_mas_larga(Array_Reglas,Terminales ,No_Terminales));
        for(int i=0;i<Regla_mas_larga(Array_Reglas,Terminales ,No_Terminales)+1;i++){
            Auxiliar.add(new ArrayList());
        }        
        
       // System.out.println(""+Auxiliar.get(0).size());
       // System.out.println(""+Auxiliar.size());
        
        for(int i=0;i<Array_Reglas.size();i++){
            No_Terminal_Actual=No_Terminales.get(i);
            //System.out.println(Array_Reglas.get(i));
            for(int j=0;j<Array_Reglas.get(i).length();j++){
               // System.out.println("<>"+Array_Reglas.get(i).charAt(j));
                switch(Array_Reglas.get(i).charAt(j)+""){
                    case ",":{
                        simbolos.add(Aux_Concat);
                        
                        Aux_Concat="";
                        
                        break;
                    }
                    
                    default:{
                        
                        switch(Array_Reglas.get(i).charAt(j)+""){
                            case "|" :{
                                if(!Aux_Concat.contentEquals("")){
                                    simbolos.add(Aux_Concat);
                                   
                                }

                                Auxiliar.get(0).add(No_Terminal_Actual);
                                //System.out.println("simbolos : "+simbolos);
                          
                                //System.out.println("tam simbolos "+simbolos.size());
                                for(k=0;k<simbolos.size();k++){
                                   

                                    Auxiliar.get(k+1).add(simbolos.get(k));
                                    //System.out.println(No_Terminal_Actual+" _ "+simbolos.get(k));
                                }
                                simbolos=new ArrayList<>(); 
                                
                                while(k<Regla_mas_larga(Array_Reglas,Terminales ,No_Terminales)){
                                   
                                    Auxiliar.get(k+1).add("%");
                                    k++;
                                }     
                                
                                Aux_Concat="";
                                break;
                            }
                            case ";" :{
                                if(!Aux_Concat.contentEquals("")){
                                    simbolos.add(Aux_Concat);
                                   
                                }

                                Auxiliar.get(0).add(No_Terminal_Actual);
                                 //System.out.println("simbolos : "+simbolos);
                          
                                //System.out.println("tam simbolos "+simbolos.size());
                                for(k=0;k<simbolos.size();k++){
                                    
                                    Auxiliar.get(k+1).add(simbolos.get(k));
                                    //System.out.println(No_Terminal_Actual+" _ "+simbolos.get(k));
                                }
                                simbolos=new ArrayList<>(); 
                                
                                while(k<Regla_mas_larga(Array_Reglas,Terminales ,No_Terminales)){

                                    Auxiliar.get(k+1).add("%");
                                    k++;
                                }
                                Aux_Concat="";
                                break;
                            }
                            default:{
                                //System.out.println("conc: "+Array_Reglas.get(i).charAt(j));
                                Aux_Concat=Aux_Concat.concat(""+Array_Reglas.get(i).charAt(j));
                                break;
                            }
                            
                        }
                        break;
                    }
                    
                }
                
                
                if(!(Array_Reglas.get(i).charAt(j)+"").contentEquals(",")){
                    
                    
                    if((Array_Reglas.get(i).charAt(j)+"").contentEquals("|") ||(Array_Reglas.get(i).charAt(j)+"").contentEquals(";")){

                        
        
                    }else{   
                        
                        
                    }
                }else{
                   
                }
                
                
            }
     
        }
        
        return Auxiliar;
    }
}