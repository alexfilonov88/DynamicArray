
public interface IArray
{
	boolean add(Object obj);
	boolean add(Object obj, int index);
	Object get(int index);
	int indexOf(Object obj);
	int lastIndexOf(Object obj);
	boolean contains(Object obj);
	Object remove(int index);
	boolean remove(Object obj);
	int size();
	Object[] toArray();
	
	boolean set(Object obj, int index);
	void addAll(DynamicArray other);
	boolean removeAll(Object obj);
}
