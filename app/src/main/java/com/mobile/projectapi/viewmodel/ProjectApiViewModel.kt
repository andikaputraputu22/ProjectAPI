package com.mobile.projectapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.projectapi.database.ProjectApiEntity
import com.mobile.projectapi.models.AreaModel
import com.mobile.projectapi.models.SizeModel
import com.mobile.projectapi.repository.ProjectApiRepository
import com.mobile.projectapi.utils.Resources
import kotlinx.coroutines.launch

class ProjectApiViewModel(
    val repository: ProjectApiRepository
) : ViewModel() {
    val responseArea: MutableLiveData<Resources<List<AreaModel>>> = MutableLiveData()
    val responseSize: MutableLiveData<Resources<List<SizeModel>>> = MutableLiveData()
    val responseDb: MutableLiveData<Resources<Boolean>> = MutableLiveData()
    val dataKomoditas: LiveData<List<ProjectApiEntity>> = repository.getKomoditas()

    init {
        fetchArea()
        fetchSize()
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

    fun fetchSize() = viewModelScope.launch {
        responseSize.value = Resources.Loading()
        try {
            val response = repository.getSize()
            responseSize.value = Resources.Success(response.body()!!)
        } catch (e: Exception) {
            responseSize.value = Resources.Error(e.message.toString())
        }
    }

    fun storeKomoditas(data: ProjectApiEntity) = viewModelScope.launch {
        responseDb.value = Resources.Loading()
        try {
            repository.store(
                ProjectApiEntity(
                    id = data.id,
                    name = data.name,
                    province = data.province,
                    city = data.city,
                    size = data.size,
                    price = data.price
                )
            )
            responseDb.value = Resources.Success(true)
        } catch (e: Exception) {
            responseSize.value = Resources.Error(e.message.toString())
        }
    }
}