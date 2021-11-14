package com.niroshan.starwarsplanets.api

import com.niroshan.starwarsplanets.data.model.PlanetList
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }

    @GET("planets/")
    suspend fun getDataFromAPI(@Query("page") query: Int): PlanetList
}