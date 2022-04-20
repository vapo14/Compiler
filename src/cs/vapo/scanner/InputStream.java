/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner;

import java.io.*;

public class InputStream {

    File currentFile;
    FileReader fileReader;
    PushbackReader pushbackReader;
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
        this.currentChar = 0;
        pushbackReader = new PushbackReader(fileReader);
    }

    public char readChar() {
        // FIXME: i dont like this exception handling
        char c = 'e';
        try {
            currentChar = pushbackReader.read();
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

    public void returnChar (char c){
        try{
            pushbackReader.unread(c);
        }catch (IOException e){
            System.out.println("Error returning char: " + e);
        }
    }

    public char peek(){
        char c = 'e';
        try{
            c = (char) pushbackReader.read();
            pushbackReader.unread(c);
        }catch (IOException e){
            System.out.println("Error while peeking input stream");
        }
        return c;
    }

    public void closeFile(){
        try{
            fileReader.close();
        }catch (IOException e){
            System.out.println("Error closing file.");
        }
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

    public int getLineCount() {
        return lineCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
