package com.mobile.projectapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobile.projectapi.R
import com.mobile.projectapi.databinding.FragmentAddListBinding
import com.mobile.projectapi.utils.Resources
import com.mobile.projectapi.viewmodel.ProjectApiViewModel

class AddListFragment : Fragment() {

    private lateinit var binding: FragmentAddListBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ProjectApiViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.responseArea.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resources.Loading -> Log.d("Result", "Loading")
                is Resources.Success -> {

                }
                is Resources.Error -> Log.d("Result", "Error")
            }
        })
    }
}