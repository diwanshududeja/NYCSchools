package com.example.photonapplication.data

import com.example.photonapplication.data.vo.SchoolData

sealed class SchoolScreenState {

    object Loading : SchoolScreenState()
    data class Success(val data: List<SchoolData>) : SchoolScreenState()
    data class Error(val message: String) : SchoolScreenState()

}