// Para usar esta clase hace falta importar la clase ObjetoString del paquete referencias, ya que es el objeto que se usará por paso de referencia
// Nota si la clase ObjetoString esta en el mismo paquete no hace falta importar
public class ConvertidorPostfijo {
	AnalizadorLexico al;
	
	// El constructor recibe el AFD y una cadena a analizar para inicializar su objeto AnalizadorLexico
	public ConvertidorPostfijo(String[][] afd, String sigma) {
		al = new AnalizadorLexico(afd, sigma);
	}
	
	// Funcion que sirve para verificar que la cadena haya sido recorrida en su totalidad
	public int getLastToken() {
		return al.yylex();
	}
	
	// Es el metodo inicial, al mandarlo a llamar se debe crear un ObjetoString para paso por referencia el cual debe estar vacio
	public boolean E(ObjetoString v) {
		if(T(v))
			if(Ep(v))
				return true;
		return false;
	}
	
	private boolean Ep(ObjetoString v) {
		ObjetoString v2 = new ObjetoString();
		int token = al.yylex();
		if(token==10||token==20) {
			if(T(v2)) {
				v.setV(v.getV()+" "+v2.getV()+" "+(token==10?"+":"-"));
				if(Ep(v))
					return true;
			}
			return false;
		}else {
			al.undoToken();
			return true;
		}
	}
	
	private boolean F(ObjetoString v) {
		int token = al.yylex();
		switch(token) {
		case 50:
			if(E(v)) {
				token = al.yylex();
				if(token==60) return true;
			}
			return false;
		case 70:
			v.setV(al.getLexema());
			return true;
		}
		return false;
	}
	
	private boolean T(ObjetoString v) {
		if(F(v))
			if(Tp(v))
				return true;
		return false;
	}
	
	private boolean Tp(ObjetoString v) {
		ObjetoString v2  = new ObjetoString();
		int token = al.yylex();
		if(token==30||token==40) {
			if(F(v2)) {
				v.setV(v.getV()+" "+v2.getV()+" "+(token==30?"*":"/"));
				if(Tp(v))
					return true;
			}
			return false;
		}else {
			al.undoToken();
			return true;
		}
	}
}