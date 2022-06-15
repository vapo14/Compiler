/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.parser;

import cs.vapo.DataStructures.CustomVector;
import cs.vapo.scanner.tokens.Token;

import java.util.Objects;
import java.util.Stack;

public class MainParserProcess {
    public void parse(CustomVector<Token> tokenStream){

        System.out.println(tokenStream);

        Stack<String> stack = new Stack<>();
        stack.push("$");

        int tokenBufferPointer = 0;
        Token currentToken = tokenStream.get(tokenBufferPointer);

        while(!Objects.equals(stack.peek(), "$")){
            // TODO: fix this shit
            if(isToken(stack.peek(), currentToken)){

            }
        }

    }

    public boolean isToken(String topStack, Token token){
        boolean isToken = false;
        String[] tokens = {"int", "void", "ID", "NUM", "if","else","while","return","input","output","+","-","*","/","<","<=",">",">=","==","!=","=",";",",","(",")","[","]","{","}" };
        for (String s : tokens) {
            if (s.equals(topStack)) {
                isToken = true;
                break;
            }
        }
        return isToken;
    }
}
