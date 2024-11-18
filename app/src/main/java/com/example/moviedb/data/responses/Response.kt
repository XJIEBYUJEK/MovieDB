package com.example.moviedb.data.responses

import com.example.moviedb.data.DataObject

interface Response : DataObject {
    fun ratingCalculation(vote: Double?): Float{
        val coefficient = 2
        return vote?.div(coefficient)?.toFloat() ?: 0.0f
    }
}