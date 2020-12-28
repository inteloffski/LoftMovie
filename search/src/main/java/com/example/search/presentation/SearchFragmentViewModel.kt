package com.example.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.network.responses.Film
import com.example.search.data.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val repository: SearchRepository,
) : ViewModel() {


}
