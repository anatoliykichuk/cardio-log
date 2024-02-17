package com.anatoliykichuk.cardiolog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogRecordBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog

class CardioLogAdapter(
    private val records: List<CardioLog>
) : RecyclerView.Adapter<CardioLogAdapter.CardioLogViewHolder>() {

    class CardioLogViewHolder(
        private val binding: FragmentCardioLogRecordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cardioLog: CardioLog) {
            binding.dateTextView.setText(DateTimeFormatter.getDateFormatted(cardioLog.date))
            binding.timeTextView.setText(DateTimeFormatter.getTimeFormatted(cardioLog.date))
            binding.diastolicPressureTextView.setText(cardioLog.diastolicPressure.toString())
            binding.systolicPressureTextView.setText(cardioLog.systolicPressure.toString())
            binding.pulseTextView.setText(cardioLog.pulse.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardioLogViewHolder {
        val binding = FragmentCardioLogRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CardioLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardioLogViewHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount(): Int {
        return records.size
    }
}