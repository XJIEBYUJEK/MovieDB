package com.example.moviedb.ui.feed

import android.view.View
import androidx.annotation.StringRes
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import com.example.moviedb.R
import com.example.moviedb.databinding.ItemCardBinding

class MainCardContainer(
    @StringRes
    private val title: Int,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemCardBinding>() {

    override fun getLayout() = R.layout.item_card

    override fun bind(view: ItemCardBinding, position: Int) {
        view.titleTextView.text = view.titleTextView.context.getString(title)
        view.itemsContainer.adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }

    override fun initializeViewBinding(p0: View): ItemCardBinding = ItemCardBinding.bind(p0)
}
