package com.example.photonapplication.data.service

import com.example.photonapplication.data.vo.SchoolData
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools() : Response<List<SchoolData>>
}