package com.niroshan.starwarsplanets.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.niroshan.starwarsplanets.api.StarWarsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarWarsRepository @Inject constructor(private val starWarsApi: StarWarsApi) {

    fun getSearchResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { StarWarsPagingSource(starWarsApi) }
        ).liveData
}