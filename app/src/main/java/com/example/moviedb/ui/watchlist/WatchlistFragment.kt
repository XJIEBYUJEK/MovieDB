package com.example.moviedb.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.example.moviedb.MovieFinderApp.Companion.appContext
import com.example.moviedb.R
import com.example.moviedb.data.database.AppDatabase
import com.example.moviedb.data.responses.MovieResponse
import com.example.moviedb.data.vo.Movie
import com.example.moviedb.data.vo.MovieDetails
import com.example.moviedb.databinding.FragmentWatchlistBinding
import com.example.moviedb.ui.BaseFragment
import com.example.moviedb.ui.applySchedulers
import com.example.moviedb.ui.feed.FeedFragment.Companion.KEY_ID
import timber.log.Timber

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding>() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWatchlistBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase.getInstance(appContext).movieDao()

        val moviesList = db.getFavoriteMovies()
        binding.moviesRecyclerView.layoutManager = GridLayoutManager(context, 4)
        compositeDisposable.add(
            moviesList.applySchedulers()
                .showProgressBar()
                .subscribe({ dbObjects ->
                    binding.moviesRecyclerView.adapter = adapter.apply {
                        addAll( dbObjects.map {
                            MoviePreviewItem(it.toViewObject()) {movie ->
                                openMovieDetails(
                                    movie
                                )

                            }
                        }.toList())
                    }
                },{ error ->
                    Timber.e(error)
                })
        )
    }

    private fun openMovieDetails(movie: MovieDetails) {
        val bundle = Bundle()
        bundle.putInt(KEY_ID, movie.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}
