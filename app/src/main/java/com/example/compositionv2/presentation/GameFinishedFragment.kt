package com.example.compositionv2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private var _b: FragmentGameFinishedBinding? = null
    private val b: FragmentGameFinishedBinding
    get() = _b ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}