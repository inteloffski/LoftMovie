package com.samarbaeffruslan.core.network.responses.FilmDTO

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String
)