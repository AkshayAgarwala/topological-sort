/*
 * Dynamic Array Stack Class that was given in class - renamed.
 * Modified from project 2 - added print method.
 */
public class ArrayStack<AnyType> implements Stack<AnyType>
{
  public static final int DEFAULT_CAPACITY = 1024;
  AnyType[] data;
  int topOfStack;

  public ArrayStack() { this(DEFAULT_CAPACITY); }

  public ArrayStack(int capacity)
  {
    topOfStack = -1;
    data = (AnyType[]) new Object[capacity];
  }
  
  /**
   * Determine the number of elements in the array.
   * 
   * @return int The number of elements in the array
   */
  public int size()
  {
	  int size = 0;
	  for (int i = 0; i < data.length; i++) 
		  // if element is present then increase size
		  if (data[i] != null) size++; 
	  return size;
  } 

  /**
   * Determine if the array is empty or not.
   * 
   * @return boolean True if array is empty
   */
  public boolean isEmpty()
  {
	  for (int i = 0; i < data.length; i++)
		  // if any element is present that isn't null the array isn't empty
		  if (data[i] != null) return false;
	  return true;
  }

  /**
   * Add an element to the top of the stack.
   * 
   * @param The element to be added
   */
  public void push(AnyType newValue)
  {
	  if(size() == data.length) // array is full so resize
		  resize(2 * data.length);
	  topOfStack++;
	  data[topOfStack] = newValue;
  }

  /**
   * Return the value of the element at the top of the stack without removing it.
   * 
   * @return AnyType the value of the element at the top of the stack
   */
  public AnyType top()
  {
	  if (isEmpty()) { // Nothing is at the top of stack if the stack is empty
		  System.out.println("Stack is Empty");
		  System.exit(1);
	  }
	  return data[topOfStack];
  }

  /**
   * Return the value of the element at the top of the stack and removes it.
   * 
   * @return AnyType the value of the element at the top of the stack
   */
  public AnyType pop()
  {
	  if (isEmpty()) { // Nothing can be removed if the stack is empty
		  System.out.println("Stack is Empty");
		  System.exit(1);
	  }
	  else if (size() <= data.length / 4) // makes array smaller if very few elements currently in the array
		  resize(data.length / 2);
	  AnyType removedValue = data[topOfStack];
	  data[topOfStack] = null;
	  topOfStack--;
	  return removedValue;
  }
  
  /**
   * prints the stack
   */
  protected void printStack() {
	  if (isEmpty()) return;
	  int i = 0;
	  while (!isEmpty()) {
		  System.out.print(pop().toString() + " ");
		  i--;
	  }
  }
  
  /**
   * Resize the array data
   * 
   * @param newCapacity the desired capacity of the array
   */
  protected void resize(int newCapacity) {
	  int n = size();
	  AnyType[] temp = (AnyType[]) new Object[newCapacity];
	  for (int i = 0; i < n; i++)
		  temp[i] = data[i]; // populate the new array with the values of previous array
	  data = temp; // give the new array pointer to data
  }
} // class
