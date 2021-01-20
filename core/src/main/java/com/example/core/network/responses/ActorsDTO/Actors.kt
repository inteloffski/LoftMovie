package com.example.core.network.responses.ActorsDTO

data class Actors(
    val cast: List<Any>,
    val crew: List<Crew>,
    val id: Int
)