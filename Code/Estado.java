import java.io.*;
import java.util.*;

public class Estado implements Serializable{
	static int contadorIdEstado=0;
	private int idEstado1;
	private boolean edoAcept1;
	private int token1;
	private HashSet<Transicion> trans1 = new HashSet<Transicion>();
	
	public Estado() {
		this.edoAcept1 = false;
		this.token1 = -1;
		//System.err.println(contadorIdEstado);
		this.idEstado1 = contadorIdEstado++;
		this.trans1.clear();
	}
	
	public int getIdEstado() {
		 return this.idEstado1;
	}
	
	public void setIdEstado(int x) {
		this.idEstado1 = x;
	}
	
	public boolean getEdoAcept() {
		return this.edoAcept1;
	}
	
	public void setEdoAcept(boolean x) {
		this.edoAcept1 = x;
	}
	
	public int getToken() {
		return this.token1;
	}
	
	public void setToken(int token) {
		this.token1 = token;
	}
	
	public HashSet<Transicion> getTransiciones(){
		return trans1;
	}
	
	public void setTransicion(Transicion t) {
		this.trans1.add(t);
	}
}
