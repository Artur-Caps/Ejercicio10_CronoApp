package com.mexiti.cronoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mexiti.cronoapp.model.Cronos
import com.mexiti.cronoapp.repository.CronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: CronosRepository):ViewModel(){

        private val _cronoList = MutableStateFlow<List<Cronos>>(emptyList())
    val cronoList= _cronoList.asStateFlow()
        init {
            viewModelScope.launch(Dispatchers.IO) {

                repository.getAllcronos().collect(){
                    item ->
                    if (item.isNullOrEmpty()){
                        _cronoList.value= emptyList()
                    } else {
                        _cronoList.value=item
                    }
                }
            }
        }
    fun addCrono (cronos: Cronos)= viewModelScope.launch {repository.addCrono(cronos)}
    fun updateCrono (cronos: Cronos) = viewModelScope.launch { repository.updateCrono(cronos)}
    fun deleteCrono (cronos: Cronos) = viewModelScope.launch { repository.deleteCrono(cronos)}
}