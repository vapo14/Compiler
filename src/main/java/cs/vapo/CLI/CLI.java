/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.CLI;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Simple CLI for input and output management.
 */
public class CLI {

    private final String filename;

    public CLI(String[] args) throws IllegalArgumentException{
        if(args.length == 0){
            System.out.println("Usage: java -jar Compiler.jar <PATH TO FILE>");
            throw new IllegalArgumentException();
        }
        try {
            Paths.get(args[0]);
        } catch (InvalidPathException | NullPointerException exception){
            System.out.println("Invalid path to file.");
        }
        this.filename = args[0];
    }

    /**
     * Sends a message to the console.
     * @param message message string to send
     */
    public void sendMessage(String message){
        System.out.println(message);
    }

    /**
     *
     * @return the current file that is being processed.
     */
    public String getFileName(){
        return this.filename;
    }
}
