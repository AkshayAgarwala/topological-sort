
/*
 * Sorts the vertices of the graph in topological order and prints it.
 */
public class TopologicalSort {

	private ArrayStack<Vertex> sortedStack; // keeps track of vertices that are done being processed
	private boolean[] testedVertices; // keeps track of which vertices were already processed/visited

	// initializes the Class data parameters
	// starts the Topological Sort algorithm
	public TopologicalSort(Vertex[] vertices) {
		sortedStack = new ArrayStack<>();
		testedVertices = new boolean[vertices.length];
		doTopologicalSort(vertices);
	} // constructor

	// calls sort method for each vertex that has not been processed yet
	private void doTopologicalSort(Vertex[] vertices) {
		int p = 0; // to keep track of which vertex is currently being worked on
		while (p < vertices.length) { // for each vertex
			if (!testedVertices[p])   // that has not been processed yet
				sort(vertices[p], p, vertices); // do a topological sort
				p++; // and then do the next vertex
		}
		printSortedStack(); // when finished with each vertex print the finished sort
	} // doTopologicalSort method

	// Do the sort for vertex v in position pos in the Vertex array, vertices
	private void sort(Vertex v, int pos, Vertex[] vertices) {
		if (testedVertices[pos]) return; // vertex v has already been processed
		testedVertices[pos] = true;      // vertex v is now considered processed
		DoublyLinkedList<Vertex> tempList = v.getList(); // temp list for vertex v's adjacency list

		// if vertex has no adjacent vertices it can be added to the sorted stack
		if (tempList.isEmpty()) {
			sortedStack.push(v);
			return;
		} // base case

		// recursively call sort for each vertex that v is adjacent to
		for (int i = 0; i < tempList.size(); i++) { // for each vertex in vertex v's adjacency list
			Vertex currentVertex = tempList.get(i);
			int p = findVertexPosition(vertices, currentVertex); // find its position in the vertex array
			sort(currentVertex, p, vertices);  // and then call sort algorithm for that vertex
		}

		sortedStack.push(v); // when vertex v is finished being processed it can be added to the sorted stack
		return;
	} // sort method

	// find the position in the vertexArray that vertex v is in
	private int findVertexPosition(Vertex[] vertexArray, Vertex v) {
		int location = 0;
		for (int i = 0; i < vertexArray.length; i++) {
			if (vertexArray[i].toString().equals(v.toString())) // the vertices names match
				location = i;
		}
		return location;
	} // findVertexPosition method

	// print the sorted stack
	private void printSortedStack() {
		System.out.println("\nTopological Sort:");
		sortedStack.printStack();
		System.out.println();
	} // printSortedStack method

} // TopologicalSort class
