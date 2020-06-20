package co.id.katalogfilmkioser.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.id.katalogfilmkioser.BuildConfig
import co.id.katalogfilmkioser.data.models.Movies
import co.id.katalogfilmkioser.data.repositories.SearchMoviesRepository
import co.id.katalogfilmkioser.utils.Coroutines
import kotlinx.coroutines.Job

class SearchViewModel(
    private val repository: SearchMoviesRepository
) : ViewModel() {

    private lateinit var job : Job
    private val _search = MutableLiveData<Movies>()
    val search : LiveData<Movies>
    get() = _search

    fun getSearchMovie(searchKey : String){
        job = Coroutines.ioTheMain(
            {
                var params : HashMap<String,String> = hashMapOf()
                params.set("api_key",BuildConfig.Api_Key)
                params.set("query",searchKey)

                repository.getSearchMovies(params)
            },
            {_search.value=it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
