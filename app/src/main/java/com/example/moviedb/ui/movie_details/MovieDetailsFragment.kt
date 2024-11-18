package com.example.moviedb.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import com.example.moviedb.BuildConfig
import com.example.moviedb.MovieFinderApp.Companion.appContext
import com.example.moviedb.data.database.AppDatabase
import com.example.moviedb.data.vo.MovieDetails
import com.example.moviedb.databinding.MovieDetailsFragmentBinding
import com.example.moviedb.databinding.MovieDetailsHeaderBinding
import com.example.moviedb.network.MovieApiClient
import com.example.moviedb.ui.BaseFragment
import com.example.moviedb.ui.applySchedulers
import com.example.moviedb.ui.loadUrl
import timber.log.Timber

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {
    private var _binding: MovieDetailsFragmentBinding? = null
    private var _posterBinding: MovieDetailsHeaderBinding? = null

    private val posterBinding get() = _posterBinding!!

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MovieDetailsFragmentBinding {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        _posterBinding = MovieDetailsHeaderBinding.bind(_binding!!.root)
        return _binding!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getInt(KEY_ID) ?: 0
        val db = AppDatabase.getInstance(appContext).movieDao()

        val getMovieDetails = MovieApiClient.apiClient.getMovieDetails(movieId, API_KEY, ENGLISH)
        val getMovieCredits = MovieApiClient.apiClient.getMovieCredits(movieId, API_KEY, ENGLISH)

        lifecycleScope.launch {
            compositeDisposable.add(
                if (db.isMovieSaved(movieId)){
                    db.getMovie(movieId)
                } else{
                    getMovieDetails
                }.applySchedulers()
                    .subscribe({ movieDetails ->
                        (movieDetails.toViewObject() as MovieDetails).apply {
                            binding.movieTitle.text = title
                            binding.rating.rating = rating
                            binding.movieOverview.text = overview
                            posterBinding.posterImage.loadUrl(posterPath)
                            binding.movieLike.isChecked = isFavorite
                            binding.movieLike.setOnCheckedChangeListener { _, isChecked ->
                                lifecycleScope.launch {
                                    if (isChecked) {
                                        isFavorite = true
                                        saveToDb()
                                    } else {
                                        isFavorite = false
                                        update()
                                    }
                                }
                            }
                        }
                    }, { error ->
                        Timber.e(error)
                    }))
        }



        compositeDisposable.add(getMovieCredits
            .applySchedulers()
            .subscribe({ response ->
                val actors = response.cast
                binding.itemsContainer.adapter = adapter.apply {
                    addAll(actors.map {
                        CastItem(it) {}
                    }.toList())
                }
            }, { error ->
                Timber.e(error)
            }))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _posterBinding = null
    }

    companion object {
        const val KEY_ID = "id"
        private const val API_KEY = BuildConfig.THE_MOVIE_DATABASE_API
        const val ENGLISH = "en-US"
    }
}
