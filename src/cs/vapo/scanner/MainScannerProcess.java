package cs.vapo.scanner;

import cs.vapo.scanner.DFA.DFA;
import cs.vapo.scanner.DFA.TransitionTable;

import java.io.File;

public class MainScannerProcess {

    String currentTokenBuffer;
    TransitionTable transitionTable;
    DFA dfa;
    InputStream inputStream;

    public MainScannerProcess(){
        dfa = new DFA();
        currentTokenBuffer = "";
        transitionTable = new TransitionTable();
    }

    public void initRead(File file){

    }

}
