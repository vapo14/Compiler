/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

/**
 * Custom vector imlementation
 * @param <T>
 */
public class CustomVector<T> {
    private int initialCapacity = 10;
    private int currentCapacity;
    private int numItems;

    private T[] data;


    public CustomVector(){
        this.currentCapacity = this.initialCapacity;
        data = (T[]) new Object[this.initialCapacity];
    }

    /**
     * Adds the given object to the vector
     * @param object object to add
     */
    public void add(T object){
        // if vector is full, reserve 2*space
        if(numItems == currentCapacity){
            reserve(2*currentCapacity);
        }
        data[numItems] = object;
        numItems++;
    }

    public int size(){
        return numItems;
    }

    /**
     *
     * @param index
     * @return returns object at index
     */
    public T get(int index){
        return data[index];
    }

    /**
     *
     * Sets object at index
     * @param index
     * @param value
     */
    public void set(int index, T value){
        data[index] = value;
    }

    /**
     * Reserves space in the vector by given capacity
     * @param newCapacity
     */
    void reserve(int newCapacity){
        if(newCapacity > currentCapacity){
            // if newCapacity is already double, just update
            // else double capacity
            if(newCapacity > 2 * currentCapacity){
                currentCapacity = newCapacity;
            }else{
                currentCapacity *= 2;
            }
            // create new doubled array and copy items
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
            str += data[i] + "\n";
        }
        return str;
    }

    /**
     * 1 for vertical, any other int for horizontal
     * @param orientation
     * @return
     */
    public String toString(int orientation){
        String str = "";
        if(orientation == 1){
            return toString();
        }else{
            for(int i = 0; i < numItems; i++){
                str += data[i] + ", ";
            }
        }
        return str;
    }
}
