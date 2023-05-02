package com.agn.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.agn.taskapp.App
import com.agn.taskapp.model.Task
import com.agn.taskapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var navArgs: TaskFragmentArgs
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            navArgs = TaskFragmentArgs.fromBundle(it)
            task = navArgs.task
        }
        if (task != null) {
            binding.etTitle.setText(task?.title)
            binding.etDesc.setText(task?.desc)
            binding.btnSave.text = "Update"
        } else {
            binding.btnSave.text = "Save"
        }

        // update and insert
        binding.btnSave.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString(),
            )
            if (data.title!!.isBlank() || data.desc!!.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Title and desc cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (task != null) {
                task!!.title = data.title
                task!!.desc = data.desc
                App.db.taskDao().update(task!!)
            } else {
                task = Task(title = data.title, desc = data.desc)
                App.db.taskDao().insert(task!!)
            }
            findNavController().navigateUp()
        }
    }

    companion object {
        const val TASK_REQUEST = "taskRequest"
        const val TASK_KEY = "taskKey"
    }
}