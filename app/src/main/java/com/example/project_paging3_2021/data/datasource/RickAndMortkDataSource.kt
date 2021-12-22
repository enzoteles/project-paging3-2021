package com.example.project_paging3_2021.data.datasource

import com.example.project_paging3_2021.data.remote.ListCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortkDataSource {

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<ListCharacters>

}