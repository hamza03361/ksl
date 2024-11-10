package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ksl.R

data class BatsmanStats(
    val batsmanName: String,
    val additionalInfo: String,
    val bowlerName: String,
    val runs: String,
    val balls: String,
    val fours: String,
    val sixes: String,
    val strikeRate: String
)


class BatsmanStatsAdapter(private val batsmanStatsList: List<BatsmanStats>) : RecyclerView.Adapter<BatsmanStatsAdapter.BatsmanStatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatsmanStatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.batsman_stats, parent, false)
        return BatsmanStatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BatsmanStatsViewHolder, position: Int) {
        val stats = batsmanStatsList[position]
        holder.bind(stats)
    }

    override fun getItemCount(): Int = batsmanStatsList.size

    class BatsmanStatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val batsmanNameTextView: TextView = itemView.findViewById(R.id.batsmanNameTextView)
        private val additionalInfoTextView: TextView = itemView.findViewById(R.id.additionalInfoTextView)
        private val bowlerTextView: TextView = itemView.findViewById(R.id.bowlerTextView)
        private val runsTextView: TextView = itemView.findViewById(R.id.runsTextView)
        private val ballsTextView: TextView = itemView.findViewById(R.id.ballsTextView)
        private val foursTextView: TextView = itemView.findViewById(R.id.foursTextView)
        private val sixesTextView: TextView = itemView.findViewById(R.id.sixesTextView)
        private val strikeRateTextView: TextView = itemView.findViewById(R.id.strikeRateTextView)

        fun bind(stats: BatsmanStats) {
            batsmanNameTextView.text = stats.batsmanName
            additionalInfoTextView.text = stats.additionalInfo
            bowlerTextView.text = stats.bowlerName
            runsTextView.text = stats.runs
            ballsTextView.text = stats.balls
            foursTextView.text = stats.fours
            sixesTextView.text = stats.sixes
            strikeRateTextView.text = stats.strikeRate
        }
    }
}
