package com.example.compositionv2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentChooseLevelBinding
import com.example.compositionv2.domain.entity.Level


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(b) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}