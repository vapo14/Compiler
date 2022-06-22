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
    /**
     * Contains the actual identifier in its corresponding index.
     */
    private final CustomVector<CustomVector<String>> mainTable;
    /**
     * Keeps track of the current index for new identifiers
     */
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
     * @return index for the identifier added
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

    public String getID(int idx) {
        return mainTable.get(idx).get(0);
    }

    public int getAttributeCount(int idx) {
        return mainTable.get(idx).size();
    }

    /**
     * Appends a string attribute to an identifier at the given index.
     * @param idx ID index
     * @param value value to update
     */
    public void update(int idx, String value){
        mainTable.get(idx).add(value);
    }

    /**
     * determines if the last method identifier is
     * a main method
     * @return isMain
     */
    public boolean lastMethodIsMain(){
        boolean isMain = false;
        // from last ID in table, check if method
        for(int i = mainTable.size() - 1; i >= 0; i--){
            // when is method, then check if main
            if(mainTable.get(i).size() > 1 && mainTable.get(i).get(1).equals("method")){
                isMain = mainTable.get(i).get(0).equals("main");
                break;
            }
        }
        return isMain;
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
