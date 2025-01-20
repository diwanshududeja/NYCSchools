package com.example.photonapplication.data.source

import com.example.photonapplication.data.vo.SchoolData
import retrofit2.Response

interface SchoolsRemoteDataSource {

    suspend fun getSchoolsData() : Response<List<SchoolData>>
}