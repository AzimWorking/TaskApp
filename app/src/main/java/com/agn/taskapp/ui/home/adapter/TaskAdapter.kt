package com.agn.taskapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agn.taskapp.databinding.ItemTaskBinding
import com.agn.taskapp.model.Task

class TaskAdapter(
    private val onLongClick: (Task) -> Unit,
    private val onClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val data = arrayListOf<Task>()

//        fun addTask(task: Task) {  `
//        data.add(0, task)
//        notifyDataSetChanged()
    // не удолять
//    }
    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(task: List<Task>) {
        data.clear()
        // не будлировались данные
        // старые данные удаляем
        data.addAll(task) // не корекно
        data.sortByDescending { it.id }  // отсортировали Item то есть поставили на первое место
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

            // отсюда обрабытваем длиннный клик на ITEM
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
            itemView.setOnClickListener {
                onClick(task)
            }
        }
    }
}