/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

/**
 * Custom vector implementation
 * @param <T>
 */
public class CustomVector<T> {
    private int currentCapacity;
    private int numItems;

    private T[] data;

    @SuppressWarnings("unchecked")
    public CustomVector(){
        int initialCapacity = 10;
        this.currentCapacity = initialCapacity;
        data = (T[]) new Object[initialCapacity];
    }

    /**
     * Adds the given object to the vector
     * @param object object to add
     */
    public CustomVector<T> add(T object){
        // if vector is full, reserve 2*space
        if(numItems == currentCapacity){
            reserve(2*currentCapacity);
        }
        data[numItems] = object;
        numItems++;
        return this;
    }

    public int size(){
        return numItems;
    }

    /**
     *
     * @param index index
     * @return returns object at index
     */
    public T get(int index){
        return data[index];
    }

    /**
     *
     * Sets object at existing index
     * @param index index
     * @param value value
     */
    public void set(int index, T value){
        data[index] = value;
    }

    /**
     * Reserves space in the vector by given capacity
     * @param newCapacity new capacity
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
     *
     * @param orientation 1 for vertical, any other int for horizontal
     * @return string to print
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
