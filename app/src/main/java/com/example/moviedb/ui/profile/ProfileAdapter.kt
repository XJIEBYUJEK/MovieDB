package com.example.moviedb.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviedb.ui.watchlist.WatchlistFragment

class ProfileAdapter(
    fragment: Fragment,
    private val itemsCount: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return WatchlistFragment.newInstance()
    }
}
