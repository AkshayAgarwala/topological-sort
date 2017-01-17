/**
 * 
 * @author Akshay Agarwala
 * Creates Vertex Object
 *	
 */
public class Vertex {
	private String name;
	private DoublyLinkedList<Vertex> adjList;
	
	/**
	 * Constructor 
	 * New Doubly Linked List is created
	 * 
	 * @param n name of vertex object
	 */
	public Vertex(String n) {
		if (n == null) 
			throw new IllegalArgumentException("Vertex must have a name");
		name = n;
		adjList = new DoublyLinkedList<>();
	}
	
	/**
	 * Get the name of the vertex
	 * 
	 * @return String name of vertex
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of a vertex
	 * 
	 * @param c the name to be given to the vertex
	 */
	public void setName(String c) {
		name = c;
	}
	
	/**
	 * Get the Adjacency List for a Vertex
	 * 
	 * @return Doubly Linked List of Vertex type containing adjacency list of Vertex
	 */
	public DoublyLinkedList<Vertex> getList() {
		return adjList;
	}
	
	/**
	 * Set the adjacency list for some Vertex
	 * 
	 * @param list The adjacency list to be given to some Vertex
	 */
	public void setList(DoublyLinkedList<Vertex> list) {
		adjList = list;
	}
	
	/**
	 * Convert the vertex object into a String object
	 */
	public String toString() {
		return name;
	}
	
} // Vertex class
