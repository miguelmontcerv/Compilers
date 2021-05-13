import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;



public class analizador{
	
	private Scanner in = new Scanner(System.in);
	private Map<String, AFN> listaAutomatas = new HashMap<String, AFN>();
	private Map<String, AFN> especiales = new HashMap<String, AFN>();
	private Map<String, String[][]> afds = new HashMap<String, String[][]>();   
        String palabras_panel = "";
	
	public boolean menu(int opcion) {
		                		
		switch(opcion) {
			case 1:
				createAFN();
				break;
			case 2:
				modifyAFN();
				break;
			case 3:
				printAFN();
				break;
			case 4:
				createSpecial();
				break;
			case 5:
				genAFD();
				break;
			case 6:
				printAFD();
				break;
			case 7:
				agregarAutomataEspecial();
				break;
			case 8:
				importAFD();
				break;
			case 9:
				probarAnalizadorLexico();
				break;
			case 10:
				probarAnalizadorSintactico();
				break;
			case 11:
				evaluador();
				break;
			case 12:
				convertidorPostfijo();
				break;
			case 13:
				crearAFN_ER();
				break;
			case 14:
				return true;
			default:
				JOptionPane.showMessageDialog(null,"Ingrese una opcion válida");
				break;
		}
		return false;
		
	}
	
	private void createAFN() {
		char opcion;
		String rango=null;
		boolean correcto=false;
		AFN a1 = new AFN();
		//System.out.println("La Transicion es un rango de valores? (Si: s / No: n)");
		opcion = JOptionPane.showInputDialog(null,"La Transicion es un rango de valores? (Si: s / No: n)").charAt(0);
		
		switch(opcion) {
		
			case 's','S':
				
				while(!correcto) {
					//System.out.println("Ingrese el rango de Transicion(Ejemplo: a-z o c2-5):");
					rango = JOptionPane.showInputDialog(null,"Ingrese el rango de Transicion (Ejemplo: a-z o c2-5):");	//A-C	c65-67
					if(rango.charAt(0)=='c') {
						rango = rango.substring(1, rango.length());
						String[] rangos = rango.split("-");
						if(verificarRango((char)Integer.parseInt(rangos[0]),(char)Integer.parseInt(rangos[1]))) {		//a-z
							//System.out.println("Completado correctamente");
                                                        JOptionPane.showMessageDialog(null,"Completado correctamente");
							//System.out.printf("%d %d", (int)rango.charAt(0), (int)rango.charAt(2) );
							a1.creaAFNBasico(Integer.parseInt(rangos[1]) /* Superior */, Integer.parseInt(rangos[0]) /* Inferior */);
							//System.out.println("Ingrese el nombre del automata:");                                                        
							rango = JOptionPane.showInputDialog(null,"Ingrese el nombre del automata:");
							listaAutomatas.put(rango, a1);
							correcto=true;
						}else {
                                                        JOptionPane.showMessageDialog(null,"Error, el rango izquierdo es mayor al derecho. Favor de verificar ascii.");
							//System.out.println("Error, el rango izquierdo es mayor al derecho. Favor de verificar ascii.");
						}
					}else {
						if(verificarRango(rango.charAt(0),rango.charAt(2))) {		//a-z
							//System.out.println("Completado correctamente");
                                                        JOptionPane.showMessageDialog(null,"Completado correctamente :D");
							//System.out.printf("%d %d", (int)rango.charAt(0), (int)rango.charAt(2) );
							a1.creaAFNBasico(rango.charAt(2) /* Superior */, rango.charAt(0) /* Inferior */);
							//System.out.println("Ingrese el nombre del automata:");
							rango = JOptionPane.showInputDialog(null,"Ingrese el nombre del automata:");
							listaAutomatas.put(rango, a1);
							correcto=true;
						}else {
                                                        JOptionPane.showMessageDialog(null,"Error, el rango izquierdo es mayor al derecho. Favor de verificar ascii.");
							//System.out.println("Error, el rango izquierdo es mayor al derecho. Favor de verificar ascii.");
						}
					}
				}
			
				break;
				
			case 'n','N':
				//System.out.println("Ingrese la Transicion");
				rango = JOptionPane.showInputDialog(null,"Ingrese la Transicion");
				if(rango.charAt(0)=='c') {
					rango = rango.substring(1, rango.length());
					if(rango.length()>=1) {
                                                JOptionPane.showMessageDialog(null,"Completado correctamente");
						//System.out.println("Completado correctamente");
						a1.creaAFNBasico(Integer.parseInt(rango));						
                                                //System.err.println("Ingrese el nombre del automata:");
						rango = JOptionPane.showInputDialog(null,"Ingrese el nombre del automata:");
						listaAutomatas.put(rango, a1);
					}else {
                                                JOptionPane.showMessageDialog(null,"Ocurrio un error desconocido, pruebe de nuevo.");
						//System.err.println("Ocurrio un error desconocido, pruebe de nuevo.");
					}
				}else {
					if(rango.length()==1) {
						JOptionPane.showMessageDialog(null,"Completado correctamente :D");
						a1.creaAFNBasico(rango.charAt(0));
						rango = JOptionPane.showInputDialog(null,"Ingrese el nombre del automata:");
						listaAutomatas.put(rango, a1);
					}else {
                                                JOptionPane.showMessageDialog(null,"Ocurrio un error desconocido, pruebe de nuevo.");
						//System.err.println("Ocurrio un error desconocido, pruebe de nuevo.");
					}
				}
				break;	
		}
	}
	
	private void modifyAFN() {
		int opcion;
                String opcion1;
                opcion1 = JOptionPane.showInputDialog(null,"Ingrese la opcion que desee:\n1) Generar cerradura positiva a un AFN (^+)\n"
                        + "2) Generar cerradura de kleen a un AFN (^*)\n3) Generar cerradura opcional a un AFN (?)\n4) Unir autómatas (Requiere dos AFN A|B)"
                        + "\n5) Concatenar autómatas (Requiere dos AFN AB)\n6) Cancelar");
		/*System.out.println("Ingrese la opcion que desee:");
		System.out.println("1) Generar cerradura positiva a un AFN (^+)");
		System.out.println("2) Generar cerradura de kleen a un AFN (^*)");
		System.out.println("3) Generar cerradura opcional a un AFN (?)");
		System.out.println("4) Unir autómatas (Requiere dos AFN A|B)");
		System.out.println("5) Concatenar autómatas (Requiere dos AFN AB)");
		System.out.println("6) Cancelar");*/
		opcion = Integer.parseInt(opcion1);
		switch (opcion) {
			case 1:
				genCerrPositiva();
				break;
			case 2:
				genCerrKleen();
				break;
			case 3:
				genCerrOpcional();
				break;
			case 4:
				unirAutomatas();
				break;
			case 5:
				concatenarAutomatas();
				break;
			case 6:
				break;
			default: 
				JOptionPane.showMessageDialog(null,"Ingrese una opción válida.");
				break;
		}
              JOptionPane.showMessageDialog(null,"Se modifico correctamente :D");
	}
        
        public void convertidorPostfijo(){

		Map<String, String[][]> automatas = new HashMap<String, String[][]>();
		//System.out.println("Ingrese la cadena que desee utilizar para probar el analizador: ");
		String sigma = JOptionPane.showInputDialog(null,"Ingrese la cadena que desee utilizar para probar el analizador:");
		
		//System.out.println("Ingrese el nombre del archivo \".txt\" a agregar:");
		String nombreDelArchivo="";
		nombreDelArchivo = JOptionPane.showInputDialog(null,"Ingrese el nombre del archivo \\\".txt\\\" a agregar:");
		try {
			FileInputStream fis = new FileInputStream(nombreDelArchivo.concat(".txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			automatas = (Map<String, String[][]>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			//System.out.println("Error al cargar el archivo");
                        JOptionPane.showMessageDialog(null,"Error al cargar el archivo");
		}
		String automata = "";
                String aux = "";
		do {
			//System.out.println("Seleccione un automata de la lista con el que se hará la prueba:");
			for(String s: automatas.keySet()) aux = aux + s;
			automata =  JOptionPane.showInputDialog(null,"Seleccione un automata de la lista con el que se hará la prueba:\n"+aux);
		}while(!automatas.containsKey(automata));
		
		ObjetoString v = new ObjetoString();
		ConvertidorPostfijo cp = new ConvertidorPostfijo(automatas.get(automata), sigma);
		if(cp.E(v)) {
			if(cp.getLastToken()== SimbolosEspeciales.FIN) {
				//System.out.println(v.getV());
                                JOptionPane.showMessageDialog(null,"Fin de cadena: "+v.getV());
                                
			}else {
                                JOptionPane.showMessageDialog(null,"Error al convertir a postfijo");
				//System.out.println("Error al convertir a postfijo");
			}
		}else {
                        JOptionPane.showMessageDialog(null,"Error al convertir a postfijo!!");
			//System.out.println("Error al convertir a postfijo");
		}
	}
        
        private void crearAFN_ER() {
		Map<String, String[][]> automatas = new HashMap<String, String[][]>();
		//System.out.println("Ingrese la cadena que desee utilizar para probar el analizador: ");
		String sigma = JOptionPane.showInputDialog(null,"Ingrese la cadena que desee utilizar para probar el analizador: ");
		
		//System.out.println("Ingrese el nombre del archivo \".txt\" a agregar:");
		//String nombreDelArchivo="";
		String nombreDelArchivo = JOptionPane.showInputDialog(null,"Ingrese el nombre del archivo \".txt\" a agregar:");
		try {
			FileInputStream fis = new FileInputStream(nombreDelArchivo.concat(".txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			automatas = (Map<String, String[][]>)ois.readObject();
			ois.close();
		} catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error al cargar el archivo");                  
			//System.out.println("Error al cargar el archivo");
		}
		String automata = "";
                String aux = "";
		do {
			//System.out.println("Seleccione un automata de la lista con el que se hará la prueba:");
			for(String s: automatas.keySet()) aux = aux + s+ "\n";
			automata = JOptionPane.showInputDialog(null,"Seleccione un automata de la lista con el que se hará la prueba:\n"+aux);
		}while(!automatas.containsKey(automata));
		
		AFN f = new AFN();
		ER_AFN ea = new ER_AFN(automatas.get(automata), sigma);
		if(ea.E(f)) {
			if(ea.getLastToken()== SimbolosEspeciales.FIN) {
				//System.out.println("Ingrese un nombre para el AFN");
				String name = JOptionPane.showInputDialog(null,"Ingrese un nombre para el AFN");
				listaAutomatas.put(name, f);
				//System.out.println("Automata creado correctamente");
                                JOptionPane.showMessageDialog(null,"Automata creado correctamente");
			}else {
                                JOptionPane.showMessageDialog(null,"Error al crear AFN");
				//System.out.println("Error al crear AFN");
			}
		}else {
                        JOptionPane.showMessageDialog(null,"Error al crear AFN");
			//System.out.println("Error al crear AFN");
		}
	}
        
        public void evaluador() {

		Map<String, String[][]> automatas = new HashMap<String, String[][]>();
		//System.out.println("Ingrese la cadena que desee utilizar para probar el analizador: ");
		String sigma = JOptionPane.showInputDialog(null,"Ingrese la cadena que desee utilizar para probar el analizador: ");
		
		//System.out.println("Ingrese el nombre del archivo \".txt\" a agregar:");
		String nombreDelArchivo="";
		nombreDelArchivo = JOptionPane.showInputDialog(null,"Ingrese el nombre del archivo \\\".txt\\\" a agregar:");
		try {
			FileInputStream fis = new FileInputStream(nombreDelArchivo.concat(".txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			automatas = (Map<String, String[][]>)ois.readObject();
			ois.close();
		} catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error al cargar el archivo");
			//System.out.println("Error al cargar el archivo");
		}
		String automata = "";
                String aux  = "";
		do {
			//System.out.println("Seleccione un automata de la lista con el que se hará la prueba:");
			for(String s: automatas.keySet()) aux = aux + s+"\n";
			automata = JOptionPane.showInputDialog(null,"Seleccione un automata de la lista con el que se hará la prueba:\n"+aux);
		}while(!automatas.containsKey(automata));
		
		ObjetoFloat v = new ObjetoFloat();
		Evaluador cp = new Evaluador(automatas.get(automata), sigma);
		if(cp.E(v)) {
			if(cp.getLastToken()==SimbolosEspeciales.FIN) {
				//System.out.println(v.getF());
                                JOptionPane.showMessageDialog(null,"Fin de cadena: "+v.getF());
			}else {
                                JOptionPane.showMessageDialog(null,"Error al convertir a postfijo EVALUADOR");
				//System.out.println("Error al convertir a postfijo");
			}
		}else {
			//System.out.println("Error al convertir a postfijo");
                        JOptionPane.showMessageDialog(null,"Error al convertir a postfijo EVALUADOR2");
		}
		
	}
        
        public void probarAnalizadorSintactico(){

		Map<String, String[][]> automatas = new HashMap<String, String[][]>();
		//System.out.println("Ingrese la cadena que desee utilizar para probar el analizador: ");
		String sigma = JOptionPane.showInputDialog(null,"Ingrese la cadena que desee utilizar para probar el analizador: ");
		
		//System.out.println("Ingrese el nombre del archivo \".txt\" a agregar:");
		String nombreDelArchivo="";
		nombreDelArchivo = JOptionPane.showInputDialog(null,"Ingrese el nombre del archivo \".txt\" a agregar:");
		try {
			FileInputStream fis = new FileInputStream(nombreDelArchivo.concat(".txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			automatas = (Map<String, String[][]>)ois.readObject();
			ois.close();
		} catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error al cargar el archivo");
			//System.out.println("Error al cargar el archivo");
		}
		String automata = "";
                String aux = "";
		do {
			//System.out.println("Seleccione un automata de la lista con el que se hará la prueba:");
			for(String s: automatas.keySet()) aux = aux + s + "\n";
			automata = JOptionPane.showInputDialog(null,"Seleccione un automata de la lista con el que se hará la prueba:\n"+aux);
		}while(!automatas.containsKey(automata));
		
		AnalizadorSintactico as = new AnalizadorSintactico(automatas.get(automata), sigma);
		if(as.E()) {
			int token = as.getLastToken();
			if(token==SimbolosEspeciales.FIN) {
                                JOptionPane.showMessageDialog(null,sigma + " es sintacticamente correcto");
				//System.out.println(sigma + " es sintacticamente correcto");
			}else {
                                JOptionPane.showMessageDialog(null,sigma + " es sintacticamente correcto");
				//System.out.println(sigma + " es sintacticamente incorrecto");
			}
		}else {
                        JOptionPane.showMessageDialog(null,sigma + " es sintacticamente correcto");
			//System.out.println("\""+ sigma + "\" es sintacticamente incorrecto");
		}
	}
		
	private void printAFN() {
		String automata,auxiliar = "";
		int opcion=0;
		boolean correcto=false, imprimir = true;		
		/*System.out.println("Que quiere imprimir:");
		System.out.println("1) AFN");
		System.out.println("2) AFN especial");*/
		
		while(!correcto) {
			opcion = Integer.parseInt(JOptionPane.showInputDialog("Que quiere imprimir:\n1) AFN\n2) AFN especial"));
			if(opcion >=1 && opcion<=2) {
				correcto = true;
			}else {
				JOptionPane.showMessageDialog(null,"El dato que ingresó no se encuentra en las opciones, intentelo de nuevo:");
			}
		}
		correcto = false;
		
		if(opcion == 1) {
			if(listaAutomatas.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen automatas a imprimir");
				imprimir = false;
				correcto = true;
			}
		}else {
			if(especiales.isEmpty()) {
				JOptionPane.showMessageDialog(null,"No existen automatas especiales a imprimir");
				imprimir = false;
				correcto = true;
			}
		}
		
		if(imprimir) {
			switch(opcion) {
			case 1: 
				while(!correcto) {
					auxiliar = "Seleccione un automata a imprimir:\n";
					for(String s: listaAutomatas.keySet()) {
						auxiliar = auxiliar + "Automata "+ s + "\n";
					}
					
					automata = JOptionPane.showInputDialog(auxiliar);
                                        auxiliar = "";
					if(listaAutomatas.containsKey(automata)) {
						correcto=true;
						listaAutomatas.get(automata).imprimeAFN(1);
                                                palabras_panel = listaAutomatas.get(automata).publicarAFN();
                                                //Imprimir AFN
					}else {
						JOptionPane.showMessageDialog(null,"Asegurese de escribir correctamente el automata.");
					}
				}
				break;
			default:
				while(!correcto) {
					auxiliar = "Seleccione un automata a imprimir:\n";
					for(String s: especiales.keySet()) {
						auxiliar = auxiliar + "Automata "+ s;
					}
					
					automata = JOptionPane.showInputDialog(auxiliar);
                                        auxiliar = "";
					if(especiales.containsKey(automata)) {
						correcto=true;
						especiales.get(automata).imprimeAFN(2);
                                                palabras_panel = especiales.get(automata).publicarAFN();
                                                //palabras_panel = "Holaa error aqui";
					}else {
						JOptionPane.showMessageDialog(null,"Asegurese de escribir correctamente el automata.");
					}
				}
				break;
			}
		}
		
		
		
	}
		
	private void genCerrPositiva() {
		
		if(listaAutomatas.isEmpty()) {
			//System.out.println("No cuenta con los suficientes automatas para realizar esta accion");
                        JOptionPane.showMessageDialog(null,"No cuenta con los suficientes automatas para realizar esta accion");
			return;
		}
		
		AFN a;
		String key,concat;             
		boolean correcto=false;
		concat = "A continuacion se muestran los automatas que tiene, ingrese el nombre de uno de la lista:";
		while(!correcto) {
			concat = concat + "\nAutomatas creados:\n";
			for(String s: listaAutomatas.keySet()) {
                            concat = concat + "\t"+s+"\n";
                            //System.out.printf("\t%s\n", s);
			}
			key = JOptionPane.showInputDialog(concat);
			if(listaAutomatas.containsKey(key)) {
				correcto = true;
				a = listaAutomatas.get(key);
				a = a.cerrPos();
				listaAutomatas.remove(key);
				listaAutomatas.put(key, a);
			}else {
				JOptionPane.showMessageDialog(null,"Asegurese de escribir el automata tal y como está en la lista");
			}
		}
	}
	
	private void genCerrKleen() {
		
		if(listaAutomatas.isEmpty()) {
			//System.out.println("No cuenta con los suficientes automatas para realizar esta accion");
                        JOptionPane.showMessageDialog(null,"No cuenta con los suficientes automatas para realizar esta accion");
			return;
		}
		
		AFN a;
		String key,concat;
		boolean correcto=false;
		concat = "A continuacion se muestran los automatas que tiene, ingrese el nombre de uno de la lista:\n";
		while(!correcto) {
			concat = concat + "Automatas creados:\n";
			for(String s: listaAutomatas.keySet()) {
				//System.out.printf("\t%s\n", s);
                                concat = concat + "\t" + s + "\n";
			}
			key = JOptionPane.showInputDialog(concat);
                        concat = "A continuacion se muestran los automatas que tiene, ingrese el nombre de uno de la lista:\n";
			if(listaAutomatas.containsKey(key)) {
				correcto = true;
				a = listaAutomatas.get(key);
				a = a.cerrKleen();
				listaAutomatas.remove(key);
				listaAutomatas.put(key, a);
			}else {
				JOptionPane.showMessageDialog(null,"Asegurese de escribir el automata tal y como está en la lista");
			}
		}
	}
		
	private void genCerrOpcional() {
		
		if(listaAutomatas.isEmpty()) {
			JOptionPane.showMessageDialog(null,"No cuenta con los suficientes automatas para realizar esta accion");
			return;
		}
		
		AFN a;
		String key,concat;
		boolean correcto=false;
		concat = "A continuacion se muestran los automatas que tiene, ingrese el nombre de uno de la lista:\n";
		while(!correcto) {
			concat = concat + "Automatas creados:\n";
			for(String s: listaAutomatas.keySet()) {
				concat = concat + "\t" + s + "\n";
			}
			key = JOptionPane.showInputDialog(concat);
                        concat = "A continuacion se muestran los automatas que tiene, ingrese el nombre de uno de la lista:\n";
			if(listaAutomatas.containsKey(key)) {
				correcto = true;
				a = listaAutomatas.get(key);
				a = a.cerrOpcional();
				listaAutomatas.remove(key);
				listaAutomatas.put(key, a);
			}else {
				JOptionPane.showMessageDialog(null,"Asegurese de escribir el automata tal y como está en la lista");
			}
		}
	}
	
	private void unirAutomatas() {
		AFN a1;
		String key1, key2,concat;
		boolean correcto=false;
		if(listaAutomatas.size()>=2) {
			//System.out.println("A continuacion se muestran los automatas que tiene, ingrese los nombres a unir de la lista:");
			concat = "A continuacion se muestran los automatas que tiene, ingrese los nombres a unir de la lista:";
                        while(!correcto) {
				concat = concat + "\nAutomatas creados:\n";
				for(String s: listaAutomatas.keySet()) {
					concat = concat + "\t" + s + "\n";
				}
				key1 = JOptionPane.showInputDialog(concat+"\nPrimer automata");
				key2 = JOptionPane.showInputDialog("Segunda automata");
				if(listaAutomatas.containsKey(key1) && listaAutomatas.containsKey(key2)) {
					correcto = true;
					a1 = listaAutomatas.get(key1);
					a1 = a1.unirAFN(listaAutomatas.get(key2));
					listaAutomatas.remove(key1);
					listaAutomatas.remove(key2);
					listaAutomatas.put(key1, a1);
                                        JOptionPane.showMessageDialog(null,"Automatas Unidos Correctamente :D");
				}else {
					JOptionPane.showMessageDialog(null,"Asegurese de escribir el automata tal y como está en la lista");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null,"No cuenta con los suficientes automatas para realizar esta operacion");
		}
	}

	private void concatenarAutomatas() {
		AFN a1;
		String key1, key2,concat;
		boolean correcto=false;
		if(listaAutomatas.size()>=2) {			
			while(!correcto) {
                                concat = "A continuacion se muestran los automatas que tiene, ingrese los nombres a unir de la lista:";
				concat = concat + "Automatas creados:\n";
				for(String s: listaAutomatas.keySet()) {
					//System.out.printf("\t%s\n", s);
                                        concat = concat + "\t" + s + "\n";
				}
				key1 = JOptionPane.showInputDialog(concat+"\nPrimer automata");
				key2 = JOptionPane.showInputDialog("Segunda automata");
				if(listaAutomatas.containsKey(key1) && listaAutomatas.containsKey(key2)) {
					correcto = true;
					a1 = listaAutomatas.get(key1);
					a1 = a1.concaAFN(listaAutomatas.get(key2));
					listaAutomatas.remove(key1);
					listaAutomatas.remove(key2);
					listaAutomatas.put(key1, a1);
                                        JOptionPane.showMessageDialog(null,"Automatas Concatenados Correctamente :D");
				}else {
					JOptionPane.showMessageDialog(null,"Asegurese de escribir el automata tal y como está en la lista");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null,"No cuenta con los suficientes automatas para realizar esta operacion");
		}
	}
	
	private boolean verificarRango(char rangoMenor, char rangoMayor) {
		if((int)rangoMayor>=(int)rangoMenor) {
			return true;
		}
		return false;
	}
	
	private void createSpecial() {
		int tokenRing;
		boolean salga = false, dejarDePedirToken = false;
		String key,concat;
		HashSet<String> automatasAUnir = new HashSet<String>();
		Map<Integer, AFN> aUnir = new HashMap<Integer, AFN>(); 
		
		if(listaAutomatas.size()<2) {                    
			JOptionPane.showMessageDialog(null,"No se cuenta con los suficientes AFN's para realizar esta operación");
			return;
		}
		
		concat = "A continuacion se muestra la lista de automatas:\n";
                String automatas = "";
                int flag = 0;
		for(String s: listaAutomatas.keySet()) {
			concat = concat + "\t" + s + "\n";
                        automatas = automatas + "\t" + s + "\n";                        
		}   		
		
		while(aUnir.size() < listaAutomatas.size() && !salga) {
			if(flag == 1)
                            concat = concat +"Automatas: \n"+ automatas + "\nEscriba el nombre del automata a añadir(que no haya seleccionado antes):\n";
                        else
                            concat = concat + "Escriba el nombre del automata a añadir:\n";
			key = JOptionPane.showInputDialog(concat);
                        flag = 1;
                        concat = "";
			if(key.contentEquals("exit") && !aUnir.isEmpty()) {
				salga = true;
			}else {
				if(key.contentEquals("exit")) {
					JOptionPane.showMessageDialog(null,"No es posible avanzar si no se ha seleccionado por lo menos un automata");
				}else {
					if(automatasAUnir.contains(key)) {
						JOptionPane.showMessageDialog(null,"Ingrese un automata que no haya seleccionado antes");
					}else {
						while(!dejarDePedirToken) {		
                                                        String opcTok = JOptionPane.showInputDialog("Ingrese el Token asociado a este automata");
							tokenRing = Integer.parseInt(opcTok);
							if(aUnir.keySet().contains(tokenRing)) {
								JOptionPane.showMessageDialog(null,"Este token ya ha sido asignado");
							}else {
								dejarDePedirToken = true;
								aUnir.put(tokenRing, listaAutomatas.get(key));
							}
						}
						dejarDePedirToken = false;
						automatasAUnir.add(key);
					}
				}
			}
		}
		concat = "Se uniran los siguientes automatas con su respectivo token\n";
		for(String s: automatasAUnir) {
			concat = concat + s + "\n";
		}
		JOptionPane.showMessageDialog(null,concat);
		for(Integer i: aUnir.keySet()) {
			for(Estado e: aUnir.get(i).edosAcept) {
				e.setToken(i);
			}
		}
		
		AFN especial = new AFN();
		Estado eInicial = new Estado();
		Transicion t;
		for(Integer i: aUnir.keySet()) {
			t = new Transicion(SimbolosEspeciales.EPSILON, aUnir.get(i).edoIni);
			eInicial.setTransicion(t);
			especial.alfabeto.addAll(aUnir.get(i).alfabeto);
			especial.edosAcept.addAll(aUnir.get(i).edosAcept);
			especial.edosAFN.addAll(aUnir.get(i).edosAFN);
		}
		especial.edoIni=eInicial;
		especial.edosAFN.add(eInicial);
				
		key = JOptionPane.showInputDialog("Ingrese un nombre para el automata especial");
		especiales.put(key, especial);
                JOptionPane.showMessageDialog(null,"AFN especial creado correctamente");
		
		for(String s: automatasAUnir) {
			listaAutomatas.remove(s);
		}
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("nombre.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(especiales);
                        oos.close();
		} catch (IOException e) {
                        System.out.println("analizador.createSpecial()");
		}
	}
        
        //Here 08/04/2021
	private void genAFD() {
		String automata="",concat;
		boolean correcto = false;
		
		if(especiales.isEmpty()) {
			JOptionPane.showMessageDialog(null,"No cuenta con los suficientes AFN's especiales para realizar esta acción");
			return;
		}
		
		concat = "Ingrese el automata especial que quiera convertir a AFD\n";
		/*Aplicar cerradura al Estado inicial*/
		for(String s: especiales.keySet()) {
			//System.out.printf("\t%s\n",s);
                        concat = concat + "\t" + s + "\n";
		}
		
		while(!correcto) {
			automata  = JOptionPane.showInputDialog(concat);
			if(especiales.keySet().contains(automata)) {
				correcto = true;
			}else {
				JOptionPane.showMessageDialog(null,"Ingrese un automata que se encuentre en la lista");
			}
		}
		
		// Comienza proceso de AFD
		int EstadoActual = 0;
		int EstadoDelAFD;
		AFN convertir = especiales.get(automata);
		Map<Integer, HashSet<Estado>> EstadosAFD = new HashMap<Integer, HashSet<Estado>>();		//Almacena los Estados del AFD con sus respectivos Estados del AFN antiguo
		List<Integer> aux = new ArrayList<Integer>();		//Sirve para realizar el bucle hasta que no se encuentren mas Estados del AFD
		HashSet<Estado> resultadoDeMoverA;	//Se usa para guardar los Estados a los que se aplicara cerradura
		HashSet<Estado> EstadosResultadoDeCerradura;		//Servirá para guardar las cerraduras epsilon de los Estados a aplicar la cerradura
		Map<HashSet<Estado>, Integer> EstadosAFDInverso = new HashMap<HashSet<Estado>, Integer>();
		
		/*for(Estado e: convertir.cerraduraEpsilon(convertir.getEdoInicial())) {
			System.out.printf("%d ",e.getIdEstado());
		}*/
		//System.out.println();
		
		// Primero se aplica cerradura al Estado inicial y se guarda en EstadosAFD
		EstadosAFD.put(EstadoActual, convertir.cerraduraEpsilon(convertir.getEdoInicial()));
		
		// La pila se inicializa en es Estado 0 del nuevo automata
		aux.add(EstadoActual);
		
		// Con la pila creada nuevamente se anexaran Estados hasta terminar de analizar cada Estado nuevo que vaya apareciendo
		while(!aux.isEmpty()) {
			// Obtengo el primer Estado y lo remuevo
			EstadoDelAFD = aux.get(0);
			aux.remove(0);
			
			// Para todo el alfabeto realizo el procedimiento CerraduraEpsilon(MoverA(listaDeEstados, caracter))
			for(Character c: convertir.alfabeto) {
				// En esta lista se almacenan los Estados a los que se llega con un simbolo en especial epsilon
				EstadosResultadoDeCerradura = new HashSet<Estado>();
				
				// En resultado de mover se guardan los Estados a los que se llega con un caracter en especifico 
				resultadoDeMoverA = convertir.moverA(EstadosAFD.get(EstadoDelAFD), c);
				
				// Aplico la cerradura epsilon a cada Estado que me dio de resultado la operacion MoverA
				for(Estado e: resultadoDeMoverA) {
					EstadosResultadoDeCerradura.addAll(convertir.cerraduraEpsilon(e));
				}
				
				// Si no es vacio mi conjunto analizado lo analizo
				if(!EstadosResultadoDeCerradura.isEmpty()) {
					
					// Si no se encuentra el conjunto de Estados en mi map lo agrego con su respectivo id de Estado así como a la pila a analizar
					if(!EstadosAFD.containsValue(EstadosResultadoDeCerradura)) {
						EstadoActual++;
						EstadosAFD.put(EstadoActual, EstadosResultadoDeCerradura);
						EstadosAFDInverso.put(EstadosResultadoDeCerradura, EstadoActual);
						aux.add(EstadoActual);
					}
				}
			}
		}
                //Esto va en el panel                
		int token;
		//Imprimo mis Estados del AFD
		palabras_panel = "Estados Actuales\n";
		for(Integer i: EstadosAFD.keySet()) {
			palabras_panel = palabras_panel + "\nEstado "+i+"{\n";
			for(Estado e: EstadosAFD.get(i)) {
				palabras_panel = palabras_panel + " "+e.getIdEstado()+"\n";
			}
			palabras_panel = palabras_panel + "}";                        
		}

		String [][]matriz = new String[EstadosAFD.size()][256];
		
		for(int fila=0; fila<EstadosAFD.size(); fila++) {
			for(int columna=0; columna<256; columna++) {
				if(columna<255) {
					EstadosResultadoDeCerradura = new HashSet<Estado>();
					resultadoDeMoverA = convertir.moverA(EstadosAFD.get(fila) ,(char)columna);
					for(Estado e: resultadoDeMoverA) {
						EstadosResultadoDeCerradura.addAll(convertir.cerraduraEpsilon(e));
					}
					if(EstadosAFD.containsValue(EstadosResultadoDeCerradura)) {
						matriz[fila][columna] = EstadosAFDInverso.get(EstadosResultadoDeCerradura)+"";
					}else {
						/*Se cambio la forma de guardar el AFD, ahora es -1 si no tiene Transicion y existe en el alfabeto y -2 cuando no tiene Transicion y no existe en el alfabeto*/
						if(convertir.alfabeto.contains((char)columna)){
							matriz[fila][columna] = "-1";
						}else {
							matriz[fila][columna] = "-2";
						}
						/* Fin del cambio */
					}
				}else {
					token=-1;
					for(Estado e: EstadosAFD.get(fila)) {
						if(e.getToken()!=-1) {
							token = e.getToken();
						}
					}
					matriz[fila][columna]=token+"";
				}
			}
		}
		//Falta esta parte
               /* palabras_panel = palabras_panel + "\n\n";
		for(int fila=0; fila<EstadosAFD.size(); fila++) {
			for(int columna=0; columna<256; columna++) {
				if(columna<255) {
					if(matriz[fila][columna]!="-1") {
                                                palabras_panel = palabras_panel +"\t("+ (char)columna +")"+ matriz[fila][columna];
						//System.out.printf("\t(%c)%s", (char)columna, matriz[fila][columna]);
					}
				}else {
                                        palabras_panel = palabras_panel +"\tToken:"+ matriz[fila][columna]+"\n";
					//System.out.printf("\tToken:%s", matriz[fila][columna]);
				}
			}	
                    palabras_panel = palabras_panel + "\n";
                    //System.out.println("\n");
		} */
		
		String key="";		
		key = JOptionPane.showInputDialog("Ingrese un nombre para el AFD");;
		afds.put(key, matriz);
		
		especiales.remove(automata);
		key = JOptionPane.showInputDialog("Ingrese el nombre del archivo para guardar");		
                
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(key.concat(".txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(afds);
			oos.close();
                        JOptionPane.showMessageDialog(null,"AFD creado correctamente con base en el AFN especial");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void agregarAutomataEspecial() {
            System.out.println("analizador.agregarAutomataEspecial()");
		try {
			FileInputStream fis = new FileInputStream("nombre.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
                        
			especiales.putAll((Map<String, AFN>)ois.readObject());
                        ois.close();
                        
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Hey");
		}
	}

	@SuppressWarnings("unchecked")
	private void importAFD() {
		String nombreDelArchivo="";		
		nombreDelArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo sin la extension txt");
		try {
			FileInputStream fis = new FileInputStream(nombreDelArchivo.concat(".txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			afds.putAll((Map<String, String[][]>)ois.readObject());
			ois.close();
                        JOptionPane.showMessageDialog(null,"AFD importado correctamente");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Holi");
		}
	}

	private void printAFD() {
		boolean correcto = false;
		String aImprimir="",concat;
		
		if(afds.isEmpty()) {
			JOptionPane.showMessageDialog(null,"No tiene automatas que imprimir");
			return;
		}
		
		concat = "Ingrese el AFD que desee imprimir\n";
		while(!correcto) {
			for(String s: afds.keySet()) {
				//System.out.println("\t"+s);
                                concat =  concat + "\t" + s + "\n";
			}
			aImprimir = JOptionPane.showInputDialog(concat);
                        palabras_panel = "";
			if(afds.containsKey(aImprimir)) {
				correcto = true;
				for(int fila=0; fila<afds.get(aImprimir).length; fila++) {
					for(int columna=0; columna<256; columna++) {
						if(columna<255) {
							if(!afds.get(aImprimir)[fila][columna].contentEquals("-2")) {
                                                                palabras_panel = palabras_panel + "\t("+(char)columna+")"+afds.get(aImprimir)[fila][columna];
								System.out.printf("\t(%c)%s", (char)columna, afds.get(aImprimir)[fila][columna]);
							}
						}else {
                                                        palabras_panel = palabras_panel + "\tToken:"+afds.get(aImprimir)[fila][columna];
							System.out.printf("\tToken:%s", afds.get(aImprimir)[fila][columna]);
						}
					}	
					palabras_panel = palabras_panel +"\n";
                                        System.out.println("\n");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Intentelo de nuevo");
			}
		}
		
	}
        
        public String publicar(){
            return palabras_panel;
        }
        
        private void probarAnalizadorLexico() {
		Map<String, String[][]> automatas = new HashMap<String, String[][]>();
		//System.out.println("Ingrese la cadena que desee utilizar para probar el analizador: ");
		String sigma = JOptionPane.showInputDialog("Ingrese la cadena que desee utilizar para probar el analizador:");
		
		//System.out.println("Ingrese el nombre del archivo \".txt\" a agregar:");
		String nombreDelArchivo="";
		nombreDelArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo \\\".txt\\\" a agregar:");
		try {
			FileInputStream fis = new FileInputStream(nombreDelArchivo.concat(".txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			automatas = (Map<String, String[][]>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.out.println("Holi en Upload file");
                        JOptionPane.showMessageDialog(null,"Intentelo de nuevo");
		}
		String automata = "";
		do {
                        String aux = "";
                        aux = "Seleccione un automata de la lista con el que se hará la prueba:\n";
			//System.out.println("Seleccione un automata de la lista con el que se hará la prueba:");
			for(String s: automatas.keySet()) aux = aux + "\n" +s; //System.out.println(s);
			automata = JOptionPane.showInputDialog(aux);
		}while(!automatas.containsKey(automata));
		
		AnalizadorLexico al = new AnalizadorLexico(automatas.get(automata), sigma);
		//printAll(automatas.get(automata));
		int token;
                palabras_panel = "";
		while((token=al.yylex())!=0) {
                        palabras_panel = palabras_panel + "Token: " + token + " y lexema: " + al.getLexema()+"\n";
			System.out.println("Token: " + token + " y lexema: " + al.getLexema());
		}
	}
}

