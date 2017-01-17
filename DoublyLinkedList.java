import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * 
 * Given in class
 * Modified/Added Methods will be commented
 * 
 */
public class DoublyLinkedList<AnyType> implements List<AnyType>
{
  private static class Node<AnyType>
  {
    private AnyType data;
    private Node<AnyType> prev;
    private Node<AnyType> next;

    public Node(AnyType d, Node<AnyType> p, Node<AnyType> n)
    {
      setData(d);
      setPrev(p);
      setNext(n);
    }

    public AnyType getData() { return data; }

    public void setData(AnyType d) { data = d; }

    public Node<AnyType> getPrev() { return prev; }

    public void setPrev(Node<AnyType> p) { prev = p; }

    public Node<AnyType> getNext() { return next; }

    public void setNext(Node<AnyType> n) { next = n; }
  }

  private int theSize;
  private int modCount;
  private Node<AnyType> header;
  private Node<AnyType> trailer;

  public DoublyLinkedList()
  {
    header = new Node<AnyType>(null, null, null);
    trailer = new Node<AnyType>(null, null, null);
    modCount = 0;
    clear();
  }

  public void clear()
  {
    header.setNext(trailer);
    trailer.setPrev(header);
    theSize = 0;
  }

  public int size()
  {
    return theSize;
  }

  public boolean isEmpty()
  {
    return (size() == 0);
  }

  /**
   * Get value at the desired index
   * 
   * @param index the index to look in
   * @return AnyType the value at that index
   */
  public AnyType get(int index)
  {
	  Node<AnyType> testNode = getNode(index);
	  return testNode.getData();
  }
  
  /**
   * Change value at specified index
   * 
   * @param index the index to look in
   * @param newValue the new Value to be entered
   * @return AnyType The old value that was changed
   */
  public AnyType set(int index, AnyType newValue)
  {
	  Node<AnyType> testNode = getNode(index);
	  AnyType oldValue = testNode.getData();
	  testNode.setData(newValue);
	  return oldValue;
  }

  public boolean add(AnyType newValue)
  {
    add(size(), newValue);
    return true;
  }

  /**
   * Add new value to list at specified index
   * Makes new node and used getNode method
   * Readjusts next/previous pointers as needed
   * 
   * @param index desired index to add value
   * @param newValue Value to be added
   */
  public void add(int index, AnyType newValue)
  {
	  Node<AnyType> nextNode = getNode(index, 0, size());
	  Node<AnyType> prevNode = getNode(index-1, 0, size());
	  Node<AnyType> newNode = new Node<>(newValue, prevNode,nextNode);
	  prevNode.setNext(newNode);
	  nextNode.setPrev(newNode);
	  theSize++;
	  modCount++;
  }

  public AnyType remove(int index)
  {
    return remove(getNode(index));
  }

  public Iterator<AnyType> iterator()
  {
    return new LinkedListIterator();    
  }

  private Node<AnyType> getNode(int index)
  {
    return (getNode(index, 0, size()-1));
  }

  /**
   * Get a node in the list at desired index
   * 
   * @param index desired location in the list
   * @param lower minimum bound 
   * @param upper maximum bound
   * @return Node<AnyType> the node at desired index
   */
  private Node<AnyType> getNode(int index, int lower, int upper)
  {
	  if (index == -1) return header;
	  checkIndexRange(index, lower, upper);
	  Node<AnyType> testNode = header;
	  int count = -1;
	  while (count != index){
		  testNode = testNode.getNext();
		  count++;
	  }
	  return testNode;
  }

  /**
   * Removes desired node from list.
   * Readjusts next/previous pointers as needed
   * 
   * @param currNode node to be removed
   * @return AnyType Data value of the removed node.
   */
  private AnyType remove(Node<AnyType> currNode)
  {
	  /*this method used the getNode method in order
	  to get the parameter currNode if not explicitly
	  given one by the user.*/
	  AnyType value = currNode.getData();
	  currNode.getPrev().setNext(currNode.getNext());
	  currNode.getNext().setPrev(currNode.getPrev());
	  currNode.setNext(null);
	  currNode.setPrev(null);
	  theSize--;
	  modCount++;
	  return value;
  }
  
  /**
   * Checks the index in relation to given index range
   * 
   * @param index the index
   * @param lower minimum bound
   * @param higher maximum bound
   * @throws IndexOutOfBoundsException if index given is invalid
   */
  private void checkIndexRange(int index, int lower, int higher) throws IndexOutOfBoundsException {
	  if (index < 0 || index > higher) 
		  throw new IndexOutOfBoundsException("Illegal index:" + index);
  }
  
  private class LinkedListIterator implements Iterator<AnyType>
  {
    private Node<AnyType> current;
    private int expectedModCount;
    private boolean okToRemove;

    LinkedListIterator()
    {
      current = header.getNext();
      expectedModCount = modCount;
      okToRemove = false;
    }

    public boolean hasNext()
    {
      return (current != trailer);
    }

    public AnyType next()
    {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
      if (!hasNext())
        throw new NoSuchElementException();

      AnyType nextValue = current.getData();
      current = current.getNext();
      okToRemove = true;
      return nextValue;
    }

    public void remove()
    {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
      if (!okToRemove)
        throw new IllegalStateException();

      DoublyLinkedList.this.remove(current.getPrev());
      expectedModCount++;
      okToRemove = false;
    }
  }
}
