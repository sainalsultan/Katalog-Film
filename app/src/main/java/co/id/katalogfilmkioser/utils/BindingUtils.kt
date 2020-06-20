package co.id.katalogfilmkioser.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import co.id.katalogfilmkioser.R
import com.bumptech.glide.Glide

/**
 * Created by Sainal Sultan on 6/19/2020.
 * sainalsultan@gmail.com | Hasanah Dev.
 */

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load("https://image.tmdb.org/t/p/w500$url")
        .into(view)
}