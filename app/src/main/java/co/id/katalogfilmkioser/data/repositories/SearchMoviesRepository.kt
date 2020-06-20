package co.id.katalogfilmkioser.data.repositories

import co.id.katalogfilmkioser.data.network.ApiInterface

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class SearchMoviesRepository(private val apiInterface : ApiInterface) : SafeApiRequest() {

    suspend fun getSearchMovies(params : HashMap<String,String>) = apiRequest { apiInterface.getSearchMovies(params) }

}