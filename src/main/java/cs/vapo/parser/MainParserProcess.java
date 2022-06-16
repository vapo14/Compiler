/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.parser;

import cs.vapo.DataStructures.CustomHashMap;
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
            if(stack.peek().equals(tokenIDToToken(currentToken))){
                stack.pop();
                tokenBufferPointer++;
                currentToken = tokenStream.get(tokenBufferPointer);
            }
        }

    }

    String tokenIDToToken(Token token) {
        CustomVector<String> tokenIDTable = new CustomVector<>();
        tokenIDTable.add("return");
        tokenIDTable.add("void");
        tokenIDTable.add("while");
        tokenIDTable.add("else");
        tokenIDTable.add("if");
        tokenIDTable.add("int");
        tokenIDTable.add("input");
        tokenIDTable.add("output");
        tokenIDTable.add("+");
        tokenIDTable.add("-");
        tokenIDTable.add("*");
        tokenIDTable.add("/");
        tokenIDTable.add("void");
        tokenIDTable.add("<");
        tokenIDTable.add(">");
        tokenIDTable.add("<=");
        tokenIDTable.add(">=");
        tokenIDTable.add("==");
        tokenIDTable.add("!=");
        tokenIDTable.add("=");
        tokenIDTable.add(";");
        tokenIDTable.add(",");
        tokenIDTable.add("(");
        tokenIDTable.add(")");
        tokenIDTable.add("[");
        tokenIDTable.add("]");
        tokenIDTable.add("{");
        tokenIDTable.add("}");
        tokenIDTable.add("ID");
        tokenIDTable.add("NUM");
        return tokenIDTable.get(token.getId() - 1);
    }
}
