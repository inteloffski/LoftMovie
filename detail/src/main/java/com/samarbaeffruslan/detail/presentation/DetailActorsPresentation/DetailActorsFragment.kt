package com.samarbaeffruslan.detail.presentation.DetailActorsPresentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samarbaeffruslan.core.utils.Resource
import com.samarbaeffruslan.detail.R
import com.samarbaeffruslan.detail.adapters.DetailActorAdapter
import com.samarbaeffruslan.detail.databinding.FragmentDetailActorsBinding
import com.samarbaeffruslan.detail.di.DetailComponent
import com.samarbaeffruslan.detail.presentation.DetailPresentation.DetailFragmentViewModel
import javax.inject.Inject
import com.samarbaeffruslan.asset.R as Asset


class DetailActorsFragment : Fragment(R.layout.fragment_detail_actors) {

    private val viewBinding: FragmentDetailActorsBinding by viewBinding()

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initState()
    }

    private fun initRecyclerView() {
        adapter = DetailActorAdapter()
        viewBinding.recycler.apply {
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
                    Toast.makeText(activity, getString(Asset.string.loading_error), Toast.LENGTH_SHORT).show()

                }
            }

        })
    }

    private fun hideProgressBar() {
        viewBinding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        viewBinding.progressBar.visibility = View.VISIBLE
    }


}