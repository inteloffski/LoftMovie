package com.example.popular.presentation



import androidx.lifecycle.*
import com.example.popular.data.PopularRepository
import javax.inject.Inject

class PopularFragmentViewModel @Inject constructor(
    private val repository: PopularRepository
) : ViewModel() {

    fun getState() = repository.getState()

    fun getListIsEmpty(): Boolean  = repository.listIsEmpty()

    fun getFilmList() = repository.getFilmList()









}









