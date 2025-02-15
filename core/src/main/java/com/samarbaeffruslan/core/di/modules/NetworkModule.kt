package com.samarbaeffruslan.core.di.modules

import com.samarbaeffruslan.core.network.service.MovieService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun providerRequestInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNTY3YzQyMjFmNzg0MjQ2MGEzYWFkNzY0ZjQyNDNmNCIsIm5iZiI6MTU2NDc0MTEwOC40NTUwMDAyLCJzdWIiOiI1ZDQ0MGRmNGVhN2IwZTAwMTE4YTIyZjUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.SnHlKB-Lb4dJWliDiQM1HIIyvGEveuhY0IsdIbvNAwM")
                .header("accept", "application/json")
                .build()
            chain.proceed(request)
        }
    }


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(providerRequestInterceptor())
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)


}

