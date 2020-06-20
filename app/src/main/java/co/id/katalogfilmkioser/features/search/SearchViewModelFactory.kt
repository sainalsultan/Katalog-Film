package co.id.katalogfilmkioser.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.id.katalogfilmkioser.data.repositories.SearchMoviesRepository

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class SearchViewModelFactory(
    private val repository: SearchMoviesRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}