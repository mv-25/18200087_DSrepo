package projectCode20280;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedDequeTest {

    @Test
    void testSize() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addLast(i);
        assertEquals(10, s.size());
    }

    @Test
    void testIsEmpty() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addLast(i);
        for(int i = 0; i < 10; ++i)
            s.removeFirst();
        assertEquals(true, s.isEmpty());
    }

    @Test
    void testEnqueue() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addLast(i);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", s.toString());
    }

    @Test
    void testFirst() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addLast(i);
        assertEquals(0, s.first());
    }


    @Test
    void testDequeue() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addLast(i);

        assertEquals(0, s.removeFirst());
        assertEquals(9, s.size());
    }

}
