package com.example.detail.presentation.DetailDescriptionPresentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.detail.R
import com.example.detail.databinding.FragmentDetailBinding
import com.example.detail.databinding.FragmentDetailDescriptionBinding

class DetailDescriptionFragment : Fragment(R.layout.fragment_detail_description) {

    private var _binding: FragmentDetailDescriptionBinding? = null
    private val binding get() = _binding!!

    companion object{
        @JvmStatic
        fun newInstance() = DetailDescriptionFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailDescriptionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }










}