/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner;

import cs.vapo.scanner.DFA.DFA;
import cs.vapo.scanner.DFA.TransitionTable;

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
        while(inputStream.getCurrentChar() != -1){
            dfa.readNextToken();
        }
    }

}
