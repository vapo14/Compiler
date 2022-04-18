/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

import java.util.Objects;

public class CustomHashMap<K, V> {
    CustomVector<MapNode<K, V>> mainList;
    int numItems;
    int size;

    public CustomHashMap(){
        mainList = new CustomVector<>();
        numItems = 10;
        size = 0;

        for (int i = 0; i < numItems; i++){
            mainList.add(null);
        }
    }

    public int size(){
        return size;
    }

    private int getHash(K key){
        return Objects.hash(key);
    }

    private int getKeyIndex(K key){
        int hash = getHash(key);
        int index = hash % numItems;
        // if the hash is a negative number, change sign
        if(index < 0 ) index *= -1;
        return index;
    }

    public void add(K key, V value){
        int headIndex = getKeyIndex(key);
        int hash = getHash(key);

        MapNode<K, V> head = mainList.get(headIndex);

        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                size++;
                return;
            }
            head = head.next;
        }

        size++;
        head = mainList.get(headIndex);
        MapNode<K, V> newNode = new MapNode<>(key, value, hash);
        newNode.next = head;
        mainList.set(headIndex, newNode);


        if( (float) size / numItems >= 0.7){
            CustomVector<MapNode<K, V>> tempList = mainList;
            mainList = new CustomVector<>();
            numItems = 2 * numItems;
            size = 0;

            for (int i = 0; i < numItems; i++){
                mainList.add(null);
            }

            for(int i = 0; i < tempList.size(); i++){
                head = tempList.get(i);
                while(head != null){
                    add(head.key, head.value);
                    head = head.next;
                }
            }

        }
    }

    public V get(K key){
        int headIndex = getKeyIndex(key);
        int hash = getHash(key);

        MapNode<K, V> head = mainList.get(headIndex);
        while(head != null){
            if(head.key.equals(key) && head.hash == hash){
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public String toString() {
        return mainList.toString();
    }
}
