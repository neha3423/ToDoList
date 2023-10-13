package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter:TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvTodoItems = findViewById<RecyclerView>(R.id.rvTodoItems)
        todoAdapter= TodoAdapter(mutableListOf())
        rvTodoItems.adapter=todoAdapter
        rvTodoItems.layoutManager=LinearLayoutManager(this)
        val btnAddTodo = findViewById<Button>(R.id.btnAddToDo)
        btnAddTodo.setOnClickListener {
            val etTodoTitle =findViewById<EditText>(R.id.etTodoTitle)
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        val btnDeleteDoneToDos=findViewById<Button>(R.id.btnDeleteDoneToDos)
        btnDeleteDoneToDos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}