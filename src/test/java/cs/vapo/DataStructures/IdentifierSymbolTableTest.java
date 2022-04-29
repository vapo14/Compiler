package cs.vapo.DataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierSymbolTableTest {

    @Test
    void givenIdentifierWhenAddThenPrintIdentifier() {
        // Given
        String expectedOutput = """
                Identifier Symbol Table:\s
                0 | identifier,\s
                """;
        IdentifierSymbolTable table = new IdentifierSymbolTable();

        // When
        table.add("identifier");

        // Then
        assertEquals(expectedOutput, table.toString());
    }

    @Test
    void givenSameIdentifierWhenAddThenPrintIdentifier(){
        // Given
        String expectedOutput = """
                Identifier Symbol Table:\s
                0 | identifier,\s
                """;
        IdentifierSymbolTable table = new IdentifierSymbolTable();

        // When
        table.add("identifier");
        table.add("identifier");

        // Then
        assertEquals(expectedOutput, table.toString());
    }
}