package com.agn.taskapp.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agn.taskapp.R
import com.agn.taskapp.databinding.FragmentAcceptCodeBinding
import com.agn.taskapp.databinding.FragmentAuthBinding

class AcceptCodeFragment : Fragment() {

    private lateinit var binding: FragmentAcceptCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptCodeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}