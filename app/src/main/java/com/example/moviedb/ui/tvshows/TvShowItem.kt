package com.example.moviedb.ui.tvshows

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.data.responses.TvShowResponse
import com.example.moviedb.databinding.ItemTvShowBinding
import com.example.moviedb.ui.loadUrl

class TvShowItem(
    private val content: TvShowResponse,
    private val onClick: (tvShow: TvShowResponse) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout(): Int = R.layout.item_tv_show

    override fun bind(view: ItemTvShowBinding, position: Int) {
        view.description.text = content.name
        view.tvShowRating.rating = content.rating
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        view.imagePreview.loadUrl(content.posterPath)
    }

    override fun initializeViewBinding(v: View) = ItemTvShowBinding.bind(v)
}
