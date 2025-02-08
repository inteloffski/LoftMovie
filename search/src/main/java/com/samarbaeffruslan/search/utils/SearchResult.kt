package com.samarbaeffruslan.search.utils

import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO

sealed class SearchResult {
    class  LoadingResult(): SearchResult()
    class SuccessResult(val result: List<FilmDTO>) : SearchResult()
    object EmptyResult : SearchResult()
    object EmptyQuery : SearchResult()
    class ErrorResult(val e: Throwable) : SearchResult()
    object TerminalError : SearchResult()
}