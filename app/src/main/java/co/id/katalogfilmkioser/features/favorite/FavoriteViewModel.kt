package co.id.katalogfilmkioser.features.favorite

import android.app.Application
import androidx.lifecycle.*
import co.id.katalogfilmkioser.data.local.Favorite
import co.id.katalogfilmkioser.data.local.FavoriteRepository
import co.id.katalogfilmkioser.data.local.FavoriteRoomDatabase
import co.id.katalogfilmkioser.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    /*private var repository: FavoriteRepository
    init {
        val favoriteDao = FavoriteRoomDatabase.getDataBase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        allFavorite = repository.allFavorite
    }*/

    private lateinit var job: Job

    private val _allMovie = MutableLiveData<List<Favorite>>()
    val allMovie: LiveData<List<Favorite>>

    init {
        allMovie = repository.allFavorite
    }

    fun insert(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(favorite)
    }

    fun getAllFavoriteMovie()  = viewModelScope.launch(Dispatchers.IO) {

    }
}
