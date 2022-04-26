package cs.vapo.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomVectorTest {

    @Test
    void add() {
        CustomVector<Integer> v = new CustomVector<>();
        v.add(1);

        assertEquals(1, v.get(0));
    }

    @Test
    void size() {
        CustomVector<Integer> v = new CustomVector<>();
        v.add(1);
        v.add(2);
        v.add(3);

        assertEquals(3, v.size());
    }

    @Test
    void set() {
        CustomVector<String> v = new CustomVector<>();
        v.add("foo");
        v.set(0, "bar");

        assertEquals("bar", v.get(0));
    }
}