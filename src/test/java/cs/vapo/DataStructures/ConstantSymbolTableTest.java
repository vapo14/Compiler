package cs.vapo.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantSymbolTableTest {

    @Test
    void givenConstantWhenAddThenPrintConstant() {
        // Given
        String expectedOutput = """
                Constant Symbol Table:\s
                0 | 1234,\s
                """;
        ConstantSymbolTable table = new ConstantSymbolTable();

        // When
        table.add("1234");

        // Then
        assertEquals(expectedOutput, table.toString());
    }

    @Test
    void givenSameConstantWhenAddThenPrintConstant(){
        // Given
        String expectedOutput = """
                Constant Symbol Table:\s
                0 | 1234,\s
                """;
        ConstantSymbolTable table = new ConstantSymbolTable();

        // When
        table.add("1234");
        table.add("1234");

        // Then
        assertEquals(expectedOutput, table.toString());
    }
}