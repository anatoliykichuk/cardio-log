package com.anatoliykichuk.cardiolog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anatoliykichuk.cardiolog.R
import com.anatoliykichuk.cardiolog.databinding.FragmentCardioLogRecordBinding
import com.anatoliykichuk.cardiolog.domain.CardioLog

class CardioLogAdapter(
    private val records: List<CardioLog>
) : RecyclerView.Adapter<CardioLogAdapter.CardioLogViewHolder>() {

    class CardioLogViewHolder(
        private val binding: FragmentCardioLogRecordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cardioLog: CardioLog) {
            //TODO("Инициаилизировать данные элемента списка")

            binding.dateTextView.setText(R.string.date_text_sample.toString())
            binding.timeTextView.setText(R.string.time_text_sample.toString())
            binding.diastolicPressureTextView.setText(R.string.diastolic_pressure_text_sample.toString())
            binding.systolicPressureTextView.setText(R.string.systolic_pressure_text_sample.toString())
            binding.pulseTextView.setText(R.string.pulse_text_sample.toString())
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