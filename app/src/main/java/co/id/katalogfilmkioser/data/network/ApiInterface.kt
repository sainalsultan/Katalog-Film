package co.id.katalogfilmkioser.data.network

import co.id.katalogfilmkioser.data.models.DetailMovie
import co.id.katalogfilmkioser.data.models.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by Sainal Sultan on 6/18/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
interface ApiInterface {

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(@Query("api_key") api_key:String) : Response<Movies>

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(@Query("api_key") api_key:String) : Response<Movies>

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetail(@Path("movie_id") movie_id:Int,
                                @Query("api_key") api_key:String) : Response<DetailMovie>

    @GET("search/movie")
    suspend fun getSearchMovies(@QueryMap params : Map<String,String>) : Response<Movies>

}