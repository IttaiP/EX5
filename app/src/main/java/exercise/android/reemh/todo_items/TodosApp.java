package exercise.android.reemh.todo_items;

import android.app.Application;
import android.util.Log;

public class TodosApp extends Application {

    TodoItemsHolderImpl todos;

    private static TodosApp instance = null;

    public TodoItemsHolderImpl getTodosData(){
        return todos;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.e("aaaaaaaaaaaaaaaa", "here");
        todos = new TodoItemsHolderImpl(this);
    }

    public static TodosApp getInstance(){
        return instance;
    }
}
