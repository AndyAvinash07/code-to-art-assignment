package com.avinashsharma.codetoarttest.model

import com.google.gson.annotations.SerializedName


class MoviesResponse {
    @SerializedName("page")
    private var page: Int = 0
    @SerializedName("results")
    private var results: List<MovieList>? = null
    @SerializedName("total_results")
    private var totalResults: Int = 0
    @SerializedName("total_pages")
    private var totalPages: Int = 0
    @SerializedName("backdrops")
    private var backdrops: List<MovieDetailsImages>? = null


    fun getPage(): Int {
        return page
    }

    fun setPage(page: Int) {
        this.page = page
    }

    fun getResults(): List<MovieList>? {
        return results
    }

    fun setResults(results: List<MovieList>) {
        this.results = results
    }

    fun getBackdrops(): List<MovieDetailsImages>? {
        return backdrops
    }

    fun setBackdrops(backdrops: List<MovieDetailsImages>) {
        this.backdrops = backdrops
    }


    fun getTotalResults(): Int {
        return totalResults
    }

    fun setTotalResults(totalResults: Int) {
        this.totalResults = totalResults
    }

    fun getTotalPages(): Int {
        return totalPages
    }

    fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }
}