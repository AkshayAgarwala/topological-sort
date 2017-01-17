import java.util.Iterator;

/*
 * Generic type List interface
 * Given in class
 */
public interface List<AnyType> {
  void clear();
  
  int size();

  boolean isEmpty();

  AnyType get(int index);

  AnyType set(int index, AnyType newValue);

  boolean add(AnyType newValue);

  void add(int index, AnyType newValue);

  AnyType remove(int index);

  Iterator<AnyType> iterator();
}