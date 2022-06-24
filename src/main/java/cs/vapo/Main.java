/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */

package cs.vapo;

import cs.vapo.CLI.CLI;
import cs.vapo.DataStructures.ConstantSymbolTable;
import cs.vapo.DataStructures.CustomVector;
import cs.vapo.DataStructures.IdentifierSymbolTable;
import cs.vapo.parser.MainParserProcess;
import cs.vapo.scanner.MainScannerProcess;
import cs.vapo.scanner.tokens.Token;

import java.io.File;

public class Main {

    public static ConstantSymbolTable constantSymbolTable = new ConstantSymbolTable();
    public static IdentifierSymbolTable identifierSymbolTable = new IdentifierSymbolTable();
    public static CLI cli;

    public static void main(String[] args) {
        // write your code here
        try{
            cli = new CLI(args);
        }catch (IllegalArgumentException e){
            return;
        }
        MainScannerProcess msp = new MainScannerProcess();
        CustomVector<Token> tokenStream = msp.initRead(new File(cli.getFileName()));

        if(tokenStream == null) return;
        MainParserProcess parser = new MainParserProcess(tokenStream);
        if (parser.parse()) cli.sendMessage("Accepted.");
        cli.sendMessage(identifierSymbolTable.toString());
    }
}
