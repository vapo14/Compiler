/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.Parser;

import cs.vapo.DataStructures.CustomVector;
import cs.vapo.parser.MainParserProcess;
import cs.vapo.scanner.DFA.DFA;
import cs.vapo.scanner.InputStream;
import cs.vapo.scanner.tokens.Token;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParserTests {

    @Test
    public void givenValidInputFileWhenParseThenAccept(){
        // Given
        File currentFile = new File("src/test/resources/Parser_resources/validProgram.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }
        // When
        MainParserProcess parserProcess = new MainParserProcess(tokenStream);
        MainParserProcess parserSpy = Mockito.spy(parserProcess);
        Mockito.doNothing().when(parserSpy).nonTerminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).terminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).noDeclarationError();
        Mockito.doNothing().when(parserSpy).noMainMethodError();

        // Then
        assertTrue(parserSpy.parse());
    }

    @Test
    public void givenEmptyFileWhenParseThenError(){
        // Given
        File currentFile = new File("src/test/resources/Parser_resources/emptyFile.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // When
        MainParserProcess parserProcess = new MainParserProcess(tokenStream);
        MainParserProcess parserSpy = Mockito.spy(parserProcess);
        Mockito.doNothing().when(parserSpy).nonTerminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).terminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).noDeclarationError();
        Mockito.doNothing().when(parserSpy).noMainMethodError();

        // Then
        assertFalse(parserSpy.parse());
    }

    @Test
    public void givenInvalidVariableDeclarationWhenParseThenError(){
        // Given
        File currentFile = new File("src/test/resources/Parser_resources/invalidVariable.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // When
        MainParserProcess parserProcess = new MainParserProcess(tokenStream);
        MainParserProcess parserSpy = Mockito.spy(parserProcess);
        Mockito.doNothing().when(parserSpy).nonTerminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).terminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).noDeclarationError();
        Mockito.doNothing().when(parserSpy).noMainMethodError();

        // Then
        assertFalse(parserSpy.parse());
    }

    @Test
    public void givenInvalidArrayDeclarationWhenParseThenError(){
        // Given
        File currentFile = new File("src/test/resources/Parser_resources/invalidArray.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // When
        MainParserProcess parserProcess = new MainParserProcess(tokenStream);
        MainParserProcess parserSpy = Mockito.spy(parserProcess);
        Mockito.doNothing().when(parserSpy).nonTerminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).terminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).noDeclarationError();
        Mockito.doNothing().when(parserSpy).noMainMethodError();

        // Then
        assertFalse(parserSpy.parse());
    }

    @Test
    public void givenInvalidExpressionWhenParseThenError(){
        // Given
        File currentFile = new File("src/test/resources/Parser_resources/invalidExpression.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // When
        MainParserProcess parserProcess = new MainParserProcess(tokenStream);
        MainParserProcess parserSpy = Mockito.spy(parserProcess);
        Mockito.doNothing().when(parserSpy).nonTerminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).terminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).noDeclarationError();
        Mockito.doNothing().when(parserSpy).noMainMethodError();

        // Then
        assertFalse(parserSpy.parse());
    }

    @Test
    public void givenInvalidReturnStatementWhenParseThenError(){
        // Given
        File currentFile = new File("src/test/resources/Parser_resources/invalidReturn.txt");
        InputStream inputStream = new InputStream(currentFile);
        CustomVector<Token> tokenStream = new CustomVector<>();
        DFA dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }

        // When
        MainParserProcess parserProcess = new MainParserProcess(tokenStream);
        MainParserProcess parserSpy = Mockito.spy(parserProcess);
        Mockito.doNothing().when(parserSpy).nonTerminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).terminalError(Mockito.anyString());
        Mockito.doNothing().when(parserSpy).noDeclarationError();
        Mockito.doNothing().when(parserSpy).noMainMethodError();

        // Then
        assertFalse(parserSpy.parse());
    }

}
