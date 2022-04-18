/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;


public class CustomVector<T> {
    private int initialCapacity = 10;
    private int currentCapacity;
    private int numItems;

    private T[] data;


    public CustomVector(){
        this.currentCapacity = this.initialCapacity;
        data = (T[]) new Object[this.initialCapacity];
    }

    public void add(T object){
        if(numItems == currentCapacity){
            reserve(2*currentCapacity);
        }
        data[numItems] = object;
        numItems++;
    }

    public int size(){
        return numItems;
    }

    public T get(int index){
        return data[index];
    }

    public void set(int index, T value){
        data[index] = value;
    }

    void reserve(int newCapacity){
        if(newCapacity > currentCapacity){
            if(newCapacity > 2 * currentCapacity){
                currentCapacity = newCapacity;
            }else{
                currentCapacity *= 2;
            }
            T[] newData = (T[]) new Object[currentCapacity];
            for(int i = 0; i < numItems; i++){
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < numItems; i++){
            str += data[i] + ", ";
        }
        return str;
    }
}
