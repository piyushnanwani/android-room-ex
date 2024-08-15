package com.example.todoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoItemBinding

class TodoAdapter(private val viewModel: ToDoViewModel) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo, viewModel, position)
    }

    class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo, viewModel: ToDoViewModel, position: Int) {
            binding.todo = todo
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Log.d("TodoAdapter", "Item at position $adapterPosition is checked")
//                    viewModel.removeItem(position)
                }
            }
            binding.deleteButton.setOnClickListener {
                viewModel.removeItem(position)
            }
            binding.executePendingBindings()
        }
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}
