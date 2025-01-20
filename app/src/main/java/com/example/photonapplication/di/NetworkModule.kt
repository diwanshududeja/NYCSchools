package com.example.photonapplication.di

import com.example.photonapplication.data.repo.SchoolsRepositoryImpl
import com.example.photonapplication.data.service.RetrofitService
import com.example.photonapplication.data.source.SchoolsRemoteDataSource
import com.example.photonapplication.data.source.SchoolsRemoteDataSourceImpl
import com.example.photonapplication.domain.SchoolsRepository
import com.example.photonapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(source: SchoolsRemoteDataSource): SchoolsRepository {
        return SchoolsRepositoryImpl(source)
    }

    @Provides
    @Singleton
    fun provideSchoolRemoteDataSource(apiService: RetrofitService): SchoolsRemoteDataSource {
        return SchoolsRemoteDataSourceImpl(apiService)
    }




}