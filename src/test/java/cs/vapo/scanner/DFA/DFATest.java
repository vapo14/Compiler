package cs.vapo.scanner.DFA;

import cs.vapo.DataStructures.CustomVector;
import cs.vapo.scanner.InputStream;
import cs.vapo.scanner.tokens.Token;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DFATest {

    @Test
    void givenFileWithValidTokenWhenRunDFAThenReturnCorrectTokens() {
        // Given
        File currentFile = new File("src/test/resources/DFA_resources/validToken.txt");
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
        File currentFile = new File("src/test/resources/DFA_resources/emptyFile.txt");
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
        File currentFile = new File("src/test/resources/DFA_resources/onlyWhiteSpaces.txt");
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
    void givenInvalidOperatorWhenRunDFAThenReturnError(){
        // Given
        File currentFile = new File("src/test/resources/DFA_resources/invalidOperatorError.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        DFA dfaSpy = Mockito.spy(dfa);
        doNothing().when(dfaSpy).handleError(Mockito.anyInt());

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfaSpy.readNextToken() < 0) break;
        }

        // Then
        Mockito.verify(dfaSpy, Mockito.times(1)).handleError(35);
    }

    @Test
    void givenCommentNotClosedWhenRunDFAThenReturnError(){
        // Given
        File currentFile = new File("src/test/resources/DFA_resources/commentNotClosedError.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        DFA dfaSpy = Mockito.spy(dfa);
        doNothing().when(dfaSpy).handleError(Mockito.anyInt());

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfaSpy.readNextToken() < 0) break;
        }

        // Then
        Mockito.verify(dfaSpy, Mockito.times(1)).handleError(4);
    }

    @Test
    void givenInvalidCharacterWhenRunDFAThenReturnError(){
        // Given
        File currentFile = new File("src/test/resources/DFA_resources/invalidCharacterError.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        DFA dfaSpy = Mockito.spy(dfa);
        doNothing().when(dfaSpy).handleError(Mockito.anyInt());

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfaSpy.readNextToken() < 0) break;
        }

        // Then
        Mockito.verify(dfaSpy, Mockito.times(1)).handleError(32);
    }

    @Test
    void givenInvalidIdentifierWhenRunDFAThenReturnError(){
        // Given
        File currentFile = new File("src/test/resources/DFA_resources/invalidIdentifierError.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        DFA dfaSpy = Mockito.spy(dfa);
        doNothing().when(dfaSpy).handleError(Mockito.anyInt());

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfaSpy.readNextToken() < 0) break;
        }

        // Then
        Mockito.verify(dfaSpy, Mockito.times(1)).handleError(33);
    }

    @Test
    void givenInvalidConstantWhenRunDFAThenReturnError(){
        // Given
        File currentFile = new File("src/test/resources/DFA_resources/invalidConstantError.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        DFA dfaSpy = Mockito.spy(dfa);
        doNothing().when(dfaSpy).handleError(Mockito.anyInt());

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfaSpy.readNextToken() < 0) break;
        }

        // Then
        Mockito.verify(dfaSpy, Mockito.times(1)).handleError(34);
    }

    @Test
    void givenValidOperatorsWhenRunDFAThenReturnCorrectTokens(){
        // Given
        String validTokens = """
                Token{id=9}
                Token{id=12}
                Token{id=10}
                Token{id=11}
                Token{id=13}
                Token{id=15}
                Token{id=14}
                Token{id=16}
                Token{id=17}
                Token{id=18}
                Token{id=19}
                Token{id=20}
                Token{id=21}
                Token{id=22}
                Token{id=23}
                Token{id=24}
                Token{id=25}
                Token{id=26}
                Token{id=27}
                """;
        File currentFile = new File("src/test/resources/DFA_resources/validOperators.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // Then
        assertEquals(validTokens, tokenStream.toString());
    }

    @Test
    void givenValidCodeWhenRunDFAThenReturnCorrectTokens(){
        // Given
        String validTokens = """
                Token{id=6}
                Token{id=28, pointer=0}
                Token{id=24}
                Token{id=29, pointer=0}
                Token{id=25}
                Token{id=20}
                Token{id=6}
                Token{id=28, pointer=1}
                Token{id=22}
                Token{id=6}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=25}
                Token{id=21}
                Token{id=6}
                Token{id=28, pointer=3}
                Token{id=21}
                Token{id=6}
                Token{id=28, pointer=4}
                Token{id=23}
                Token{id=26}
                Token{id=6}
                Token{id=28, pointer=5}
                Token{id=20}
                Token{id=6}
                Token{id=28, pointer=0}
                Token{id=20}
                Token{id=6}
                Token{id=28, pointer=6}
                Token{id=20}
                Token{id=28, pointer=6}
                Token{id=19}
                Token{id=28, pointer=3}
                Token{id=20}
                Token{id=28, pointer=0}
                Token{id=19}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=3}
                Token{id=25}
                Token{id=20}
                Token{id=28, pointer=5}
                Token{id=19}
                Token{id=28, pointer=3}
                Token{id=9}
                Token{id=29, pointer=1}
                Token{id=20}
                Token{id=3}
                Token{id=22}
                Token{id=28, pointer=5}
                Token{id=13}
                Token{id=28, pointer=4}
                Token{id=23}
                Token{id=26}
                Token{id=5}
                Token{id=22}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=5}
                Token{id=25}
                Token{id=13}
                Token{id=28, pointer=0}
                Token{id=23}
                Token{id=26}
                Token{id=28, pointer=0}
                Token{id=19}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=5}
                Token{id=25}
                Token{id=20}
                Token{id=28, pointer=6}
                Token{id=19}
                Token{id=28, pointer=5}
                Token{id=20}
                Token{id=27}
                Token{id=28, pointer=5}
                Token{id=19}
                Token{id=28, pointer=5}
                Token{id=9}
                Token{id=29, pointer=1}
                Token{id=20}
                Token{id=27}
                Token{id=1}
                Token{id=28, pointer=6}
                Token{id=20}
                Token{id=27}
                Token{id=2}
                Token{id=28, pointer=7}
                Token{id=22}
                Token{id=6}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=25}
                Token{id=21}
                Token{id=6}
                Token{id=28, pointer=3}
                Token{id=21}
                Token{id=6}
                Token{id=28, pointer=4}
                Token{id=23}
                Token{id=26}
                Token{id=6}
                Token{id=28, pointer=5}
                Token{id=20}
                Token{id=6}
                Token{id=28, pointer=6}
                Token{id=20}
                Token{id=28, pointer=5}
                Token{id=19}
                Token{id=28, pointer=3}
                Token{id=20}
                Token{id=3}
                Token{id=22}
                Token{id=28, pointer=5}
                Token{id=13}
                Token{id=28, pointer=4}
                Token{id=10}
                Token{id=29, pointer=1}
                Token{id=23}
                Token{id=26}
                Token{id=6}
                Token{id=28, pointer=8}
                Token{id=20}
                Token{id=28, pointer=6}
                Token{id=19}
                Token{id=28, pointer=9}
                Token{id=22}
                Token{id=28, pointer=2}
                Token{id=21}
                Token{id=28, pointer=10}
                Token{id=21}
                Token{id=28, pointer=4}
                Token{id=23}
                Token{id=20}
                Token{id=28, pointer=8}
                Token{id=19}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=6}
                Token{id=25}
                Token{id=20}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=6}
                Token{id=25}
                Token{id=19}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=5}
                Token{id=25}
                Token{id=20}
                Token{id=28, pointer=2}
                Token{id=24}
                Token{id=28, pointer=5}
                Token{id=25}
                Token{id=19}
                Token{id=28, pointer=8}
                Token{id=20}
                Token{id=28, pointer=5}
                Token{id=19}
                Token{id=28, pointer=5}
                Token{id=9}
                Token{id=29, pointer=1}
                Token{id=20}
                Token{id=27}
                Token{id=27}
                """;
        File currentFile = new File("src/test/resources/DFA_resources/code1.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);

        // When
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // Then
        assertEquals(validTokens, tokenStream.toString());
    }
}