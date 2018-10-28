package com.avinashsharma.codetoarttest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.avinashsharma.codetoarttest.R
import com.avinashsharma.codetoarttest.model.MovieList
import com.avinashsharma.codetoarttest.util.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions



class UpcomingMovieListAdapter(private val movieList: ArrayList<MovieList>,private val listener: (Int) -> Unit)
    :RecyclerView.Adapter<UpcomingMovieListAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(
            movieList: MovieList,
            position: Int,
            listener: (Int) -> Unit
        ) {

            var title: TextView = view.findViewById(R.id.title)
            var date: TextView = view.findViewById(R.id.date_text_view)
            var adult: TextView = view.findViewById(R.id.adult)
            var imageView: ImageView = view.findViewById(R.id.imageView)
//            var arrowImageView: ImageView = view.findViewById(R.id.arrowImageView)

            val  url : String = Constants.IMAGE_BASE_URL+movieList.getPosterPath()

            title.text = movieList.getTitle()
            date.text=movieList.getReleaseDate()
            adult.text = if(movieList.getAdult()!!) "(A)" else "(U/A)"

            Glide.with(view.context).asDrawable().load(url).apply(RequestOptions().placeholder(R.drawable.info).error(
                R.mipmap.ic_launcher
            ).fitCenter().centerCrop())
                .into(imageView)
            view.setOnClickListener {
                    listener(position)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent!!.context).inflate(
                R.layout.upcoming_movies_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(movieList[position],position,listener)

    }

    override fun getItemCount() = movieList.size
}