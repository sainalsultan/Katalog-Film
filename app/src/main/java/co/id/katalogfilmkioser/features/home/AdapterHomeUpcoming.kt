package co.id.katalogfilmkioser.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.id.katalogfilmkioser.R
import co.id.katalogfilmkioser.data.models.Result
import co.id.katalogfilmkioser.databinding.ItemNowPlayingBinding
import co.id.katalogfilmkioser.databinding.ItemUpcomingBinding

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */
class AdapterHomeUpcoming(
    private val movies: List<Result>
) : RecyclerView.Adapter<AdapterHomeUpcoming.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_upcoming,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemUpcomingBinding.movie = movies[position]
        holder.itemUpcomingBinding.root.setOnClickListener { v ->
            var data = bundleOf(
                "tes" to movies.get(position).title,
                "movie_id" to movies.get(position).id
            )
            v.findNavController().navigate(R.id.action_moviesFragment_to_detailMovieFragment, data)
        }
        holder.itemView.run {
            val marginStart: Int
            val marginEnd: Int
            val marginTop: Int
            val marginBottom: Int

            marginTop = resources.getDimensionPixelSize(R.dimen.marginSpace)
            marginBottom = resources.getDimensionPixelSize(R.dimen.marginSpace)

            if (position % 3 == 0) {
                marginStart = resources.getDimensionPixelSize(R.dimen.marginLayout)
            } else {
                marginStart = resources.getDimensionPixelSize(R.dimen.marginSpace)
            }

            if (position % 3 == 2) {
                marginEnd = resources.getDimensionPixelSize(R.dimen.marginLayout)
            } else {
                marginEnd = resources.getDimensionPixelSize(R.dimen.marginSpace)
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
        val itemUpcomingBinding: ItemUpcomingBinding
    ) : RecyclerView.ViewHolder(itemUpcomingBinding.root)
}