
public class Util {
		
	// Contructor vacio o por defecto
	public Util(){}
	
	// Valida que la letra de la solucion sea una entre A-Z
	public boolean validarLetras(char sol, char[] var) {
		
		boolean centinela = false;
		
		for(char c: var) {
			if(c == sol) centinela = true;
		}
		
		return centinela;
	}

}
