import java.io.Serializable;

public class Transicion implements Serializable{

	private char simbInf1;
	private char simbSup1;
	private Estado edo;
	
	public Transicion(char simb, Estado e) {
		this.simbInf1 = simbSup1 = simb; 
		this.edo = e;
	}
	
	public Transicion(char simb1/*Superior*/, char simb2/*Inferior*/, Estado e) {
		this.simbInf1 = simb2;
		this.simbSup1 = simb1;
		this.edo = e;
	}
	
	public Transicion() {
		this.edo = null;
	}
	
	public void setTransicion( char rangoMayor, char rangoInferior, Estado e) {
		this.simbInf1 = rangoInferior;
		this.simbSup1 = rangoMayor;
		this.edo = e;
	}
	
	public void setTransicion(char elemento, Estado e) {
		this.simbInf1 = simbSup1 = elemento;
		this.edo = e;
	}
	
	public char getSimbInf() {
		return this.simbInf1;
	}
	
	public void setSimbInf(char v) {
		this.simbInf1 = v;
	}
	
	public char getSimbSup() {
		return this.simbSup1;
	}
	
	public void setSimbSup(char v) {
		this.simbSup1 = v;
	}
	
	public Estado getEdoTransicion(char s) {
		if(simbInf1<=s && s<=simbSup1) {
			return edo;
		}
		return null;
	}
}
