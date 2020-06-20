package co.id.katalogfilmkioser.features.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.id.katalogfilmkioser.data.local.Favorite
import co.id.katalogfilmkioser.data.local.FavoriteRepository
import co.id.katalogfilmkioser.data.models.DetailMovie
import co.id.katalogfilmkioser.data.repositories.DetailMoviesRepository
import co.id.katalogfilmkioser.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val repository: DetailMoviesRepository,
    private val localRepository: FavoriteRepository
) : ViewModel() {

    private lateinit var job: Job
    private val _detail = MutableLiveData<DetailMovie>()
    val detail: LiveData<DetailMovie>
        get() = _detail

    fun getMovieDetail(movie_id : Int){
        job = Coroutines.ioTheMain(
            { repository.getMoviesDetail(movie_id)},
            { _detail.value = it}
        )
    }


    fun insert(favorite : Favorite) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.insert(favorite)
    }
}
