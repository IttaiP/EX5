package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;


// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder, Serializable {
  public List<TodoItem> todos = new ArrayList<>();
  @Override
  public List<TodoItem> getCurrentItems() {return todos; }
//  private transient final Context context;
  private transient final SharedPreferences sp;


  @RequiresApi(api = Build.VERSION_CODES.N)
  public TodoItemsHolderImpl(Context context) throws ParseException {
//    this.context = context;
    this.sp = context.getSharedPreferences("local_todo_db", Context.MODE_PRIVATE);
    initFromSp();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void initFromSp() throws ParseException {
    Set<String> keys = sp.getAll().keySet();
    for(String key: keys){
      String todoAsString = sp.getString(key, null);
      TodoItem newTodo = stringToTodo(todoAsString);
      newTodo.id = key;
      todos.add(0, newTodo);
    }
    todos.sort((a,b)->a.done?1:b.done?-1:0);

  }


  @RequiresApi(api = Build.VERSION_CODES.O)
  private TodoItem stringToTodo(String todoAsString) throws ParseException {
    String[] temp = todoAsString.split("#");
    TodoItem newTodo = new TodoItem();
    newTodo.SetText(temp[0]);
    newTodo.done = Boolean.parseBoolean(temp[1]);
    Log.e("xxxsd", todoAsString);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    newTodo.SetCreatedDate(LocalDate.parse(temp[2]));
    newTodo.SetModifiedDateAndTime(LocalDate.parse(temp[3]), LocalTime.parse(temp[4]));

    return newTodo;
  }

  private String TodoToString(TodoItem todo) {

    return todo.todoText+"#"+Boolean.toString(todo.done)+"#"+todo.created+"#"+todo.modifiedDate+"#"+todo.modifiedTime;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newTodo = new TodoItem();
    newTodo.SetCreatedDate(LocalDate.now());
    Log.e("TTTIIIMMMEEE on save", String.valueOf(LocalDateTime.now()));
    newTodo.SetModifiedDateAndTime(LocalDate.now(), LocalTime.now());
    newTodo.SetText(description);
    newTodo.id = UUID.randomUUID().toString();
    todos.add(0, newTodo);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newTodo.id, TodoToString(newTodo));
    editor.apply();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void markItemDone(TodoItem item) {
    item.done = true;
    todos.remove(item);
    todos.add(item);
    item.SetModifiedDateAndTime(LocalDate.now(), LocalTime.now());
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(item.id, TodoToString(item));
    editor.apply();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void markItemInProgress(TodoItem item) {
    item.done=false;
    todos.remove(item);
    todos.add(0, item);
    item.SetModifiedDateAndTime(LocalDate.now(), LocalTime.now());
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(item.id, TodoToString(item));
    editor.apply();
  }

  @Override
  public void deleteItem(TodoItem item) {
    SharedPreferences.Editor editor = sp.edit();
    Log.e("aaaaaaaaaaaaaa",sp.getAll().toString());
    Log.e("aaaaaaaaaaaaaa",item.id);
    editor.remove(item.id);
    editor.apply();
    todos.remove(item);

  }
}
