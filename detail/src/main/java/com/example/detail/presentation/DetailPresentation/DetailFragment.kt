package com.example.detail.presentation.DetailPresentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.detail.databinding.FragmentDetailBinding
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

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleName.text = titleFilm
        binding.releaseDate.text = releaseDate
        binding.voteAverage.text = voteAverage
        Glide.with(view.context).load(BASE_IMAGE_URL + posterPath).into(binding.posterPath)
        Glide.with(view.context).load(BASE_IMAGE_URL + backdropPoster).into(binding.backdropPoster)

        detailPagerAdapter = DetailPagerAdapter(this)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = detailPagerAdapter

        TabLayoutMediator(binding.tabLayout, viewPager){ tab, position ->
            when(position) {
                0 ->{
                    tab.text = "Description"
                }
                1->{
                    tab.text = "Actors"
                }
                else -> tab.text = "Undefined"
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}