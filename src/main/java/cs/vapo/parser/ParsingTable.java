/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.parser;

import cs.vapo.DataStructures.CustomHashMap;
import cs.vapo.DataStructures.CustomVector;

public class ParsingTable {
    public Integer[][] parsingTable = {
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 9, 0, 8, 0, 0, 0, 0},
            {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 12, 0, 0, 0, 0},
            {13, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 17, 0, 0, 0, 0, 0},
            {18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 19, 0, 0, 0, 0},
            {21, 0, 22, 0, 22, 0, 22, 22, 22, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 22, 0},
            {0, 0, 23, 0, 23, 0, 23, 23, 23, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 24, 0},
            {0, 0, 25, 0, 27, 0, 28, 29, 30, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 34, 0, 33, 0, 0, 0, 0},
            {0, 0, 36, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 35, 0, 36, 0, 0, 0, 0, 0, 0},
            {0, 0, 38, 0, 38, 37, 38, 38, 38, 38, 38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 38, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, 0, 0, 0, 40, 0, 0, 0, 0},
            {0, 0, 42, 43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 44, 44, 44, 44, 44, 44, 0, 45, 0, 0, 45, 0, 0, 0, 0, 0},
            {0, 0, 47, 48, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 51, 0, 0, 51, 0, 51, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 54, 54, 52, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 56, 57, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 59, 61, 62, 63, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 65, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65, 66, 0, 0, 0, 0, 0},
            {0, 0, 67, 67, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 68, 67, 0, 0, 0, 0, 0, 0},
    };

    public CustomHashMap<Integer, CustomVector<String>> productionList = new CustomHashMap<>();


    public CustomHashMap<String, Integer> nonTerminals;
    public CustomHashMap<String, Integer> terminals;

    public ParsingTable() {
        nonTerminals = new CustomHashMap<>();
        terminals = new CustomHashMap<>();
        initProductionList();
        initNonTerminals();
        initTerminals();
    }

    /**
     * uses a terminal, non-terminal and parsing table to determine the next production
     * @param nonTerminal
     * @param terminal
     * @return int pointing to the corresponding production
     */
    public int move(String nonTerminal, String terminal){
        return parsingTable[getNonTerminalIndex(nonTerminal)][getTerminalIndex(terminal)];
    }

    /**
     * returns the index of the non-terminal for parsing table use
     * @param nonTerminal non-terminal string
     * @return non-terminal index
     */
    int getNonTerminalIndex(String nonTerminal){
        return nonTerminals.get(nonTerminal);
    }

    /**
     * returns the index of the terminal for parsing table use
     * @param terminal terminal string
     * @return terminal index
     */
    int getTerminalIndex(String terminal){
        return terminals.get(terminal);
    }

    void initTerminals(){
        terminals.add("int", 0);
        terminals.add("void", 1);
        terminals.add("ID", 2);
        terminals.add("NUM", 3);
        terminals.add("if", 4);
        terminals.add("else", 5);
        terminals.add("while", 6);
        terminals.add("return", 7);
        terminals.add("input", 8);
        terminals.add("output", 9);
        terminals.add("+", 10);
        terminals.add("-", 11);
        terminals.add("*", 12);
        terminals.add("/", 13);
        terminals.add("<", 14);
        terminals.add("<=", 15);
        terminals.add(">", 16);
        terminals.add(">=", 17);
        terminals.add("==", 18);
        terminals.add("!=", 19);
        terminals.add("=", 20);
        terminals.add(";", 21);
        terminals.add(",", 22);
        terminals.add("(", 23);
        terminals.add(")", 24);
        terminals.add("[", 25);
        terminals.add("]", 26);
        terminals.add("{", 27);
        terminals.add("}", 28);
        terminals.add("$", 29);
    }

    void initNonTerminals(){
        nonTerminals.add("program", 0);
        nonTerminals.add("program'", 1);
        nonTerminals.add("declarationList'", 2);
        nonTerminals.add("declaration'", 3);
        nonTerminals.add("var_dec", 4);
        nonTerminals.add("var_dec'", 5);
        nonTerminals.add("params", 6);
        nonTerminals.add("params_list", 7);
        nonTerminals.add("params_list'", 8);
        nonTerminals.add("param", 9);
        nonTerminals.add("param'", 10);
        nonTerminals.add("local_dec'", 11);
        nonTerminals.add("stmt_list'", 12);
        nonTerminals.add("stmt", 13);
        nonTerminals.add("stmt'", 14);
        nonTerminals.add("stmt''", 15);
        nonTerminals.add("selection_stmt'", 16);
        nonTerminals.add("input_stmt", 17);
        nonTerminals.add("expr", 18);
        nonTerminals.add("expr'", 19);
        nonTerminals.add("a_expr", 20);
        nonTerminals.add("a_expr'", 21);
        nonTerminals.add("term'", 22);
        nonTerminals.add("factor", 23);
        nonTerminals.add("factor'", 24);
        nonTerminals.add("relop", 25);
        nonTerminals.add("args", 26);
        nonTerminals.add("args_list", 27);
    }

    void initProductionList() {
        productionList.add(1, new CustomVector<String>().add("int").add("ID").add("declaration'").add("declarationList'"));
        productionList.add(2, new CustomVector<String>().add("void").add("ID").add("(").add("program'"));
        productionList.add(3, new CustomVector<String>().add("param").add("params_list'").add(")").add("{").add("local_dec'").add("stmt_list'").add("}").add("declarationList'"));
        productionList.add(4, new CustomVector<String>().add("void").add(")").add("{").add("local_dec'").add("stmt_list'").add("}").add("declarationList'"));
        productionList.add(5, new CustomVector<String>().add("int").add("ID").add("declaration'").add("declarationList'"));
        productionList.add(6, new CustomVector<String>().add("void").add("ID").add("(").add("params").add(")").add("{").add("local_dec'").add("stmt_list'").add("}").add("declarationList'"));
        productionList.add(7, new CustomVector<String >().add(""));
        productionList.add(8, new CustomVector<String>().add("var_dec'"));
        productionList.add(9, new CustomVector<String>().add("(").add("params").add(")").add("{").add("local_dec'").add("stmt_list'").add("}"));
        productionList.add(10, new CustomVector<String>().add("int").add("ID").add("var_dec'"));
        productionList.add(11, new CustomVector<String>().add(";"));
        productionList.add(12, new CustomVector<String>().add("[").add("NUM").add("]").add(";"));
        productionList.add(13, new CustomVector<String>().add("params_list"));
        productionList.add(14, new CustomVector<String>().add("void"));
        productionList.add(15, new CustomVector<String>().add("param").add("params_list'"));
        productionList.add(16, new CustomVector<String>().add(",").add("params").add("params_list'"));
        productionList.add(17, new CustomVector<String>().add(""));
        productionList.add(18, new CustomVector<String>().add("int").add("ID").add("param'"));
        productionList.add(19, new CustomVector<String>().add("[").add("]"));
        productionList.add(20, new CustomVector<String>().add(""));
        productionList.add(21, new CustomVector<String>().add("var_dec").add("local_dec'"));
        productionList.add(22, new CustomVector<String>().add(""));
        productionList.add(23, new CustomVector<String>().add("stmt").add("stmt_list'"));
        productionList.add(24, new CustomVector<String>().add(""));
        productionList.add(25, new CustomVector<String>().add("ID").add("stmt'"));
        productionList.add(26, new CustomVector<String>().add("{").add("local_dec'").add("stmt_list'").add("}"));
        productionList.add(27, new CustomVector<String>().add("if").add("(").add("expr").add(")").add("stmt").add("selection_stmt'"));
        productionList.add(28, new CustomVector<String>().add("while").add("(").add("expr").add(")").add("stmt"));
        productionList.add(29, new CustomVector<String>().add("return").add("stmt''"));
        productionList.add(30, new CustomVector<String>().add("input").add("ID").add("input_stmt"));
        productionList.add(31, new CustomVector<String>().add("output").add("expr").add(";"));
        productionList.add(32, new CustomVector<String>().add("").add("=").add("expr").add(";"));
        productionList.add(33, new CustomVector<String>().add("[").add("a_expr").add("]").add("=").add("expr").add(";"));
        productionList.add(34, new CustomVector<String>().add("(").add("args").add(")").add(";"));
        productionList.add(35, new CustomVector<String>().add(";"));
        productionList.add(36, new CustomVector<String>().add("expr").add(";"));
        productionList.add(37, new CustomVector<String>().add("else").add("stmt"));
        productionList.add(38, new CustomVector<String>().add(""));
        productionList.add(39, new CustomVector<String>().add(";"));
        productionList.add(40, new CustomVector<String>().add("[").add("a_expr").add("]").add(";"));
        productionList.add(41, new CustomVector<String>().add("(").add("a_expr").add(")").add("term'").add("a_expr'").add("expr'"));
        productionList.add(42, new CustomVector<String>().add("ID").add("factor'").add("term'").add("a_expr'").add("expr'"));
        productionList.add(43, new CustomVector<String>().add("NUM").add("term'").add("a_expr'").add("expr'"));
        productionList.add(44, new CustomVector<String>().add("relop").add("a_expr"));
        productionList.add(45, new CustomVector<String>().add(""));
        productionList.add(46, new CustomVector<String>().add("(").add("a_expr").add(")").add("term").add("a_expr'"));
        productionList.add(47, new CustomVector<String>().add("ID").add("factor'").add("term'").add("a_expr'"));
        productionList.add(48, new CustomVector<String>().add("NUM").add("term'").add("a_expr'"));
        productionList.add(49, new CustomVector<String>().add("+").add("factor").add("term'").add("a_expr'"));
        productionList.add(50, new CustomVector<String>().add("-").add("factor").add("term'").add("a_expr'"));
        productionList.add(51, new CustomVector<String>().add(""));
        productionList.add(52, new CustomVector<String>().add("*").add("factor").add("term'"));
        productionList.add(53, new CustomVector<String>().add("/").add("factor").add("term'"));
        productionList.add(54, new CustomVector<String>().add(""));
        productionList.add(55, new CustomVector<String>().add("(").add("a_expr").add(")"));
        productionList.add(56, new CustomVector<String>().add("ID").add("factor'"));
        productionList.add(57, new CustomVector<String>().add("NUM"));
        productionList.add(58, new CustomVector<String>().add("[").add("a_expr").add("]"));
        productionList.add(59, new CustomVector<String>().add("<="));
        productionList.add(60, new CustomVector<String>().add("<"));
        productionList.add(61, new CustomVector<String>().add(">"));
        productionList.add(62, new CustomVector<String>().add(">="));
        productionList.add(63, new CustomVector<String>().add("=="));
        productionList.add(64, new CustomVector<String>().add("!="));
        productionList.add(65, new CustomVector<String>().add("args_list"));
        productionList.add(66, new CustomVector<String>().add(""));
        productionList.add(67, new CustomVector<String>().add("a_expr"));
        productionList.add(68, new CustomVector<String>().add(",").add("a_expr").add("args_list"));
    }
}
