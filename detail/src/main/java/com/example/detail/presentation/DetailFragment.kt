package com.example.detail.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.detail.R
import com.example.detail.di.DaggerDetailComponent
import com.example.detail.di.DetailComponent
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        DetailComponent.injectFragment(this)
        super.onAttach(context)
    }
}