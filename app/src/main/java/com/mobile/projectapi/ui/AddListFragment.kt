package com.mobile.projectapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mobile.projectapi.R
import com.mobile.projectapi.database.ProjectApiEntity
import com.mobile.projectapi.databinding.FragmentAddListBinding
import com.mobile.projectapi.models.AreaModel
import com.mobile.projectapi.models.SizeModel
import com.mobile.projectapi.utils.Resources
import com.mobile.projectapi.viewmodel.ProjectApiViewModel

class AddListFragment : Fragment() {

    private lateinit var binding: FragmentAddListBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ProjectApiViewModel::class.java) }
    private var province: String? = ""
    private var city: String? = ""
    private var size: String? = ""

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
        setupListener()
    }

    private fun setupObserver() {
        viewModel.responseArea.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resources.Loading -> Log.d("Result", "Loading")
                is Resources.Success -> {
                    val data = it.data!!
                    setupSpinnerProvince(data)
                    setupSpinnerCity(data)
                }
                is Resources.Error -> Log.d("Result", "Error")
            }
        })

        viewModel.responseSize.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resources.Loading -> Log.d("Result", "Loading")
                is Resources.Success -> {
                    setupSpinnerSize(it.data!!)
                }
                is Resources.Error -> Log.d("Result", "Error")
            }
        })

        viewModel.responseDb.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resources.Loading -> Log.d("Result", "Loading")
                is Resources.Success -> {
                    Log.d("Success", "Save to DB")
                    Toast.makeText(requireContext(), "Sukses menyimpan ke database", Toast.LENGTH_LONG).show()
                    binding.komoditas.setText("")
                    binding.price.setText("")
                    findNavController().popBackStack()
                }
                is Resources.Error -> Log.d("Result", "Error")
            }
        })
    }

    private fun setupSpinnerProvince(data: List<AreaModel>) {
        val listProvince: ArrayList<String> = ArrayList()
        for (i in 0 until data.count()) {
            listProvince.addAll(setOf(data[i].province!!))
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.spinner_item, listProvince)
        binding.spinnerProvince.adapter = adapter
        binding.spinnerProvince.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                province = data[p2].province
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }

    private fun setupSpinnerCity(data: List<AreaModel>) {
        val listCity: ArrayList<String> = ArrayList()
        for (i in 0 until data.count()) {
            listCity.addAll(setOf(data[i].city!!))
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.spinner_item, listCity)
        binding.spinnerCity.adapter = adapter
        binding.spinnerCity.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                city = data[p2].city
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }

    private fun setupSpinnerSize(data: List<SizeModel>) {
        val listSize: ArrayList<String> = ArrayList()
        for (i in 0 until data.count()) {
            listSize.addAll(setOf(data[i].size!!))
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.spinner_item, listSize)
        binding.spinnerSize.adapter = adapter
        binding.spinnerSize.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                size = data[p2].size
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }

    private fun setupListener() {
        binding.add.setOnClickListener {
            val postData = ProjectApiEntity(
                0,
                binding.komoditas.text.toString(),
                province,
                city,
                size,
                binding.price.text.toString().toInt()
            )
            viewModel.storeKomoditas(postData)
        }

        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}