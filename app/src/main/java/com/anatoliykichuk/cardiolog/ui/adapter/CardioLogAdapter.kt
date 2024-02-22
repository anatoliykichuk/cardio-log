package com.anatoliykichuk.cardiolog.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogRecordBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog

class CardioLogAdapter(
    private val records: MutableList<CardioLog>,
    private val listener: ICardioLogOnRecordDataChangeListener
) : RecyclerView.Adapter<CardioLogAdapter.CardioLogViewHolder>() {

    class CardioLogViewHolder(
        private val binding: FragmentCardioLogRecordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val diastolicPressureTextView = binding.diastolicPressureTextView
        val systolicPressureTextView = binding.systolicPressureTextView
        val pulseTextView = binding.pulseTextView

        fun bind(cardioLog: CardioLog) {
            binding.dateTextView.text = DateTimeFormatter.getDateFormatted(cardioLog.date)
            binding.timeTextView.text = DateTimeFormatter.getTimeFormatted(cardioLog.date)

            diastolicPressureTextView.setText(cardioLog.diastolicPressure.toString())
            systolicPressureTextView.setText(cardioLog.systolicPressure.toString())
            pulseTextView.setText(cardioLog.pulse.toString())

            binding.idTextView.text = cardioLog.id
            binding.idTextView.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardioLogViewHolder {
        val binding = FragmentCardioLogRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CardioLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardioLogViewHolder, position: Int) {
        val cardioLog = records[position]

        holder.bind(cardioLog)

        setOnDiastolicPressureTextChangedListener(holder, cardioLog)
        setOnSystolicPressureTextChangedListener(holder, cardioLog)
        setOnPulseTextChangedListener(holder, cardioLog)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    private fun setOnDiastolicPressureTextChangedListener(
        holder: CardioLogViewHolder, cardioLog: CardioLog
    ) {
        holder.diastolicPressureTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                cardioLog.diastolicPressure = p0.toString().toInt()
                listener.onChanged(cardioLog)
            }
        })
    }

    private fun setOnSystolicPressureTextChangedListener(
        holder: CardioLogViewHolder, cardioLog: CardioLog
    ) {
        holder.systolicPressureTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                cardioLog.systolicPressure = p0.toString().toInt()
                listener.onChanged(cardioLog)
            }
        })
    }

    private fun setOnPulseTextChangedListener(
        holder: CardioLogViewHolder, cardioLog: CardioLog
    ) {
        holder.pulseTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                cardioLog.pulse = p0.toString().toInt()
                listener.onChanged(cardioLog)
            }
        })
    }
}