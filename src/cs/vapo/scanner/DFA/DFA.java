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

    public DFA(InputStream inputStream){
        this.inputStream = inputStream;
        this.transitionTable = new TransitionTable();
        tokenBuffer = "";
        isComment = false;
    }

    //TODO: implement a look ahead
    public void readNextToken(){
        tokenBuffer = "";
        int state = 0;
        isComment = false;
        char currentChar = inputStream.readChar();
        while (!transitionTable.acceptStates[state] && !transitionTable.errorStates[state]){
            int newState = transitionTable.moveState(state, currentChar);
            if(newState == 4 || newState == 5){
                isComment = true;
                tokenBuffer = "";
            }
            if(transitionTable.advanceInput(state, currentChar)){
                currentChar = inputStream.readChar();
            }
            state = newState;
        }
        if(transitionTable.acceptStates[state]){
            // TODO: record token
            System.out.println("Token: " + tokenBuffer);
        } else {
            //TODO: ERROR
            System.out.println("Error in line: " + inputStream.getLineCount());
        }
    }

    private void appendBuffer(Character c){
        if(!isComment) tokenBuffer += c;
    }
}
