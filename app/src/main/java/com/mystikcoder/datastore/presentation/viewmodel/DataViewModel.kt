package com.mystikcoder.datastore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mystikcoder.datastore.data.preferences.abstraction.DataStoreRepository
import com.mystikcoder.datastore.utils.AGE
import com.mystikcoder.datastore.utils.NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    fun saveName(value: String) {
        viewModelScope.launch {
            repository.putString(NAME, value)
        }
    }

    fun getName(): String? = runBlocking {
        repository.getString(NAME)
    }

    fun saveAge(value: Int) {
        viewModelScope.launch {
            repository.putInt(AGE, value)
        }
    }

    fun getAge(): Int? = runBlocking {
        repository.getInt(AGE)
    }

}