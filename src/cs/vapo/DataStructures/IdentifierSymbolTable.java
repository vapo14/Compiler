/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

public class IdentifierSymbolTable {

    /**
     * Contains the index of a given identifier in the symbol table.
     */
    private final CustomHashMap<String, Integer> indexTable;
    private final CustomVector<CustomVector<String>> mainTable;
    private int idCount;

    public IdentifierSymbolTable(){
        indexTable = new CustomHashMap<>();
        mainTable = new CustomVector<>();
        idCount = 0;
    }

    /**
     * Adds a new identifier to the symbol table, if the
     * identifier already exists, it returns the index.
     * @param identifier identifier string
     * @return
     */
    public int add(String identifier){
        int currId;
        if(!exists(identifier)){
            currId = idCount;
            indexTable.add(identifier, idCount);
            CustomVector<String> v = new CustomVector<>();
            v.add(identifier);
            mainTable.add(v);
            idCount++;
        }else{
            currId = indexTable.get(identifier);
        }
        return currId;
    }

    public void update(String identifier){

    }

    @Override
    public String toString() {
        String str = "Identifier Symbol Table: \n";
        for(int i = 0; i < this.mainTable.size(); i++){
            str += i + " | " + mainTable.get(i).toString(0) + "\n";
        }
        return str;
    }


    public boolean exists(String identifier){
        return indexTable.get(identifier) != null;
    }
}
