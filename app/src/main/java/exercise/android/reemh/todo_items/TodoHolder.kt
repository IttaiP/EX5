package exercise.android.reemh.todo_items

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoHolder(view: View):RecyclerView.ViewHolder(view) {
    val text: TextView = view.findViewById(R.id.TodoText);
    val completed: CheckBox = view.findViewById(R.id.Checkbox)
    val deleteButton: Button = view.findViewById(R.id.deleteButton)
}