package co.id.katalogfilmkioser.features.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.id.katalogfilmkioser.data.local.FavoriteRepository

/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class FavoriteViewModelFactory(
    private val repository: FavoriteRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(repository) as T
    }
}