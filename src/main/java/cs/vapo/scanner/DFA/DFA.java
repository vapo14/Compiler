/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner.DFA;

import cs.vapo.CLI.CLI;
import cs.vapo.DataStructures.ConstantSymbolTable;
import cs.vapo.DataStructures.CustomHashMap;
import cs.vapo.DataStructures.CustomVector;
import cs.vapo.Main;
import cs.vapo.DataStructures.IdentifierSymbolTable;
import cs.vapo.scanner.InputStream;
import cs.vapo.scanner.tokens.Token;

import java.util.Locale;

/**
 *  Table-driven implementation of the DFA
 */
public class DFA {

    InputStream inputStream;
    TransitionTable transitionTable;
    String tokenBuffer;
    boolean isComment;
    ConstantSymbolTable constantSymbolTable = Main.constantSymbolTable;
    IdentifierSymbolTable identifierSymbolTable = Main.identifierSymbolTable;
    CLI cli = Main.cli;
    CustomHashMap<String, Integer> keywords;
    CustomVector<Token> tokenStream;

    public DFA(InputStream inputStream, CustomVector<Token> tokenStream) {
        this.inputStream = inputStream;
        this.transitionTable = new TransitionTable();
        tokenBuffer = "";
        isComment = false;
        this.tokenStream = tokenStream;
        keywords = new CustomHashMap<>();
        keywords.add("void", 2);
        keywords.add("else", 4);
        keywords.add("if", 5);
        keywords.add("int", 6);
        keywords.add("return", 1);
        keywords.add("while", 3);
        keywords.add("input", 7);
        keywords.add("output", 8);

    }

    /**
     * Table-driven DFA implementation. Analyzes an input stream and identifies
     * valid tokens. Makes use of the TransitionTable class.
     * @return 0 if a token has been validly recognized, -1 if an error state is reached
     */
    public int readNextToken() {
        // init buffer to empty string and state to 0
        // read initial char and append to buffer
        tokenBuffer = "";
        int state = 0;
        isComment = false;
        char currentChar = inputStream.readChar();
        appendBuffer(currentChar);
        // repeat until reaching an accept or error state
        while (!transitionTable.acceptStates[state] && !transitionTable.errorStates[state]) {
            // if we haven't moved state and reached the end of file, end DFA process
            if(currentChar == '\uFFFF' && state == 0) break;
            // get the new state with currentChar
            int newState = transitionTable.moveState(state, currentChar);
            // if the current char is part of a comment, disregard from buffer
            if (newState == 4 || newState == 5) {
                isComment = true;
                tokenBuffer = "";
                // if we haven't moved state and reached the end of file, end DFA process
                if(currentChar == '\uFFFF') break;
            }
            // as long as the character is not a delimiter for the next state
            // advance the input stream. If it is a delimiter, read the character but
            // don't advance the input stream.
            if (transitionTable.advanceInput(state, currentChar) && !transitionTable.isDelimiter(newState, inputStream.peek())) {
                currentChar = inputStream.readChar();
                appendBuffer(currentChar);
            } else {
                currentChar = inputStream.peek();
            }
            state = newState;
        }
        // when reaching an accepting state, record the token
        // else handle the appropriate error state.
        if (transitionTable.acceptStates[state]) {
            recordToken(state, tokenBuffer);

        } else {
            handleError(state);
            return -1;
        }
        return 0;
    }

    /**
     * Appends to the current token buffer if the character is not a whitespace
     * and does not belong to a comment token.
     * @param c current character to append
     */
    private void appendBuffer(Character c) {
        if (!isComment && !transitionTable.isWhiteSpace(c)) tokenBuffer += c;
    }

    /**
     *  Sends an error message according to the current state
     * @param state current state
     */
    void handleError(int state){
        switch (state) {
            // invalid character error
            case 32 -> cli.sendMessage("Invalid character in " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            // invalid identifier construction
            case 33 -> cli.sendMessage("Invalid Identifier: " + tokenBuffer + " in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            // invalid constant construction
            case 34 -> cli.sendMessage("Invalid constant: " + tokenBuffer + " in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            // invalid operator construction
            case 35 -> cli.sendMessage("Invalid operator: " + tokenBuffer + " in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            default -> {
                if(state == 4 || state == 5){
                    // error state not reached, comment not closed error
                    cli.sendMessage("Comment not closed in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount() + ", reached EOF.");
                }
            }
        }
    }

    /**
     * Records the token in the stream. Appends to te corresponding symbol table
     * if appropriate.
     * @param state final state of the DFA
     * @param tokenBuffer   string that contains the accepted lexeme
     */
    private void recordToken(int state, String tokenBuffer){
        Token token;
        switch (state) {
            // identifiers
            case 10:
                // if the token is a keyword, create a token with just an ID
                // else create an identifier token and add it to the symbol table
                if(keywords.get(tokenBuffer.toLowerCase(Locale.ROOT)) != null){
                    token = new Token(keywords.get(tokenBuffer), inputStream.getLineCount(), inputStream.getColumnCount());
                }else{
                    int tableID = identifierSymbolTable.add(tokenBuffer);
                    token = new Token(28, tableID, inputStream.getLineCount(), inputStream.getColumnCount());
                }
                tokenStream.add(token);
                break;
            // numeric constants
            case 11:
                // the current constant is converted in the add function of the
                // constant symbol table.
                int tableID = constantSymbolTable.add(tokenBuffer);
                token = new Token(29, tableID, inputStream.getLineCount(), inputStream.getColumnCount());
                constantSymbolTable.add(tokenBuffer);
                tokenStream.add(token);
                break;
            // division operator
            case 12:
                token = new Token(12, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Comment
            case 13:
                break;
            // Addition operator
            case 14:
                token = new Token(9, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Subtraction operator
            case 15:
                token = new Token(10, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Multiplication operator
            case 16:
                token = new Token(11, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Less than operator
            case 17:
                token = new Token(13, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Less than or equal to operator
            case 18:
                token = new Token(15, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Greater than operator
            case 19:
                token = new Token(14, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Greater than or equal to operator
            case 20:
                token = new Token(16, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Assignment operator
            case 21:
                token = new Token(19, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Equal to operator
            case 22:
                token = new Token(17, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // Not equal to operator
            case 23:
                token = new Token(18, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // ;
            case 24:
                token = new Token(20, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // ,
            case 25:
                token = new Token(21, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // {
            case 26:
                token = new Token(26, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // }
            case 27:
                token = new Token(27, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // [
            case 28:
                token = new Token(24, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // ]
            case 29:
                token = new Token(25, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // (
            case 30:
                token = new Token(22, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            // )
            case 31:
                token = new Token(23, inputStream.getLineCount(), inputStream.getColumnCount());
                tokenStream.add(token);
                break;
            default:
                break;
        }
    }
}
