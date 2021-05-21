package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder, Serializable {
  public List<TodoItem> todos = new ArrayList<>();
  @Override
  public List<TodoItem> getCurrentItems() {return todos; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newTodo = new TodoItem();
    newTodo.SetText(description);
    todos.add(0, newTodo);

  }

  @Override
  public void markItemDone(TodoItem item) {
    item.done = true;
    todos.remove(item);
    todos.add(item);
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.done=false;
    todos.remove(item);
    todos.add(0, item);
  }

  @Override
  public void deleteItem(TodoItem item) {
    todos.remove(item);
  }
}
