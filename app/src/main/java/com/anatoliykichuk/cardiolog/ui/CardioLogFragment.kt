package com.anatoliykichuk.cardiolog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.google.android.material.snackbar.Snackbar

class CardioLogFragment : Fragment() {

    private var _binding: FragmentCardioLogBinding? = null

    private val binding
        get() = _binding!!

    private lateinit var records: MutableList<CardioLog>
    private lateinit var adapter: CardioLogAdapter
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
        records = mutableListOf()
        adapter = CardioLogAdapter(records)
        cardioLogRecyclerView = binding.cardioLogRecyclerView

        setFabOnClickListeners()
    }

    private fun observeData() {

    }

    private fun initData() {
        cardioLogRecyclerView.adapter = adapter
        cardioLogRecyclerView.setHasFixedSize(true)
    }

    private fun setFabOnClickListeners() {
        binding.addRecordFab.setOnClickListener { view ->
            records.add(CardioLog(120, 80, 65))
            adapter.notifyItemInserted(records.size)
            cardioLogRecyclerView.scrollToPosition(records.size)
        }

        binding.removeRecordFab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    companion object {
        fun newInstance() = CardioLogFragment()
    }
}