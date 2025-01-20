package com.example.photonapplication.ui.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photonapplication.data.SchoolScreenState
import com.example.photonapplication.data.SchoolsDataResult
import com.example.photonapplication.domain.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel @Inject constructor(private val schoolsRepository: SchoolsRepository) : ViewModel(){

    private val _state = MutableStateFlow<SchoolScreenState>(SchoolScreenState.Loading)
    val state: StateFlow<SchoolScreenState> = _state.asStateFlow()

    fun getSchoolsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getSchoolDataFromAPI()
        }
    }

    private suspend fun getSchoolDataFromAPI() {
        when (val result = schoolsRepository.loadSchoolsData()){
            is SchoolsDataResult.Success -> {
                // Handle success
                _state.value = SchoolScreenState.Success(result.data)
            }
            is SchoolsDataResult.Error -> {
                // Handle error
                _state.value = SchoolScreenState.Error(result.message)
            }
        }
    }


}