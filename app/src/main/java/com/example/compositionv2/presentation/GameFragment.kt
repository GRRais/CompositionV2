package com.example.compositionv2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentGameBinding
import com.example.compositionv2.domain.entity.GameResult
import com.example.compositionv2.domain.entity.GameSettings
import com.example.compositionv2.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _b: FragmentGameBinding? = null
    private val b: FragmentGameBinding
        get() = _b ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentGameBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.tvOption1.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(
                    true,
                    0,
                    0,
                    GameSettings(0,0,0,0)
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}