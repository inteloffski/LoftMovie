package com.example.popular.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.network.responses.Film
import com.example.popular.R
import kotlinx.android.synthetic.main.item_popular.view.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

class PopularFilmAdapter : PagedListAdapter<Film, PopularFilmAdapter.FilmViewHolder>(filmDiffUtil) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    //var listFilm: MutableList<Film> = emptyList<Film>() as MutableList<Film>


    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return FilmViewHolder(view)
    }


    companion object {
        val filmDiffUtil = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }

        }
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.tvTitle)

        fun bind(film: Film) {
            title.text = film.title
        }


        companion object {
            fun create(parent: ViewGroup): FilmViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_popular, parent, false)
                return FilmViewHolder(view)
            }
        }

    }


}
