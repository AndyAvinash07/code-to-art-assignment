package com.avinashsharma.codetoarttest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class   MovieList() : Parcelable{

    @SerializedName("adult")
    @Expose
    private var adult: Boolean? = null
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("original_language")
    @Expose
    private var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    private var originalTitle: String? = null
    @SerializedName("overview")
    @Expose
    private var overview: String? = null
    @SerializedName("popularity")
    @Expose
    private var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
    private var posterPath: String? = null
    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null

    constructor(parcel: Parcel) : this() {
        adult = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        originalLanguage = parcel.readString()
        originalTitle = parcel.readString()
        overview = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        posterPath = parcel.readString()
        releaseDate = parcel.readString()
        title = parcel.readString()
    }


    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun setReleaseDate(releaseDate: String?) {
        this.releaseDate = releaseDate
    }

    fun getAdult(): Boolean? {
        return adult
    }

    fun setAdult(adult: Boolean?) {
        this.adult = adult
    }


    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }


    fun getOriginalLanguage(): String? {
        return originalLanguage
    }

    fun setOriginalLanguage(originalLanguage: String) {
        this.originalLanguage = originalLanguage
    }

    fun getOriginalTitle(): String? {
        return originalTitle
    }

    fun setOriginalTitle(originalTitle: String) {
        this.originalTitle = originalTitle
    }

    fun getOverview(): String? {
        return overview
    }

    fun setOverview(overview: String) {
        this.overview = overview
    }

    fun getPopularity(): Double? {
        return popularity
    }

    fun setPopularity(popularity: Double?) {
        this.popularity = popularity
    }

    fun getPosterPath(): String? {
        return posterPath
    }

    fun setPosterPath(posterPath: String) {
        this.posterPath = posterPath
    }


    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(adult)
        parcel.writeValue(id)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieList> {
        override fun createFromParcel(parcel: Parcel): MovieList {
            return MovieList(parcel)
        }

        override fun newArray(size: Int): Array<MovieList?> {
            return arrayOfNulls(size)
        }
    }

}