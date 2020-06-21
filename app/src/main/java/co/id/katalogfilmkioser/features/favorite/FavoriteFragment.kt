package co.id.katalogfilmkioser.features.favorite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.local.FavoriteRepository
import co.id.katalogfilmkioser.data.local.FavoriteRoomDatabase
import co.id.katalogfilmkioser.data.repositories.DetailMoviesRepository
import co.id.katalogfilmkioser.features.detailmovie.DetailMovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.favorite_fragment.*


class FavoriteFragment : Fragment() {

    private lateinit var factory : FavoriteViewModelFactory
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val favoriteDao = FavoriteRoomDatabase.getDataBase(requireContext()).favoriteDao()
        val localRepository = FavoriteRepository(favoriteDao)
        factory = FavoriteViewModelFactory(localRepository)
        viewModel = ViewModelProviders.of(this,factory).get(FavoriteViewModel::class.java)
        viewModel.getAllFavoriteMovie()
        viewModel.allMovie.observe(viewLifecycleOwner, Observer {favorite ->
            if (favorite.size == 0) {
                textview_info.visibility = View.VISIBLE
            }else{
                textview_info.visibility = View.GONE
                recyclerview_favorite.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                    adapter = AdapterFavoriteMovies(favorite)
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        requireActivity().bottomNav.visibility = View.VISIBLE
    }

}
