/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

public class MapNode <K, V>{
    K key;
    V value;
    int hash;
    MapNode<K, V> next;

    public MapNode(K key, V value, int hash){
        this.key = key;
        this.value = value;
        this.hash = hash;
    }
}
