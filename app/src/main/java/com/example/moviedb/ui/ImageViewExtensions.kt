package com.example.moviedb.ui

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?) =
    Picasso.get()
        .load(url)
        .into(this)