package com.anatoliykichuk.cardiolog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog

class CardioLogFragment : Fragment() {

    private var _binding: FragmentCardioLogBinding? = null

    private val binding
        get() = _binding!!

    private lateinit var cardioLogRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCardioLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        cardioLogRecyclerView = binding.cardioLogRecyclerView
    }

    private fun observeData() {

    }

    private fun initData() {
        //TODO("Инициализировать список")

        val records = listOf<CardioLog>()

        cardioLogRecyclerView.adapter = CardioLogAdapter(records)
        cardioLogRecyclerView.setHasFixedSize(true)
    }
}