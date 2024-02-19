package com.anatoliykichuk.cardiolog.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.anatoliykichuk.cardiolog.ui.AppState
import com.anatoliykichuk.cardiolog.ui.adapter.CardioLogAdapter
import com.anatoliykichuk.cardiolog.ui.adapter.CardioLogOnRecordDataChangeListener

class CardioLogFragment : Fragment(), CardioLogOnRecordDataChangeListener {

    private var _binding: FragmentCardioLogBinding? = null

    private val binding
        get() = _binding!!

    private lateinit var viewModel: CardioLogViewModel

    private var records: MutableList<CardioLog> = mutableListOf()
    private var adapter: CardioLogAdapter = CardioLogAdapter(records, this)

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
                    adapter = CardioLogAdapter(records, this)

                    cardioLogRecyclerView.setHasFixedSize(true)
                    cardioLogRecyclerView.adapter = adapter
                }

                is AppState.Error -> {
                    Toast.makeText(
                        activity, it.error.message.toString(), Toast.LENGTH_SHORT
                    ).show()
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
            viewModel.addRecord(record)

            adapter.notifyItemInserted(records.size)
            cardioLogRecyclerView.scrollToPosition(records.size)
        }

        binding.removeRecordFab.setOnClickListener { view ->
            val layoutManager = cardioLogRecyclerView.layoutManager as LinearLayoutManager
            val currentPosition = layoutManager?.findFirstVisibleItemPosition()

            if (currentPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }

            val record = records[currentPosition!!]

            records.remove(record)
            viewModel.removeRecord(record)

            adapter.notifyItemInserted(records.size)
            cardioLogRecyclerView.scrollToPosition(records.size)
        }
    }

    override fun onChanged(cardioLog: CardioLog) {
        viewModel.updateRecord(cardioLog)
    }

    companion object {
        fun newInstance() = CardioLogFragment()
    }
}