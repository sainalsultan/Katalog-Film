package co.id.katalogfilmkioser.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.network.ApiClient
import co.id.katalogfilmkioser.data.repositories.SearchMoviesRepository
import co.id.katalogfilmkioser.features.home.AdapterHomeUpcoming
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment() {

    private lateinit var factory: SearchViewModelFactory
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = ApiClient()
        val repository = SearchMoviesRepository(api)
        factory = SearchViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)

        edittext_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getSearchMovie(s.toString())
                viewModel.search.observe(viewLifecycleOwner, Observer { search ->
                    recyclerview_movie_search.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                        adapter = AdapterSearchMovies(search.results)
                    }
                })
            }

        })
    }

    override fun onResume() {
        super.onResume()
        requireActivity().bottomNav.visibility = View.VISIBLE
    }

}
