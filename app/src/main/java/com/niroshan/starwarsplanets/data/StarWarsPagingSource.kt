package com.niroshan.starwarsplanets.data

import android.net.Uri
import androidx.paging.PagingSource
import com.niroshan.starwarsplanets.api.StarWarsApi
import com.niroshan.starwarsplanets.data.model.PlanetData
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class StarWarsPagingSource(
    private val starWarsApi: StarWarsApi,
) : PagingSource<Int, PlanetData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetData> {
        val position = params.key ?: STARTING_PAGE_INDEX
        var nextPageNumber: Int? = null
        var prevPageNumber: Int? = null

        return try {
            val response = starWarsApi.getDataFromAPI(position)
            val planets = response.results

            if (response?.next != null) {
                val uri = Uri.parse(response?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            if (response?.prev != null) {
                val uri = Uri.parse(response?.prev!!)
                val prevPageQuery = uri.getQueryParameter("page")

                prevPageNumber = prevPageQuery?.toInt()
            }

            LoadResult.Page(
                data = planets,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}