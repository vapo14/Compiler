/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner;

import cs.vapo.CLI.CLI;
import cs.vapo.DataStructures.CustomVector;
import cs.vapo.Main;
import cs.vapo.scanner.DFA.DFA;
import cs.vapo.scanner.DFA.TransitionTable;
import cs.vapo.scanner.tokens.Token;

import java.io.File;

/**
 * Handles the scanning process.
 */
public class MainScannerProcess {

    DFA dfa;
    InputStream inputStream;
    File currentFile;
    CustomVector<Token> tokenStream;
    CLI cli = Main.cli;

    public MainScannerProcess(){
        tokenStream = new CustomVector<>();
    }

    /**
     * Scans the file until reaching an EOF.
     * @param file file to scan
     */
    public void initRead(File file){
        if(file == null) return;
        this.currentFile = file;
        inputStream = new InputStream(currentFile);
        dfa = new DFA(inputStream, tokenStream);
        while(inputStream.getCurrentChar() != '\uFFFF' && inputStream.getCurrentChar() != -1){
            if(dfa.readNextToken() < 0) break;
        }
        inputStream.closeFile();
        cli.sendMessage(tokenStream.toString());
    }

}
