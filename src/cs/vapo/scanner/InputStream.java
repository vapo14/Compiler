package cs.vapo.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class InputStream {

    File currentFile;
    FileReader fileReader;
    int lineCount;
    int columnCount;


    public InputStream(File currentFile){
        this.currentFile = currentFile;
        try{
            fileReader = new FileReader(currentFile);
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
        this.lineCount = 0;
        this.columnCount = 0;

    }

    public char readChar(){
        return 'c';
    }

    void incrementLine(){

    }

    void incrementColumn(){

    }
}
