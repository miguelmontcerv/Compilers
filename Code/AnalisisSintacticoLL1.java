import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

public class AnalisisSintacticoLL1 {

	private AnalizadorLexico al;
	private Tabla_LL1 tl;
	private String alString;
        String palabras_panel = "";
	
	public AnalisisSintacticoLL1(Tabla_LL1 tab, String alString) {
		this.tl = tab;
		this.alString = alString;
	}
	
	public void setLexicAnalyzer(String[][] afd) {
		al = new AnalizadorLexico(afd, alString);
	}
	
	public boolean startLexic() {
		ArrayList<String> cols = tl.getTerminals();
		ArrayList<String> rows = tl.getRows();
		String[][] content = tl.getContent();
		Stack<String> Pila = new Stack<String>();

		int token;
        boolean No_Aceptada=true;
        String Accion="";
        this.alString=this.alString.concat("$");
        Pila.push("$");
        Pila.push(rows.get(0)); 
        int h=0;
        int tabs;
        String lexema;
    	token = al.yylex();
    	lexema = al.getLexema();
    	
        while(No_Aceptada){
            
        	if(token == SimbolosEspeciales.TOKENERROR) {
        		//System.out.println("La cadena es sintacticamente incorrecta");
        		JOptionPane.showMessageDialog(null,"La cadena es sintacticamente incorrecta");
        		return false;
        	}
        	
            try {
            	Accion=Sin_coma(Encontrar_En_Tabla(this.tl,Pila.peek(),lexema));
			} catch (Exception e) {
        		//System.out.println("La cadena es sintacticamente incorrecta");
        		JOptionPane.showMessageDialog(null,"La cadena es sintacticamente incorrecta");
        		return false;
			}
            Accion = Accion.trim();
            
            String[] miniAcciones = Accion.split(",");
            
            Pila.pop();
            if(!(Accion.contentEquals("pop"))){
                if(Accion.contentEquals("ACEPTAR")){
                	//System.out.println("La cadena es sintacticamente correcta");
                	JOptionPane.showMessageDialog(null,"La cadena es sintacticamente correcta");
                    return true;
                }
                
                if(!(Accion.contentEquals("EPSILON"))){
                    for(int x=miniAcciones.length-1; x>=0; x--) {
                    	Pila.push(miniAcciones[x]);
                    }
                }
                
            }else{
            	/* Agregar return de token */ 
            	System.out.println("Lexema: "+lexema+" y token: "+token);
                palabras_panel = palabras_panel + "Lexema: "+lexema+" y token: "+token+"\n";
            	token=al.yylex();
            	lexema = al.getLexema();
            }/*
            h++;
            if(h==2){
                No_Aceptada=false;
            }*/
            
        }
    
        return false;
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
	
	private String Sin_coma(String s){
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
    public String publicar(){
        return palabras_panel;
    }
}
