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
    ParsingTable parsingTable;

    int tokenBufferPointer;
    boolean isLocal;

    CLI cli = Main.cli;

    public MainParserProcess(CustomVector<Token> tokenStream){
        this.tokenStream = tokenStream;
        tokenBufferPointer = 0;
        isLocal = false;
        parsingTable = new ParsingTable();
    }

    /**
     * Method for initiating the parsing process.
     */
    public boolean parse(){
        // if there is an empty file, throw error
        if(tokenStream.size() == 0) {
            noDeclarationError();
            return false;
        }
        // add $ to token stream
        tokenStream.add(new Token(30, -1, 0, 0));


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
                terminalError(stack.peek());
                return false;
            }

            else if(parsingTable.move(stack.peek(), tokenIDToToken(currentToken)) == 0){
                nonTerminalError(stack.peek());
                return false;
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
                noMainMethodError();
                return false;
            }
            return true;
        } else {
            nonTerminalError(stack.peek());
            return false;
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
     * Error helper function
     */
    public void noDeclarationError(){
        cli.sendMessage("Error: Program must have at least one declaration.");
    }

    /**
     * Error helper function
     */
    public void noMainMethodError(){
        cli.sendMessage("Error, last method is not of type void main(void)");
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
     * Handles terminal on top of stack error.
     * @param currentGrammarElement current element on top of stack
     */
    public void terminalError(String currentGrammarElement){
        Token currentToken = tokenStream.size() == 1 ? tokenStream.get(tokenBufferPointer) : tokenStream.get(tokenBufferPointer - 1);
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


    /**
     * Non-terminal on top of stack error
     * @param currentGrammarElement current element on top of stack
     */
    public void nonTerminalError(String currentGrammarElement){
        Token currentToken;
        try{
            currentToken = tokenStream.size() == 1 ? tokenStream.get(tokenBufferPointer) : tokenStream.get(tokenBufferPointer - 1);
        }catch (ArrayIndexOutOfBoundsException e){
            cli.sendMessage("Program must begin with a declaration");
            return;
        }
        cli.sendMessage("Error in line " + currentToken.getLine());
        int terminalIdx = parsingTable.getTerminalIndex(tokenIDToToken(currentToken));
        switch (currentGrammarElement){
            case "program":
            case "program'":
                cli.sendMessage("Program must begin with a declaration.");
                break;
            case "declarationList'":
                cli.sendMessage("Declarations must begin with int or void.");
                break;
            case "declaration'":
                cli.sendMessage("Invalid declaration, expected ';'");
                break;
            case "var_dec":
                cli.sendMessage("Invalid variable declaration, expected 'int' type.");
                break;
            case "var_dec'":
                cli.sendMessage("Invalid variable declaration, expected ';' or [size]");
                break;
            case "params":
                cli.sendMessage("Invalid parameter, must be int type or void.");
                break;
            case "params_list":
                cli.sendMessage("Invalid parameter list, must begin with int type.");
                break;
            case "params_list'":
                cli.sendMessage("Invalid parameter list, parameters must be seperated by ','");
                break;
            case "param":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Parameters can only have type int.");
                break;
            case "param'":
                cli.sendMessage("Invalid parameter, must begin with int type.");
                break;
            case "local_dec'":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Local declarations cannot be void.");
                if(getTerminalType(terminalIdx).equals("STATEMENT")) cli.sendMessage("Else statement can only follow a previous if statement.");
                if(getTerminalType(terminalIdx).equals("OPERATOR")) cli.sendMessage("Operators must follow an expression.");
                else cli.sendMessage("Invalid local declaration, unexpected token '" + tokenIDToToken(currentToken)+"'");
                break;
            case "stmt_list'":
            case "stmt":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Cannot have declarations within statements.");
                if(getTerminalType(terminalIdx).equals("STATEMENT")) cli.sendMessage("Else statement can only follow a previous if statement.");
                if(getTerminalType(terminalIdx).equals("OPERATOR")) cli.sendMessage("Operators must follow an expression.");
                else cli.sendMessage("Invalid statement, unexpected token '" + tokenIDToToken(currentToken) +"'");
                break;
            case "stmt'":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Cannot have declarations within statements.");
                if(getTerminalType(terminalIdx).equals("STATEMENT")) cli.sendMessage("Cannot have statement within statement.");
                if(getTerminalType(terminalIdx).equals("OPERATOR")) cli.sendMessage("Operators must follow an expression.");
                if(getTerminalType(terminalIdx).equals(";")) cli.sendMessage("Invalid termination of statement.");
                else cli.sendMessage("Invalid statement, unexpected token '" + tokenIDToToken(currentToken)+"'");
                break;
            case "stmt''":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Cannot have declarations within statements.");
                if(getTerminalType(terminalIdx).equals("STATEMENT")) cli.sendMessage("Cannot have statement within statement.");
                if(getTerminalType(terminalIdx).equals("OPERATOR")) cli.sendMessage("Operators must follow an expression.");
                else cli.sendMessage("Invalid statement, unexpected token '" + tokenIDToToken(currentToken) + "' , statement must end with a ';'.");
                break;
            case "selection_stmt'":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Cannot have declarations within statements.");
                if(getTerminalType(terminalIdx).equals("OPERATOR")) cli.sendMessage("Operators must follow an expression.");
                if(getTerminalType(terminalIdx).equals(";")) cli.sendMessage("Invalid termination of selection statement.");
                else cli.sendMessage("Invalid selection statement, unexpected token '" + tokenIDToToken(currentToken));
                break;
            case "input_stmt":
                cli.sendMessage("Invalid input statement. Must specify a variable.");
                break;
            case "expr":
            case "expr'":
            case "a_expr":
            case "a_expr'":
            case "term'":
                if(getTerminalType(terminalIdx).equals("TYPE")) cli.sendMessage("Cannot have declarations within expressions.");
                if(getTerminalType(terminalIdx).equals("STATEMENT")) cli.sendMessage("Cannot have statement within expression.");
                if(getTerminalType(terminalIdx).equals("OPERATOR")) cli.sendMessage("Invalid use of operator '" + tokenIDToToken(currentToken) + "'");
                else cli.sendMessage("Invalid expression statement, unexpected token '" + tokenIDToToken(currentToken));
                break;
            case "factor":
            case "factor'":
                 cli.sendMessage("Invalid expression statement, unexpected token '" + tokenIDToToken(currentToken));
                break;
            case "relop":
                cli.sendMessage("Invalid expression, expected relational operator.");
                break;
            case "args":
                cli.sendMessage("Invalid statement, arguments must be enclosed within parentheses.");
                break;
            case "args_list":
                cli.sendMessage("Invalid statement, arguments must be of type int.");
                break;
            case "args_list'":
                cli.sendMessage("Invalid argument list, arguments must be seperated by ','");
                break;
        }
    }

    /**
     * returns the type or actual value of a terminal for error handling
     * @param terminalIdx terminal idx for parsing table
     * @return type or value of terminal
     */
    String getTerminalType(int terminalIdx){
        if(terminalIdx == 0 || terminalIdx == 1) return "TYPE";
        else if(terminalIdx == 2) return "ID";
        else if(terminalIdx == 3) return "CONSTANT";
        else if(terminalIdx >= 4 && terminalIdx <= 9) return "STATEMENT";
        else if(terminalIdx >= 10 && terminalIdx <= 20) return "OPERATOR";
        else if(terminalIdx == 21) return ";";
        else if(terminalIdx == 22) return ",";
        else if(terminalIdx == 23) return "(";
        else if(terminalIdx == 24) return ")";
        else if(terminalIdx == 25) return "[";
        else if(terminalIdx == 26) return "]";
        else if(terminalIdx == 27) return "{";
        else if(terminalIdx == 28) return "}";
        return "NULL";
    }

}
