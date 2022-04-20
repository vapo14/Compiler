/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */

package cs.vapo;

import cs.vapo.DataStructures.ConstantSymbolTable;
import cs.vapo.DataStructures.IdentifierSymbolTable;
import cs.vapo.scanner.MainScannerProcess;

import java.io.File;

public class Main {

    public static ConstantSymbolTable constantSymbolTable = new ConstantSymbolTable();
    public static IdentifierSymbolTable identifierSymbolTable = new IdentifierSymbolTable();

    public static void main(String[] args) {
        // write your code here
        MainScannerProcess msp = new MainScannerProcess();
        msp.initRead(new File("test.txt"));
    }
}
