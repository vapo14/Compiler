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
        File currentFile = new File("src/test/resources/test1.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }
        assertEquals("Token{id=6}\n", tokenStream.toString());
    }

    @Test
    void givenEmptyFileWhenRunDFAThenReturnNothing(){
        File currentFile = new File("src/test/resources/emptyFile.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }
        assertEquals("", tokenStream.toString());
    }

    @Test
    void givenFileWithOnlyWhiteSpacesWhenRunDFAThenReturnNothing(){
        File currentFile = new File("src/test/resources/onlyWhiteSpaces.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }
        assertEquals("", tokenStream.toString());
    }
}