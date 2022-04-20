/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner.DFA;

import cs.vapo.DataStructures.CustomHashMap;

public class TransitionTable {

    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String nums = "01234556789";
    String whiteSpaces = "\n\t ";

    CustomHashMap<Character, Integer> charMap;

    boolean[] acceptStates;
    boolean[] errorStates;
    boolean[][] advanceStates;
    int[][] transition;

    public TransitionTable() {
        acceptStates = new boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        errorStates = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true};
        advanceStates = new boolean[][]{
                {true,true,false,false,false,true,true,true,true,true,false,false,false,false,false,false,false,false,true,false},
                {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
        };
        // letter	num	+	-	*	/	<	>	!	=	[	]	{	}	(	)	;	,	white spaces	any other char
        //   0        1  2  3   4   5   6   7   8   9   10  11 12   13  14  15  16  17      18              19
        transition = new int[][]{
                {1, 2, 14, 15, 16, 3, 6, 7, 9, 8, 28, 29, 26, 27, 30, 31, 24, 25, 0, 32},
                {1, 33, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 33},
                {34, 2, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 34},
                {12, 12, 12, 12, 4, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 35},
                {4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                {4, 4, 4, 4, 4, 13, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                {17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 17, 17, 17, 17, 17, 17, 17, 17, 17, 35},
                {19, 19, 19, 19, 19, 19, 19, 19, 19, 20, 19, 19, 19, 19, 19, 19, 19, 19, 19, 35},
                {21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 21, 21, 21, 21, 21, 21, 21, 21, 21, 35},
                {35, 35, 35, 35, 35, 35, 35, 35, 35, 23, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35}

        };
        charMap = new CustomHashMap<>();
        charMap.add('+', 2);
        charMap.add('-', 3);
        charMap.add('*', 4);
        charMap.add('/', 5);
        charMap.add('<', 6);
        charMap.add('>', 7);
        charMap.add('!', 8);
        charMap.add('=', 9);
        charMap.add('[', 10);
        charMap.add(']', 11);
        charMap.add('{', 12);
        charMap.add('}', 13);
        charMap.add('(', 14);
        charMap.add(')', 15);
        charMap.add(';', 16);
        charMap.add(',', 17);
    }

    public int moveState(int state, char c) {
        if(isLetter(c))
            return transition[state][0];
        else if(isNum(c))
            return transition[state][1];
        else if(isWhiteSpace(c))
            return transition[state][18];
        else if(charMap.get(c) == null)
            return transition[state][19];
        return transition[state][charMap.get(c)];
    }

    public boolean advanceInput(int state, char c){
        if(isLetter(c))
            return advanceStates[state][0];
        else if(isNum(c))
            return advanceStates[state][1];
        else if(isWhiteSpace(c))
            return advanceStates[state][18];
        else if(charMap.get(c) == null)
            return advanceStates[state][19];
        return advanceStates[state][charMap.get(c)];
    }

    boolean isLetter(Character c){
        for(int i = 0; i < alphabet.length(); i++){
            if(alphabet.charAt(i) == c){
                return true;
            }
        }
        return false;
    }

    boolean isNum(Character c){
        for(int i = 0; i < nums.length(); i++){
            if(nums.charAt(i) == c){
                return true;
            }
        }
        return false;
    }

    boolean isWhiteSpace(Character c){
        for(int i = 0; i < whiteSpaces.length(); i++){
            if(whiteSpaces.charAt(i) == c){
                return true;
            }
        }
        return false;
    }
}
