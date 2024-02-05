package com.walmart.countrieslist.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("capital")
    val countryCapital: String?,
    @SerializedName("code")
    val countryCode: String?,
    @SerializedName("region")
    val countryRegion: String?
)
