/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.CLI;

/**
 * Simple CLI for input and output management.
 */
public class CLI {

    private final String filename;

    public CLI(String[] args) throws IllegalArgumentException{
        if(args.length == 0){
            System.out.println("Usage: javac -jar compiler <PATH TO FILE>");
            throw new IllegalArgumentException();
        }
        this.filename = args[0];
    }

    public void sendMessage(String message){
        System.out.println(message);
    }

    public String getFileName(){
        return this.filename;
    }
}
