package exercise.android.reemh.todo_items

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit.MINUTES


class EditTodoActivity : AppCompatActivity() {
    @Transient
    private val sp: SharedPreferences? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_todo);

        val todo_to_edit: TodoItem = intent.getSerializableExtra("description") as TodoItem
        val position = intent.getIntExtra("position", 0)
        val description: TextView = findViewById(R.id.description)
        val progress: TextView = findViewById(R.id.progress)
        val created: TextView = findViewById(R.id.created)

        val modified: TextView = findViewById(R.id.modified)
        description.setText(todo_to_edit.todoText)
        progress.setText(todo_to_edit.done.toString())

        created.setText("Created: " + todo_to_edit.created.toString())

        if(todo_to_edit.modifiedDate!= LocalDate.now()){
            modified.setText("Modified: " + todo_to_edit.modifiedDate.toString() + "\nat hour: " + todo_to_edit.modifiedTime)
        }
        else{

            if(todo_to_edit.modifiedTime.until(LocalTime.now(), MINUTES)<60){
                modified.setText("Modified: " + todo_to_edit.modifiedTime.until(LocalTime.now(), MINUTES).toString() + " minutes ago")

                todo_to_edit.modifiedTime.until(LocalTime.now(), MINUTES)
            }
            else{
                modified.setText("Modified: today at" + todo_to_edit.modifiedDate)

            }
        }

        val returnIntent = Intent()
        setResult(RESULT_CANCELED, returnIntent)

        progress.setOnClickListener { v: View? ->
            if(todo_to_edit.done){
                TodosApp.getInstance().todos.markItemDone(TodosApp.getInstance().todos.currentItems[position])
                todo_to_edit.done = !todo_to_edit.done
                progress.setText(todo_to_edit.done.toString())
            }else{
                TodosApp.getInstance().todos.markItemInProgress(TodosApp.getInstance().todos.currentItems[position])
                todo_to_edit.done = !todo_to_edit.done
                progress.setText(todo_to_edit.done.toString())
            }
        }



    }

}
