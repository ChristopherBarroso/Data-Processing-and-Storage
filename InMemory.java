//import libraries
import java.util.HashMap;
import java.util.Map;

public class InMemory implements InMemoryDB {

    //implement dataStructures and boolean for one implementation
    private Map<String, Integer> database = new HashMap<>();
    private Map<String, Integer> transactionData = new HashMap<>();
    private boolean inTransaction = false;

    //Returns value (if it exists) or null
    @Override
    public Integer get(String key) {
        //checks to see if value is in the data structure and has a key
        if ((inTransaction) && (transactionData.containsKey(key))) {
            return transactionData.get(key);
        }
        return database.get(key);
    }

    //inserts or updates the key/value 
    @Override
    public void put(String key, int value) {
        if (!inTransaction) {
            throw new IllegalStateException("Errror: There is no transaction currently in progress");
        }
        transactionData.put(key, value);
    }

    //starts new transaction process
    @Override
    public void begin_Transaction() {
        if (inTransaction) {
            throw new IllegalStateException("Error: There is a transaction already in progress");
        }
        inTransaction = true;
        transactionData.clear();
    }

    //commits the transaction process
    @Override
    public void commit() {
        if (!inTransaction) {
            throw new IllegalStateException("Errror: There is no transaction currently in progress");
        }
        database.putAll(transactionData);
        transactionData.clear();
        inTransaction = false;
    }

    //cancels the transaction
    @Override
    public void rollback() {
        
        if (!inTransaction) {
            throw new IllegalStateException("Errror: There is no transaction currently in progress");
        }
        transactionData.clear();
        inTransaction = false;
    }
}
