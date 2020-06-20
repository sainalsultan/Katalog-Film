package co.id.katalogfilmkioser.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.id.katalogfilmkioser.data.repositories.MoviesRepository

/**
 * Created by Sainal Sultan on 6/18/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory (
    private val repository : MoviesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}