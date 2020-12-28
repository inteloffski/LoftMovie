package com.example.popular.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.network.responses.Film
import com.example.popular.R
import kotlinx.android.synthetic.main.item_popular.view.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

class PopularFilmAdapter : RecyclerView.Adapter<PopularFilmAdapter.ArticleViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_popular,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val film = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(BASE_IMAGE_URL +film.posterPath).into(ivArticleImage)
        }
    }
}
