package co.id.katalogfilmkioser.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.models.Result
import co.id.katalogfilmkioser.databinding.ItemSearchBinding
import co.id.katalogfilmkioser.databinding.ItemUpcomingBinding
import co.id.katalogfilmkioser.features.home.AdapterHomeUpcoming

/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class AdapterSearchMovies(
    private val movies: List<Result>
) : RecyclerView.Adapter<AdapterSearchMovies.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemSearchBinding.movie = movies[position]
        holder.itemSearchBinding.root.setOnClickListener { v ->
            var data = bundleOf(
                "tes" to movies.get(position).title,
                "movie_id" to movies.get(position).id
            )
            v.findNavController().navigate(R.id.action_navSearch_to_detailMovieFragment, data)
        }
        holder.itemView.run {
            val marginStart: Int
            val marginEnd: Int
            val marginTop: Int
            val marginBottom: Int

            marginStart = resources.getDimensionPixelSize(R.dimen.marginContent)
            marginEnd = resources.getDimensionPixelSize(R.dimen.marginContent)

            if (position == 0) {
                marginTop = resources.getDimensionPixelSize(R.dimen.marginContent)
            } else {
                marginTop = resources.getDimensionPixelSize(R.dimen.marginLayout)
            }

            if (position == movies.size-1) {
                marginBottom = resources.getDimensionPixelSize(R.dimen.marginLayout)
            } else {
                marginBottom = 0
            }

            (this.layoutParams as RecyclerView.LayoutParams).also {
                it.marginStart = marginStart
                it.marginEnd = marginEnd
                it.topMargin = marginTop
                it.bottomMargin = marginBottom
            }
        }
    }

    inner class MoviesViewHolder(
        val itemSearchBinding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(itemSearchBinding.root)
}