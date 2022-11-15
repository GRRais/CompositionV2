package com.example.compositionv2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentChooseLevelBinding


class ChooseLevelFragment : Fragment() {

    private var _b: FragmentChooseLevelBinding? = null
    private val b: FragmentChooseLevelBinding
    get() = _b ?: throw RuntimeException("FragmentChooseLevelBinding == 0")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}