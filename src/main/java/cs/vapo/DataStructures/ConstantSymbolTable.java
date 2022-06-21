/*
 * Written by: Víctor Padrón 2022
 * Student ID: A01561947
 *
 * */
package cs.vapo.DataStructures;

public class ConstantSymbolTable {

    /**
     * Contains the index of a given constant in the symbol table.
     */
    private final CustomHashMap<String, Integer> indexTable;
    private final CustomVector<CustomVector<String>> mainTable;
    private int idCount;

    public ConstantSymbolTable(){
        indexTable = new CustomHashMap<>();
        mainTable = new CustomVector<>();
        idCount = 0;
    }

    /**
     * Adds a new constant to the symbol table.
     * If the constant already exists, it returns the index.
     *
     * Constant is not converted to an integer during this process.
     *
     * @param constant constant string
     * @return  index of the constant
     */
    public int add(String constant){
        int currId;
        if(!exists(constant)){
            currId = idCount;
            indexTable.add(constant, idCount);
            CustomVector<String> v = new CustomVector<>();
            v.add(constant);
            mainTable.add(v);
            idCount++;
        }else{
            currId = indexTable.get(constant);
        }
        return currId;
    }

    public void update(String constant){

    }


    @Override
    public String toString() {
        String str = "Constant Symbol Table: \n";
        for(int i = 0; i < this.mainTable.size(); i++){
            str += i + " | " + mainTable.get(i).toString(0) + "\n";
        }
        return str;
    }

    public boolean exists(String constant){
        return indexTable.get(constant) != null;
    }
}
