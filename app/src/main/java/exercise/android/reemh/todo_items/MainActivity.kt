package exercise.android.reemh.todo_items

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    @JvmField
    var holder: TodoItemsHolderImpl? = null
    var newTaskText = ""
    var adapter: TodoAdapter?= null;
    private var instance: TodosApp? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val context: Context = instance!!.applicationContext
        setContentView(R.layout.activity_main)
        instance = applicationContext as TodosApp

        if (holder == null) {
            holder = instance!!.todosData;
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


            }
            else{
                holder!!.markItemInProgress(holder!!.currentItems[position])
                adapter!!.setTodos(holder!!.currentItems)


            }
        }
        adapter!!.onDeleteClickCallback = { position ->
            holder!!.deleteItem(holder!!.currentItems[position])
            adapter!!.setTodos(holder!!.currentItems)
        }
        adapter!!.onEditClickCallback = { todo, position ->
            val intentForEditScreen =  Intent(this, EditTodoActivity::class.java).apply {
                putExtra("description", holder!!.currentItems[position])
                putExtra("position", position)
                Log.e("aaaaaaaaaaaaaaaa", holder!!.currentItems[position].todoText)




            }
            startActivityForResult(intentForEditScreen, 1)
            adapter!!.setTodos(holder!!.currentItems)
            Log.e("after activity", holder!!.currentItems[position].done.toString())
//            if(holder!!.currentItems[position].done){
//                holder!!.markItemDone(holder!!.currentItems[position])
//
//            }
//            else{
//                holder!!.markItemInProgress(holder!!.currentItems[position])
//            }
//            adapter!!.setTodos(holder!!.currentItems)

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("todos", holder as? TodoItemsHolderImpl)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        holder = savedInstanceState.getSerializable("todos") as?TodoItemsHolderImpl
        adapter!!.setTodos(holder!!.currentItems)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = data!!.getStringExtra("result")
        val position = data.getStringExtra("position")?.toInt()
        Log.e("res", result.toString())
        Log.e("pos", position.toString())
        Log.e("done", holder!!.currentItems[position!!].done.toString())
        holder!!.currentItems[position!!].done
        holder!!.currentItems[position!!].SetText(result)
        adapter!!.setTodos(holder!!.currentItems)
    } //onActivityResult


}