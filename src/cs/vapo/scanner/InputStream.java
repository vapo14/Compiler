/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputStream {

    File currentFile;
    FileReader fileReader;
    int currentChar;
    private int lineCount;
    private int columnCount;


    public InputStream(File currentFile) {
        this.currentFile = currentFile;
        try {
            fileReader = new FileReader(currentFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        this.lineCount = 0;
        this.columnCount = 0;
        this.currentChar = 'e';
    }

    public char readChar() {
        // FIXME: i dont like this exception handling
        Character c = 'e';
        try {
            currentChar = fileReader.read();
            c = (char) currentChar;
            if (c == '\n'){
                incrementLine();
                columnCount = 0;
            }
            incrementColumn();
        } catch (IOException e) {

        }
        return c;
    }

    void incrementLine() {
        lineCount++;
    }

    void incrementColumn() {
        columnCount++;
    }

    public int getCurrentChar(){
        return this.currentChar;
    }
}
