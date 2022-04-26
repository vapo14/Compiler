package cs.vapo.scanner.DFA;

import cs.vapo.DataStructures.CustomVector;
import cs.vapo.scanner.InputStream;
import cs.vapo.scanner.tokens.Token;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DFATest {

    @Test
    void givenFileWithValidTokenWhenRunDFAThenObtainToken() {
        // Given
        File currentFile = new File("src/test/resources/test1.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // Then
        assertEquals("Token{id=6}\n", tokenStream.toString());
    }

    @Test
    void givenEmptyFileWhenRunDFAThenReturnNothing(){
        // Given
        File currentFile = new File("src/test/resources/emptyFile.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // Then
        assertEquals("", tokenStream.toString());
    }

    @Test
    void givenFileWithOnlyWhiteSpacesWhenRunDFAThenReturnNothing(){
        // Given
        File currentFile = new File("src/test/resources/onlyWhiteSpaces.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // Then
        assertEquals("", tokenStream.toString());
    }

    @Test
    void givenInvalidIdentifierWhenRunDFAThenReturnError(){
        // Given
        File currentFile = new File("src/test/resources/identifierError.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // Then
        assertEquals("", tokenStream.toString());
    }
}