package co.id.katalogfilmkioser.features.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.network.ApiClient
import co.id.katalogfilmkioser.data.repositories.MoviesRepository
import co.id.katalogfilmkioser.features.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*


class HomeFragment : Fragment() {

    private lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =inflater.inflate(R.layout.home_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = ApiClient()
        val repository = MoviesRepository(api)
        factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        viewModel.getMovies()
        viewModel.getMoviesUpcoming()
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recyclerview_nowplaying.apply {
                layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = AdapterHomeNowPlaying(movies.results)
            }
        })
        viewModel.moviesupcoming.observe(viewLifecycleOwner, Observer { movies ->
            recyclerview_upcoming.apply {
                layoutManager = GridLayoutManager(requireContext(),3)
                setHasFixedSize(true)
                adapter = AdapterHomeUpcoming(movies.results)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        requireActivity().bottomNav.visibility = View.VISIBLE
    }
}
