package com.samarbaeffruslan.popular.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samarbaeffruslan.core.navigation.PopularNavigator
import com.samarbaeffruslan.core.network.responses.FilmDTO.FilmDTO
import com.samarbaeffruslan.core.utils.BaseStrings.BASE_IMAGE_URL
import com.samarbaeffruslan.popular.R
import com.samarbaeffruslan.popular.databinding.ItemPopularBinding
import javax.inject.Inject





class PopularFilmAdapter(private val listener: Listener) :
    PagingDataAdapter<FilmDTO, PopularFilmAdapter.FilmViewHolder>(filmDiffUtil) {

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
