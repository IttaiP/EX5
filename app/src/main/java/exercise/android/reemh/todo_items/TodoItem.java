package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    String todoText;
    boolean done = false;


    void SetText(String text){
        todoText = text;
    }

}
