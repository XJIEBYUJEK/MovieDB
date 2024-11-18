package com.example.moviedb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.databinding.FeedHeaderBinding
import com.example.moviedb.databinding.FragmentSearchBinding
import com.example.moviedb.ui.BaseFragment
import com.example.moviedb.ui.feed.FeedFragment.Companion.KEY_SEARCH

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private var _binding: FragmentSearchBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null


    private val searchBinding get() = _searchBinding!!

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(_binding!!.root)
        return _binding!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        searchBinding.searchToolbar.setText(searchTerm)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }
}
