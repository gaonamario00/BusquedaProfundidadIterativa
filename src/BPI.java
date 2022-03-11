import java.util.ArrayList;
import java.util.Stack;

	public class BPI {
		 
	    // Informaci�n del nodo de almacenamiento
	    private char[] vertices;
	 
	    // Almacenar informaci�n lateral (matriz de adyacencia)
	    private  int[][] arcs;  
	 
	    // El n�mero de nodos maximos en el gr�fico
	    private int cantNodos;
	 
	    // Registrar si el nodo ha sido atravesado
	    private boolean[] visited;
	    
	    // Registrta si la solicion ha sido encontrada
	    public boolean solucionFounded = false;
	    
	    // Almacena la solucion
	    public char solucion;
	    
	    // Almacena los nodos abiertos
	    public ArrayList<Object> verticesVisitados = new ArrayList<Object>();
	 
	    // Inicializar
	    public BPI(int n) {
	    	cantNodos = n;
	          vertices = new char[n];
	          arcs = new int[n][n];
	          visited = new boolean[n];
	          for (int i = 0; i < cantNodos; i++) {
	             for (int j = 0; j < cantNodos; j++) {
	                 arcs[i][j] = 0;
	             }
	          }
	    }
	 
	    // Agrega aristas, o sea, las relaciones entre los nodos
	    public void addEdge(int i, int j) {
	    	
	          // No puede haber lazos en el grafo
	          if (i == j)return;
	 
	          arcs[i][j] = 1;
	          arcs[j][i] = 1;
	    }
	    
	    // Imprime la matriz de adyacencia actual
	    public void printVertices() {
	    	
	    	for(int i=0; i<cantNodos; i++) {
	    		for(int j=0; j<cantNodos; j++) {
		    		System.out.print("["+arcs[i][j]+"]" + " ");
		    	}
	    		System.out.print("\n");
	    	}	
	    }
	 
	    // Establece el conjunto de nodos
	    public void setVertices(char[] vertices) {
	        this.vertices = vertices;
	    }
	 
	    // Establece bandera de acceso al nodo
	    public void setVisited(boolean[] visited) {
	        this.visited = visited;
	    }
	 
	    // imprimir nodos visitados y agrega a vertivesVisitados
	    public void visit(int i){
	        System.out.print("Actual: " + vertices[i] + " ");
	        verticesVisitados.add(vertices[i]);
	    }
	 
	    // Recorrido en profundidad del gr�fico (no recursivo)
	    public void BPITraverse(){
	                 // Inicializar la marca transversal del nodo
	        for (int i = 0; i < cantNodos; i++) {
	            visited[i] = false;
	        }
	 
	        // Se declara e inicializa la pila
	        Stack<Integer> s = new Stack<Integer>();
	        for(int i=0;i<cantNodos;i++){
	            if(!visited[i]){
	                // Nodo inicial del subgrafo conectado
	                s.add(i);
	                do{ 
	                     // Pop - saca el elemento actual de la pila
	                    int curr = s.pop();
	 
	                    // Si no se ha atravesado el nodo, atravesar el nodo y empujar los nodos secundarios a la pila
	                    if(visited[curr]==false){
	                    	
	                       // atravesar e imprimir
	                        visit(curr);
	                        visited[curr] = true;
	                        
	                        
	 
	                        // Los nodos secundarios no cruzados se insertan en la pila
	                        for(int j=cantNodos-1; j>=0 ; j-- ){
	                            if(arcs[curr][j]==1 && visited[j]==false){
	                                s.add(j);
	                            }
	                        }
	                        System.out.println();
	                        // Imprimimos la pila
	                        System.out.println("Pila:");
	                        int t = 0;
	                        for(int k: s) {
	                        		System.out.println("["+vertices[k]+"]");
	                        		t=1;
	                        }
	                        if(t==0) System.out.println("[vacia]");
	                        System.out.println();
	                        
	                        // Imprime los nodos visitados 
	                        System.out.print("Nodos abiertos: ");
	                        for(int k=0; k<verticesVisitados.size(); k++) {
	                        		
	                        		System.out.print(verticesVisitados.get(k) + " ");
	                        }
	                        System.out.println();
	                        System.out.println();
	                        System.out.println();
	                        
	                    }
	                    // Rompe el ciclo while si se encontro la solucion
	                    if(solucion == vertices[curr]) {
                        	this.solucionFounded = true;
                        	break;
                        }
	                    
	                }while(!s.isEmpty());
	                
	                // Rompe el ciclo for si se encontro la solucion
	                if(this.solucionFounded==true) break;
	            }
	        }
	    } 
	}
