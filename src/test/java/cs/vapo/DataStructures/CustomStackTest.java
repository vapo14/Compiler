/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomStackTest {

    @Test
    void givenStackWhenPopThenReturnPoppedItem(){
        // Given
        CustomStack<String> stack = new CustomStack<>();
        stack.push("one");
        stack.push("two");
        stack.push("three");
        String expected = "three";

        // When
        String actual = stack.pop();

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void givenStackWhenPopAllThenEmpty(){
        // Given
        CustomStack<String> stack = new CustomStack<>();
        stack.push("one");
        stack.push("two");
        stack.push("three");

        // When
        stack.pop();
        stack.pop();
        stack.pop();

        // Then
        Assertions.assertTrue(stack.isEmpty());

    }

    @Test
    void givenStackWhenPeekThenReturnItem(){
        // Given
        CustomStack<String> stack = new CustomStack<>();
        stack.push("one");
        stack.push("two");
        stack.push("three");
        String expected = "three";

        // When
        String actual = stack.peek();

        // Then
        Assertions.assertEquals(expected, actual);
    }
}
