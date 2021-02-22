package com.example.detail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.network.responses.ActorsDTO.Cast
import com.example.core.utils.BaseStrings.BASE_IMAGE_URL
import com.example.detail.R
import com.example.detail.databinding.ItemActorBinding

class DetailActorAdapter : ListAdapter<Cast, DetailActorAdapter.ActorViewHolder>(actorDiffUtil){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val actorDiffUtil = object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemActorBinding.bind(itemView)


        fun bind(cast: Cast) {
            binding.nameActor.text = cast.name
            binding.nameCharacter.text = cast.character
            Glide.with(itemView.context).load(BASE_IMAGE_URL + cast.profilePath).into(binding.actorPoster)
        }
    }


}