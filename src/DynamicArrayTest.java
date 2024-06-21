import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DynamicArrayTest
{
	IArray numbers;
	IArray strings;
	IArray additional;
	
	Integer[] arNumbers = {10, 7, 11, -2, 13, 10, 2000};
	String[] arStrings = {"abc", "lmn", "fg", "abc"};
	Integer[] arAdditional = {1,2,3,4,5};
	
	@BeforeEach
	void setUp() throws Exception
	{
		numbers = new DynamicArray(5);
		for(int i=0; i<arNumbers.length; i++)
		{
			numbers.add(arNumbers[i]);
		}
		
		strings = new DynamicArray();
		for(int i=0; i<arStrings.length; i++)
		{
			strings.add(arStrings[i]);
		}
		
		additional = new DynamicArray();
		for(int i=0; i<arAdditional.length; i++)
		{
			additional.add(arAdditional[i]);
		}
	}
	
	@Test
	void testSetUp()
	{
		assertEquals(arNumbers.length, numbers.size());
		assertEquals(arStrings.length, strings.size());
		
		for(int i=0; i<arNumbers.length; i++)
		{
			assertEquals(arNumbers[i], numbers.get(i));
		}
		
		for(int i=0; i<arStrings.length; i++)
		{
			assertEquals(arStrings[i], strings.get(i));
		}
	}
	
	@Test
	void testIndexOf()
	{
		assertEquals(0, numbers.indexOf(10));
		assertEquals(6, numbers.indexOf(2000));
		assertEquals(0, strings.indexOf("abc"));
		assertEquals(-1, numbers.indexOf(1000));
		assertEquals(-1, strings.indexOf("hello"));
	}

	@Test
	void testRemoveAtIndex()
	{
		Integer[] expNumbers = {10, 11, -2, 13, 10, 2000};
		String[] expStrings = {"abc", "fg", "abc"};
		
		assertEquals(7, numbers.remove(1));
		assertEquals("lmn", strings.remove(1));
		
		for(int i=0; i<expNumbers.length; i++)
		{
			assertEquals(expNumbers[i], numbers.get(i));
		}
		
		for(int i=0; i<expStrings.length; i++)
		{
			assertEquals(expStrings[i], strings.get(i));
		}
		
		assertNull(numbers.remove(60));
		assertNull(strings.remove(60));
	}

	@Test
	void testRemoveObject()
	{
		assertTrue(numbers.remove((Integer)2000));
		assertTrue(strings.remove("abc"));
		
		assertFalse(numbers.remove((Integer)1000));
		assertFalse(strings.remove("hello"));
	}
	

	@Test
	void testLastIndexOf()
	{
		assertEquals(5, numbers.lastIndexOf(10));
		assertEquals(-1, numbers.lastIndexOf(1000));
		assertEquals(3, strings.lastIndexOf("abc"));
		assertEquals(-1, strings.lastIndexOf("kuku"));
		assertEquals(6, numbers.indexOf(2000));
	}

	@Test
	void testContains()
	{
		assertTrue(numbers.contains(2000));
		assertFalse(numbers.contains(100));
	}
	
	@Test
	void testAddAtIndex()
	{
		Integer[] exp = {10, 7, 11, -2, 13, 3, 10, 2000};
		assertTrue(numbers.add(3, 5));
		assertArrayEquals(exp, numbers.toArray());
		assertFalse(numbers.add(numbers.size() + 1, 100));
		assertFalse(numbers.add(-1, 100));
	}

	@Test
	void testToArray()
	{
		assertArrayEquals(arNumbers, numbers.toArray());
		assertArrayEquals(arStrings, strings.toArray());
		
		assertNotSame(arNumbers, numbers.toArray());
		assertNotSame(arStrings, strings.toArray());
	}
	
	@Test
	void testSet()
	{
		Integer[] expNumber = {10, 7, 1000, -2, 13, 10, 2000};
		assertTrue(numbers.set(1000, 2));
		assertEquals(7, numbers.size());
		assertFalse(numbers.set(8, numbers.size()));
		assertFalse(numbers.set(1000, -1));
		assertArrayEquals(expNumber, numbers.toArray());
		
		String[] expString = {"abc", "lmna", "fg", "abc"};
		assertTrue(strings.set("lmna", 1));
		assertFalse(strings.set("lmna", 1));
		assertFalse(strings.set("lmna", numbers.size()));
		assertFalse(strings.set("lmna", -1));
		assertArrayEquals(expString, strings.toArray());
	}
	@Test
	void addAllTest()
	{
		Integer[] exp = {10, 7, 11, -2, 13, 10, 2000, 1, 2, 3, 4, 5};
		numbers.addAll((DynamicArray)additional);
		assertArrayEquals(exp, numbers.toArray());
	}

	@Test
	void testRemoveAll()
	{
		Integer[] expNumbers = {7, 11, -2, 13, 2000};
		assertTrue(numbers.removeAll(10));
		assertFalse(numbers.removeAll(1000));
		assertArrayEquals(expNumbers, numbers.toArray());
		
		String[] expStrings = {"lmn", "fg"};
		assertTrue(strings.removeAll("abc"));
		assertFalse(strings.removeAll("ABC"));
		assertArrayEquals(expStrings, strings.toArray());
	}
}
