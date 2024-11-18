package com.example.moviedb.ui.watchlist

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.data.vo.MovieDetails
import com.example.moviedb.databinding.ItemSmallBinding
import com.example.moviedb.ui.loadUrl

class MoviePreviewItem(
    private val content: MovieDetails,
    private val onClick: (movie: MovieDetails) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout() = R.layout.item_small

    override fun bind(view: ItemSmallBinding, position: Int) {
        view.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }
        view.imagePreview.loadUrl(content.posterPath)
    }

    override fun initializeViewBinding(v: View): ItemSmallBinding = ItemSmallBinding.bind(v)
}
