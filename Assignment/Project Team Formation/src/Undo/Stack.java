package Undo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
class Stack<T> {
    private List<T> dataCollection;
    Stack() {
        dataCollection = new LinkedList<>();
    }
    void push(T item) {
        dataCollection.add(dataCollection.size(), item);
    }
    T pop() {
        if(dataCollection.size() > 0)
            return dataCollection.remove(dataCollection.size()-1);
        else
            return null;
    }
    public int size()
    {
    	    return dataCollection.size();
    }
    void clear() {
        dataCollection.clear();
    }
    
    public T getTopElement() {
    	return  dataCollection.get(dataCollection.size()-1);
    }
}
