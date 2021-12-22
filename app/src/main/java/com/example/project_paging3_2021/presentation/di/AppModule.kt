package com.example.project_paging3_2021.presentation.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    fun provideRickAndMortiApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}

