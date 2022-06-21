/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner.tokens;

public class Token {
    int id;
    int pointer;
    int line;
    int column;

    public Token(int id, int line, int column){
        this.id = id;
        this.pointer = -1;
        this.line = line;
        this.column = column;
    }

    public Token(int id, int pointer, int line, int column){
        this.id = id;
        this.pointer = pointer;
        this.line = line;
        this.column = column;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        String ptr = pointer == -1 ? "" : ", pointer=" + Integer.toString(pointer);
        return "Token{" +
                "id=" + id +
                ptr +
                '}';
    }
}
