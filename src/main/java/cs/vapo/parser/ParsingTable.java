/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.parser;

import cs.vapo.DataStructures.CustomHashMap;
import cs.vapo.DataStructures.CustomVector;

public class ParsingTable {
    public Integer[][] ParsingTable = {
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 9, 0, 8, 0, 0, 0, 0},
            {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 12, 0, 0, 0, 0},
            {13, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 16, 0, 0, 0, 0, 0},
            {17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 18, 0, 0, 0, 0},
            {20, 0, 21, 0, 21, 0, 21, 21, 21, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0},
            {0, 0, 22, 0, 22, 0, 22, 22, 22, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 23, 0},
            {0, 0, 24, 0, 26, 0, 27, 28, 29, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 33, 0, 32, 0, 0, 0, 0},
            {0, 0, 35, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 35, 0, 0, 0, 0, 0, 0},
            {0, 0, 37, 0, 37, 36, 37, 37, 37, 37, 37, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 38, 0, 0, 0, 39, 0, 0, 0, 0},
            {0, 0, 41, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 43, 43, 43, 43, 43, 0, 44, 0, 0, 44, 0, 0, 0, 0, 0},
            {0, 0, 46, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 45, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 50, 0, 50, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 53, 53, 51, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 55, 56, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 59, 58, 60, 61, 62, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 64, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 65, 0, 0, 0, 0, 0},
            {0, 0, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 66, 0, 0, 0, 0, 0, 0}
    };

    public CustomHashMap<Integer, CustomVector<String>> productionList;

    public ParsingTable() {
        initProductionList();
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
        productionList.add(15, new CustomVector<String>().add(",").add("params").add("params_list'"));
        productionList.add(16, new CustomVector<String>().add(""));
        productionList.add(17, new CustomVector<String>().add("int").add("ID").add("param'"));
        productionList.add(18, new CustomVector<String>().add("[").add("]"));
        productionList.add(19, new CustomVector<String>().add(""));
        productionList.add(20, new CustomVector<String>().add("var_dec").add("local_dec'"));
        productionList.add(21, new CustomVector<String>().add(""));
        productionList.add(22, new CustomVector<String>().add("stmt").add("stmt_list'"));
        productionList.add(23, new CustomVector<String>().add(""));
        productionList.add(24, new CustomVector<String>().add("ID").add("stmt'"));
        productionList.add(25, new CustomVector<String>().add("{").add("local_dec'").add("stmt_list'").add("}"));
        productionList.add(26, new CustomVector<String>().add("if").add("(").add("expr").add(")").add("stmt").add("selection_stmt'"));
        productionList.add(27, new CustomVector<String>().add("while").add("(").add("expr").add(")").add("stmt"));
        productionList.add(28, new CustomVector<String>().add("return").add("stmt''"));
        productionList.add(29, new CustomVector<String>().add("input").add("ID").add("input_stmt"));
        productionList.add(30, new CustomVector<String>().add("output").add("expr").add(";"));
        productionList.add(31, new CustomVector<String>().add("").add("=").add("expr").add(";"));
        productionList.add(32, new CustomVector<String>().add("[").add("a_expr").add("]").add("=").add("expr").add(";"));
        productionList.add(33, new CustomVector<String>().add("(").add("args").add(")").add(";"));
        productionList.add(34, new CustomVector<String>().add(";"));
        productionList.add(35, new CustomVector<String>().add("expr").add(";"));
        productionList.add(36, new CustomVector<String>().add("else").add("stmt"));
        productionList.add(37, new CustomVector<String>().add(""));
        productionList.add(38, new CustomVector<String>().add(";"));
        productionList.add(39, new CustomVector<String>().add("[").add("a_expr").add("]").add(";"));
        productionList.add(40, new CustomVector<String>().add("(").add("a_expr").add(")").add("term'").add("a_expr'").add("expr'"));
        productionList.add(41, new CustomVector<String>().add("ID").add("factor'").add("term'").add("a_expr'").add("expr'"));
        productionList.add(42, new CustomVector<String>().add("NUM").add("term'").add("a_expr'").add("expr'"));
        productionList.add(43, new CustomVector<String>().add("relop").add("a_expr"));
        productionList.add(44, new CustomVector<String>().add(""));
        productionList.add(45, new CustomVector<String>().add("(").add("a_expr").add(")").add("term").add("a_expr'"));
        productionList.add(46, new CustomVector<String>().add("ID").add("factor'").add("term'").add("a_expr'"));
        productionList.add(47, new CustomVector<String>().add("NUM").add("term'").add("a_expr'"));
        productionList.add(48, new CustomVector<String>().add("+").add("factor").add("term'").add("a_expr'"));
        productionList.add(49, new CustomVector<String>().add("-").add("factor").add("term'").add("a_expr'"));
        productionList.add(50, new CustomVector<String>().add(""));
        productionList.add(51, new CustomVector<String>().add("*").add("factor").add("term'"));
        productionList.add(52, new CustomVector<String>().add("/").add("factor").add("term'"));
        productionList.add(53, new CustomVector<String>().add(""));
        productionList.add(54, new CustomVector<String>().add("(").add("a_expr").add(")"));
        productionList.add(55, new CustomVector<String>().add("ID").add("factor'"));
        productionList.add(56, new CustomVector<String>().add("NUM"));
        productionList.add(57, new CustomVector<String>().add("[").add("a_expr").add("]"));
        productionList.add(58, new CustomVector<String>().add("<="));
        productionList.add(59, new CustomVector<String>().add("<"));
        productionList.add(60, new CustomVector<String>().add(">"));
        productionList.add(61, new CustomVector<String>().add(">="));
        productionList.add(62, new CustomVector<String>().add("=="));
        productionList.add(63, new CustomVector<String>().add("!="));
        productionList.add(64, new CustomVector<String>().add("args_list"));
        productionList.add(65, new CustomVector<String>().add(""));
        productionList.add(66, new CustomVector<String>().add("a_expr"));
        productionList.add(67, new CustomVector<String>().add(",").add("a_expr").add("args_list"));
    }
}
