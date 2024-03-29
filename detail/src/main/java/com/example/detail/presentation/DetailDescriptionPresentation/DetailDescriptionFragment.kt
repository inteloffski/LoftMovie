package com.example.detail.presentation.DetailDescriptionPresentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.detail.R
import com.example.detail.databinding.FragmentDetailDescriptionBinding
import com.example.detail.di.DetailComponent
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import javax.inject.Inject


class DetailDescriptionFragment : Fragment(R.layout.fragment_detail_description) {

    private val viewBinding: FragmentDetailDescriptionBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }


    companion object{
        @JvmStatic
        fun newInstance() = DetailDescriptionFragment()

    }

    override fun onAttach(context: Context) {
        DetailComponent.injectFragmentDescription(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.selectedMovieLiveData.observe(viewLifecycleOwner, Observer { film ->
            viewBinding.description.text = film.overview
        })

    }










}