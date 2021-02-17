package com.example.search.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.network.responses.FilmDTO.FilmResultResponse
import com.example.search.data.SearchRepository
import com.example.search.utils.SearchResult
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

const val SEARCH_DELAY_MS = 500L
const val MIN_QUERY_LENGTH = 3

class SearchFragmentViewModel @Inject constructor(
    private val repository: SearchRepository,
) : ViewModel() {

    @ExperimentalCoroutinesApi
    @VisibleForTesting
    internal val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)


    @FlowPreview
    @ExperimentalCoroutinesApi
    @VisibleForTesting
    val internalSearchResult: Flow<SearchResult> = queryChannel
        .asFlow()
        .debounce(SEARCH_DELAY_MS)
        .mapLatest {queryString ->
            try {
                if (queryString.length >= MIN_QUERY_LENGTH) {
                    val searchResult: Response<FilmResultResponse> = withContext(Dispatchers.IO) {
                        repository.searchFilm(queryString)
                    }
                    searchResult.body()?.items?.let {filmList ->
                        SearchResult.SuccessResult(filmList) }
                } else {
                    SearchResult.EmptyQuery
                }

            } catch (e: Throwable) {
                if (e is CancellationException) {
                    println("Search was cancelled!")
                    throw e
                } else {
                    SearchResult.ErrorResult(e)
                }
            }
        }
        .catch { it: Throwable -> emit(SearchResult.TerminalError) } as Flow<SearchResult>

    @FlowPreview
    @ExperimentalCoroutinesApi
    val searchResultLiveData: LiveData<SearchResult> = internalSearchResult.asLiveData()


}


