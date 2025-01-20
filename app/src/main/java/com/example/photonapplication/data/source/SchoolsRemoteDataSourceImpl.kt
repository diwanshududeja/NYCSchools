package com.example.photonapplication.data.source

import com.example.photonapplication.data.service.RetrofitService
import com.example.photonapplication.data.vo.SchoolData
import retrofit2.Response
import javax.inject.Inject

class SchoolsRemoteDataSourceImpl @Inject constructor(private val apiService: RetrofitService): SchoolsRemoteDataSource {

    override suspend fun getSchoolsData(): Response<List<SchoolData>> {
        return apiService.getSchools()
    }


}