/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

/**
 * Custom HashMap implementation
 * @param <K> key
 * @param <V> value
 */
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

    /**
     *
     * @param key key to hash
     * @return hash for the given key
     */
    private int getHash(K key){
        return key.hashCode();
    }

    private int getKeyIndex(K key){
        // hash the index
        int hash = getHash(key);
        // calculate the index
        int index = hash % numItems;
        // if the hash is a negative number, change sign
        if(index < 0 ) index *= -1;
        return index;
    }

    /**
     * Adds an element to the hashmap
     * @param key key
     * @param value value
     */
    public void add(K key, V value){
        // get the index for the head
        int headIndex = getKeyIndex(key);
        // calculate the hash for the key
        int hash = getHash(key);
        // get the head of the list at the head Index
        MapNode<K, V> head = mainList.get(headIndex);

        // traverse the linked list until we reach the end
        // if the node exists, update the value
        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                size++;
                return;
            }
            head = head.next;
        }
        // if the node doesn't exist in the list, update the head
        size++;
        head = mainList.get(headIndex);
        // create the new node and point the next to the old head
        // update new head to the new node
        MapNode<K, V> newNode = new MapNode<>(key, value, hash);
        newNode.next = head;
        mainList.set(headIndex, newNode);

        // if the size surpasses the threshold, increment
        if( (float) size / numItems >= 0.7){
            // create a temporary list and point to the current list
            CustomVector<MapNode<K, V>> tempList = mainList;
            // create a new list
            mainList = new CustomVector<>();
            // expand by twice the current numItems
            numItems = 2 * numItems;
            size = 0;
            // fill the new list with nulls
            for (int i = 0; i < numItems; i++){
                mainList.add(null);
            }
            // copy old list to the new list
            for(int i = 0; i < tempList.size(); i++){
                // cycle through each linked list in temp vector
                head = tempList.get(i);
                while(head != null){
                    // add node to the current list
                    add(head.key, head.value);
                    head = head.next;
                }
            }

        }
    }

    /**
     *
     * @param key key
     * @return returns the value for the given key, null if not found
     */
    public V get(K key){
        // get the head index and hash the provided key
        int headIndex = getKeyIndex(key);
        int hash = getHash(key);
        // init head at index
        MapNode<K, V> head = mainList.get(headIndex);
        // loop through the linked list until the keys and hashes match
        // then return the value
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
