package com.example.detail.presentation.DetailActorsPresentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.utils.Resource
import com.example.detail.R
import com.example.detail.adapters.DetailActorAdapter
import com.example.detail.databinding.FragmentDetailActorsBinding
import com.example.detail.di.DetailComponent
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import javax.inject.Inject


class DetailActorsFragment : Fragment(R.layout.fragment_detail_actors) {

    private var _binding: FragmentDetailActorsBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: DetailActorAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }

    companion object {
        @JvmStatic
        fun newInstance() = DetailActorsFragment()

    }

    override fun onAttach(context: Context) {
        DetailComponent.injectFragmentActors(this)
        super.onAttach(context)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailActorsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initState()






    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        adapter = DetailActorAdapter()
        binding.recycler.apply {
            this.adapter = this@DetailActorsFragment.adapter
            layoutManager = LinearLayoutManager(activity)

        }


    }



    private fun initState() {
        detailViewModel.stateListActors.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    adapter.submitList(it.data?.cast)

                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, R.string.loading_error, Toast.LENGTH_SHORT).show()

                }
            }

        })
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }


}