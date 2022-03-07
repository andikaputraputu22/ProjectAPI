package com.mobile.projectapi.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobile.projectapi.repository.ProjectApiRepository
import com.mobile.projectapi.viewmodel.ProjectApiViewModel

class ProjectApiViewModelFactory(
    private val repository: ProjectApiRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProjectApiViewModel(repository) as T
    }
}