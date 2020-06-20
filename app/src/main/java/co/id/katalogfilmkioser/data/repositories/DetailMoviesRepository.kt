package co.id.katalogfilmkioser.data.repositories

import co.id.katalogfilmkioser.BuildConfig
import co.id.katalogfilmkioser.data.network.ApiInterface

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class DetailMoviesRepository(private val apiInterface : ApiInterface) : SafeApiRequest() {

    suspend fun getMoviesDetail(movie_id : Int) = apiRequest {
        apiInterface.getMoviesDetail(movie_id,BuildConfig.Api_Key)
    }

}