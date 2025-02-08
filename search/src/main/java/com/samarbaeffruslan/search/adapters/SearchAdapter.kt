package com.samarbaeffruslan.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.core.utils.BaseStrings.BASE_IMAGE_URL
import com.samarbaeffruslan.search.R
import com.samarbaeffruslan.search.databinding.CellSearchBinding

class SearchAdapter(private val listener: Listener) : ListAdapter<FilmDTO, SearchAdapter.FilmSearchViewHolder>(searchDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmSearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_search, parent, false)
        return FilmSearchViewHolder(view)
    }

    override fun onBindViewHolder(holderSearch: FilmSearchViewHolder, position: Int) {
        getItem(position).let { holderSearch.bind(it) }
        holderSearch.itemView.setOnClickListener {
            getItem(position)?.let {
                listener.onMovieClicked(it)
            }
        }
    }

    companion object {
        val searchDiffUtil = object : DiffUtil.ItemCallback<FilmDTO>() {
            override fun areItemsTheSame(oldItem: FilmDTO, newItem: FilmDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FilmDTO, newItem: FilmDTO): Boolean {
                return oldItem == newItem
            }

        }
    }


    class FilmSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CellSearchBinding.bind(itemView)

        fun bind(filmDTO: FilmDTO) {
            binding.tilteFilm.text = filmDTO.title
            binding.voteAverage.text = filmDTO.voteAverage.toString()
            binding.releaseDate.text = filmDTO.releaseDate.toString()
            Glide.with(itemView.context).load(BASE_IMAGE_URL + filmDTO.posterPath).into(binding.filmPoster)
        }

        companion object {
            fun create(parent: ViewGroup): FilmSearchViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_search, parent, false)
                return FilmSearchViewHolder(view)
            }
        }

    }

    interface Listener{
        fun onMovieClicked(filmDTO: FilmDTO)
    }
}

