package cs.vapo.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapTest {


    @Test
    void size() {
        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();
        hashMap.add("key1", 1);
        hashMap.add("key2", 1);
        hashMap.add("key3", 1);
        assertEquals(3, hashMap.size());
    }

    @Test
    void add() {
        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();
        hashMap.add("key1", 1);
        assertEquals(1, hashMap.size());
    }

    @Test
    void get() {
        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();
        hashMap.add("key1", 15);
        assertEquals(15, hashMap.get("key1"));
    }
}