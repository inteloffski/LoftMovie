package com.example.popular.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.navigation.PopularNavigator
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.popular.R
import com.example.popular.databinding.ItemPopularBinding
import javax.inject.Inject

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"



class PopularFilmAdapter(private val listener: Listener) :
    PagedListAdapter<FilmDTO, PopularFilmAdapter.FilmViewHolder>(filmDiffUtil) {

    @Inject
    lateinit var navigator: PopularNavigator

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let {
                listener.onMovieClicked(it)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
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

        private val binding = ItemPopularBinding.bind(itemView)

        fun bind(filmDTO: FilmDTO) {
            binding.tvTitle.text = filmDTO.title
            Glide.with(itemView.context).load(BASE_IMAGE_URL + filmDTO.posterPath).into(binding.image)
        }


        companion object {
            fun create(parent: ViewGroup): FilmViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_popular, parent, false)
                return FilmViewHolder(view)
            }
        }

    }

    interface Listener {
        fun onMovieClicked(filmDTO: FilmDTO)

    }


}
