/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.CLI;

public class CLI {

    private String [] args;
    private String filename;

    public CLI(String[] args){
        this.args = args;
        this.filename = args[0];
    }

    public void sendMessage(String message){
        System.out.println(message);
    }

    public String getFileName(){
        return this.filename;
    }
}
