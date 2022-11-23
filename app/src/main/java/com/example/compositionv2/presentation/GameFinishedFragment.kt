package com.example.compositionv2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentGameFinishedBinding
import com.example.compositionv2.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _b: FragmentGameFinishedBinding? = null
    private val b: FragmentGameFinishedBinding
        get() = _b ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentGameFinishedBinding.inflate(inflater , container , false)
        return b.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        setupClickListeners()
        b.gameResult = args.gameResult
    }

    private fun setupClickListeners() {
        b.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}