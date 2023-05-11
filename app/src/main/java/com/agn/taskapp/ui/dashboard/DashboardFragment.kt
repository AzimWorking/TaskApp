package com.agn.taskapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.agn.taskapp.databinding.FragmentDashboardBinding
import com.agn.taskapp.utils.showToast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        binding.btnSave.setOnClickListener {
            save()
        }

        private fun save() {
            val value = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).document().set(value)
                .addOnSuccessListener {
                    showToast("Данные успешно сохранены!")
                }.addOnFailureListener {
                    showToast(it.message.toString())
                }
            // 1.08.34 youtube

        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}