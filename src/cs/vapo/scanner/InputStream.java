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
    PushbackReader bufferedReader;
    int currentChar;
    private int lineCount;
    private int columnCount;


    public InputStream(File currentFile) {
        this.currentFile = currentFile;
        try {
            fileReader = new FileReader(currentFile);
            bufferedReader = new PushbackReader(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        this.lineCount = 0;
        this.columnCount = 0;
        this.currentChar = 0;
    }

    public char readChar() {
        // FIXME: i dont like this exception handling
        char c = 'e';
        try {
            currentChar = bufferedReader.read();
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

    public char peekChar(){
        char c = 'e';
        try{
            c = (char) bufferedReader.read();
            bufferedReader.unread(c);
        }catch (IOException e){
            System.out.println("Peeking failed");
        }
        return c;
    }

    public void returnChar(char c){
        try{
            bufferedReader.unread(c);
        }catch (IOException e){
            System.out.println("Peeking failed");
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
