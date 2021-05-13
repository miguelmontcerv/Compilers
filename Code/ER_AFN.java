public class ER_AFN {
	AnalizadorLexico al;
	
	public ER_AFN(String[][] afd, String sigma) {
		al = new AnalizadorLexico(afd, sigma);
	}
	
	public int getLastToken() {
		return al.yylex();
	}
	
	public boolean E(AFN f) {
		if(T(f))
			if(Ep(f))
				return true;
		return false;
	}
	
	private boolean Ep(AFN f) {
		int token = al.yylex();
		AFN f2 = new AFN();
		if(token == 10) {	// OR
			if(T(f2)) {
				f.unirAFN(f2);
				if(E(f))
					return true;
			}
			return false;
		}else {
			al.undoToken();
			return true;
		}
	}
	
	private boolean T(AFN f) {
		if(C(f))
			if(Tp(f))
				return true;
		return false;
	}
	
	private boolean Tp(AFN f) {
		int token = al.yylex();
		AFN f2 = new AFN();
		if(token == 20) {	// AND
			if(C(f2)) {
				f.concaAFN(f2);
				if(Tp(f))
					return true;
			}
			return false;
		}else {
			al.undoToken();
			return true;
		}
	}
	
	private boolean C(AFN f) {
		if(F(f))
			if(Cp(f))
				return true;
		return false;
	}
	
	private boolean Cp(AFN f) {
		int token = al.yylex();
		
		switch(token) {
			case 30:	// +
				f.cerrPos();
				if(Cp(f))
					return true;
				return false;
				
			case 40:	// *
				f.cerrKleen();
				if(Cp(f))
					return true;
				return false;
				
			case 50:	// ?
				f.cerrOpcional();
				if(Cp(f))
					return true;
				return false;
				
		}
		
		al.undoToken();
		return true;
	}
	
	private boolean F(AFN f) {
		int token = al.yylex();
		
		switch(token) {
			case 60:	// (
				if(E(f)){
					token = al.yylex();
					if(token == 70)
						return true;
				}
				return false;
				
			case 80:	// [
				token = al.yylex();
				if(token == 110) {	//		\?	3
					char st1 = (al.getLexema().length()>1?al.getLexema().charAt(1):al.getLexema().charAt(0));
					token = al.yylex();
					if(token == 100) {
						token = al.yylex();
						if(token == 110) {
							char st2 = (al.getLexema().length()>1?al.getLexema().charAt(1):al.getLexema().charAt(0));
							token = al.yylex();
							if(token == 90) {
								if(verificarRango(st1,st2)) {
									f.creaAFNBasico(st2, st1);
									return true;
								}
							}
						}
					}
				}
				return false;
				
			case 110:	// SIMB
				char st1 = (al.getLexema().length()>1?al.getLexema().charAt(1):al.getLexema().charAt(0));
				f.creaAFNBasico(st1);
				return true;

		}
		return false;
	}
	
	private boolean verificarRango(char rangoMenor, char rangoMayor) {
		System.out.println("Verificando menor "+rangoMenor+", mayor "+rangoMayor);
		if((int)rangoMayor>=(int)rangoMenor) {
			return true;
		}
		return false;
	}
}
