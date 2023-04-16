package com.agn.taskapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.agn.taskapp.R
import com.agn.taskapp.adapter.TaskAdapter
import com.agn.taskapp.databinding.FragmentHomeBinding
import com.agn.taskapp.model.Task
import com.agn.taskapp.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private val adapter = TaskAdapter()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
       return _binding?.root ?: binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recicleView.adapter = adapter
       setFragmentResultListener(TaskFragment.TASK_REQUEST){_,bundle ->
           val result = bundle.getSerializable(TaskFragment.TASK_KEY) as Task
           adapter.addTask(result)
       }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }
}