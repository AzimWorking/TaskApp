package com.agn.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agn.taskapp.databinding.ItemTaskBinding
import com.agn.taskapp.model.Task

class TaskAdapter(private val onLongClick: (Task) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val data = arrayListOf<Task>()

//    fun addTask(task: Task) {
//        data.add(0, task)
//        notifyDataSetChanged()
//    }

    fun addTasks(task: List<Task>) {
        data.clear()
        // не будлировались данные
        // старые данные удаляем
        data.addAll(task) // не корекно
            data.sortByDescending { it.id }
            // добавили новые данные
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc

            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
        }
    }
}