package com.example.detail.presentation.DetailActorsPresentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.detail.R
import com.example.detail.databinding.FragmentDetailActorsBinding


class DetailActorsFragment : Fragment(R.layout.fragment_detail_actors) {

    private var _binding: FragmentDetailActorsBinding? = null
    private val binding get() = _binding!!

    companion object{
        @JvmStatic
        fun newInstance() = DetailActorsFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailActorsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}