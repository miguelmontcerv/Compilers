public class AnalizadorSintactico {
	AnalizadorLexico al;
	
	public AnalizadorSintactico(String[][] afd, String sigma) {
		al = new AnalizadorLexico(afd, sigma);
	}
	
	public int getLastToken() {
		return al.yylex();
	}
	
	public boolean E() {
		if(T())
			if(Ep())
				return true;
		return false;
	}
	
	private boolean Ep() {
		int token = al.yylex();
		System.out.println("Ep= " + token);
		if(token==10||token==20) {
			if(T())
				if(Ep())
					return true;
			return false;
		}else {
			al.undoToken();
			System.out.println("Ep: Realize undoToken de "+token);
			return true;
		}
	}
	
	private boolean F() {
		int token = al.yylex();
		System.out.println("F= " + token);
		switch(token) {
		case 50:
			if(E()) {
				token = al.yylex();
				System.out.println("E= " + token);
				if(token==60) return true;
			}
			return false;
		case 70:
			return true;
		}
		return false;
	}
	
	private boolean T() {
		if(F())
			if(Tp())
				return true;
		return false;
	}
	
	private boolean Tp() {
		int token = al.yylex();
		System.out.println("Tp= " + token);
		if(token==30||token==40) {
			if(F())
				if(Tp())
					return true;
			return false;
		}else {
			System.out.println("Tp: Realize undoToken de "+token);
			al.undoToken();
			return true;
		}
	}
	
}
