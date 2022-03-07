 package com.mobile.projectapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mobile.projectapi.databinding.ActivityMainBinding
import com.mobile.projectapi.factory.ProjectApiViewModelFactory
import com.mobile.projectapi.viewmodel.ProjectApiViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

 class MainActivity : AppCompatActivity(), KodeinAware {

     override val kodein by kodein()
     private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
     private lateinit var viewModel: ProjectApiViewModel
     private val viewModelFactory: ProjectApiViewModelFactory by instance()

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(binding.root)

         setupViewModel()
     }

     private fun setupViewModel() {
         viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectApiViewModel::class.java)
     }
}