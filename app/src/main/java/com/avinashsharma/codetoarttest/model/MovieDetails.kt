package com.avinashsharma.codetoarttest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetails() : Parcelable{

    @SerializedName("overview")
    @Expose
    private var overview: String? = null
    @SerializedName("popularity")
    @Expose
    private  var popularity: Double? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    constructor(parcel: Parcel) : this() {
        overview = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        title = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(overview)
        parcel.writeValue(popularity)
        parcel.writeString(title)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetails> {
        override fun createFromParcel(parcel: Parcel): MovieDetails {
            return MovieDetails(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetails?> {
            return arrayOfNulls(size)
        }
    }

}