package com.walmart.countrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walmart.countrieslist.model.CountriesApi
import com.walmart.countrieslist.model.Country
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel: ViewModel(){
    val countries = MutableLiveData<List<Country>>()
    val countriesLoadError = MutableLiveData<Boolean>()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(CountriesApi::class.java)
    fun refresh(){
        fetchDetails()
    }

//    private fun fetchDetails() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = apiService.getData()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    countries.value = response.body()
//                    countriesLoadError.value = false
//                } else {
//                    countriesLoadError.value = true
//                }
//            }
//        }
//    }
    private fun fetchDetails(){
        viewModelScope.launch {
            try{
                val result = apiService.getData()
                countries.value = result.body()
                countriesLoadError.value = false
            } catch (e: Exception){
                countriesLoadError.value = true
            }
        }
    }
}