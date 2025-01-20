package com.example.photonapplication.di

import com.example.photonapplication.data.repo.SchoolsRepositoryImpl
import com.example.photonapplication.data.service.RetrofitService
import com.example.photonapplication.data.source.SchoolsRemoteDataSource
import com.example.photonapplication.data.source.SchoolsRemoteDataSourceImpl
import com.example.photonapplication.domain.SchoolsRepository
import com.example.photonapplication.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ActivityComponent::class)
abstract class DIBindingModule {

    /*@Binds
    abstract fun bindRepository(repository: SchoolsRepositoryImpl): SchoolsRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: SchoolsRemoteDataSourceImpl): SchoolsRemoteDataSource*/

}