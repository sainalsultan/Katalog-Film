package co.id.katalogfilmkioser.data.repositories

import co.id.katalogfilmkioser.BuildConfig
import co.id.katalogfilmkioser.data.network.ApiInterface

/**
 * Created by Sainal Sultan on 6/18/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class MoviesRepository(private val apiInterface : ApiInterface) : SafeApiRequest(){

    suspend fun getMovies() = apiRequest{ apiInterface.getMoviesNowPlaying(BuildConfig.Api_Key) }

    suspend fun getMoviesUpcoming() = apiRequest{ apiInterface.getMoviesUpcoming(BuildConfig.Api_Key) }

}