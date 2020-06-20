package co.id.katalogfilmkioser.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.katalogfilmkioser.data.models.Movies
import co.id.katalogfilmkioser.data.repositories.MoviesRepository
import co.id.katalogfilmkioser.utils.Coroutines
import kotlinx.coroutines.Job

class HomeViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private lateinit var job : Job

    private val _movies = MutableLiveData<Movies>()
    val movies: LiveData<Movies>
        get() = _movies

    private val _moviesupcoming = MutableLiveData<Movies>()
    val moviesupcoming: LiveData<Movies>
        get() = _moviesupcoming

    fun getMovies(){
        job = Coroutines.ioTheMain(
            { repository.getMovies()},
            { _movies.value = it}
        )
    }

    fun getMoviesUpcoming(){
        job = Coroutines.ioTheMain(
            { repository.getMoviesUpcoming()},
            { _moviesupcoming.value = it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
