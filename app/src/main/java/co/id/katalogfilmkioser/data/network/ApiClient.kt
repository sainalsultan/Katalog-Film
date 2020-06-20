package co.id.katalogfilmkioser.data.network

import co.id.katalogfilmkioser.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
object ApiClient {
    operator fun invoke(): ApiInterface {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.Base_Url)
            .build()
            .create(ApiInterface::class.java)
    }
}