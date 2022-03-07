package com.mobile.projectapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.projectapi.models.AreaModel
import com.mobile.projectapi.repository.ProjectApiRepository
import com.mobile.projectapi.utils.Resources
import kotlinx.coroutines.launch

class ProjectApiViewModel(
    val repository: ProjectApiRepository
) : ViewModel() {
    val responseArea: MutableLiveData<Resources<List<AreaModel>>> = MutableLiveData()

    init {
        fetchArea()
    }

    fun fetchArea() = viewModelScope.launch {
        responseArea.value = Resources.Loading()
        try {
            val response = repository.getArea()
            responseArea.value = Resources.Success(response.body()!!)
        } catch (e: Exception) {
            responseArea.value = Resources.Error(e.message.toString())
        }
    }
}