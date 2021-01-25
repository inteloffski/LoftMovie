package com.example.detail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.network.responses.ActorsDTO.Crew
import com.example.core.network.responses.FilmDTO.Film
import com.example.detail.R
import com.example.detail.databinding.ItemActorBinding
import com.example.detail.presentation.DetailPresentation.BASE_IMAGE_URL

class DetailActorAdapter : ListAdapter<Crew, DetailActorAdapter.ActorViewHolder>(actorDiffUtil){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val actorDiffUtil = object : DiffUtil.ItemCallback<Crew>() {
            override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemActorBinding.bind(itemView)

        fun bind(crew: Crew) {
            binding.nameActor.text = crew.name
            Glide.with(itemView.context).load(BASE_IMAGE_URL + crew.profilePath).into(binding.actorPoster)
        }


        companion object {
            fun create(parent: ViewGroup): ActorViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_actor, parent, false)
                return ActorViewHolder(view)
            }
        }
    }


}