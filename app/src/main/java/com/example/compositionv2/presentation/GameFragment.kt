package com.example.compositionv2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentGameBinding
import com.example.compositionv2.domain.entity.GameResult
import com.example.compositionv2.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this ,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(b.tvOption1)
            add(b.tvOption2)
            add(b.tvOption3)
            add(b.tvOption4)
            add(b.tvOption5)
            add(b.tvOption6)
        }
    }

    private var _b: FragmentGameBinding? = null
    private val b: FragmentGameBinding
        get() = _b ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentGameBinding.inflate(inflater , container , false)
        return b.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            b.tvSum.text = it.sum.toString()
            b.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            b.progressBar.setProgress(it, true)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            val colorResId = if (it) {
            }
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
            .replace(R.id.main_container , GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL , level)
                }
            }
        }
    }
}
}