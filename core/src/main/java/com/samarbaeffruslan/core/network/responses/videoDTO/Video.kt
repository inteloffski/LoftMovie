package com.samarbaeffruslan.core.network.responses.videoDTO

import com.google.gson.annotations.SerializedName

data class Video(
    val id: Int,
    @SerializedName("results")
    val results: List<ResultVideo>
)