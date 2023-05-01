package com.agn.taskapp.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.agn.taskapp.App
import com.agn.taskapp.model.Task
import com.agn.taskapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var navArgs: TaskFragmentArgs
    private var task: Task? = null

    //    private lateinit var navController: NavController
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

//        binding.btnSave.setOnClickListener {
//                val title = binding.etTitle.text.toString()
//                val desc = binding.etDesc.text.toString()
//                if (title.isNotEmpty() || desc.isNotEmpty()) {
//                    task = Task(id, title, desc)
//                    App.db.taskDao().update(task!!)
//                    findNavController().navigateUp()
//                } else {
//                    Toast.makeText(requireContext(), "Title and desc can not by empty", Toast.LENGTH_SHORT).show()
//                }
//        }

        binding.btnSave.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString(),
            )

            if (data.title != null || data.desc != null) {
                task = Task(id, data.title, data.desc)
                App.db.taskDao().update(data)
                App.db.taskDao().insert(data)
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Title and desc can not by empty", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun save() {
//        val data = Task(
//            title = binding.etTitle.text.toString(),
//            desc = binding.etDesc.text.toString(),
//        )
//        App.db.taskDao().insert(data)
//        findNavController().navigateUp()
    }

    companion object {
        const val TASK_REQUEST = "taskRequest"
        const val TASK_KEY = "taskKey"
    }
}