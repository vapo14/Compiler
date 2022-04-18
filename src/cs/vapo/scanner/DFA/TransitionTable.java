package cs.vapo.scanner.DFA;

public class TransitionTable {

    boolean[] acceptStates;
    boolean[] errorStates;
    boolean[] advanceStates;
    int[][] transition;

    public TransitionTable() {
        acceptStates = new boolean[]{false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        errorStates = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true};
        advanceStates = new boolean[]{true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
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
    }

    public int move(int state, char c) {
        return 0;
    }
}
