import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


public static void main(String[] args) {
	
	//Variables para inputs
	@SuppressWarnings("resource")
	Scanner input = new Scanner(System.in);
	
	// Variable para saber si se econtro la solucion
	boolean solucionEncontrada = false;
	
	// Arreglo de letras que pueden ser los nodos
	char[] vertices = {'A','B','C','D','E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
			 			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	System.out.println();
	
	//Variables a utilizar para guardar y validar la solucion
	char sol = 'A';
	boolean solcionIsCorrect = false;
	
	// Se valida que se ingrese datos coherentes en la solucion
	while(!solcionIsCorrect) {
		
		Util util = new Util();
		
		sol = 'A';
		solcionIsCorrect = true;
		
		try {
			System.out.print("Ingrese la solucion a buscar (A-Z): ");
			sol = input.next().charAt(0);
			sol = Character.toUpperCase(sol);
			solcionIsCorrect = util.validarLetras(sol,vertices);
			if(!solcionIsCorrect) {
				System.out.println("El caracter ingresado no es una letra");
			}
			
		}catch(InputMismatchException e) {
			System.out.println("Solo ingrese letras de la A-Z");
			solcionIsCorrect = false;
		}
	}
	
	int p = 0;
	boolean profundidadIsCorrect = false;
	
	// Se valida que se ingrese datos coherentes en la profundidad maxima
	while(!profundidadIsCorrect) {
		
		p = 0;
		profundidadIsCorrect = true;
		
		try {
			System.out.print("Ingrese la profunidad maxima:");
			p = input.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Solo ingrese numeros enteros");
			profundidadIsCorrect = false;
			 input.nextLine();
		}
	}
	input.nextLine();
	
	// Guarda la cantidad de nodos por nivel
	int [] cantNodosPorNivel = new int[p+1];
	
	// Guarda la suma de todos los nodos de cada nivel
	int suma = 1;
	cantNodosPorNivel[0] = suma;
	System.out.println();

	// Imprime que en el primer nivel siempre sera solo un nodo, el nodo raiz
	System.out.print("Ingrese la cantidad de nodos/vertices en el nivel 0 : 1 (Por defecto nodo raiz)\n");
	
	// for para pregunar la cantidad de nodos por  nivel
	for(int i=1; i<=p; i++) {
		
		boolean valorIsCorrect = false;
		
		// Se valida que se ingrese datos coherentes en la cantidad de nodos por nivel
		while(!valorIsCorrect) {
			
			valorIsCorrect = true;
			
			try {
				System.out.print("Ingrese la cantidad de nodos/vertices en el nivel " + i + " :");
				cantNodosPorNivel[i] = input.nextInt(); 
			}catch(InputMismatchException e) {
				System.out.println("Solo ingrese numeros enteros");
				valorIsCorrect = false;
				 input.nextLine();
			}
		}
		input.nextLine();
		
		suma = suma + cantNodosPorNivel[i];
		cantNodosPorNivel[i] = suma;
	}
	
	// Matriz de adyacencia
	int[][] matrizAd = new int[suma][suma];
	
	// for para preguntar los datos de la matriz de adyacencia
	for(int i=0; i<suma; i++) {
		System.out.print("\nFila #"+i+": \n");
		for(int j=0; j<suma; j++) {
			
			boolean valorIsCorrect = false;
			
			// Se valida que se ingrese datos coherentes en la matriz de adyacencia
			while(!valorIsCorrect) {
				
				valorIsCorrect = true;
				
				try {
					System.out.print("Posicion ["+i+"]["+j+"]: ");
					matrizAd[i][j] = input.nextInt();
					
					if(!(matrizAd[i][j]==1 || matrizAd[i][j]==0)) {
						valorIsCorrect = false;
						System.out.println("Solo ingrese 0 o 1");
					}
					
				}catch(InputMismatchException e) {
					System.out.println("Solo ingrese numeros (0 o 1)");
					valorIsCorrect = false;
					 input.nextLine();
				}
			}
			input.nextLine();
			
		}
	}
	
	// for del proceso iteracion por cada nivel
	for(int i=0; i<=p; i++) {
		
		System.out.println();
		System.out.println("ITERACION #"+(i+1)+":");
		
		int n = cantNodosPorNivel[i];
		BPI g = new BPI(n); //inicializa un BPI con n como cantidad de nodos a explorar
	   
		// Setea el contenido de los nodos/vertices
	    g.setVertices(vertices);
	    
	    // Hace que el atributo de g sea igual a la sol
	    g.solucion = sol;
	    
	    // Crea el grafo en la clase BPI agregando los aristas
	    for(int k=0; k<n; k++) {
	    	for(int j=0; j<n; j++) {
	    		if(matrizAd[k][j] == 1) {
	    			g.addEdge(k, j);
	    		}
	    	}
	    }
	    
	    System.out.println();
	    System.out.println();
	    
	    // Manda a llamar el metodo de el recorrido de DFS limitado para el BPI
	    g.BPITraverse();
	    
	    System.out.println();
	    
	    // Imprime la matriz de adyacencia actual
	    System.out.println("Matriz de adyacencia (grafo no dirigido):");
	    g.printVertices();
	    System.out.println();
	    
	    // Muestra la solucion y rompe el ciclo
	    if(g.solucionFounded == true) {
	    	solucionEncontrada = g.solucionFounded;
	    	System.out.print("El recorrido De la solucion es: ");
	    	for(int k=0; k<g.verticesVisitados.size(); k++) {
        		
        		System.out.print(g.verticesVisitados.get(k));
        		if(k != g.verticesVisitados.size()-1) System.out.print("->");
	    	}
	    	break;
	    }
	}
	
	// Si no se encontro la solucion se muestra un mensaje
	if(!solucionEncontrada) {
		System.out.println();
		System.out.println("No se ha encontrado la solucion :(");
	}
	
}

}
