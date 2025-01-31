package com.example.popular.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.db.dao.mapper.FilmDTOFilmEntityMapper
import com.example.core.navigation.PopularNavigator
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.popular.R
import com.example.popular.adapters.PopularFilmAdapter
import com.example.popular.databinding.FragmentPopularBinding
import com.example.popular.di.PopularComponent
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import com.example.asset.R as Asset


class PopularFragment : Fragment(R.layout.fragment_popular), PopularFilmAdapter.Listener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mapper: FilmDTOFilmEntityMapper
    @Inject
    lateinit var navigator: PopularNavigator

    private val mDisposable = CompositeDisposable()

    private val viewModel by activityViewModels<PopularFragmentViewModel> { viewModelFactory }
    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }

    private lateinit var adapter: PopularFilmAdapter

    private val viewBinding: FragmentPopularBinding by viewBinding()

    override fun onAttach(context: Context) {
        PopularComponent.injectFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        mDisposable.addAll(
            viewModel.getMovie().subscribe(::success)
        )
    }

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        adapter = PopularFilmAdapter(this)
        viewBinding.recycler.apply {
            this.adapter = this@PopularFragment.adapter
            layoutManager = GridLayoutManager(activity, 3)
        }

    }

    private fun success(pagingData: PagingData<FilmDTO>){
        adapter.submitData(lifecycle, pagingData)
    }
    private fun hideProgressBar() {
        viewBinding.progressBar.visibility = View.INVISIBLE
    }


    private fun showSnackbarCheckInternet(view: View) {
        Snackbar.make(view, Asset.string.no_internet_connection, Snackbar.LENGTH_SHORT).show()
    }


    override fun onMovieClicked(filmDTO: FilmDTO) {
        val navController = findNavController()
        detailViewModel.selectedMovie(filmDTO)
        navigator.navigateToDetail(navController)

    }
}






