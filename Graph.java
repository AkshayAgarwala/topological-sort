import java.util.*;

/*
 * Makes adjacency matrix by eliminating any white space(s) from input file.
 * Makes array of Vertex objects of the vertices of the graph.
 * Makes and prints the adjacency list of each vertex of the graph.
 * Calls the Topological Sort algorithm.
 */
public class Graph {
	
	// makes a graph
	public Graph(String[] info) {
		makeGraph(info);
	} // constructor
	
	// make adjacency matrix/lists and vertex array of graph vertices
	// print the lists and call for a  Topological Sort 
	private void makeGraph(String[] info) {
		String[][] adjMatrix = makeAdjMatrix(info); // eliminate white spaces from input file to make an adj. matrix
		Vertex[] vertices = makeVertexArray(adjMatrix); // Make an Array of vertices of the graph
		makeAdjList(adjMatrix, vertices); 
		printLists(vertices);
		TopologicalSort sortGraph = new TopologicalSort(vertices);
	} // makeGraph method
	
	// eliminate white spaces from input file
	private String[][] makeAdjMatrix (String[] info) {
		StringTokenizer st = new StringTokenizer(info[0]);
		int vertexCount = st.countTokens();
		String[][] matrix = new String[vertexCount+1][vertexCount];
		
		// eliminate white spaces and make adjacency matrix
		for (int r = 0; r < matrix.length; r++) {
			st = new StringTokenizer(info[r]);
			for (int c = 0; c < vertexCount; c++) {
				if (st.hasMoreTokens())
					matrix[r][c] = st.nextToken();
			} // for c loop
		} // for r loop
		
		checkMatrixValues(matrix); // check for valid values of input file
		return matrix;
	} // makeAdjMatrix method
	
	// checks if values are only 0 or 1 in a 2d String matrix
	private void checkMatrixValues(String[][] m) {
		for (int r = 1; r < m.length; r++) {
			for (int c = 0; c < m[r].length; c++) {
				if (!((m[r][c].equals("0")) || (m[r][c].equals("1"))))
					throw new IllegalArgumentException("Input File Error: Only 0's and 1's allowed in Adjacency Matrix");
			} // for c loop
		} // for r loop
	} // checkMatrixValues method
	
	// make vertex array of vertices
	private Vertex[] makeVertexArray(String[][] am) {
		Vertex[] vertices = new Vertex[am[0].length];
		for (int i = 0; i < am[0].length; i++) {
			vertices[i] = new Vertex(am[0][i]); // Array of Vertex objects
		}
		return vertices;
	} // makeVertexArray method
	
	// make adjacency list for each vertex and give it to vertex object's data parameter
	private void makeAdjList(String[][] am, Vertex[] v) {
		DoublyLinkedList<Vertex> temp; // temp list to hold adjacency list
		
		for (int r = 1; r < am.length; r++) { 				// for each vertex
			temp = new DoublyLinkedList<>();				// make a temp list
			for (int c = 0; c < am[r].length; c++) {
				if (am[r][c].equals("1")) temp.add(v[c]);	// add each adjacent vertex to the vertex's adjacency list  
			} // for c loop
			
			v[r-1].setList(temp);							// give the temp list to the Vertex object's adj list data parameter
		} // for r loop
	} // makeAdjList method
	
	// print all of the Adjacency lists
	public void printLists(Vertex[] v) {
		DoublyLinkedList<Vertex> t;
		System.out.println("The Adjacency Lists \nVertex: List");
		for (int i = 0; i < v.length; i++){
			t = new DoublyLinkedList<>();
			t = v[i].getList();
			System.out.print(v[i].toString() + ": ");
			for (int a = 0; a < t.size(); a++) {
				System.out.print(t.get(a).toString() + " ");
			}
			System.out.println();
		}
	} // printLists method
	
} // Graph class
