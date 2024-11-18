package com.example.moviedb.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import io.reactivex.Observable
import com.example.moviedb.R
import com.example.moviedb.databinding.SearchToolbarBinding
import com.example.moviedb.ui.feed.FeedFragment.Companion.MIN_LENGTH
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    lateinit var binding: SearchToolbarBinding

    private var hint: String = ""
    private var isCancelVisible: Boolean = true

    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
                hint = getString(R.styleable.SearchBar_hint).orEmpty()
                isCancelVisible = getBoolean(R.styleable.SearchBar_cancel_visible, true)
                recycle()
            }
        }
    }

    fun setText(text: String?) {
        binding.searchEditText.setText(text)
    }

    fun clear() {
        binding.searchEditText.setText("")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = SearchToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        binding.searchEditText.hint = hint
        binding.deleteTextButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        binding.searchEditText.afterTextChanged { text ->
            if (!text.isNullOrEmpty() && !binding.deleteTextButton.isVisible) {
                binding.deleteTextButton.visibility = View.VISIBLE
            }
            if (text.isNullOrEmpty() && binding.deleteTextButton.isVisible) {
                binding.deleteTextButton.visibility = View.GONE
            }
        }
    }

    fun getSearchObservableWithFilter(): Observable<String> = Observable.create<String> { e ->
        binding.searchEditText.afterTextChanged {
            e.onNext(
                it.toString()
            )
        }
        e.setCancellable {
            binding.searchEditText.setOnTouchListener(null)
            e.onComplete()
        }
    }.debounce(500, TimeUnit.MILLISECONDS)
        .filter { it.length > MIN_LENGTH }
        .map { it.filter {filtered -> !filtered.isWhitespace() } }

}
