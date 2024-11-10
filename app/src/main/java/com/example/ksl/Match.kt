package com.example.ksl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Match(
    val teamA: String,
    val teamB: String,
    val time: String,
    val status: String
)

class MatchesAdapter(private val matches: List<Match>) : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int = matches.size

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamATextView: TextView = itemView.findViewById(R.id.teamATextView)
        private val teamBTextView: TextView = itemView.findViewById(R.id.teamBTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timetextView)

        fun bind(match: Match) {
            teamATextView.text = match.teamA
            teamBTextView.text = match.teamB
            timeTextView.text = match.time
       }

    }
}
