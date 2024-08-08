package com.example.moviedb.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.data.CreditsResponse
import com.example.moviedb.data.MovieDetails
import com.example.moviedb.databinding.MovieDetailsFragmentBinding
import com.example.moviedb.databinding.MovieDetailsHeaderBinding
import com.example.moviedb.network.MovieApiClient
import timber.log.Timber

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {
    private var _binding: MovieDetailsFragmentBinding? = null
    private var _posterBinding: MovieDetailsHeaderBinding? = null

    private val binding get() = _binding!!
    private val posterBinding get() = _posterBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        _posterBinding = MovieDetailsHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getInt(KEY_ID) ?: 0

        val getMovieDetails = MovieApiClient.apiClient.getMovieDetails(movieId, API_KEY, ENGLISH)
        val getMovieCredits = MovieApiClient.apiClient.getMovieCredits(movieId, API_KEY, ENGLISH)

        getMovieDetails.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                val movieDetails = response.body()
                binding.movieTitle.text = movieDetails?.title
                binding.rating.rating = movieDetails?.rating ?: 0.0f
                binding.movieOverview.text = movieDetails?.overview
                Picasso.get()
                    .load(movieDetails?.posterPath)
                    .into(posterBinding.posterImage)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Timber.e(t.toString())
            }
        })

        getMovieCredits.enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(
                call: Call<CreditsResponse>,
                response: Response<CreditsResponse>
            ) {
                val actors = response.body()?.cast
                binding.actor1Text.text = actors?.get(0)?.name ?: ""
                binding.actor2Text.text = actors?.get(1)?.name ?: ""
                binding.actor3Text.text = actors?.get(2)?.name ?: ""
                binding.actor4Text.text = actors?.get(3)?.name ?: ""
                Picasso.get()
                    .load(actors?.get(0)?.profilePath)
                    .into(binding.actor1Image)
                Picasso.get()
                    .load(actors?.get(1)?.profilePath)
                    .into(binding.actor2Image)
                Picasso.get()
                    .load(actors?.get(2)?.profilePath)
                    .into(binding.actor3Image)
                Picasso.get()
                    .load(actors?.get(3)?.profilePath)
                    .into(binding.actor4Image)
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                Timber.e(t.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _posterBinding = null
    }

    companion object {
        const val KEY_ID = "id"
        private val API_KEY = BuildConfig.THE_MOVIE_DATABASE_API
        const val ENGLISH = "en-US"
    }
}
