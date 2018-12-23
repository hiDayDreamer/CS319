package test;
import java.util.LinkedList;
public class GraphTest {
		
		private boolean hintWanted;

		public boolean getHintWanted() {
			return this.hintWanted;
		}

		/**
		 * 
		 * @param hintWanted
		 */
		public void setHintWanted(boolean hintWanted) {
			this.hintWanted = hintWanted;
		}

		/**
		 * 
		 * @param hintWanted
		 */
		public int generateHint(boolean hintWanted) {
			// TODO - implement GameSolver.generateHint
			throw new UnsupportedOperationException();
		}

		static public class Coordinate{
			int x, y;
			public Coordinate(int x, int y) {
				this.x = x;
				this.y = y;
			}
			public int getX() {
				return x;
			}
			public int getY() {
				return y;
			}
		}
		static public class Graph {

			private int vertexNumber;
			private LinkedList algoList;
			private LinkedList<Coordinate> array[];
			
			Graph(int V){
				vertexNumber = V;
				array = new LinkedList[vertexNumber];
				
				for(int i = 0; i < vertexNumber; i++)
				{
					array[i] = new LinkedList<>();
				}
			}

			public int getVertexNumber() {
				return this.vertexNumber;
			}

			
			/**
			 * 
			 * @param vertexNumber
			 */
			public void setVertexNumber(int vertexNumber) {
				this.vertexNumber = vertexNumber;
			}

			public LinkedList getAlgoList() {
				return this.algoList;
			}

			/**
			 * 
			 * @param algoList
			 */
			public void setAlgoList(LinkedList algoList) {
				this.algoList = algoList;
			}

		}
		 
		static void addEdge(Graph graph, int index, Coordinate src, Coordinate dest) 
	    { 
	        // Add an edge from src to dest.  
	        graph.array[index].add(dest); 
	          
	        // Since graph is undirected, add an edge from dest 
	        // to src also 
	        graph.array[index].add(src); 
	    } 
		
		// representation of graph 
	    static void printGraph(Graph graph) 
	    {        
	        for(int v = 0; v < graph.vertexNumber; v++) 
	        { 
	            System.out.println("Adjacency list of vertex "+ v); 
	            System.out.print("head"); 
	            for(Coordinate pCrawl: graph.array[v]){ 
	                System.out.print(" -> "+pCrawl.getX() + " " + pCrawl.getY()); 
	            } 
	            System.out.println("\n"); 
	        } 
	    }

	

	// Driver program to test above functions

	    public static void main(String args[]) 
	    { 
	        // create the graph given in above figure 
	        int V = 10; 
	        Graph graph = new Graph(V); 
	        Coordinate c = new Coordinate(0,1);
	        Coordinate c1 = new Coordinate(0,4);
	        Coordinate c2 = new Coordinate(3,2);
	        Coordinate c3 = new Coordinate(6,7);
	        Coordinate c4 = new Coordinate(2,3);
	        Coordinate c5 = new Coordinate(6,5);
	        Coordinate c6 = new Coordinate(8,9);
	        Coordinate c7 = new Coordinate(4,5);
	        addEdge(graph, 0, c, c1); 
	        addEdge(graph,1, c2,c3); 
	        addEdge(graph,2, c4,c5); 
	        addEdge(graph,3, c6, c7); 
	     
	        // print the adjacency list representation of  
	        // the above graph 
	        printGraph(graph); 
	    } 
}
 

