/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

public class StackNode<T> {
    T data;
    StackNode<T> next;

    public StackNode(T data){
        this.data = data;
    }
}
