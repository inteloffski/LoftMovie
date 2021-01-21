package com.example.detail.presentation.DetailPresentation

import android.content.Context
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
import com.example.detail.R
import com.example.detail.adapters.DetailPagerAdapter
import com.example.detail.databinding.FragmentDetailBinding
import com.example.detail.di.DetailComponent
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

class DetailFragment : Fragment(R.layout.fragment_detail) {


    private var titleFilm: String? = null
    private var backdropPoster: String? = null
    private var posterPath: String? = null
    private var releaseDate: String? = null
    private var voteAverage: String? = null

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
                    tab.text = getString(R.string.tab1_description_fragment)
                }
                1->{
                    tab.text = getString(R.string.tab2_actors_fragment)
                }
                else -> tab.text = getString(R.string.tab3_undefined_fragment)
            }
        }.attach()

        detailViewModel.getSelectedMovie().observe(viewLifecycleOwner, Observer { film ->
            detailViewModel.selectedMovieLiveData(film)
        })

    }


    override fun onStart() {
        super.onStart()
        (activity as? AppCompatActivity)?.supportActionBar?.title = titleFilm
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