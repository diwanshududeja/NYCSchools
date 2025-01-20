package com.example.photonapplication.data

import com.example.photonapplication.data.vo.SchoolData

sealed class SchoolsDataResult {
    data class Success(val data: List<SchoolData>) : SchoolsDataResult()
    data class Error(val message: String) : SchoolsDataResult()
}