package Undo;

public interface Action {
    void execute();
    void undo();
    String getName();
}

