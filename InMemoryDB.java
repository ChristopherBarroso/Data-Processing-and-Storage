public interface InMemoryDB {
    
    //Returns value (if it exists) or null
    Integer get(String key); 
    
    //inserts or updates the key/value 
    void put(String key, int value) throws IllegalStateException; 
    
    //starts new transaction process
    void begin_Transaction() throws IllegalStateException; 
    
    //commits the transaction process
    void commit() throws IllegalStateException; 
    
    //cancels the transaction
    void rollback() throws IllegalStateException;
}
