package exercise.android.reemh.todo_items;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.ParseException;

public class TodosApp extends Application implements Serializable {

    TodoItemsHolderImpl todos;

    private static TodosApp instance = null;

    public TodoItemsHolderImpl getTodosData(){
        return todos;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.e("aaaaaaaaaaaaaaaa", "here");
        try {
            todos = new TodoItemsHolderImpl(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static TodosApp getInstance(){
        return instance;
    }
}
