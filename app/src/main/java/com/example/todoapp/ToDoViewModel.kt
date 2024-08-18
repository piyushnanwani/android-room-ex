package com.example.todoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoRepository
    val todoList: LiveData<List<Todo>>

    init {
        val todoDao = TodoDatabase.getDataBase(application).todoDao()
        repository = TodoRepository(todoDao)
        todoList = repository.allTodos
    }

    fun add(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }

    fun updateItem(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(todo)
        }
    }

    fun removeItem(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(todo)
        }
    }

}