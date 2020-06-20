package co.id.katalogfilmkioser.features.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.local.Favorite
import co.id.katalogfilmkioser.databinding.ItemFavoriteBinding
import co.id.katalogfilmkioser.databinding.ItemNowPlayingBinding

/**
 * Created by Sainal Sultan on 6/20/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class AdapterFavoriteMovies(
    private val movies: List<Favorite>
) : RecyclerView.Adapter<AdapterFavoriteMovies.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_favorite,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemFavoriteBinding.favorite = movies[position]
        holder.itemFavoriteBinding.root.setOnClickListener { v ->
            var data = bundleOf(
                "tes" to movies.get(position).title,
                "movie_id" to movies.get(position).id_movie.toInt()
            )
            v.findNavController().navigate(R.id.action_navFavorite_to_detailMovieFragment, data)
        }
        holder.itemView.run {
            val marginStart: Int
            val marginEnd: Int
            val marginTop: Int
            val marginBottom: Int

            marginStart = resources.getDimensionPixelSize(R.dimen.marginContent)
            marginEnd = resources.getDimensionPixelSize(R.dimen.marginContent)

            if (position == 0) {
                marginTop = resources.getDimensionPixelSize(R.dimen.marginLarge)
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
        val itemFavoriteBinding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(itemFavoriteBinding.root)
}