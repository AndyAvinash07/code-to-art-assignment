package com.avinashsharma.codetoarttest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avinashsharma.codetoarttest.*
import com.avinashsharma.codetoarttest.adapter.UpcomingMovieListAdapter
import com.avinashsharma.codetoarttest.model.MovieDetails
import com.avinashsharma.codetoarttest.model.MovieList
import com.avinashsharma.codetoarttest.util.ApiCallInterface
import com.avinashsharma.codetoarttest.util.Constants
import com.avinashsharma.codetoarttest.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingMovieListFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mViewManager: RecyclerView.LayoutManager
    var mMovieDetails: MovieDetails? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.upcoming_movies, container, false)
        retainInstance = true
        (activity as MainActivity).supportActionBar?.title = getString(R.string.upcoming_movies)

        mViewManager = LinearLayoutManager(context)
        val args = arguments
        val movieList: ArrayList<MovieList> = args!!.getParcelableArrayList<MovieList>(Constants.MOVIES_LIST)

        mViewAdapter = UpcomingMovieListAdapter(movieList) {

            val call = RetrofitClient.getClient()!!.create(ApiCallInterface::class.java)
                .getMovieDetails(movieList.get(it).getId()!!, Constants.API_KEY)

            call.enqueue(object : Callback<MovieDetails> {
                override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    mMovieDetails = (response.body() as MovieDetails)
                    val bundle = Bundle()
                    bundle.putParcelable(Constants.MOVIE_DETALS, mMovieDetails)

                    val moviesDetailsFragment = MoviesDetailsFragment()
                    moviesDetailsFragment.arguments = bundle

                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, moviesDetailsFragment).addToBackStack(null).commit()
                }
            })
        }

        if (container != null) {
            mRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
                setHasFixedSize(true)
                layoutManager = mViewManager
                adapter = mViewAdapter
            }
        }
        return view
    }
}