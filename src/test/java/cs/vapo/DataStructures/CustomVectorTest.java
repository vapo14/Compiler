package cs.vapo.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomVectorTest {

    @Test
    void givenElementWhenGetReturnValue() {
        // Given
        CustomVector<Integer> v = new CustomVector<>();

        // When
        v.add(1);

        // Then
        assertEquals(1, v.get(0));
    }

    @Test
    void givenThreeElementsWhenGetSizeThenReturnThree() {
        // Given
        CustomVector<Integer> v = new CustomVector<>();

        // When
        v.add(1);
        v.add(2);
        v.add(3);

        // Then
        assertEquals(3, v.size());
    }

    @Test
    void givenValueWhenSetThenReturnUpdatedValue() {
        // Given
        CustomVector<String> v = new CustomVector<>();

        // When
        v.add("foo");
        v.set(0, "bar");

        // Then
        assertEquals("bar", v.get(0));
    }
}