package com.example.todolistapp
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap.Title
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter(private val todos: MutableList<Todo>):RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun deleteDoneTodos(){
        todos.removeAll { todo -> todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }
    private fun toggleStrikeThrough(tvTodoTitle:TextView, isChecked:Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        var currTodo=todos[position]
        val tvTodoTitle = holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val cbDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        holder.itemView.apply {
            tvTodoTitle.text = currTodo.title
            cbDone.isChecked = currTodo.isChecked
            toggleStrikeThrough(tvTodoTitle,currTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                currTodo.isChecked=!currTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

