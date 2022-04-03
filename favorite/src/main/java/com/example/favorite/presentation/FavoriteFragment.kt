package com.example.favorite.presentation

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.db.dao.mapper.FilmDTOFilmEntityMapper
import com.example.core.navigation.FavoriteNavigator
import com.example.core.navigation.PopularNavigator
import com.example.core.network.responses.FilmDTO.FilmDTO
import com.example.detail.presentation.DetailPresentation.DetailFragmentViewModel
import com.example.favorite.R
import com.example.favorite.adapters.FavoriteAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteComponent
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite), FavoriteAdapter.Listener {

    private val viewBinding: FragmentFavoriteBinding by viewBinding()

    @Inject
    lateinit var navigatorToDetail: FavoriteNavigator

    val clearPaint =
        Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }


    private lateinit var adapter: FavoriteAdapter

    @Inject
    lateinit var navigator: FavoriteNavigator

    @Inject
    lateinit var mapper: FilmDTOFilmEntityMapper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavoriteFragmentViewModel> { viewModelFactory }
    private val detailViewModel by activityViewModels<DetailFragmentViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        FavoriteComponent.injectFragment(this)
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.getSavedFilm().observe(viewLifecycleOwner, Observer {
            val list = mapper.reverseMap(it)
            adapter.submitList(list)
        })

        swipeToDelete(view)
    }

    private fun setupRecyclerView() {
        adapter = FavoriteAdapter(this)
        viewBinding.recyclerFavorite.apply {
            this.adapter = this@FavoriteFragment.adapter
            layoutManager = LinearLayoutManager(activity)
        }


    }

    private fun swipeToDelete(view: View) {
        val helper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {

                val deleteIcon =
                    ContextCompat.getDrawable(recyclerView.context, R.drawable.ic__delete_white_24)
                val intrinsicWidth = deleteIcon?.intrinsicWidth
                val intrinsicHeight = deleteIcon?.intrinsicHeight
                val background = ColorDrawable()
                val backgroundColor = Color.parseColor("#FADADD")


                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val isCanceled = dX == 0f && !isCurrentlyActive

                if (isCanceled) {
                    clearCanvas(c,
                        itemView.right + dX,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat())
                    super.onChildDraw(c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive)
                    return
                }

                // Draw the red delete background
                background.color = backgroundColor
                background.setBounds(itemView.right + dX.toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom)
                background.draw(c)

                // Calculate position of delete icon
                val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight!!) / 2
                val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
                val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth!!
                val deleteIconRight = itemView.right - deleteIconMargin
                val deleteIconBottom = deleteIconTop + intrinsicHeight

                // Draw the delete icon
                deleteIcon.setBounds(deleteIconLeft,
                    deleteIconTop,
                    deleteIconRight,
                    deleteIconBottom)
                deleteIcon.draw(c)



                super.onChildDraw(c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val entity = adapter.currentList[position]
                viewModel.deleteFilm(mapper.map(entity))
                Snackbar.make(view, R.string.delete_film, Snackbar.LENGTH_SHORT).apply {
                    setAction(R.string.undo_button) {
                        viewModel.saveFilm(mapper.map(entity).also {
                            it.isFavorite = true
                        })
                    }
                }.show()
            }

        }

        ItemTouchHelper(helper).apply {
            attachToRecyclerView(viewBinding.recyclerFavorite)
        }
    }
    private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
        c?.drawRect(left, top, right, bottom, clearPaint)
    }

    override fun onMovieClicked(filmDTO: FilmDTO) {
        val navController = findNavController()
        detailViewModel.selectedMovie(filmDTO)
        navigatorToDetail.navigateToDetail(navController)
    }

}