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
    private final CustomVector<CustomVector<Integer>> mainTable;
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
     * Constant is converted to an integer during this process.
     *
     * @param constant constant string
     * @return  index of the constant
     */
    public int add(String constant){
        int currId;
        if(!exists(constant)){
            idCount++;
            currId = idCount;
            indexTable.add(constant, idCount);
            CustomVector<Integer> v = new CustomVector<>();
            v.add(Integer.parseInt(constant));
            mainTable.add(v);
        }else{
            currId = indexTable.get(constant);
        }
        return currId;
    }

    public void update(String constant){

    }

    //TODO: implement tostring for table printing
    public boolean exists(String constant){
        return indexTable.get(constant) != null;
    }
}
