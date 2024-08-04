package com.example.todoapp

import androidx.lifecycle.ViewModel

class ToDoViewModel: ViewModel() {
    private val todoList = mutableListOf<Todo>()

    fun add(todo: Todo) {
        todoList.add(todo)
    }

    fun getTodoList(): List<Todo> {
        return todoList
    }
}