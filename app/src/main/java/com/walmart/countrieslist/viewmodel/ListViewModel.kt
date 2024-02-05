package com.walmart.countrieslist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walmart.countrieslist.model.CountriesApi
import com.walmart.countrieslist.model.Country
import androidx.lifecycle.viewModelScope
import com.walmart.countrieslist.model.NetworkModule
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel: ViewModel(){

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries
    private val _countriesLoadError = MutableLiveData<Boolean>()
    val countriesLoadError: LiveData<Boolean> get() = _countriesLoadError
    private val api: CountriesApi = NetworkModule.createCountriesApi()
    fun refresh(){
        fetchDetails()
    }
    private fun fetchDetails(){
        viewModelScope.launch {
            try{
                val result = api.getData()
                if(result.isSuccessful){
                    _countries.value = result.body()
                    _countriesLoadError.value = false
                }else{
                    _countriesLoadError.value = true
                }

            } catch (e: Exception){
                Log.e("ListViewModel", "error fetching data" ,e)
                _countriesLoadError.value = true
            }
        }
    }

}