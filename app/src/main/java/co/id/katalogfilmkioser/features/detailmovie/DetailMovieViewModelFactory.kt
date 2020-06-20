package co.id.katalogfilmkioser.features.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.id.katalogfilmkioser.data.local.FavoriteRepository
import co.id.katalogfilmkioser.data.repositories.DetailMoviesRepository

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */

class DetailMovieViewModelFactory (
    private val repository: DetailMoviesRepository,
    private val localRepository: FavoriteRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMovieViewModel(repository,localRepository) as T
    }
}