package com.example.core.network.responses.ActorsDTO

import com.google.gson.annotations.SerializedName

data class Cast(
    val adult: Boolean,
    val character: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    @SerializedName("profile_path")
    val profilePath: String
)