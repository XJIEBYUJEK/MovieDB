package com.example.moviedb.ui.tvshows

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.data.TvShow
import com.example.moviedb.databinding.ItemTvShowBinding

class TvShowItem(
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout(): Int = R.layout.item_tv_show

    override fun bind(view: ItemTvShowBinding, position: Int) {
        view.description.text = content.name
        view.tvShowRating.rating = content.rating ?: 0.0f
        view.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.posterPath)
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(v: View) = ItemTvShowBinding.bind(v)
}
