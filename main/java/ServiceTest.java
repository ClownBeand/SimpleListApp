import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import service.SimpleList;
import service.impl.SimpleListImpl;

public class ServiceTest {
	
	@Test
	public void testSize() {
	SimpleList<Integer> list = new SimpleListImpl<>();
	assertEquals(1, list.size());
	list.add(1);
	assertEquals(2, list.size());
	}
	
	@Test
	public void testAdd() {
	SimpleList<Integer> list = new SimpleListImpl<>();
	list.add(1);
	list.add(5);
	list.add(3);
	assertEquals(4, list.size());
	assertTrue(list.contains(5));
	}
	
	@Test
	public void testInsert() throws Exception {
	SimpleList<Integer> list = new SimpleListImpl<>();
	list.add(5);
	list.insert(0, 1);
	assertEquals(3, list.size());
	assertEquals(1,(int) list.get(0).orElse(-1));
	assertEquals(5,(int) list.get(1).orElse(-1));
	}
	
	@Test
	public void testRemove() throws Exception {
	SimpleList<Integer> list = new SimpleListImpl<>();
	list.add(3);
	list.add(4);
	list.remove(1);
	assertEquals(2, list.size());
	assertTrue(list.contains(4));
	}
	
	@Test
	public void testAddAll() {
	SimpleList<Integer> list = new SimpleListImpl<>();
	SimpleList<Integer> otherList = new SimpleListImpl<>();
	
	otherList.add(9);
    otherList.add(5);
    otherList.add(7);
    
    list.add(2);
    list.add(3);

    list.addAll(otherList);
    
    assertEquals(7, list.size());
    assertTrue(list.contains(3));
    assertTrue(list.contains(9));
	}
}
