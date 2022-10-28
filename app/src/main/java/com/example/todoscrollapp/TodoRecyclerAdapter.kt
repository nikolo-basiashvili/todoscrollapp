package com.example.todoscrollapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoRecyclerAdapter(private val todos : List<Todo>) : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>(){

    inner class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val titleView = itemView.findViewById<TextView>(R.id.todoTitle)
        private val contentView = itemView.findViewById<TextView>(R.id.todoText)

        fun setData(todo : Todo){
            titleView.text = todo.title
            contentView.text = todo.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.setData(todo)
    }

    override fun getItemCount() = todos.size

}