package com.example.search.utils

import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.core.network.responses.FilmDTO.FilmResultResponse

sealed class SearchResult {
    class  LoadingResult(): SearchResult()
    class SuccessResult(val result: List<FilmDTO>) : SearchResult()
    object EmptyResult : SearchResult()
    object EmptyQuery : SearchResult()
    class ErrorResult(val e: Throwable) : SearchResult()
    object TerminalError : SearchResult()
}