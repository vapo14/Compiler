package cs.vapo.scanner.DFA;

import cs.vapo.scanner.InputStream;

public class DFA {

    InputStream inputStream;
    TransitionTable transitionTable;

    public DFA(InputStream inputStream){
        this.inputStream = inputStream;
        this.transitionTable = new TransitionTable();
    }

    public void readNextToken(){
        int state = 0;
        char currentChar =  inputStream.readChar();
        while (!transitionTable.acceptStates[state] && !transitionTable.errorStates[state]){
            int newState = transitionTable.move(state, currentChar);
            // TODO: finish dfa table implementation
        }
    }
}
