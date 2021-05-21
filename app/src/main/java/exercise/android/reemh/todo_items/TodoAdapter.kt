package exercise.android.reemh.todo_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class TodoAdapter: RecyclerView.Adapter<TodoHolder>() {
    private val _todos:MutableList<TodoItem> = ArrayList();


    public var onItemClickCallback: ((TodoHolder, Int)->Unit)?=null
    public var onDeleteClickCallback: ((Int)->Unit)?=null

    fun setTodos(todos: List<TodoItem>){
        _todos.clear()
        _todos.addAll(todos)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return _todos.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false)
        return TodoHolder(view)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todo = _todos[position]
        holder.text.setText(todo.todoText)

        holder.completed.setOnClickListener{
            val callback = onItemClickCallback ?: return@setOnClickListener
            callback(holder, position)
        }

        holder.deleteButton.setOnClickListener{
            val callback = onDeleteClickCallback ?: return@setOnClickListener
            callback(position)
        }

    }
}