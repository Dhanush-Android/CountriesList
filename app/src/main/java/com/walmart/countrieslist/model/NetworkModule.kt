package com.walmart.countrieslist.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createCountriesApi(): CountriesApi {
        return createRetrofit().create(CountriesApi::class.java)
    }
}