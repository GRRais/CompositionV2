package com.example.compositionv2.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.compositionv2.R
import com.example.compositionv2.databinding.FragmentGameBinding
import com.example.compositionv2.domain.entity.GameResult
import com.example.compositionv2.domain.entity.Level

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level , requireActivity().application)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this , viewModelFactory)[GameViewModel::class.java]
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
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
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
            b.progressBar.setProgress(it , true)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            b.tvAnswersProgress.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            b.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            b.tvTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            b.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            b.tvAnswersProgress.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext() , colorResId)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult)
        )
    }
}