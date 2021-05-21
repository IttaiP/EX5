package exercise.android.reemh.todo_items

import android.graphics.Paint
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    @JvmField
    var holder: TodoItemsHolder? = null
    var newTaskText = ""
    var adapter: TodoAdapter?= null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (holder == null) {
            holder = TodoItemsHolderImpl()
        }

        // TODO: implement the specs as defined below
        //    (find all UI components, hook them up, connect everything you need)
        adapter = TodoAdapter()
        val tempData = holder!!.currentItems

        adapter!!.setTodos(holder!!.currentItems)
        val todoRecycler = findViewById<RecyclerView>(R.id.recyclerTodoItemsList)
        todoRecycler.adapter = adapter
        todoRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val editTextUserInput = findViewById<EditText>(R.id.editTextInsertTask)
        val buttonCreateTodoItem = findViewById<FloatingActionButton>(R.id.buttonCreateTodoItem)
        editTextUserInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                newTaskText = editTextUserInput.text.toString()
            }
        })
        buttonCreateTodoItem.setOnClickListener { v: View? ->
            if (newTaskText != "") {
                holder!!.addNewInProgressItem(newTaskText)
                adapter!!.setTodos(holder!!.currentItems)
                newTaskText = ""
                editTextUserInput.setText("")
                return@setOnClickListener
            }
        }

        adapter!!.onItemClickCallback = { todo, position ->
            if(todo.completed.isChecked){
                holder!!.markItemDone(holder!!.currentItems[position])
                adapter!!.setTodos(holder!!.currentItems)
                todo.text.paintFlags = todo.text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            }
            else{
                holder!!.markItemInProgress(holder!!.currentItems[position])
                adapter!!.setTodos(holder!!.currentItems)
                todo.text.setPaintFlags(todo.text.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())


            }
        }
        adapter!!.onDeleteClickCallback = { position ->
            holder!!.deleteItem(holder!!.currentItems[position])
            adapter!!.setTodos(holder!!.currentItems)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("todos", holder as?TodoItemsHolderImpl)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        holder = savedInstanceState.getSerializable("todos") as?TodoItemsHolder
        adapter!!.setTodos(holder!!.currentItems)
    }

}