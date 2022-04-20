/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner.DFA;

import cs.vapo.DataStructures.ConstantSymbolTable;
import cs.vapo.DataStructures.CustomHashMap;
import cs.vapo.Main;
import cs.vapo.DataStructures.IdentifierSymbolTable;
import cs.vapo.scanner.InputStream;
import cs.vapo.scanner.tokens.Token;

import java.util.Locale;

public class DFA {

    InputStream inputStream;
    TransitionTable transitionTable;
    String tokenBuffer;
    boolean isComment;
    ConstantSymbolTable constantSymbolTable = Main.constantSymbolTable;
    IdentifierSymbolTable identifierSymbolTable = Main.identifierSymbolTable;
    CustomHashMap<String, Integer> keywords;

    public DFA(InputStream inputStream) {
        this.inputStream = inputStream;
        this.transitionTable = new TransitionTable();
        tokenBuffer = "";
        isComment = false;
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
        tokenBuffer = "";
        int state = 0;
        isComment = false;
        char currentChar = inputStream.readChar();
        appendBuffer(currentChar);
        while (!transitionTable.acceptStates[state] && !transitionTable.errorStates[state]) {
            int newState = transitionTable.moveState(state, currentChar);
            if (newState == 4 || newState == 5) {
                isComment = true;
                tokenBuffer = "";
            }
            if (transitionTable.advanceInput(state, currentChar) && !transitionTable.isDelimiter(newState, inputStream.peek())) {
                currentChar = inputStream.readChar();
                appendBuffer(currentChar);
            } else {
                currentChar = inputStream.peek();
            }
            state = newState;
        }
        if (transitionTable.acceptStates[state]) {
            tokenBuffer = tokenBuffer.toLowerCase(Locale.ROOT);
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
    private void handleError(int state){
        switch (state) {
            // invalid character error
            case 32 -> System.out.println("Invalid character in " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            // invalid identifier construction
            case 33 -> System.out.println("Invalid Identifier: " + tokenBuffer + " in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            // invalid constant construction
            case 34 -> System.out.println("Invalid constant: " + tokenBuffer + " in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());

            // invalid operator construction
            case 35 -> System.out.println("Invalid operator: " + tokenBuffer + " in line: " + inputStream.getLineCount() + ":" + inputStream.getColumnCount());
            default -> {
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
                if(keywords.get(tokenBuffer) != null){
                    token = new Token(keywords.get(tokenBuffer));
                    System.out.println(token +  " State: " + state);
                }else{
                    int tableID = identifierSymbolTable.add(tokenBuffer);
                    token = new Token(28, tableID);
                    System.out.println(token +  " State: " + state);
                }
                break;
            // numeric constants
            case 11:
                // the current constant is converted in the add function of the
                // constant symbol table.
                int tableID = constantSymbolTable.add(tokenBuffer);
                token = new Token(29, tableID);
                constantSymbolTable.add(tokenBuffer);
                System.out.println(token +  " State: " + state);
                break;
            // division operator
            case 12:
                token = new Token(12);
                System.out.println(token +  " State: " + state);
                break;
            // Comment
            case 13:
                break;
            // Addition operator
            case 14:
                token = new Token(9);
                System.out.println(token +  " State: " + state);
                break;
            // Subtraction operator
            case 15:
                token = new Token(10);
                System.out.println(token +  " State: " + state);
                break;
            // Multiplication operator
            case 16:
                token = new Token(11);
                System.out.println(token +  " State: " + state);
                break;
            // Less than operator
            case 17:
                token = new Token(13);
                System.out.println(token +  " State: " + state);
                break;
            // Less than or equal to operator
            case 18:
                token = new Token(15);
                System.out.println(token +  " State: " + state);
                break;
            // Greater than operator
            case 19:
                token = new Token(14);
                System.out.println(token +  " State: " + state);
                break;
            // Greater than or equal to operator
            case 20:
                token = new Token(16);
                System.out.println(token +  " State: " + state);
                break;
            // Assignment operator
            case 21:
                token = new Token(19);
                System.out.println(token +  " State: " + state);
                break;
            // Equal to operator
            case 22:
                token = new Token(17);
                System.out.println(token +  " State: " + state);
                break;
            // Not equal to operator
            case 23:
                token = new Token(18);
                System.out.println(token +  " State: " + state);
                break;
            // ;
            case 24:
                token = new Token(20);
                System.out.println(token +  " State: " + state);
                break;
            // ,
            case 25:
                token = new Token(21);
                System.out.println(token +  " State: " + state);
                break;
            // {
            case 26:
                token = new Token(26);
                System.out.println(token +  " State: " + state);
                break;
            // }
            case 27:
                token = new Token(27);
                System.out.println(token +  " State: " + state);
                break;
            // [
            case 28:
                token = new Token(24);
                System.out.println(token +  " State: " + state);
                break;
            // ]
            case 29:
                token = new Token(25);
                System.out.println(token +  " State: " + state);
                break;
            // (
            case 30:
                token = new Token(22);
                System.out.println(token +  " State: " + state);
                break;
            // )
            case 31:
                token = new Token(23);
                System.out.println(token +  " State: " + state);
                break;
            default:
                break;
        }
    }
}
