package com.example.photonapplication.domain

import com.example.photonapplication.data.SchoolsDataResult

interface SchoolsRepository {

    suspend fun loadSchoolsData(): SchoolsDataResult

}