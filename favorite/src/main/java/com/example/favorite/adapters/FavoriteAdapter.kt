package com.example.favorite.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.favorite.R
import com.example.favorite.databinding.CellFavoriteBinding

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

class FavoriteAdapter : ListAdapter<FilmDTO, FavoriteAdapter.FilmViewHolder>(filmDiffUtil){

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_favorite, parent, false)
        return FilmViewHolder(view)
    }


    companion object {
        val filmDiffUtil = object : DiffUtil.ItemCallback<FilmDTO>() {
            override fun areItemsTheSame(oldItem: FilmDTO, newItem: FilmDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FilmDTO, newItem: FilmDTO): Boolean {
                return oldItem == newItem
            }

        }
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CellFavoriteBinding.bind(itemView)

        fun bind(filmDTO: FilmDTO) {
            binding.tilteFilm.text = filmDTO.title
            Glide.with(itemView.context).load(BASE_IMAGE_URL + filmDTO.posterPath).into(binding.filmPoster)
        }


        companion object {
            fun create(parent: ViewGroup): FilmViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_favorite, parent, false)
                return FilmViewHolder(view)
            }
        }

    }
}