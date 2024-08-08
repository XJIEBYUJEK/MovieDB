package com.example.moviedb.ui.feed

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.data.Movie
import com.example.moviedb.databinding.ItemWithTextBinding

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun getLayout(): Int = R.layout.item_with_text

    override fun bind(view: ItemWithTextBinding, position: Int) {
        view.description.text = content.title
        view.movieRating.rating = content.rating ?: 0.0f
        view.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.posterPath)
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(v: View) = ItemWithTextBinding.bind(v)
}
