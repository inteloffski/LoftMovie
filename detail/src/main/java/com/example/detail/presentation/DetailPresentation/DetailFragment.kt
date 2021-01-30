package com.example.detail.presentation.DetailPresentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.core.network.responses.videoDTO.Video
import com.example.core.utils.Resource
import com.example.detail.R
import com.example.detail.adapters.DetailPagerAdapter
import com.example.detail.databinding.FragmentDetailBinding
import com.example.detail.di.DetailComponent
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="

class DetailFragment : Fragment(R.layout.fragment_detail) {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }


    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    private lateinit var detailPagerAdapter: DetailPagerAdapter
    private lateinit var viewPager: ViewPager2


    override fun onAttach(context: Context) {
        DetailComponent.injectFragment(this)
        super.onAttach(context)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playTrailerFilmButton.setOnClickListener {
            detailViewModel.getTrailerVideo()
            detailViewModel.videoLiveData.observe(viewLifecycleOwner, Observer {
                when(it){
                    is Resource.Loading ->{

                    }
                    is Resource.Success ->{
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BASE_YOUTUBE_URL + it.data?.key))
                        startActivity(intent)
                    }
                    is Resource.Error ->{

                    }
                }

            })


        }


        detailPagerAdapter = DetailPagerAdapter(this)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = detailPagerAdapter

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
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

        bindingData(view)


    }

    private fun bindingData(view: View) {
        detailViewModel.selectedMovieLiveData.observe(viewLifecycleOwner, Observer { film ->
            binding.titleName.text = film.title
            binding.releaseDate.text = film.releaseDate
            binding.voteAverage.text = film.voteAverage.toString()
            Glide.with(view.context).load(BASE_IMAGE_URL + film.posterPath).into(binding.posterPath)
            Glide.with(view.context).load(BASE_IMAGE_URL + film.backdropPath)
                .into(binding.backdropPoster)
            (activity as? AppCompatActivity)?.supportActionBar?.title = film.title

        })
    }


    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.action_bar_name_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}