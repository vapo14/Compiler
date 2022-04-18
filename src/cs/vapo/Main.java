/*
* Written by: Víctor Padrón 2022
* Student ID: A01561947
*
* */

package cs.vapo;

import cs.vapo.DataStructures.CustomHashMap;
import cs.vapo.scanner.MainScannerProcess;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MainScannerProcess msp = new MainScannerProcess();
        msp.initRead(new File("test.txt"));
    }
}
