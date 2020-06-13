package com.sa.demo.exoxycarouseldemo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sa.demo.exoxycarouseldemo.data.Movie

data class MovieData (
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null
)
