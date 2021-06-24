import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;

public class Tabla_LL1 implements Serializable {
    String[][] Tabla;
    ArrayList<String> Filas;
    ArrayList<String> Columnas;
    
    String palabras_panel = "";    
    
    public Tabla_LL1(Gramatica g){
        AnalisisGramatical a = new AnalisisGramatical(g);
        Columnas=g.listaTerminales;
        Columnas.add("$");
       // Columnas = a.gram.listaTerminales;
        Filas=Pegar_Listas(g.listaNo_Terminales,g.listaTerminales); 
        //Filas = a.gram.listaNo_Terminales;
       //ArrayList<String> Aux_cols = a.gram.listaTerminales;
        //ArrayList<String> Aux_filas =  a.gram.listaNo_Terminales;
        ArrayList<String> Aux_cols = g.listaTerminales;
        
        ArrayList<String> Aux_filas =  Pegar_Listas(g.listaNo_Terminales,g.listaTerminales);
        //Aux_filas=Pegar_Listas(Aux_filas,Aux_cols); 
        //ImpLista(Aux_cols);
        //ImpLista(Aux_filas);
        
        
        ArrayList<String> Auxiliar_First;
        ArrayList<String> Auxiliar_Follow;
        ArrayList<String> Auxiliar_Resto;
        ArrayList<String> Auxiliar_Tabla;
        String Cadena_Tabla,Cadena_Regla;
        int fila,columna;
        
        int columnas=Aux_cols.size();
        int filas=Aux_filas.size();
        /*System.out.println("Tamaño filas: "+Aux_filas.size());
        System.out.println("Tamaño columnas: "+Aux_cols.size());
        System.out.println("Columnas: "+columnas);
        System.out.println("Filas: "+filas);*/
        
        Tabla =new String[filas][columnas];
        for(int i=0;i<columnas;i++){
            
            for(int j=(filas-columnas);j<filas;j++){
                
                if(Aux_cols.get(i).contentEquals(Aux_filas.get(j))){
                    
                    if(Aux_cols.get(i).contentEquals("$")){
                        Tabla[j][i]="ACEPTAR";
                    }else{
                       
                        Tabla[j][i]="pop";
                    }
                }
            }
        }
        /*System.out.println("----------------");
        Imp_Tabla(Tabla);
        System.out.println("----------------");*/
        for(int i=0;i<g.Reglas.get(0).size();i++){
           Auxiliar_Resto=Resto(g.Reglas,i);
           Auxiliar_First= a.First(g,Auxiliar_Resto);
           if(Auxiliar_First.contains("EPSILON")){
              
               Auxiliar_Follow=a.Follow(g.Reglas,g.Reglas.get(0).get(i));
               Auxiliar_Tabla=Auxiliar_Follow;
               Cadena_Regla="EPSILON";
           }else{
               Auxiliar_Tabla=Auxiliar_First;
               Cadena_Regla=Regla_a_String(Auxiliar_Resto);
           }
            
            Cadena_Tabla=Cadena_Regla+"¬R#"+(i+1);
            //System.out.println(Cadena_Tabla);
            //ImpLista(Auxiliar_Tabla);
            
           for(int j=0;j<Auxiliar_Tabla.size();j++){
                
                fila=Indice_Fila(Aux_filas,g.Reglas.get(0).get(i));
                
                
                columna=Indice_Columna(Aux_cols,Auxiliar_Tabla.get(j));
               
                //System.out.println("("+fila+", "+columna+")");
                Tabla[fila][columna] =  Cadena_Tabla;
               
                       
              
           }
           
                   
        }
     
        
    }
    
    public void Imp_Tabla(String[][] T){
        String s = "";
        for(int i=0;i<T.length;i++){
            for(int j=0;j<T[0].length;j++){
                //System.out.print(T[i][j]+"\t");
                s = s + T[i][j]+"\t";
            }
            //System.out.println("");
            s+="\n";
        }
      palabras_panel = palabras_panel + s;
      //JOptionPane.showMessageDialog(null,s);
    }
    
    public void Imp_TablaLL1(){
        String s = "";
        s+="\t";
        System.out.print("\t");
        for(int i=0;i<Columnas.size();i++){
            //System.out.print(Columnas.get(i)+"\t\t");
            s = s +Columnas.get(i)+"\t\t";
        }
        System.out.println("");
        s+="\n";
        for(int i=0;i<Filas.size();i++){
            //System.out.print(Filas.get(i)+"\t");
            s = s + Filas.get(i)+"\t";
            for(int j=0;j<Columnas.size();j++){
                if(Tabla[i][j]==null){
                    //System.out.print("%\t\t");
                    s = s + "%\t\t";
                }else{
                    if(Tabla[i][j].contains("EPSILON")||Tabla[i][j].length()>7){
                       //System.out.print(Tabla[i][j]+"\t");
                        s = s + Tabla[i][j]+"\t";
                    }else{System.out.print(Tabla[i][j]+"\t\t"); s = s + Tabla[i][j]+"\t\t";}                                        
                }
                
            }
            //System.out.println("");
            s+="\n";           
        }
        palabras_panel = palabras_panel + s;
        //JOptionPane.showMessageDialog(null,s);
    }
    
    private int Indice_Columna(ArrayList<String> columnas,String Simbolo){
        
        for(int i=0;i<columnas.size();i++){
            if(columnas.get(i).contentEquals(Simbolo)){
                return i;
            }
            
        }
        return -1;
    }
    
    private int Indice_Fila(ArrayList<String> filas, String Simbolo) {
       for(int i=0;i<filas.size();i++){
            if(filas.get(i).contentEquals(Simbolo)){
                return i;
            }
            
        }
        return -1;
    }
    
  
    private ArrayList<String> Resto(List<List<String>> Lista, int i) {
        ArrayList<String> Auxiliar= new ArrayList<>();
        
            for(int j=1;j<Lista.size();j++){
                
                
                Auxiliar.add(Lista.get(j).get(i));
            }
        return Auxiliar;
    }
    
    public String Regla_a_String(ArrayList<String> regla){
        String cadena="";
        for(int i=0;i<regla.size();i++){
            if(!regla.get(i).contentEquals("%")){
                if(i==0){
                    cadena=cadena.concat(regla.get(i));
                }else{
                    cadena=cadena.concat(","+regla.get(i));
                }
                        
                
            }
                
        }
     
        return cadena;
    }
    
    public ArrayList Pegar_Listas(ArrayList<String> Lista, ArrayList<String> Lista2){
        ArrayList<String> Auxiliar = Lista ;
        if(Lista2.size()<1){
            return Auxiliar;
        }
        for(int i=0;i<Lista2.size();i++){
            if(!(Auxiliar.contains(Lista2.get(i)))){   
                Auxiliar.add(Lista2.get(i));
            }
        }
        return Auxiliar;
    }

    private static void ImpLista(ArrayList<String> Lista) {
        System.out.println("");
        System.out.print("{");
        for(int i=0;i<Lista.size();i++){
            System.out.print(Lista.get(i)+",");
        }
        System.out.print("}");
    }
    
    public ArrayList<String> getTerminals() {
    	return Columnas;
    }
    
    public ArrayList<String> getRows(){
    	return Filas;
    }
    
    public String[][] getContent(){
    	return Tabla;
    }

    public String publicar(){
            return palabras_panel;
        }    
    
}
