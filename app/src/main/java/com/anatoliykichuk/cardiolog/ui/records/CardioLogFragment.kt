package com.anatoliykichuk.cardiolog.ui.records

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog
import com.anatoliykichuk.cardiolog.ui.adapter.CardioLogAdapter
import com.anatoliykichuk.cardiolog.ui.adapter.ICardioLogOnRecordDataChangeListener
import com.anatoliykichuk.cardiolog.ui.state.AppState
import com.anatoliykichuk.cardiolog.ui.state.CardioLogEventType
import com.anatoliykichuk.cardiolog.ui.state.ResponseData

class CardioLogFragment : Fragment(), ICardioLogOnRecordDataChangeListener {

    private var _binding: FragmentCardioLogBinding? = null

    private val binding
        get() = _binding!!

    private lateinit var viewModel: CardioLogViewModel

    private var records: MutableList<CardioLog> = mutableListOf()
    private var adapter: CardioLogAdapter = CardioLogAdapter(records, this)

    private lateinit var cardioLogRecyclerView: RecyclerView
    private var currentPosition = RecyclerView.NO_POSITION

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
        setOnItemTouchListener()
        setFabOnClickListeners()
    }

    private fun observeData() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    processResponseData(it.responseData)
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

    private fun setOnItemTouchListener() {
        val gestureDetector = GestureDetector(
            activity,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    val view = cardioLogRecyclerView.findChildViewUnder(e.x, e.y)!!
                    currentPosition = cardioLogRecyclerView.getChildAdapterPosition(view)

                    return super.onSingleTapConfirmed(e)
                }
            }
        )

        cardioLogRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return gestureDetector.onTouchEvent(e)
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }

    private fun setFabOnClickListeners() {
        binding.addRecordFab.setOnClickListener { view ->
            val record = CardioLog()
            viewModel.addRecord(record)
        }

        binding.removeRecordFab.setOnClickListener { view ->
            if (currentPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }

            val record = records[currentPosition]
            viewModel.removeRecord(record)
        }
    }

    override fun onChanged(cardioLog: CardioLog) {
        viewModel.updateRecord(cardioLog)
    }

    private fun processResponseData(responseData: ResponseData) {
        if (responseData.records != null) {
            records = responseData.records
        } else if (!recordsUpdatedByEventType(responseData)) {
            return
        }

        records.sortBy { it.date }
        adapter = CardioLogAdapter(records, this)

        cardioLogRecyclerView.setHasFixedSize(true)
        cardioLogRecyclerView.adapter = adapter
    }

    private fun recordsUpdatedByEventType(responseData: ResponseData): Boolean {
        if (responseData.record == null) {
            return false
        }

        if (responseData.eventType == CardioLogEventType.ADDING) {
            records.add(responseData.record)
            return true
        }

        val indexOfRecord = records.indexOf(responseData.record)

        if (indexOfRecord < 0) {
            return false
        }

        if (responseData.eventType == CardioLogEventType.UPDATING) {
            records[indexOfRecord] = responseData.record
        } else if (responseData.eventType == CardioLogEventType.REMOVING) {
            records.remove(responseData.record)
        }
        return true
    }

    companion object {
        fun newInstance() = CardioLogFragment()
    }
}