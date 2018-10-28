package com.avinashsharma.codetoarttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.avinashsharma.codetoarttest.fragments.InfoFragment
import com.avinashsharma.codetoarttest.fragments.UpcomingMovieListFragment
import com.avinashsharma.codetoarttest.model.MovieList
import com.avinashsharma.codetoarttest.model.MoviesResponse
import com.avinashsharma.codetoarttest.util.ApiCallInterface
import com.avinashsharma.codetoarttest.util.Constants
import com.avinashsharma.codetoarttest.util.InternetCheck
import com.avinashsharma.codetoarttest.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var mMoviesList: ArrayList<MovieList>? = null
    val mUpcomingMovieListFragment = UpcomingMovieListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        if (savedInstanceState == null) {
            val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)

            if (!InternetCheck.isInternetAvailable(this)) {
                showMessage(constraintLayout, getString(R.string.ineternet_connection_message))
            } else {
                val call =
                    RetrofitClient.getClient()!!.create(ApiCallInterface::class.java)
                        .getUpcomingMovies(Constants.API_KEY)

                call.enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                        mMoviesList = (response.body()!!.getResults() as ArrayList<MovieList>?)!!
                        val bundle = Bundle()
                        bundle.putParcelableArrayList(Constants.MOVIES_LIST, mMoviesList)

                        mUpcomingMovieListFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .add(R.id.frame_layout, mUpcomingMovieListFragment).commit()
                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        showMessage(constraintLayout, getString(R.string.something_went_wrong))
                    }
                })
            }
        }else
            supportFragmentManager.findFragmentByTag("mUpcomingMovieListFragment") as UpcomingMovieListFragment?
    }

    private fun showMessage(constraintLayout: ConstraintLayout, errorMessage: String) {
        val snackbar = Snackbar
            .make(constraintLayout, errorMessage, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.info -> {
            supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frame_layout, InfoFragment()).commit()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
