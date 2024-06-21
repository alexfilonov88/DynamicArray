import java.util.Arrays;

public class DynamicArray implements IArray
{
	private static final int CAPACITY = 16;
	
	private Object[] array;
	private int size;
	
	public DynamicArray(int capacity)
	{
		array = new Object[capacity];
		size = 0;
	}
	
	public DynamicArray()
	{
		this(CAPACITY);
	}

	@Override
	public boolean add(Object obj)
	{
		if(obj == null)
			return false;
		
		if(size == array.length)
			allocateArray();
		
		array[size++] = obj;
		return true;
	}

	private void allocateArray()
	{
		array = Arrays.copyOf(array, array.length + CAPACITY);
	}

	@Override
	public Object get(int index)
	{
		if(index<0 || index>=size)
			return null;
		return array[index];
	}

	@Override
	public int indexOf(Object obj)
	{
		if(obj == null)
			return -1;
		
		for(int i=0; i<size; i++)
		{
			if(array[i].equals(obj))
				return i;
		}
		return -1;
	}
	
	@Override
	public Object remove(int index)
	{
		if(index<0 || index>=size)
			return null;
		
		Object res = array[index];
		if(index < size-1)
			System.arraycopy(array, index+1, array, index, size-index-1);
		size--;
		return res;
	}

	@Override
	public boolean remove(Object obj)
	{
		if(obj == null)
			return false;
		
		int index = indexOf(obj);
		return remove(index) != null;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public Object[] toArray()
	{
		return Arrays.copyOf(array, size);
	}

	@Override
	public boolean add(Object obj, int index)
	{
		if (obj == null || index < 0 || index > size)
			return false;
		if (index == size)
			return add(obj);
		if (size == array.length)
			allocateArray();
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
		return true;
	}
	
	@Override
	public int lastIndexOf(Object obj)
	{
		if(obj == null)
			return -1;
		
		for (int i=size-1; i>=0; i--)
		{
			if (array[i].equals(obj))
				return i;
		}
		return -1;
	}

	@Override
	public boolean contains(Object obj)
	{
//		for(int i=0; i<size; i++)
//		{
//			if(array[i].equals(obj))
//				return true;
//		}
//		return false;
		return indexOf(obj) >= 0;
	}

	@Override
	public boolean set(Object obj, int index)
	{
		if (obj == null || index < 0 || index >= size || array[index].equals(obj))
			return false;
		array[index] = obj;
		return true;
	}

	@Override
	public void addAll(DynamicArray other)
	{
		if(other == null || other.size() == 0)
			return;
		
		for (int i=0; i<other.size; i++)
		{
			add(other.get(i));
		}
	}
//1,1,1,1,2
	@Override
	public boolean removeAll(Object obj)
	{
		if(obj == null)
			return false;
		
		int temp = size;
		for (int i=size-1; i>=0; i--)
		{
			if (array[i].equals(obj))
				remove(i);
		}
		return temp != size;
	}
}

