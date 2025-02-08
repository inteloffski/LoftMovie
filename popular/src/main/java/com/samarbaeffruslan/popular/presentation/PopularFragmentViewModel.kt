package com.samarbaeffruslan.popular.presentation



import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.popular.data.PopularRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularFragmentViewModel @Inject constructor(
    private val repository: PopularRepository
) : ViewModel() {

    fun getMovie(): Observable<PagingData<FilmDTO>> = repository.fetchPopularFilms()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .flatMap (::onFetchMovieStateSuccess)
        .onErrorResumeNext(::onFetchMovieStateError)

    private fun onFetchMovieStateSuccess(pagingData: PagingData<FilmDTO>): Observable<PagingData<FilmDTO>> =
        Observable.just(pagingData)

    private fun onFetchMovieStateError(error: Throwable): Observable<PagingData<FilmDTO>> =
        Observable.error(error)


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null) {
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
        }
        else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if(activeNetworkInfo != null && activeNetworkInfo.isConnected){
                return true
            }
        }
        return false
    }
}









