package co.id.katalogfilmkioser.features.detailmovie

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.Observer

import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.local.Favorite
import co.id.katalogfilmkioser.data.local.FavoriteRepository
import co.id.katalogfilmkioser.data.local.FavoriteRoomDatabase
import co.id.katalogfilmkioser.data.network.ApiClient
import co.id.katalogfilmkioser.data.repositories.DetailMoviesRepository
import co.id.katalogfilmkioser.features.MainActivity
import co.id.katalogfilmkioser.utils.loadImage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_movie_fragment.*
import kotlinx.android.synthetic.main.detail_movie_fragment.view.*
import kotlinx.android.synthetic.main.view_bottom_sheet.*
import kotlinx.android.synthetic.main.view_bottom_sheet.view.*

class DetailMovieFragment : Fragment() {

    private lateinit var factory: DetailMovieViewModelFactory
    private lateinit var viewModel: DetailMovieViewModel
    private var idMovie: String? = null
    private var titles: String? = null
    private var posterPath: String? = null
    private var voteAverage: Float? = null
    private var releaseDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.detail_movie_fragment, container, false)

        val bottomSheetBehavior = BottomSheetBehavior.from(root.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

        })
        return root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().bottomNav.visibility = View.INVISIBLE
        val api = ApiClient()
        val favoriteDao = FavoriteRoomDatabase.getDataBase(requireContext()).favoriteDao()
        val repository = DetailMoviesRepository(api)
        val localRepository = FavoriteRepository(favoriteDao)
        factory = DetailMovieViewModelFactory(repository, localRepository)
        viewModel = ViewModelProviders.of(this, factory).get(DetailMovieViewModel::class.java)
        viewModel.getMovieDetail(arguments?.getInt("movie_id")!!)
        viewModel.detail.observe(viewLifecycleOwner, Observer { detail ->
            detail.run {
                idMovie = id.toString()
                titles = title
                posterPath = poster_path
                voteAverage = vote_average.toFloat()
                releaseDate = release_date

                textview_title.text = title
                textview_release_date.text = release_date
                textview_overview.text = overview
                rating_average.rating = vote_average.toFloat()
                loadImage(imageview_poster, poster_path)
            }
            /*Toast.makeText(activity,detail.toString(),Toast.LENGTH_LONG).show()*/
        })

        checbox_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.insert(
                    Favorite(
                        0,
                        idMovie!!,
                        titles!!,
                        posterPath!!,
                        voteAverage!!,
                        releaseDate!!,
                        isChecked
                    )
                )
                Toast.makeText(
                    requireContext(),
                    "Berhasil menjadikan favorit",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}
