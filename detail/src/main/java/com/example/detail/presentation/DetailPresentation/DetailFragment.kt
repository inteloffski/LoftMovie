package com.example.detail.presentation.DetailPresentation

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.core.utils.Resource
import com.example.detail.R
import com.example.detail.adapters.DetailPagerAdapter
import com.example.detail.databinding.FragmentDetailBinding
import com.example.detail.di.DetailComponent
import com.example.core.db.dao.mapper.FilmDTOFilmEntityMapper
import com.example.core.utils.BaseStrings.BASE_IMAGE_URL
import com.example.core.utils.BaseStrings.BASE_YOUTUBE_URL
import com.example.detail.databinding.FragmentDetailActorsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


class DetailFragment : Fragment(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }

    private lateinit var intent: Intent

    private val mapper = FilmDTOFilmEntityMapper()

    private val viewBinding: FragmentDetailBinding by viewBinding()


    private lateinit var detailPagerAdapter: DetailPagerAdapter
    private lateinit var viewPager: ViewPager2


    override fun onAttach(context: Context) {
        DetailComponent.injectFragment(this)
        super.onAttach(context)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playTrailerClickButton()
        observePlayTrailer(view)
        initViewPager(view)
        bindingData(view)
        savedFilmOnClickButton(view)


    }

    override fun onStart() {
        super.onStart()
        observeTitleActionBar()
    }


    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.title =
            getString(R.string.action_bar_name_fragment)
    }

    private fun savedFilmOnClickButton(view: View) {
        viewBinding.addFavoriteButton.setOnClickListener {
            detailViewModel.savedFilm()
            Snackbar.make(view, R.string.film_saved_successfully, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun playTrailerClickButton() {
        viewBinding.playTrailerFilmButton.setOnClickListener {
            detailViewModel.getTrailerVideo()
        }
    }

    private fun observeTitleActionBar() {
        detailViewModel.selectedMovieLiveData.observe(viewLifecycleOwner, Observer { film ->
            (activity as? AppCompatActivity)?.supportActionBar?.title = film.title
        })
    }

    private fun observePlayTrailer(view: View) {
        detailViewModel.videoLiveData?.observe(viewLifecycleOwner, Observer { video ->
            when (video) {
                is Resource.Loading -> {
                    showProgress()
                }
                is Resource.Success -> {
                    hideProgress()
                    video.data?.results?.let { videoResult ->
                        if (videoResult.isNullOrEmpty()) {
                            Toast.makeText(view.context,
                                R.string.trailer_not_found,
                                Toast.LENGTH_SHORT).show()
                        } else {
                            val trailerUrl = videoResult[0].key
                            intent =
                                Intent(ACTION_VIEW, Uri.parse(BASE_YOUTUBE_URL + trailerUrl))
                            intent.flags = FLAG_ACTIVITY_SINGLE_TOP
                            startActivity(intent)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgress()

                }
            }
        })
    }

    private fun initViewPager(view: View) {
        detailPagerAdapter = DetailPagerAdapter(this)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = detailPagerAdapter

        TabLayoutMediator(viewBinding.tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.tab2_actors_fragment)
                }
                1 -> {
                    tab.text = getString(R.string.tab1_description_fragment)

                }
                else -> tab.text = getString(R.string.tab3_undefined_fragment)
            }
        }.attach()
    }

    private fun bindingData(view: View) {
        detailViewModel.selectedMovieLiveData.observe(viewLifecycleOwner, Observer { film ->
            viewBinding.titleName.text = film.title
            viewBinding.releaseDate.text = film.releaseDate
            viewBinding.voteAverage.text = film.voteAverage.toString()
            Glide.with(view.context).load(BASE_IMAGE_URL + film.posterPath)
                .into(viewBinding.posterPath)
            Glide.with(view.context).load(BASE_IMAGE_URL + film.backdropPath)
                .into(viewBinding.backdropPoster)
            (activity as? AppCompatActivity)?.supportActionBar?.title = film.title

        })
    }

    private fun showProgress() {
        viewBinding.playTrailerProgress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        viewBinding.playTrailerProgress.visibility = View.INVISIBLE
    }
}
