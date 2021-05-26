package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TodoItem implements Serializable {
  // TODO: edit this class as you want
    String id;
    String todoText;
    boolean done = false;
    LocalDate created;
    LocalDate modifiedDate;
    LocalTime modifiedTime;


    void SetText(String text){
        todoText = text;
    }
    void SetCreatedDate(LocalDate newDate){created = newDate;}
    void SetModifiedDateAndTime(LocalDate newDate, LocalTime newTime){modifiedDate = newDate; modifiedTime = newTime;}
}
