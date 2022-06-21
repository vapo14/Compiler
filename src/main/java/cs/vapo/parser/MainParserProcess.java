/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.parser;

import cs.vapo.DataStructures.CustomHashMap;
import cs.vapo.DataStructures.CustomStack;
import cs.vapo.DataStructures.CustomVector;
import cs.vapo.scanner.tokens.Token;

import java.util.Objects;

public class MainParserProcess {
    public void parse(CustomVector<Token> tokenStream){

        tokenStream.add(new Token(30, -1, 0, 0));
        ParsingTable parsingTable = new ParsingTable();

        CustomStack<String> stack = new CustomStack<>();
        stack.push("$");
        stack.push("program");

        int tokenBufferPointer = 0;
        Token currentToken = tokenStream.get(tokenBufferPointer);

        while(!Objects.equals(stack.peek(), "$")){
            if(stack.peek().equals(tokenIDToToken(currentToken))){
                stack.pop();
                tokenBufferPointer++;
                currentToken = tokenStream.get(tokenBufferPointer);
            }
            else if(parsingTable.terminals.get(stack.peek()) != null){
                error(tokenStream.get(tokenBufferPointer - 1));
                return;
            }

            else if(parsingTable.move(stack.peek(), tokenIDToToken(currentToken)) == 0){
                error(tokenStream.get(tokenBufferPointer - 1));
                return;
            }
            else if(parsingTable.move(stack.peek(), tokenIDToToken(currentToken)) != 0) {

                // get index from parsing table
                int idx = parsingTable.move(stack.peek(), tokenIDToToken(currentToken));
                // pop the non-terminal and replace with corresponding production
                stack.pop();
                // get corresponding production
                CustomVector<String> productionRule = parsingTable.productionList.get(idx);
                // if the production is of form A -> e, then DO NOT PUSH
                if(!productionRule.get(0).equals("")){
                    // push RHS from right to left into stack
                    for(int i = productionRule.size() - 1; i >= 0; i--){
                        stack.push(productionRule.get(i));
                    }
                }
            }
        }
        if(stack.peek().equals("$")){
            System.out.println("ACCEPT");
        }else{
            error(tokenStream.get(tokenBufferPointer - 1));
        }

    }

    /**
     * temporary generic error method
     */
    void error(Token currentToken){
        System.out.println("Error in line " + currentToken.getLine());
    }

    /**
     * based on the tokenID table
     * @param token
     * @return
     */
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
        tokenIDTable.add("$");
        return tokenIDTable.get(token.getId() - 1);
    }
}
