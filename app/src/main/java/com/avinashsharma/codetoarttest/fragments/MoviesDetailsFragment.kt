package com.avinashsharma.codetoarttest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.avinashsharma.codetoarttest.*
import com.avinashsharma.codetoarttest.model.MovieDetails
import com.avinashsharma.codetoarttest.model.MovieDetailsImages
import com.avinashsharma.codetoarttest.model.MoviesResponse
import com.avinashsharma.codetoarttest.util.ApiCallInterface
import com.avinashsharma.codetoarttest.util.Constants
import com.avinashsharma.codetoarttest.util.RetrofitClient
import com.smarteist.autoimageslider.SliderLayout
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesDetailsFragment : Fragment() {
    var mMoviesDetailsImages: ArrayList<MovieDetailsImages>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.movies_details_fragment, container, false)
        retainInstance = true

        (activity as MainActivity).supportActionBar?.title = getString(R.string.movie_details)

        val args = arguments
        var movieDetails: MovieDetails? = args!!.getParcelable(Constants.MOVIE_DETALS)

        var sliderLayout: SliderLayout = view.findViewById(R.id.imageSlider)
        var titleTV: TextView = view.findViewById(R.id.titleTV)
        var overViewTV: TextView = view.findViewById(R.id.overvireTV)
//        var ratingBar: RatingBar = view.findViewById(R.id.ratingBar)

        titleTV.text = movieDetails!!.getTitle()
        overViewTV.text = movieDetails!!.getOverview()
//        ratingBar.numStars = myList.getPopularity()

        val call = RetrofitClient.getClient()!!.create(ApiCallInterface::class.java)
            .getMovieDetailsImages(movieDetails.getId()!!, Constants.API_KEY)

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                mMoviesDetailsImages = (response.body()!!.getBackdrops() as ArrayList<MovieDetailsImages>?)!!
                setSliderViews(sliderLayout, mMoviesDetailsImages)
            }
        })
        return view
    }

    private fun setSliderViews(sliderLayout: SliderLayout, myItem: ArrayList<MovieDetailsImages>?) {
        for (i in 0..4) {

            val sliderView = SliderView(context)
            if (myItem!!.size <= i)
                break
            else
                sliderView.imageUrl = Constants.IMAGE_BASE_URL + myItem!!.get(i).getFilePath()

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            sliderLayout.addSliderView(sliderView)
        }
    }
}

