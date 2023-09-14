
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.stream.IntStream;

import service.SimpleList;
import service.impl.SimpleListImpl;

public class ServiceTest {
    private SimpleList<Integer> list;
    private SimpleList<Integer> listTo;

    @Before
    public void setUp() {
        list = new SimpleListImpl<>();
        fillRange(list, 1, 500);

        listTo = new SimpleListImpl<>();
        fillRange(listTo, 501, 1000);
    }

    @Test
    public void testSize() {
        assertEquals(500, list.size());
        assertEquals(500, listTo.size());
    }

    @Test
    public void testAdd() {
        assertTrue(list.contains(2));
        assertTrue(listTo.contains(501));
    }

    @Test
    public void testInsert() throws Exception {
        list.insert(250, 1);
        assertEquals(1, (int) list.get(250).orElse(-1));
    }

    @Test
    public void testRemove() throws Exception {
        list.remove(250);
        assertEquals(499, list.size());
    }

    @Test
    public void testAddAll() {
        list.addAll(listTo);

        assertEquals(1000, list.size());

        assertTrue(list.contains(5));
        assertTrue(list.contains(999));
    }

    private void fillRange(SimpleList<Integer> list, int start, int end) {
        IntStream.rangeClosed(start, end).forEach(list::add);
    }
}
