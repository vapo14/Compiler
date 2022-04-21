/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner;

import java.io.*;

/**
 * Input Stream handler. This class is in charge of the movement of
 * the input stream.
 */
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
        this.lineCount = 1;
        this.columnCount = 0;
        pushbackReader = new PushbackReader(fileReader);
        this.currentChar = peek();
    }

    /**
     * Updates the currentChar by reading and advancing the input stream.
     * @return the new character from the input stream
     */
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

    /**
     * Function for 'peeking' the input stream. Reads the character and pushes it back
     * into the input stream.
     * @return the 'peeked' character.
     */
    public char peek(){
        char c = 'e';
        try{
            c = (char) pushbackReader.read();
            pushbackReader.unread(c);
        }catch (IOException e){
            System.out.println("Error while peeking input stream");
        }
        currentChar = c;
        return c;
    }

    /**
     * Closes the current file.
     */
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
