package cs.vapo.scanner;

import cs.vapo.scanner.DFA.DFA;
import cs.vapo.scanner.DFA.TransitionTable;
import jdk.internal.util.xml.impl.Input;

import java.io.File;

public class MainScannerProcess {

    String currentTokenBuffer;
    DFA dfa;
    InputStream inputStream;
    File currentFile;

    public MainScannerProcess(){
        currentTokenBuffer = "";
    }

    public void initRead(File file){
        this.currentFile = file;
        inputStream = new InputStream(currentFile);
        dfa = new DFA(inputStream);
    }

}
