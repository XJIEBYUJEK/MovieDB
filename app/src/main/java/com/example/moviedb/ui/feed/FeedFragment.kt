package com.example.moviedb.ui.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.data.Movie
import com.example.moviedb.data.MoviesResponse
import com.example.moviedb.databinding.FeedFragmentBinding
import com.example.moviedb.databinding.FeedHeaderBinding
import com.example.moviedb.network.MovieApiClient
import com.example.moviedb.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private var _binding: FeedFragmentBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        val getNowPlaying = MovieApiClient.apiClient.getNowPlaying(API_KEY, ENGLISH)
        val getPopular = MovieApiClient.apiClient.getPopularMovies(API_KEY, ENGLISH)
        val getUpcoming = MovieApiClient.apiClient.getUpcomingMovies(API_KEY, ENGLISH)

        getNowPlaying.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val movies = response.body()?.results
                binding.moviesRecyclerView.adapter = adapter.apply {
                    addAll(movies?.map {
                        MovieItem(it) { movie ->
                            openMovieDetails(
                                movie
                            )
                        }
                    }?.toList() ?: listOf())
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.toString())
            }
        })

        getPopular.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                Timber.i("Success")
                // TODO
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.toString())
            }
        })

        getUpcoming.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                Timber.i("Success")
                // TODO
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.toString())
            }
        })
    }

    private fun openMovieDetails(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(KEY_ID, movie.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_ID = "id"
        const val KEY_SEARCH = "search"
        const val ENGLISH = "en-US"
        private val API_KEY = BuildConfig.THE_MOVIE_DATABASE_API
    }
}
