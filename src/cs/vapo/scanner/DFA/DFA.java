/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner.DFA;

import cs.vapo.scanner.InputStream;

public class DFA {

    InputStream inputStream;
    TransitionTable transitionTable;
    String tokenBuffer;
    boolean isComment;

    public DFA(InputStream inputStream) {
        this.inputStream = inputStream;
        this.transitionTable = new TransitionTable();
        tokenBuffer = "";
        isComment = false;
    }

    //TODO: implement a look ahead
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
            if (transitionTable.advanceInput(state, currentChar) && !transitionTable.isDelimiter(state, inputStream.peek())) {
                currentChar = inputStream.readChar();
                appendBuffer(currentChar);
            } else {
                currentChar = inputStream.peek();
            }
            state = newState;
        }
        if (transitionTable.acceptStates[state]) {
            // TODO: record token
            switch (state) {
                case 10:
                    break;
                default:
                    break;
            }
            System.out.println("Token: " + tokenBuffer + " State: " + state);

        } else {
            //TODO: ERROR
            System.out.println("Error in line: " + inputStream.getLineCount() + "\n\tColumn: " + inputStream.getColumnCount());
            return -1;
        }
        return 0;
    }

    private void appendBuffer(Character c) {
        if (!isComment && !transitionTable.isWhiteSpace(c)) tokenBuffer += c;
    }
}
