/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.scanner.tokens;

public class Token {
    int id;
    int pointer;

    public Token(int id, int pointer){
        this.id = id;
        this.pointer = pointer;
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

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", pointer=" + pointer +
                '}';
    }
}
