package com.mobile.projectapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mobile.projectapi.KomoditasAdapter
import com.mobile.projectapi.R
import com.mobile.projectapi.databinding.FragmentListHargaBinding
import com.mobile.projectapi.viewmodel.ProjectApiViewModel

class ListHargaFragment : Fragment() {

    private lateinit var binding: FragmentListHargaBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ProjectApiViewModel::class.java) }
    private lateinit var komoditasAdapter: KomoditasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListHargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupListener() {
        binding.btnTambah.setOnClickListener {
            findNavController().navigate(
                R.id.action_listHargaFragment_to_addListFragment
            )
        }

        binding.search.doAfterTextChanged {
            komoditasAdapter.filter.filter(it.toString())
        }
    }

    private fun setupObserver() {
        viewModel.dataKomoditas.observe(viewLifecycleOwner, Observer {
            komoditasAdapter.setData(it)
        })
    }

    private fun setupRecyclerView() {
        komoditasAdapter = KomoditasAdapter(arrayListOf())
        binding.listHarga.adapter = komoditasAdapter
    }
}