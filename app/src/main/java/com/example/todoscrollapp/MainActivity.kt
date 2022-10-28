package com.example.todoscrollapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.StringReader
import java.lang.reflect.Type
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : TodoRecyclerAdapter
    private lateinit var sharedPref : SharedPreferences

    private lateinit var titleEdit : EditText
    private lateinit var todoEdit : EditText
    private lateinit var addBtn : ImageButton
    private lateinit var todos : ArrayList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleEdit = findViewById(R.id.titleEdit)
        todoEdit = findViewById(R.id.todoEdit)
        addBtn = findViewById(R.id.addButton)

        sharedPref = getSharedPreferences("myTodo", Context.MODE_PRIVATE)
        val json = sharedPref.getString("TODO_DATA", null)

        Toast.makeText(this, json, Toast.LENGTH_LONG).show()

        val todoTemp = ArrayList(Gson().fromJson(json, Array<Todo>::class.java).asList())

        todos = if(todoTemp != null){
            todoTemp
        }else{
            ArrayList()
        }

        val recyclerView = findViewById<View>(R.id.todosView) as RecyclerView
        adapter = TodoRecyclerAdapter(todos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addContent()
    }

    override fun onStop() {
        super.onStop()
        if(todos.isNotEmpty()){
            val editor = sharedPref.edit()
            editor.putString("TODO_DATA", Gson().toJson(todos))
            editor.apply()
        }
    }

    private fun addContent() {
        this.addBtn.setOnClickListener(){
            if(this.todoEdit.text.isEmpty() || this.titleEdit.text.isEmpty()){
                Toast.makeText(this, "Text fields can't be empty", Toast.LENGTH_SHORT).show()
            }else{
                todos.add(Todo(1, this.titleEdit.text.toString(), this.todoEdit.text.toString()))
                adapter.notifyItemInserted(todos.size - 1)
            }
        }
    }

}