package com.example.moviedb.ui.feed

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.data.responses.MovieResponse
import com.example.moviedb.databinding.ItemWithTextBinding
import com.example.moviedb.ui.loadUrl

class MovieItem(
    private val content: MovieResponse,
    private val onClick: (movie: MovieResponse) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun getLayout(): Int = R.layout.item_with_text

    override fun bind(view: ItemWithTextBinding, position: Int) {
        view.description.text = content.title
        view.movieRating.rating = content.rating
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        view.imagePreview.loadUrl(content.posterPath)
    }

    override fun initializeViewBinding(v: View) = ItemWithTextBinding.bind(v)
}
