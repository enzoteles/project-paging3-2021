package com.example.project_paging3_2021.presentation.ui.paging

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.project_paging3_2021.data.datasource.RickAndMortkDataSource
import com.example.project_paging3_2021.data.remote.Character
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(
    val api: RickAndMortkDataSource
): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {
            val nextPage = params.key ?: 1
            Log.i("Page", nextPage.toString())
            val response = api.getCharactersPage(nextPage)
            var nextPageNumber: Int ?= null
            response.body()?.info?.let {
                val uri = Uri.parse(it.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            response.body()!!.results.let {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = nextPageNumber
                )
            }

        }catch (e: HttpException){
            LoadResult.Error(e)
        }catch (e: IOException){
            LoadResult.Error(e)
        }
    }
}