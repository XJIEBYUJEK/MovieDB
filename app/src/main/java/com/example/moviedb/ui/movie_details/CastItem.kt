package com.example.moviedb.ui.movie_details

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.data.responses.CastResponse
import com.example.moviedb.databinding.ItemCastBinding
import com.example.moviedb.ui.loadUrl

class CastItem(
    private val content: CastResponse,
    private val onClick: (cast: CastResponse) -> Unit
) : BindableItem<ItemCastBinding>() {

    override fun getLayout(): Int = R.layout.item_cast

    override fun bind(view: ItemCastBinding, position: Int) {
        view.castName.text = content.name
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        view.castImage.loadUrl(content.profilePath)
    }

    override fun initializeViewBinding(view: View) = ItemCastBinding.bind(view)
}