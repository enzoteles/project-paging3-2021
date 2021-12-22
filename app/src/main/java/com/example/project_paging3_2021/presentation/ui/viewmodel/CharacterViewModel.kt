package com.example.project_paging3_2021.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.project_paging3_2021.data.datasource.RickAndMortkDataSource
import com.example.project_paging3_2021.data.remote.Character
import com.example.project_paging3_2021.presentation.di.AppModule
import com.example.project_paging3_2021.presentation.ui.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class CharacterViewModel: ViewModel() {

    lateinit var retroService: RickAndMortkDataSource

    init {
        retroService = AppModule.provideRickAndMortiApi().create(RickAndMortkDataSource::class.java)
    }

    fun getListCharacter(): Flow<PagingData<Character>>{
        return Pager(
            config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = {CharacterPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }



}