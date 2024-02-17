package com.anatoliykichuk.cardiolog.ui.pages.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.anatoliykichuk.cardiolog.ui.AppState
import com.anatoliykichuk.cardiolog.ui.adapter.CardioLogAdapter
import com.google.android.material.snackbar.Snackbar

class CardioLogFragment : Fragment() {

    private var _binding: FragmentCardioLogBinding? = null

    private val binding
        get() = _binding!!

    private lateinit var viewModel: CardioLogViewModel

    private var records: MutableList<CardioLog> = mutableListOf()
    private var adapter: CardioLogAdapter = CardioLogAdapter(records)

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

        viewModel = ViewModelProvider(this).get(CardioLogViewModel::class.java)

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
        setFabOnClickListeners()
    }

    private fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    records = it.records
                    adapter = CardioLogAdapter(records)

                    cardioLogRecyclerView.setHasFixedSize(true)
                    cardioLogRecyclerView.adapter = adapter
                }

                is AppState.Error -> {

                }

                is AppState.Loading -> {

                }
            }
        }
    }

    private fun initData() {
        viewModel.getRecords()
    }

    private fun setFabOnClickListeners() {
        binding.addRecordFab.setOnClickListener { view ->
            val record = CardioLog()

            records.add(record)
            adapter.notifyItemInserted(records.size)
            cardioLogRecyclerView.scrollToPosition(records.size)

            viewModel.addRecord(record)
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