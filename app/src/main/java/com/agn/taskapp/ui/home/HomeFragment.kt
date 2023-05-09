package com.agn.taskapp.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.agn.taskapp.App
import com.agn.taskapp.R
import com.agn.taskapp.ui.home.adapter.TaskAdapter
import com.agn.taskapp.databinding.FragmentHomeBinding
import com.agn.taskapp.model.Task

class HomeFragment : Fragment() {

    private val adapter = TaskAdapter(this::onLongClick, this::onClick)
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
        setData()
        binding.recicleView.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onClick(task: Task) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment2(task))
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Вы точно хотите удалить ? ")
        alertDialog.setNegativeButton("Нет", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })

        alertDialog.setPositiveButton("Да", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.db.taskDao().delete(task)
                setData()
            }
        })
        alertDialog.create().show()
    }

    private fun setData() {
        val list = App.db.taskDao().getAll()
        adapter.addTasks(list)
    }
}