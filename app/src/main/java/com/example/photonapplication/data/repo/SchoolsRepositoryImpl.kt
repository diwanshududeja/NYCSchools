package com.example.photonapplication.data.repo

import com.example.photonapplication.data.SchoolsDataResult
import com.example.photonapplication.data.source.SchoolsRemoteDataSource
import com.example.photonapplication.domain.SchoolsRepository
import javax.inject.Inject

class SchoolsRepositoryImpl @Inject constructor(private val remoteDataSource: SchoolsRemoteDataSource) : SchoolsRepository {

    override suspend fun loadSchoolsData(): SchoolsDataResult {
        val response = remoteDataSource.getSchoolsData()
        return if(response.isSuccessful) {
            val responseBody = response.body()
            return if(responseBody != null) {
                SchoolsDataResult.Success(responseBody)
            } else {
                SchoolsDataResult.Error("Response body is null")
            }

        } else {
            SchoolsDataResult.Error("Error fetching schools data")
        }
    }
}