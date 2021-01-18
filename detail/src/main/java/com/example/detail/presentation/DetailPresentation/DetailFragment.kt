package com.example.detail.presentation.DetailPresentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.detail.R
import com.example.detail.adapters.DetailPagerAdapter
import com.example.detail.di.DetailComponent
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

class DetailFragment : Fragment(R.layout.fragment_detail) {


    var titleFilm: String? = null
    var backdropPoster: String? = null
    var posterPath: String? = null
    var releaseDate: String? = null
    var voteAverage: String? = null

    private lateinit var detailPagerAdapter: DetailPagerAdapter
    private lateinit var viewPager: ViewPager2

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        DetailComponent.injectFragment(this)
        super.onAttach(context)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleFilm = arguments?.getString("titleFilm")
        backdropPoster = arguments?.getString("backdropPoster")
        posterPath = arguments?.getString("posterPath")
        releaseDate = arguments?.getString("releaseDate")
        voteAverage = arguments?.getDouble("voteAverage").toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleName: TextView = view.findViewById(R.id.titleName)
        val backdropPosterImage: ImageView = view.findViewById(R.id.backdropPoster)
        val posterPathImage: ImageView = view.findViewById(R.id.posterPath)
        val releaseDateText: TextView = view.findViewById(R.id.releaseDate)
        val voteAverageText: TextView = view.findViewById(R.id.voteAverage)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)


        titleName.text = titleFilm
        releaseDateText.text = releaseDate
        voteAverageText.text = voteAverage
        Glide.with(view.context).load(BASE_IMAGE_URL + posterPath).into(posterPathImage)
        Glide.with(view.context).load(BASE_IMAGE_URL + backdropPoster).into(backdropPosterImage)

        detailPagerAdapter = DetailPagerAdapter(this)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = detailPagerAdapter

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            if(position == 1){
                tab.text = "Description"
            } else{
                tab.text = "Actors"
            }
        }.attach()
    }


    override fun onStart() {
        super.onStart()
        (activity as? AppCompatActivity)?.supportActionBar?.title = titleFilm
    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "LoftMovie"
    }
}