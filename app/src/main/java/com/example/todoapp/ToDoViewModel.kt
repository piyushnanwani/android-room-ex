package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel: ViewModel() {

    private val _todoList = MutableLiveData<MutableList<Todo>>()
    val todoList: LiveData<MutableList<Todo>> get() = _todoList

    init {
        _todoList.value = mutableListOf()
    }

    fun add(todo: Todo) {
        _todoList.value?.apply {
            add(todo)
            _todoList.value = this
        }
    }

}