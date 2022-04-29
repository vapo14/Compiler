package cs.vapo.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapTest {


    @Test
    void givenThreePairsWhenGetSizeThenReturnThree() {
        // Given
        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();

        // When
        hashMap.add("key1", 1);
        hashMap.add("key2", 1);
        hashMap.add("key3", 1);

        // Then
        assertEquals(3, hashMap.size());
    }

    @Test
    void givenPairWhenAddThenReturnSizeOne() {
        // Given
        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();

        // When
        hashMap.add("key1", 1);

        // Then
        assertEquals(1, hashMap.size());
    }

    @Test
    void givenKeyWhenGetThenReturnValue() {
        // Given
        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();

        // When
        hashMap.add("key1", 15);

        // Then
        assertEquals(15, hashMap.get("key1"));
    }
}