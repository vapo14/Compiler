/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.parser;

import cs.vapo.CLI.CLI;
import cs.vapo.DataStructures.CustomStack;
import cs.vapo.DataStructures.CustomVector;
import cs.vapo.Main;
import cs.vapo.scanner.tokens.Token;

import java.util.Objects;

public class MainParserProcess {

    CustomVector<Token> tokenStream;

    int tokenBufferPointer;
    boolean isLocal;

    CLI cli = Main.cli;

    public MainParserProcess(CustomVector<Token> tokenStream){
        this.tokenStream = tokenStream;
        tokenBufferPointer = 0;
        isLocal = false;
    }

    public void parse(){
        // add $ to token stream
        tokenStream.add(new Token(30, -1, 0, 0));
        ParsingTable parsingTable = new ParsingTable();

        CustomStack<String> stack = new CustomStack<>();
        stack.push("$");
        stack.push("program");

        Token currentToken = tokenStream.get(tokenBufferPointer);

        while(!Objects.equals(stack.peek(), "$")){
            if(stack.peek().equals(tokenIDToToken(currentToken))){
                checkScope(currentToken);
                handleToken(currentToken);
                stack.pop();
                tokenBufferPointer++;
                currentToken = tokenStream.get(tokenBufferPointer);
            }
            else if(parsingTable.terminals.get(stack.peek()) != null){
                terminalError(tokenStream.get(tokenBufferPointer - 1), stack.peek());
                return;
            }

            else if(parsingTable.move(stack.peek(), tokenIDToToken(currentToken)) == 0){
                error(tokenStream.get(tokenBufferPointer - 1), stack.peek());
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
        if(stack.peek().equals("$")) {
            if(!Main.identifierSymbolTable.lastMethodIsMain()){
                cli.sendMessage("Error, last method is not of type main()");
                return;
            }
            System.out.println("ACCEPT");
        } else {
            error(tokenStream.get(tokenBufferPointer - 1), stack.peek());
        }

    }

    /**
     * checks the current scope and updates the flag
     * @param currentToken current token
     */
    void checkScope(Token currentToken){
        if(currentToken.getId() == 26){
            isLocal = true;
        }
        if(currentToken.getId() == 27){
            isLocal = false;
        }
    }

    /**
     * Handle recognized token for updating symbol table if necessary
     * @param currentToken current token
     */
    public void handleToken(Token currentToken) {
        if(currentToken.getId() == 28){
            int nextTokenID = tokenStream.get(tokenBufferPointer + 1).getId();
            // current token is a variable ID
            if (nextTokenID == 20 || nextTokenID == 23) {
                updateIDToken(currentToken, "variable");
            }
            // current token is an array
            if (nextTokenID == 24) {
                updateIDToken(currentToken, "array");
            }
            // current token is a method
            if (nextTokenID == 22) {
                updateIDToken(currentToken, "method");
            }
        }
    }

    /**
     * updates the ID token with the corresponding type
     * @param currentToken current token
     * @param type type of ID
     */
    void updateIDToken(Token currentToken, String type){
        int idAttributeCount = Main.identifierSymbolTable.getAttributeCount(currentToken.getPointer());
        // check if the ID has been previously declared, if not, declare
        // the type and the scope
        if(idAttributeCount == 1){
            // update ID type
            Main.identifierSymbolTable.update(currentToken.getPointer(), type);
            // update ID scope
            Main.identifierSymbolTable.update(currentToken.getPointer(), isLocal ? "local" : "global");
        }
    }

    /**
     * temporary generic error method
     */
    void error(Token currentToken, String currentGrammarElement){
        cli.sendMessage("Error in line " + currentToken.getLine());
    }

    /**
     * Handles terminal on top of stack error.
     * @param currentToken
     * @param currentGrammarElement
     */
    void terminalError(Token currentToken, String currentGrammarElement){
        cli.sendMessage("Error in line " + currentToken.getLine() + ", Expected: " + currentGrammarElement + " Got: " + tokenIDToToken(currentToken));
    }

    /**
     * based on the tokenID table
     * @param token token to evaluate
     * @return the token string
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
